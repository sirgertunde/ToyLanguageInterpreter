package model;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    Expression exp;
    String varName;

    public ReadFile(Expression exp1, String variable) {
        exp = exp1;
        varName = variable;
    }
    public String toString(){
        return "readFile(" + exp + ");";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (!state.getMap().isDefined(varName)) {
            throw new MyException("Variable " + varName + " is not defined");
        }
        ValueInterface varValue = state.getMap().lookUp(varName);
        if (!(varValue.getType() instanceof IntType)) {
            throw new MyException("Variable " + varName + " must be of type int");
        }
        ValueInterface result = exp.evaluate(state.getMap(), state.getHeap());
        if (!(result.getType() instanceof StringType)) {
            throw new MyException("File name must be a string");
        }
        StringValue fileName = (StringValue) result;
        if (!state.getFileTable().contains(fileName)) {
            throw new MyException("File " + fileName + " is not open");
        }
        BufferedReader fileDescriptor = state.getFileTable().get(fileName);
        try {
            String line = fileDescriptor.readLine();
            int intValue;
            if (line == null) {
                intValue = 0;
            } else {
                intValue = Integer.parseInt(line);
            }
            state.getMap().update(varName, new IntValue(intValue));
        } catch (IOException | NumberFormatException e) {
            throw new MyException("Error reading from file: " + fileName);
        }
        return state;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface expType = exp.typeCheck(typeEnv);
        TypeInterface varType = typeEnv.lookUp(varName);
        if(varType.equals(new IntType()))
            if(expType.equals(new StringType()))
                return typeEnv;
            else
                throw new MyException("ReadFileStatement: file name is not of type string");
        else
            throw new MyException("The variable name is not of type int");
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFile(exp, varName);
    }
}