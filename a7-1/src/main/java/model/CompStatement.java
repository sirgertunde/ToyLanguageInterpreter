package model;

public class CompStatement implements IStatement{
    IStatement first;
    IStatement second;
    public CompStatement(IStatement f, IStatement s){
        first = f;
        second = s;
    }
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }
    public ProgramState execute(ProgramState state) throws MyException{
        StackInterface<IStatement> stack = state.getStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public IStatement deepCopy() {
        return new CompStatement(first, second);
    }
}
