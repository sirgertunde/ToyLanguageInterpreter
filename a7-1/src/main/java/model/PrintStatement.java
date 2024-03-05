package model;

public class PrintStatement implements IStatement{
    Expression exp;
    public PrintStatement(Expression e){
        exp = e;
    }
    public String toString(){
        return "print("+exp.toString()+")";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ListInterface<ValueInterface> list = state.getList();
        MapInterface<String, ValueInterface> map = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface value = exp.evaluate(map, heap);
        list.add(value);
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(exp);
    }
}
