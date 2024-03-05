package model;

import java.util.Map;

public interface MapInterface<K, V> {
    void add(K key, V value);
    boolean isDefined(K id);
    void update(K id, V value);
    V lookUp(K id);
    Map<K, V> getAll();
}
