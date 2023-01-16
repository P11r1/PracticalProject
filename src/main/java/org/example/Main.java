package org.example;

import db.Database;
import entities.Tasks;
import entities.menu.Menu;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = Database.getHibSesh();
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

    }
}