package org.example;

import db.Database;
import entities.Tasks;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = Database.getHibSesh();

        //Tasks tasks = session.find(Tasks.class, 1);
        //System.out.println(tasks);
        // Tasks.deleteTasks(1);
        //Tasks tasks = session.find(Tasks.class, 1);
        // System.out.println(tasks);
        Tasks tasks = new Tasks("suva", "savi", "2022-10-10", true);
        Tasks.createTasks(tasks);
       // Tasks.updateTasks(2, "ns", "midagi", );
    }
}