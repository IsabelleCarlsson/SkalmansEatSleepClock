package com.example.skalmanseatsleepclock;

import org.junit.Test;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testConstructor() {
        ToDoUpdater toDoUpdater = new ToDoUpdater();
        assertEquals("", toDoUpdater.toString());
    }

    @Test
    public void testUpdateToDoText() {
        ToDoUpdater toDoUpdater = new ToDoUpdater();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        gregorianCalendar.set(2019, 1, 1, 13, 20, 5);
        toDoUpdater.setTime(gregorianCalendar);
        toDoUpdater.updateText();
        assertEquals("Time to sleep", toDoUpdater.toString());

        gregorianCalendar.set(1492, 10, 12, 15, 1, 10);
        toDoUpdater.setTime(gregorianCalendar);
        toDoUpdater.updateText();
        assertEquals("Time to eat", toDoUpdater.toString());

        gregorianCalendar.set(1993, 11, 24, 4, 25, 50);
        toDoUpdater.setTime(gregorianCalendar);
        toDoUpdater.updateText();
        assertEquals("Time to drink", toDoUpdater.toString());
    }
}