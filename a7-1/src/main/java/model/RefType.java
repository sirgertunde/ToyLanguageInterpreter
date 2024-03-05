package model;

public class RefType implements TypeInterface{
    TypeInterface inner;
    public RefType(TypeInterface i){
        inner = i;
    }
    public TypeInterface getInner(){
        return inner;
    }
    public boolean equals(Object another){
        if(another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
    @Override
    public ValueInterface defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public TypeInterface deepCopy() {
        TypeInterface copyInner = inner.deepCopy();
        return new RefType(copyInner);
    }
}
