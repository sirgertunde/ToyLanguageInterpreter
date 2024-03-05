package model;

public class IntValue implements ValueInterface{
    int value;
    public IntValue(int v){
        value = v;
    }
    public int getValue(){
        return value;
    }
    public String toString(){
        return "" + value;
    }
    public TypeInterface getType(){
        return new IntType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new IntValue(value);
    }

    public boolean equals(Object another){
        return another instanceof IntValue;
    }
}
