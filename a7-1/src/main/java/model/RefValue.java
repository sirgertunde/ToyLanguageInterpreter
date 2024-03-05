package model;

public class RefValue implements ValueInterface{
    int address;
    TypeInterface locationType;
    public RefValue(int addr, TypeInterface loc){
        address = addr;
        locationType = loc;
    }
    public int getAddr(){
        return address;
    }

    @Override
    public TypeInterface getType() {
        return new RefType(locationType);
    }

    @Override
    public ValueInterface deepCopy() {
        return new RefValue(address, locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }
}
