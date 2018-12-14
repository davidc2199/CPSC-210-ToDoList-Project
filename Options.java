package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Options {
    public void printList();
    public void remove(String s);
    public void insert(String s);
    public void save() throws IOException, NothingToSaveException;
    public void load() throws FileNotFoundException;
}
