package model;

public class BoolValue implements ValueInterface{
    boolean value;
    public BoolValue(boolean v){
        value = v;
    }
    public String toString(){
        return "" + value;
    }
    public boolean getValue(){return value;}
    public TypeInterface getType(){
        return new BoolType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new BoolValue(value);
    }

    public boolean equals(Object another){
        return another instanceof BoolValue;
    }
}
