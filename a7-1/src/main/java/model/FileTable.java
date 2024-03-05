package model;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements FileTableInterface{
    Map<StringValue, BufferedReader> fileTable;
    public FileTable() {
        fileTable = new HashMap<>();
    }

    @Override
    public void add(StringValue filename, BufferedReader fileDescriptor) {
        fileTable.put(filename, fileDescriptor);
    }

    @Override
    public void remove(StringValue filename) {
        fileTable.remove(filename);
    }

    @Override
    public BufferedReader get(StringValue filename) {
        return fileTable.get(filename);
    }

    @Override
    public boolean contains(StringValue filename) {
        return fileTable.containsKey(filename);
    }

    @Override
    public Iterable<Map.Entry<StringValue, BufferedReader>> getAll() {
        return fileTable.entrySet();
    }
    public Map<StringValue, BufferedReader> getContent() {
        return fileTable;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("FileTable:\n");
        for (StringValue entry : fileTable.keySet()) {
            stringBuilder.append(entry).append("\n");
        }
        return stringBuilder.toString();
    }
}
