package model;

public class IntType implements TypeInterface{
    public boolean equals(Object another){
        return another instanceof IntType;
    }
    public String toString(){
        return "int";
    }

    @Override
    public ValueInterface defaultValue() {
        return new IntValue(0);
    }

    @Override
    public TypeInterface deepCopy() {
        return this;
    }
}
