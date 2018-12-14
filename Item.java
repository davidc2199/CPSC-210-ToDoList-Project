package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Item extends Observable {

    private List<Checklist> checklists;
    public String itemName;
    public Integer itemDate;

    public Item(String itemName, Integer itemDate) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.checklists = new ArrayList<>();
    }

    public void addContainsChecklist(Checklist checklist) {
        if (!checklists.contains(checklist)) {
            checklists.add(checklist);
            checklist.addItem(this);
        }
    }
    public void listOfChecklistNames() {
        ArrayList<String> nameList = new ArrayList<>();
        if (checklists != null) {
            for (Checklist c : checklists) {
                nameList.add(c.checklistName);
            }
            System.out.println(nameList);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }

    public void removeList(Checklist list) {
        if (checklists.contains(list)) {
            checklists.remove(list);
            list.removeItem(this);
        }
    }
}
