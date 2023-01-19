package org.example;

import entities.Tasks;
import entities.menu.Menu;

import java.util.Calendar;
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
          Menu.chooseAccount();
        // Tasks.listTasks();
       // Tasks.saodgi();
       // Tasks.getDueDate();


//        Timer timer = new Timer();
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Check due date if method here");
//            }
//        };
//
//        Calendar date = Calendar.getInstance();
//        date.set(Calendar.HOUR,12);
//        date.set(Calendar.MINUTE, 0);
//        date.set(Calendar.SECOND,0);
//
//       // timer.schedule(task, 1000);
//        timer.schedule(task, date.getTime());



    }
}