package model;

import java.io.BufferedReader;
import java.util.Map;

public interface FileTableInterface{
    void add(StringValue filename, BufferedReader fileDescriptor);
    void remove(StringValue filename);
    BufferedReader get(StringValue filename);
    boolean contains(StringValue filename);
    Iterable<Map.Entry<StringValue, BufferedReader>> getAll();

    Map<StringValue, BufferedReader> getContent();
}
