package test;
import model.NothingToSaveException;
import model.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WordBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveLoadTest {
    private WordBot wordBot;

    @BeforeEach
    public void runBefore() {
        wordBot = new WordBot();
    }

    @Test
    public void testLoad() throws IOException, NothingToSaveException {
        wordBot.insert("cheese");
        wordBot.save();
        wordBot.remove("cheese");
        testOptionsLoad(wordBot);
    }

    @Test
    public void testSave() throws IOException, NothingToSaveException {
        wordBot.insert("chicken");
        wordBot.save();
        ArrayList<String> test = new ArrayList<>();
        Scanner scan = new Scanner(new File("wordlist.txt"));
        while (scan.hasNextLine()) {
            test.add(scan.nextLine());
        }
        assertTrue(test.contains("chicken"));
    }

    public void testOptionsLoad(Options o) throws FileNotFoundException {
      o = new WordBot();
       o.load();
    }

    public void testOptionsSave(Options o) throws IOException, NothingToSaveException {
        o = new WordBot();
        o.save();
    }
}
