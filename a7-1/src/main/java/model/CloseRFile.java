package model;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement{
    Expression exp;
    public CloseRFile(Expression exp1){
        exp = exp1;
    }

    public String toString(){
        return "closeReadFile(" + exp + ");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> symTable = state.getMap();
        FileTableInterface fileTable = state.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        try {
            ValueInterface expValue = exp.evaluate(symTable, heap);
            if (!(expValue instanceof StringValue)) {
                throw new MyException("File name must be a string.");
            }
            StringValue fileName = (StringValue) expValue;
            if (!fileTable.contains(fileName)) {
                throw new MyException("File not found in the file table.");
            }
            BufferedReader reader = fileTable.get(fileName);
            try {
                reader.close();
            } catch (IOException e) {
                throw new MyException("Error closing file: " + e.getMessage());
            }
            fileTable.remove(fileName);
        } catch (MyException e) {
            throw new MyException("Error closing file: " + e.getMessage());
        }
        return state;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface expType = exp.typeCheck(typeEnv);
        if(!expType.equals(new StringType()))
            throw new MyException("File name is not of type string");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseRFile(exp);
    }
}
