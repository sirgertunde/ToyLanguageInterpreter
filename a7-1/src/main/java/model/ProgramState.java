package model;


public class ProgramState {
    int id;
    static int nextId = 0;
    StackInterface<IStatement> stack;
    MapInterface<String, ValueInterface> map;
    ListInterface<ValueInterface> list;
    FileTableInterface fileTable;
    HeapInterface<Integer, ValueInterface> heap;
    IStatement originalProgram;

    public ProgramState(StackInterface<IStatement> s, MapInterface<String, ValueInterface> m, ListInterface<ValueInterface> l, HeapInterface<Integer, ValueInterface> h, IStatement program, FileTableInterface fileT, int id1){
        id = id1;
        stack = s;
        map = m;
        list = l;
        heap = h;
        originalProgram = program.deepCopy();
        fileTable = fileT;
        stack.push(program);
    }
    public StackInterface<IStatement> getStack(){
        return stack;
    }

    public MapInterface<String, ValueInterface> getMap(){
        return map;
    }
    public ListInterface<ValueInterface> getList(){
        return list;
    }

    public HeapInterface<Integer, ValueInterface> getHeap(){
        return heap;
    }

    public FileTableInterface getFileTable() {
        return fileTable;
    }

    public ProgramState oneStep() throws MyException{
        if(stack.isEmpty())
            throw new MyException("program state stack is empty");
        IStatement crtStmt = stack.pop();
        return crtStmt.execute(this);
    }

    public static synchronized int getNewId(){
        return nextId++;
    }
    public int getId(){
        return id;
    }
    public boolean isNotCompleted(){
        if(!stack.isEmpty())
            return true;
        return false;
    }

    @Override
    public String toString(){
        String state = "Id=" + id + "\n" +
                "Stack:\n" +
                stack.toString() +
                "\nSymbol Table:\n" +
                map.toString() +
                "\nOutput:\n" +
                list.toString() +
                "\nHeap:\n" +
                heap.toString();
        return state;
    }
}
