package timer;

import java.time.Period;

public class Timer {
    public static void main(String[] args) {
        //  Users should be able to set due dates for tasks
        //  and receive reminders when a task is coming due.

        java.sql.Date futureDate = java.sql.Date.valueOf("2031-11-12");
        java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());

        Period period = Period.between(currentDate.toLocalDate(), futureDate.toLocalDate());

        int days = period.getDays();

        System.out.println("Number of days between current date and Task due date: " + days);
    }
}