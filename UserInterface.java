package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;

public class UserInterface extends JPanel implements ActionListener {
    private static JLabel theLabel;
    private JTextArea textArea;
    private static WebpageReader reader = new WebpageReader();
    private static String testText = "<html>\n" +
            "Please select an option below (Type number and click (enter))\n" +
            "<li>1. Create a new Checklist\n" +
            "<li>2. Create a new Item\n" +
            "<li>3. Add selected item to a Checklist\n" +
            "<li>4. Remove selected item from this Checklist\n" +
            "<li>5. View the Checklists you have made so far\n" +
            "<li>6. View the names of the items you have made so far.\n" +
            "<li>7. View the items in a Checklist\n" +
            "<li>8. Exit ";
    public ChecklistBot cbot = new ChecklistBot();


    public UserInterface() {

        setLayout((new BoxLayout(this, BoxLayout.LINE_AXIS)));
        textArea = new JTextArea(10, 20);
        JButton changeTheLabel = new JButton("Menu");
        JButton checklistBotActions = new JButton("Enter");
        JButton createChecklist = new JButton(("New Checklist"));
        JButton createItemName = new JButton(("New Item Name"));
        JButton createItem = new JButton(("Make Item"));
        JButton addItem = new JButton("Check Selected Item");
        JButton addItemChecklist = new JButton("Add Selected Item to this Checklist");
        JButton viewChecklist = new JButton("Select Checklist to view");
        JButton removeItem = new JButton("Remove Selected Item from this Checklist");
        changeTheLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeTheLabel.setActionCommand("label");
        changeTheLabel.addActionListener(this);
        checklistBotActions.setAlignmentX(Component.RIGHT_ALIGNMENT);
        checklistBotActions.setActionCommand("enter");
        checklistBotActions.addActionListener(this);
        createChecklist.setAlignmentX(RIGHT_ALIGNMENT);
        createChecklist.setActionCommand("list");
        createChecklist.addActionListener(this);
        createItemName.setAlignmentX(LEFT_ALIGNMENT);
        createItemName.setActionCommand("name");
        createItemName.addActionListener(this);
        createItem.setAlignmentX(Component.RIGHT_ALIGNMENT);
        createItem.setActionCommand("item");
        createItem.addActionListener(this);
        addItem.setAlignmentX(Component.LEFT_ALIGNMENT);
        addItem.setActionCommand("check");
        addItem.addActionListener(this);
        addItemChecklist.setAlignmentX(Component.LEFT_ALIGNMENT);
        addItemChecklist.setActionCommand("add2");
        addItemChecklist.addActionListener(this);
        viewChecklist.setAlignmentX(Component.RIGHT_ALIGNMENT);
        viewChecklist.setActionCommand("view");
        viewChecklist.addActionListener(this);
        removeItem.setAlignmentX(RIGHT_ALIGNMENT);
        removeItem.setActionCommand("remove");
        removeItem.addActionListener(this);
        JScrollPane scrollPane = new JScrollPane(textArea);

        theLabel = new JLabel() {
            public Dimension getPreferredSize() {
                return new Dimension(600, 600);
            }
            public Dimension getMinimumSize() {
                return new Dimension(600, 600);
            }
            public Dimension getMaximumSize() {
                return new Dimension(600, 600);
            }
        };
        theLabel.setVerticalAlignment(SwingConstants.CENTER);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Input"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        leftPanel.add(scrollPane);
        leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftPanel.add(changeTheLabel);
        leftPanel.add(checklistBotActions);
        leftPanel.add(createChecklist);
        leftPanel.add(createItemName);
        leftPanel.add(createItem);
        leftPanel.add(addItem);
        leftPanel.add(addItemChecklist);
        leftPanel.add(viewChecklist);
        leftPanel.add(removeItem);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        rightPanel.add(theLabel);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);
    }



    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        if (e.getActionCommand().equals("label"))
            setTheLabel(testText);
        if (e.getActionCommand().equals("enter"))
            cbot.cbotActions(textArea.getText());
        if (e.getActionCommand().equals("list"))
            cbot.makeChecklistGui(textArea.getText());
        if (e.getActionCommand().equals("name"))
            cbot.makeItemName(textArea.getText());
        if (e.getActionCommand().equals("item"))
            cbot.makeItem(textArea.getText());
        if (e.getActionCommand().equals("check"))
            cbot.checkCurrentItem();
        if (e.getActionCommand().equals("add2"))
            cbot.addItemChecklist(textArea.getText());
        if (e.getActionCommand().equals("view"))
            cbot.viewChecklist(textArea.getText());
        if (e.getActionCommand().equals("remove"))
            cbot.removeSelectedItem(textArea.getText());
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("ChecklistBot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new UserInterface());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void setTheLabel(String text) {
        theLabel.setText(text);
    }

    public static void main(String args[]) throws IOException, InputMismatchException, NullPointerException {
            reader.readWebPage();
            UserInterface.createAndShowGUI();
            UserInterface.setTheLabel(testText);
            }

}

