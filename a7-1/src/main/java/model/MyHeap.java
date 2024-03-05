package model;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, V> implements HeapInterface<K, V>{
    Map<K, V> heap;
    int freeAddress;
    public MyHeap(){
        heap = new HashMap<>();
        freeAddress = 1;
    }
    @Override
    public K allocate(V content) {
        K newAddress = (K) Integer.valueOf(freeAddress);
        heap.put(newAddress, content);
        freeAddress++;
        return newAddress;
    }

    @Override
    public void deallocate(K address) {
        heap.remove(address);
    }

    public int getFreeAddress(){
        return freeAddress;
    }
    @Override
    public V read(K address) {
        return heap.get(address);
    }

    @Override
    public void write(K address, V content) {
        heap.put(address, content);
    }

    @Override
    public String toString() {
        StringBuilder heapString = new StringBuilder();
        for (Map.Entry<K, V> entry : heap.entrySet()) {
            heapString.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return heapString.toString();
    }
    @Override
    public boolean isDefined(K id) {
        if (heap.containsKey(id))
            return true;
        else
            return false;
    }

    @Override
    public void setContent(Map<K, V> newContent) {
        this.heap = new HashMap<>(newContent);
    }

    @Override
    public int allocateAddress(V value) {
        int newAddress = freeAddress;
        freeAddress++;
        heap.put((K) Integer.valueOf(newAddress), value);
        return newAddress;
    }

    @Override
    public Map<K, V> getContent() {
        return heap;
    }


}
