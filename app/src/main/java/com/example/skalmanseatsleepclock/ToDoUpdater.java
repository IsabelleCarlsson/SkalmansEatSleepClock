package com.example.skalmanseatsleepclock;

import java.util.GregorianCalendar;

public class ToDoUpdater {
    private String toDoText;
    private GregorianCalendar gregorianCalendar;

    public ToDoUpdater() {
        toDoText = "";
        gregorianCalendar = new GregorianCalendar();
    }

    public void updateText() {
        int minutes = gregorianCalendar.get(GregorianCalendar.MINUTE);

        // Update Time To Do text according to analog clock dials
        if ((minutes >= 7 && minutes <= 12) || (minutes >= 17 && minutes <= 21) ||
            (minutes >= 27 && minutes <= 33) || (minutes >= 38 && minutes <= 42) ||
            (minutes >= 48 && minutes <= 55)) {
            toDoText = "Time to sleep";
        } else if ((minutes >= 0 && minutes <= 6) || (minutes >= 13 && minutes <= 16) ||
                   (minutes >= 34 && minutes <= 37) || (minutes >= 56 && minutes <= 59)) {
            toDoText = "Time to eat";
        } else {
            toDoText = "Time to drink";
        }
    }

    @Override
    public String toString() {
        return toDoText;
    }

    public void setTime(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }
}