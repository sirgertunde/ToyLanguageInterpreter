package model;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements StackInterface<T>{
    Stack<T> mystack;
    public MyStack(){
        mystack = new Stack<>();
    }
    public T pop(){
        return mystack.pop();
    }
    public void push(T v){
        mystack.push(v);
    }
    public boolean isEmpty(){
        return mystack.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder stackString = new StringBuilder();
        for (T item : mystack) {
            stackString.append(item).append("\n");
        }
        return stackString.toString();
    }
    public List<T> getAll() {
        return mystack;
    }
}
