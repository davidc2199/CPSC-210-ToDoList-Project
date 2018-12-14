package ui;

import jdk.nashorn.internal.ir.annotations.Ignore;
import model.AbstractBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.NothingToSaveException;
import model.Options;
import model.SimpleFunctions;

public class WordBot extends AbstractBot implements SimpleFunctions, Options {

    // EFFECTS: set is empty
    public WordBot() {
        words = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: If input is yes, next input will be added to list
    //          otherwise method terminates if input is no
    public void options() throws IOException {
        boolean x = true;
        while (x) {
            printOptions();
            String input = keyboard.nextLine();
            if (input.equalsIgnoreCase("1")) {
                addWord();
            } if (input.equalsIgnoreCase("2")) {
                removeWord();
            } if (input.equalsIgnoreCase("3")) {
                printList();
            } if (input.equalsIgnoreCase("4")) {
                try {
                    save();
                } catch (NothingToSaveException e) {
                    System.out.println("There are no words to save. ");
                }
            } if (input.equalsIgnoreCase("5")) {
                    load();
            } if (input.equalsIgnoreCase("6")) {
                    x = false;
            }
        }
    }

    // EFFECTS: returns the list of words
    public ArrayList<String> getList() {
        return words;
    }

    public void addWord() {
        printForAddNumberAndWord("word");
        //System.out.print(" Please enter the word you want to add. \n");
        String addWord = keyboard.nextLine();
        insert(addWord);
    }

    public void removeWord() {
        System.out.print(" Please enter the word you want to remove. \n");
        String removeWord = keyboard.nextLine();
        remove(removeWord);
    }

    // EFFECTS: prints the list of words
    public void printList() {
        String printOut = ("This is the list of words you have added: " + getList());
        System.out.println(printOut);
    }

    // MODIFIES: this
    // EFFECTS: removes the word given from the list if it is contained in it
    public void remove(String s) {
        if (words.contains(s)) {
            words.remove(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: inserts the word given into the list
    public void insert(String s) {
        words.add(s);
    }

    // EFFECTS: returns true if word is found in list, false if not
    public boolean contains(String s) {
        if (words.contains(s)) {
            return true;
        } else return false;
    }

    @Override
    // EFFECTS: returns size of word list
    public int size() {
        return words.size();
    }

    public void load() throws FileNotFoundException {
        Scanner s = new Scanner(new File("wordlist.txt"));
        while (s.hasNextLine()) {
            insert(s.nextLine());
        }
    }

    public void printOptions() {
        System.out.println("\n Please select an option below. ");
        System.out.println(" 1. Add a word ");
        System.out.println(" 2. Remove a word ");
        System.out.println(" 3. Print current word list ");
        System.out.println(" 4. Save current word list ");
        System.out.println(" 5. Load word list from file ");
        System.out.println(" 6. Exit ");
    }
}
