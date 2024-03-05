package model;

public class NopStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }
}
