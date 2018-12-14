package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Checklist {

    public HashMap<String, Integer> itemList;
    public List<Item> listOfItems;
    public String checklistName;

    public Checklist(String checklistName) {
        this.itemList = new HashMap<>();
        this.checklistName = checklistName;
        this.listOfItems = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!itemList.containsKey((item.itemName)) && !listOfItems.contains(item)) {
            itemList.put(item.itemName, item.itemDate);
            listOfItems.add(item);
            item.addContainsChecklist(this);
        }
    }

    public void removeItem(Item item) {
        if (listOfItems.contains(item)) {
            listOfItems.remove(item);
            itemList.remove(item.itemName);
            item.removeList(this);
        }
    }

    public boolean contains(Item item) {
        if (itemList.containsKey(item.itemName)) {
            return true;
        } return false;
    }

    public void printItemList() {
        System.out.println(itemList.keySet());
        System.out.println(itemList.values());
    }

    public String printItemListGui() {
        String list = itemList.keySet().toString() + "\n" + itemList.values().toString();
        return list;
    }
}
