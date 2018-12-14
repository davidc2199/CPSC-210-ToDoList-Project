package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public abstract class AbstractBot extends Observable {

   public Scanner keyboard = new Scanner(System.in);
   public ArrayList<String> words = new ArrayList<>();
   public ArrayList<Integer> numbers;

     public abstract void options() throws IOException;
     public abstract void printList();
     public abstract void printOptions();
     public void save() throws IOException, NothingToSaveException {
        Path file = Paths.get("wordlist.txt");
         if (words.size() == 0)
             throw new NothingToSaveException();
        Files.write(file, words, Charset.forName("UTF-8"));
    }
    public int size() {
        return numbers.size();
    }

    public void printForAddNumberAndWord (String type) {
        System.out.print(" Please enter the " + type + " you want to add. \n");
    }
}
