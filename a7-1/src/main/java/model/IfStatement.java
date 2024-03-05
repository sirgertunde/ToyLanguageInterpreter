package model;

import java.util.Map;

public class IfStatement implements IStatement{
    Expression exp;
    IStatement thenS;
    IStatement elseS;
    public IfStatement(Expression e, IStatement t, IStatement el){
        exp = e;
        thenS = t;
        elseS = el;
    }
    public String toString(){
        return "(IF("+exp.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+"))";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> map = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface conditionValue = exp.evaluate(map, heap);

        if (!(conditionValue instanceof BoolValue)) {
            throw new MyException("Conditional expression is not a boolean");
        }
        boolean conditionResult = ((BoolValue) conditionValue).getValue();
        StackInterface<IStatement> executionStack = state.getStack();
        if (conditionResult) {
            executionStack.push(thenS);
        } else {
            executionStack.push(elseS);
        }
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeExp = exp.typeCheck(typeEnv);
        if(typeExp.equals(new BoolType())) {
            thenS.typeCheck(clone(typeEnv));
            elseS.typeCheck(clone(typeEnv));
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

    private MapInterface<String, TypeInterface> clone(MapInterface<String, TypeInterface> typeEnv) {
        MapInterface<String, TypeInterface> clonedMap = new MyMap<>();
        for (Map.Entry<String, TypeInterface> entry : typeEnv.getAll().entrySet())
            clonedMap.add(entry.getKey(), entry.getValue().deepCopy());
        return clonedMap;
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(exp, thenS, elseS);
    }
}
