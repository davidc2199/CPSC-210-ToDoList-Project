package ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project {
    private static Scanner keyboard = new Scanner(System.in);
    private static WebpageReader reader = new WebpageReader();
    private static String options = "<html>\n" +
            "Please select an option below.\n" +
            "<li>1. WordBot\n" +
            "<li>2. NumberBot\n" +
            "<li>3. ChecklistBot";


    public static void main(String args[]) throws IOException, InputMismatchException, NullPointerException {
        BotMonitor botmon = new BotMonitor();
        WordBot wbot = new WordBot();
        NumberBot nbot = new NumberBot();
        ChecklistBot cbot = new ChecklistBot();
        reader.readWebPage();
        boolean y = true;
        while (y) {
            System.out.println("Please select an option below.");
            System.out.println("1. WordBot");
            System.out.println("2. NumberBot");
            System.out.println("3. ChecklistBot");
            String input = keyboard.nextLine();
            if (input.equalsIgnoreCase("1")) {
                y = false;
                wbot.addObserver(botmon);
                wbot.options();
            }
            if (input.equalsIgnoreCase("2")) {
                y = false;
                nbot.addObserver(botmon);
                nbot.options();
            }
            if (input.equalsIgnoreCase("3")) {
                y = false;
                cbot.addObserver(botmon);
                cbot.options();
            }
        }
    }
}

