package model;

public class VarExp implements Expression{
    String id;
    public VarExp(String var){
        id = var;
    }
    @Override
    public ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException {
        return m.lookUp(id);
    }

    @Override
    public TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv.lookUp(id);
    }

    @Override
    public String toString(){
        return id;
    }
}
