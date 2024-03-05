package model;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface>typeEnv) throws MyException;
    IStatement deepCopy();
}
