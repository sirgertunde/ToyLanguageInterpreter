package model;

public interface Expression {
    ValueInterface evaluate(MapInterface<String, ValueInterface> m, HeapInterface<Integer, ValueInterface> h) throws MyException;
    TypeInterface typeCheck(MapInterface<String, TypeInterface> typeEnv) throws MyException;
}
