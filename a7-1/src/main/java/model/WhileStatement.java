package model;

import java.util.Map;

public class WhileStatement implements IStatement{
    Expression expr;
    IStatement statement;
    public WhileStatement(Expression e, IStatement s){
        expr = e;
        statement = s;
    }

    @Override
    public String toString(){
        return "WHILE(" + expr.toString() + ")" + statement.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> symbolTable = state.getMap();
        StackInterface<IStatement> stack = state.getStack();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        FileTableInterface fileTable = state.getFileTable();
        ValueInterface conditionValue = expr.evaluate(symbolTable, heap);
        if (conditionValue instanceof BoolValue) {
            BoolValue boolConditionValue = (BoolValue) conditionValue;
            if (boolConditionValue.getValue()) {
                stack.push(new WhileStatement(expr, statement));
                stack.push(statement);
            }
        } else {
            throw new MyException("Condition expression is not a boolean");
        }
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface exprType = expr.typeCheck(typeEnv);
        if(! exprType.equals(new BoolType()))
            throw new MyException("The condition of WHILE is not of type bool");
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
        return new WhileStatement(expr, statement);
    }
}
