package ui;

import model.AbstractBot;
import model.NothingToSaveException;
import model.NumberTooLargeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class  NumberBot extends AbstractBot {

    // EFFECTS: List is empty
    public NumberBot() {
        numbers = new ArrayList<>();
    }
    public void options() throws InputMismatchException, IOException {
        boolean x = true;
        while (x) {
            printOptions();
            String input = keyboard.next();
            if (input.equalsIgnoreCase("1")) {
               addNumber();
            }
            if (input.equalsIgnoreCase("2"))
            {
                System.out.println(" The current total is: " + addAll());
            }
            if (input.equalsIgnoreCase("3")) {
                System.out.println(" The current size is: " + size());
            }
            if (input.equalsIgnoreCase("4")) {
                printList();
            }
            if (input.equalsIgnoreCase("5")) {
                try {
                    save();
                } catch (NothingToSaveException e) {
                    System.out.println("There are no numbers to save. ");
                }
            }
            if (input.equalsIgnoreCase("6")) {
                load();
            }
            if (input.equalsIgnoreCase("7")) {
                removeNumber();
            }
            if (input.equalsIgnoreCase("8")) {
                x = false;
            }
        }
    }

    public Integer add(int a, int b) {
        return (a + b);
    }

    public int addAll() {
        int total = 0;
        for (int a : numbers) {
            total = add(total, a);
        }
        return total;
    }

    public void insert(int number) {
        numbers.add(number);
    }

    @Override
    public void save() throws IOException, NothingToSaveException {
        ArrayList<String> stringNumbers = new ArrayList<>();
        for (int a: numbers) {
            String strNumber = Integer.toString(a);
            stringNumbers.add(strNumber);
        } if (stringNumbers.size() == 0)
            throw new NothingToSaveException();
        Path file = Paths.get("numberlist.txt");
        Files.write(file, stringNumbers, Charset.forName("UTF-8"));
    }

    public void load() throws FileNotFoundException {
        Scanner s = new Scanner(new File("numberlist.txt"));
        while (s.hasNextLine()) {
            int r = Integer.parseInt(s.nextLine());
            insert(r);
        }
    }

    public  ArrayList<Integer> getList() {
        return numbers;
    }

    public void printList() {
        String printOut = ("This is the list of numbers you have so far: " + getList());
        System.out.println(printOut);
    }

    public void printOptions() {
        System.out.println("\n Please select an option below. ");
        System.out.println(" 1. Insert a number");
        System.out.println(" 2. Print the total of the current list");
        System.out.println(" 3. Print the size of the current list");
        System.out.println(" 4. Print the current list");
        System.out.println(" 5. Save current number list ");
        System.out.println(" 6. Load number list from file ");
        System.out.println(" 7. Remove a number from list ");
        System.out.println(" 8. Exit ");
    }

    public void remove(int index) throws NumberTooLargeException {
            if (index >= numbers.size())
                throw new NumberTooLargeException();
            numbers.remove(index);

    }

    public void addNumber() {
        boolean inputValidity;
        do {
            try {
                printForAddNumberAndWord("number");
                //System.out.print(" Please enter the number you want to add. \n");
                int addNumber = keyboard.nextInt();
                insert(addNumber);
                inputValidity = true;
            } catch (InputMismatchException i) {
                keyboard.nextLine();
                inputValidity = false;
                System.out.println("Your input is invalid. ");
            }
        } while (!inputValidity);
    }

    public void removeNumber() {
        System.out.print(" Please enter the index of the number you want to remove. \n");
        int removeNumber = keyboard.nextInt();
        try {
            remove(removeNumber);
        } catch (NumberTooLargeException e) {
            System.out.println("The index you entered exceeds the size of the number list.");
        } finally {
            System.out.println("You can only remove numbers based on their index in the list.");
        }
    }
}
