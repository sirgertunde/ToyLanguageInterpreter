package model;

import java.util.List;

public interface ListInterface<T> {
    void add(T element);
    List<T> getAll();
}
