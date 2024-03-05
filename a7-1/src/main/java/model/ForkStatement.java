package model;

import java.util.Map;

public class ForkStatement implements IStatement{
    IStatement statement;
    public ForkStatement(IStatement stmt){
        statement = stmt;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<IStatement> newStack = new MyStack<>();
        MapInterface<String, ValueInterface> symTable = new MyMap<>();
        state.getMap().getAll().forEach((key, value) -> symTable.add(key, value.deepCopy()));
        ProgramState newThread = new ProgramState(newStack, symTable, state.getList(), state.getHeap(), statement, state.getFileTable(), ProgramState.getNewId());
        return newThread;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        statement.typeCheck(clone(typeEnv));
        return typeEnv;
    }

    private MapInterface<String, TypeInterface> clone(MapInterface<String, TypeInterface> typeEnv) {
        MapInterface<String, TypeInterface> clonedMap = new MyMap<>();
        for (Map.Entry<String, TypeInterface> entry : typeEnv.getAll().entrySet())
            clonedMap.add(entry.getKey(), entry.getValue().deepCopy());
        return clonedMap;
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement);
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }
}
