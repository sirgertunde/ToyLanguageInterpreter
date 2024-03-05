package model;

public class StringType implements TypeInterface{
    public boolean equals(Object another){
        return another instanceof StringType;
    }
    public String toString(){
        return "string";
    }
    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }

    @Override
    public TypeInterface deepCopy() {
        return this;
    }
}
