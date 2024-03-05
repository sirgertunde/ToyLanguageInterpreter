package model;

import java.util.HashMap;
import java.util.Map;

public class MyMap<K, V> implements MapInterface<K, V>{
    Map<K, V> map;
    public MyMap(){
        map = new HashMap<>();
    }
    public void add(K key, V value){
        map.put(key, value);
    }
    public boolean isDefined(K id) {
        if (map.containsKey(id))
            return true;
        else
            return false;
    }
    public void update(K id, V value){
        map.put(id, value);
    }
    public V lookUp(K id){
        return map.get(id);
    }
    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            mapString.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return mapString.toString();
    }
    public Map<K, V> getAll() {
        return map;
    }

}
