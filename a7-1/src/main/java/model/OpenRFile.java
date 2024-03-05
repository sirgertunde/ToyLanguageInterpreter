package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement{
    Expression exp;
    public OpenRFile(Expression exp1){
        exp = exp1;
    }

    public String toString(){
        return "openReadFile(" + exp + ");";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface result = exp.evaluate(state.getMap(), state.getHeap());
        if (!(result.getType() instanceof StringType)) {
            throw new MyException("File name must be a string");
        }
        StringValue fileName = (StringValue) result;
        if (state.getFileTable().contains(fileName)) {
            throw new MyException("File " + fileName + " is already open");
        }
        try {
            BufferedReader fileDescriptor = new BufferedReader(new FileReader(fileName.getValue()));
            state.getFileTable().add(fileName, fileDescriptor);
        } catch (FileNotFoundException e) {
            throw new MyException("File not found: " + fileName);
        }
        return state;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeExp = exp.typeCheck(typeEnv);
        if(!typeExp.equals(new StringType()))
            throw new MyException("File name is not of type string");
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenRFile(exp);
    }
}
