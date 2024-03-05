package repository;

import model.MyException;
import model.ProgramState;

import java.util.List;

public interface RepoInterface {
    //void add(ProgramState state);
    //void remove(int index);
    //ProgramState getCrtPrg();
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> programList);
    void logPrgStateExec(ProgramState prgState) throws MyException;
}
