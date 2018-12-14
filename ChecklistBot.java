package ui;

import model.AbstractBot;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ChecklistBot extends AbstractBot {

    private Scanner keyboard = new Scanner(System.in);
    private HashMap<String, Checklist> currentChecklists;
    private HashMap<String, Item> currentItems;
    private String itemName;
    private String listName;
    private Integer date;
    private Item item = new Item("", 0);
    private String testText = "<html>\n" +
            "Please select an option below (Type number and click (enter))\n" +
            "<li>1. Create a new Checklist\n" +
            "<li>2. Create a new Item\n" +
            "<li>3. Add selected item to this Checklist\n" +
            "<li>4. Remove selected item from this Checklist\n" +
            "<li>5. View the Checklists you have made so far\n" +
            "<li>6. View the names of the items you have made so far\n" +
            "<li>7. View the items in a Checklist\n" +
            "<li>8. Exit ";

    public ChecklistBot() {
        this.currentChecklists = new HashMap<>();
        this.currentItems = new HashMap<>();
    }

    public void options() throws InputMismatchException {
        boolean x = true;
        while (x) {
            printOptions();
            String input = keyboard.next();
            if (input.equalsIgnoreCase("1")) {
                makeChecklist();
            }
            if (input.equalsIgnoreCase("2")) {
                makeItem();
            }
            if (input.equalsIgnoreCase("3")) {
                addItem();
            }
            if (input.equalsIgnoreCase("4")) {
                removeItem();
            }
            if (input.equalsIgnoreCase("5")) {
                viewChecklistsContainingItem();
            }
            if (input.equalsIgnoreCase("6")) {
                printMoreOptions();
            }
            if (input.equalsIgnoreCase("7")) {
                x = false;
            }
            if (input.equalsIgnoreCase("8")) {
                printList();
            }
            if (input.equalsIgnoreCase("9")) {
                System.out.println(currentChecklists.keySet());
            }
            if (input.equalsIgnoreCase("10")) {
                System.out.println(currentItems.keySet());
            }
        }
    }

    public void printOptions() {
        System.out.println("\n Please select an option below. ");
        System.out.println(" 1. Create a new Checklist ");
        System.out.println(" 2. Create a new Item ");
        System.out.println(" 3. Add an item to a Checklist ");
        System.out.println(" 4. Remove an item from a Checklist ");
        System.out.println(" 5. View a list of Checklists containing an item ");
        System.out.println(" 6. View more options ");
        System.out.println(" 7. Exit ");
    }

    public void makeChecklist() {
        System.out.print(" Please enter the name of the checklist you want to make. \n");
        listName = keyboard.next();
        Checklist list = new Checklist(listName);
        currentChecklists.put(listName, list);
    }

    public void makeItem() {
        System.out.println(" Please enter the name of the item you want to make. ");
        itemName = keyboard.next();
        System.out.println(" Please enter the due date of the item. (month-day) ");
        System.out.println(" Example: 0112 for January 12th ");
        date = keyboard.nextInt();
        if (currentItems.keySet().contains(itemName)) {
            item = currentItems.get(itemName);
            currentItems.put(itemName, item);
        } else {
            item = new Item(itemName, date);
            currentItems.put(itemName, item);
        }
        setChanged();
        notifyObservers();
    }

    public void addItem() {
        System.out.println(" Please enter the name of the item you would like to add. ");
        itemName = keyboard.next();
        System.out.println(" Please enter the name of the checkList you would like to add your item to. ");
        listName = keyboard.next();
        if (currentItems.containsKey(itemName)) {
            item = currentItems.get(itemName);
        }
        if (currentChecklists.containsKey(listName)) {
            currentChecklists.get(listName).addItem(item);
        } else {
            System.out.println(" Sorry, that list does not exist. ");
        }
    }

    public void removeItem() {
        System.out.println(" Please enter the name item you would like to remove. ");
        itemName = keyboard.next();
        if (currentItems.containsKey(itemName)) {
            item = currentItems.get(itemName);
        }
        System.out.println(" Please enter the name of the list you would like to remove from. ");
        listName = keyboard.next();
        if (currentChecklists.containsKey(listName)) {
            currentChecklists.get(listName).removeItem(item);
        }
    }

    public void viewChecklistsContainingItem() {
        System.out.println(" Please enter the name of the item you want to look for. ");
        itemName = keyboard.next();
        if (currentItems.containsKey(itemName)) {
            currentItems.get(itemName).listOfChecklistNames();
        }
    }

    public void printList() {
        System.out.println(" Please enter the name of the checklist you would like to see. ");
        listName = keyboard.next();
        if (currentChecklists.containsKey(listName)) {
            currentChecklists.get(listName).printItemList();
        }
    }

    public void printMoreOptions() {
        System.out.println(" 8. View the items in a Checklist ");
        System.out.println(" 9. View the Checklists you have made so far. ");
        System.out.println(" 10. View the names of the items you have made so far. ");
    }

    public Set<String> getCurrentItemNames() {
        return currentItems.keySet();
    }

    public void cbotActions(String input) {
        if (input.equalsIgnoreCase("1")) {
            UserInterface.setTheLabel("Please type in the name of the checklist and click (new checklist) ");
        }
        if (input.equalsIgnoreCase("2")) {
            UserInterface.setTheLabel("<html>\n" +
                    "<li>Please type the name of the item you want to make\n" +
                    "<li>Then click (new item name)");
        }
        if (input.equalsIgnoreCase("3")) {
            UserInterface.setTheLabel("<html>\n" +
                    "<li>Your current selected item is the last one you made\n" +
                    "<li>To check your current selected item, click on (check selected item)\n" +
                    "<li>To select a previously added item, make a new item with the same name\n" +
                    "<li>To add your selected item to a checklist, type in the list you would like to add it to\n" +
                    "<li>Then click on (Add Selected Item to this Checklist)");
        }
        if (input.equalsIgnoreCase("4")) {
            UserInterface.setTheLabel("<html>\n" +
                    "<li>Your current selected item is the last one you made\n" +
                    "<li>To check your current selected item, click on (check selected item)\n" +
                    "<li>To select a previously added item, make a new item with the same name\n" +
                    "<li>To remove selected item from list, enter the checklist you would like to remove from\n" +
                    "<li>Then click (Remove Selected Item from this Checklist)");
        }
        if (input.equalsIgnoreCase("5")) {
            UserInterface.setTheLabel("<html>\n" +
                    currentChecklists.keySet().toString() +
                    "<li>Click on Menu");
        }
        if (input.equalsIgnoreCase("6")) {
            UserInterface.setTheLabel("<html>\n" +
                    currentItems.keySet().toString() +
                    "<li>Click on Menu");
        }
        if (input.equalsIgnoreCase("7")) {
            UserInterface.setTheLabel("<html>\n" +
                    "<li>Please type the name of the checklist you want to see\n" +
                    "<li>Then click (Select Checklist to View)");
        }
        if (input.equalsIgnoreCase("8")) {
            System.exit(0);
        }
    }

    public void makeChecklistGui(String name) {
        Checklist list = new Checklist(name);
        currentChecklists.put(name, list);
        UserInterface.setTheLabel("You have made a new checklist! Click on Menu");
    }

    public void makeItemName(String text) {
        itemName = text;
        UserInterface.setTheLabel("<html>\n" +
                "<li>Next, type in the due date of your new item and click (make item)\n" +
                "<li>Example: 112 for January 12th");
    }

    public void makeItem(String text) {
        date = Integer.parseInt(text);
        if (currentItems.keySet().contains(itemName)) {
            item = currentItems.get(itemName);
            currentItems.put(itemName, item);
            UserInterface.setTheLabel("You have made a new item! Click on Menu");
        } else {
            item = new Item(itemName, date);
            currentItems.put(itemName, item);
            UserInterface.setTheLabel("You have made a new item! Click on Menu");

        }
    }

    public void addItemChecklist(String text) {
        if (currentChecklists.containsKey(text)) {
            currentChecklists.get(text).addItem(item);
            UserInterface.setTheLabel("<html>\n" +
                    "<li>You have added an item to " + text +
                    "<li>Click on Menu");
        } else {
            UserInterface.setTheLabel(" Sorry, that Checklist does not exist. Click on Menu");
        }
    }

    public void viewChecklist(String text) {
        if (currentChecklists.containsKey(text)) {
            UserInterface.setTheLabel(currentChecklists.get(text).printItemListGui());
        } else UserInterface.setTheLabel(" Sorry, that Checklist does not exist. Click on Menu.");
    }

    public void removeSelectedItem(String text) {
        if (currentChecklists.containsKey(text)) {
            currentChecklists.get(text).removeItem(item);
            UserInterface.setTheLabel("You have removed the selected item. Click on Menu.");
        } else UserInterface.setTheLabel(" Sorry, that Checklist does not exist. Click on Menu.");
    }


    public void checkCurrentItem() {
        UserInterface.setTheLabel("<html>\n" + item.itemName + item.itemDate.toString() +
                "<li>Click on Menu");
    }
}
