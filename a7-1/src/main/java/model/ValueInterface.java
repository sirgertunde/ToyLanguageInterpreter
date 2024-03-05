package model;

public interface ValueInterface {
    TypeInterface getType();

    ValueInterface deepCopy();
}
