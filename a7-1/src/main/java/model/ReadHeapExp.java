package model;

public class ReadHeapExp implements Expression{
    Expression expr;
    public ReadHeapExp(Expression e){
        expr = e;
    }

    @Override
    public String toString() {
        return "rH(" + expr.toString() + ")";
    }

    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        ValueInterface result = expr.evaluate(m, h);
        if (!(result instanceof RefValue)) {
            throw new MyException("HeapReadingExp requires a RefValue as the result of the inner expression.");
        }
        int address = ((RefValue) result).getAddr();
        if (!h.isDefined(address)) {
            throw new MyException("Invalid heap address: " + address);
        }
        return h.read(address);
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type = expr.typeCheck(typeEnv);
        if(type instanceof RefType){
            RefType reft = (RefType) type;
            return reft.getInner();
        }
        else
            throw new MyException("the rH argument is not a RefType");
    }
}
