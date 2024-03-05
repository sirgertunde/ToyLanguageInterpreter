package model;

public class StringValue implements ValueInterface{
    String value;
    public StringValue(String string){
        value = string;
    }
    public String getValue(){
        return value;
    }

    public String toString(){
        return value;
    }
    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new StringValue(value);
    }

    public boolean equals(Object another){
        return another instanceof StringValue;
    }
}
