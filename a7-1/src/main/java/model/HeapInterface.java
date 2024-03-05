package model;

import java.util.Map;

public interface HeapInterface<K, V> {
    K allocate(V content);
    void deallocate(K address);
    V read(K address);
    void write(K address, V content);
    Map<K, V> getContent();
    int getFreeAddress();

    boolean isDefined(K address);

    void setContent(Map<K, V> newContent);
    int allocateAddress(V value);
}
