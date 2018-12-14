package test;

import model.NothingToSaveException;
import model.NumberTooLargeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WordBot;
import ui.NumberBot;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BotTest {
    private WordBot wordBot;
    private NumberBot numBot;

    @BeforeEach
    public void runBefore() {
        wordBot = new WordBot();
        numBot = new NumberBot();
    }

    @Test
    public void testAddWord() {
        wordBot.insert("hi");
        assertTrue(wordBot.contains("hi"));
    }

    @Test
    public void testAddMultipleWords() {
        wordBot.insert("hi");
        wordBot.insert("dog");
        assertTrue(wordBot.contains("hi"));
        assertTrue(wordBot.contains("dog"));
        assertEquals(2, wordBot.size());
    }

    @Test
    public void testAddNumber() {
        wordBot.insert("20");
        assertTrue(wordBot.contains("20"));
    }

    @Test
    public void testGetList() {
        assertEquals(0, wordBot.size());
    }

    @Test
    public void testRemove() {
        wordBot.insert("alice");
        wordBot.remove("alice");
        assertEquals(0, wordBot.size());
    }

    @Test
    public void testRemoveNone() {
        wordBot.insert("alice");
        wordBot.remove("dog");
        assertEquals(1, wordBot.size());
    }

    @Test
    public void testAddAll() {
        numBot.insert(5);
        numBot.insert(4);
        numBot.insert(10);
        assertEquals(19, numBot.addAll());
    }

    @Test
    public void testNumberTooLargeExceptionPass() {
        numBot.numbers.add(25);
        try {
            numBot.remove(10);
            fail("This should not happen");
        } catch (NumberTooLargeException n) {
            System.out.println("The exception was caught. ");
        }
    }

    @Test
    public void testNumberTooLargeExceptionFail() {
        numBot.numbers.add(25);
        numBot.numbers.add(30);
        try {
            numBot.remove(1);
        } catch (NumberTooLargeException e) {
            fail("This exception should not be caught. ");
        }
    }

    @Test
    public void testNothingToSaveExceptionPass() {
        try {
            wordBot.save();
            fail("This should not happen. ");
        } catch (NothingToSaveException e) {
            System.out.println("Exception has been caught. ");
        } catch (IOException i) {
        }
    }

    @Test
    public void testNothingToSaveExceptionFail() {
        wordBot.insert("dog");
        try {
            wordBot.save();
        } catch (IOException i) {
        } catch (NothingToSaveException e) {
            fail("This exception should not be caught. ");
        }
    }
}