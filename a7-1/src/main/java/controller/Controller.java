package controller;

import model.*;
import repository.RepoInterface;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Controller {
    RepoInterface repository;
    ExecutorService executor;
    public Controller(){}
    public Controller(RepoInterface repo){
        repository = repo;
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p)-> (Callable<ProgramState>)(()->{return p.oneStep();}))
                .collect(Collectors.toList());
        List<ProgramState> newPrgList = executor.invokeAll(callList).stream().map(future -> {try{return future.get();}catch(
                InterruptedException | ExecutionException e){
            System.out.println(e.getMessage());
        }return null;}).filter(p -> p!= null).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        repository.setPrgList(prgList);
    }
    public void allSteps() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrg(repository.getPrgList());
        while(prgList.size() > 0){
            Collection<ValueInterface> addresses = prgList.stream().
                    flatMap(program -> program.getMap().getAll().values().stream())
                    .collect(Collectors.toList());
            prgList.get(0).getHeap().setContent(this.safeGarbageCollector(getAllAddresses(addresses, prgList.get(0).getHeap()), prgList.get(0).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);
    }
    public void oneStepAll() throws IndexOutOfBoundsException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrg(repository.getPrgList());
        ProgramState state = programList.get(0);
        Collection<ValueInterface> addresses = programList.stream().
                flatMap(program -> program.getMap().getAll().values().stream())
                .collect(Collectors.toList());
        state.getHeap().setContent(this.safeGarbageCollector(getAllAddresses(addresses, state.getHeap()), state.getHeap().getContent()));
        oneStepForAllPrg(programList);
        programList = removeCompletedPrg(repository.getPrgList());
        executor.shutdownNow();
        repository.setPrgList(programList);
    }

    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream().filter(p-> p.isNotCompleted()).collect(Collectors.toList());
    }

    List<Integer> getAllAddresses(Collection<ValueInterface> symTableValues, HeapInterface<Integer, ValueInterface> heapTable){
        ConcurrentLinkedDeque<Integer> symTableAddr = symTableValues.stream().filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        symTableAddr.stream().forEach(a-> {
            ValueInterface v= heapTable.getContent().get(a);
            if (v instanceof  RefValue)
                if (!symTableAddr.contains(((RefValue)v).getAddr()))
                    symTableAddr.add(((RefValue)v).getAddr());});
        return symTableAddr.stream().toList();
    }

    Map<Integer, ValueInterface> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,ValueInterface> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<ProgramState> getPrograms() {
        return repository.getPrgList();
    }
}
