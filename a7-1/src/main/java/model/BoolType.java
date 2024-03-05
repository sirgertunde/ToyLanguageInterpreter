package model;

public class BoolType implements TypeInterface{
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
    public String toString(){
        return "boolean";
    }

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public TypeInterface deepCopy() {
        return this;
    }
}
