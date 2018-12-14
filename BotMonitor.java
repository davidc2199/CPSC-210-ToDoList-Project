package ui;

import java.util.Observable;
import java.util.Observer;

public class BotMonitor implements Observer {
    private int numberOfItemsCreated;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("You have created a new item! ");
        ChecklistBot c = (ChecklistBot) o;
        numberOfItemsCreated = 0;
        for (String s: c.getCurrentItemNames()) {
            numberOfItemsCreated+= 1;
        } System.out.println("You have made " + numberOfItemsCreated + " item(s) so far. ");
    }
}

