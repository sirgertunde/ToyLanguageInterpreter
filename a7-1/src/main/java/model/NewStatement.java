package model;

public class NewStatement implements IStatement{
    String varName;
    Expression expr;
    public NewStatement(String var, Expression e){
        varName = var;
        expr = e;
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + expr.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MapInterface<String, ValueInterface> symTable = state.getMap();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if (!symTable.isDefined(varName)) {
            throw new MyException("Variable " + varName + " is not defined in the symbol table");
        }
        ValueInterface varValue = symTable.lookUp(varName);
        if (!(varValue.getType() instanceof RefType)) {
            throw new MyException("Variable " + varName + " is not of RefType");
        }
        ValueInterface exprValue = expr.evaluate(symTable, heap);
        TypeInterface locationType = ((RefType) varValue.getType()).getInner();
        if (!exprValue.getType().equals(locationType)) {
            throw new MyException("Type mismatch: " + exprValue.getType() + " cannot be assigned to " + locationType);
        }
        int newAddress = heap.allocateAddress(exprValue);
        RefValue newRefValue = new RefValue(newAddress, locationType);
        symTable.update(varName, newRefValue);
        return null;
    }

    @Override
    public MapInterface<String, TypeInterface> typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeVar = typeEnv.lookUp(varName);
        TypeInterface typeExpr = expr.typeCheck(typeEnv);
        if(typeVar.equals(new RefType(typeExpr)))
            return typeEnv;
        else
            throw new MyException("NEW statement: right hand side and left hand side have different types");

    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(varName, expr);
    }
}
