package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
     //   Session session = Database.getHibSesh();
        // Tasks tasks = session.find(Tasks.class, 4);
        // System.out.println(tasks);
        //Tasks.deleteTasks();
        //Tasks tasks = session.find(Tasks.class, 1);
        // System.out.println(tasks);
        //Tasks tasks = new Tasks("suva", "savi", "2022-10-10", true);
        // Tasks.createTasks(tasks);
        // Tasks.updateTasks(2, "ns", "midagi", );
        // Tasks.viewTasks("suva", "savi", "2022-10-10", true);
        //  Menu.chooseAccount();
        // Tasks.listTasks();


        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Check due date if method here");
            }
        };
        timer.schedule(task, 1000);


    }
}