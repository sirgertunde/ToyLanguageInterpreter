package model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements ListInterface<T>{
    List<T> list;
    public MyList(){
        list = new ArrayList<>();
    }
    public void add(T element){
        list.add(element);
    }

    @Override
    public List<T> getAll() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        for (T item : list) {
            listString.append(item).append("\n");
        }
        return listString.toString();
    }
}
