package model;

public class WriteHeapStatement implements IStatement{
    String varName;
    Expression expr;
    public WriteHeapStatement(String var, Expression e){
        varName = var;
        expr = e;
    }

    @Override
    public String toString() {
        return "wH(" + expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> symTable = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if (!symTable.isDefined(varName)) {
            throw new MyException("Variable " + varName + " is not defined in SymTable.");
        }
        ValueInterface varValue = symTable.lookUp(varName);
        if (!(varValue instanceof RefValue)) {
            throw new MyException("Variable " + varName + " must have type RefValue for HeapWriting statement.");
        }
        RefValue refValue = (RefValue) varValue;
        int address = refValue.getAddr();
        if (!heap.isDefined(address)) {
            throw new MyException("Invalid heap address: " + address);
        }
        ValueInterface result = expr.evaluate(symTable, heap);
        if (!((RefType)varValue.getType()).getInner().equals(result.getType())) {
            throw new MyException("Expression must evaluate to IntValue for HeapWriting statement.");
        }
        heap.write(address, result);
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface varType = typeEnv.lookUp(varName);
        TypeInterface expType = expr.typeCheck(typeEnv);
        if(varType.equals(new RefType(expType)))
            return typeEnv;
        else
            throw new MyException("WriteHeapStatement: expression cannot be evaluated to " + expType);
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expr);
    }
}
