package model;

import java.util.List;

public interface StackInterface<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    List<T> getAll();
}
