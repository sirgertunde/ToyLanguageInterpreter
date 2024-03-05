package model;

public class ValueExp implements Expression{
    ValueInterface e;
    public ValueExp(ValueInterface value){
        e = value;
    }
    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        return e;
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
