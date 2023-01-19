package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import timer.Timer;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

@Entity(name = "tasks")
@Data
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "finished")
    private boolean isFinished;

    static Session session = Database.getHibSesh();


    public Tasks(String title, String description, String dueDate, boolean isFinished) {
        this.title = title;
        this.description = description;
        this.dueDate = Date.valueOf(dueDate);
        this.isFinished = isFinished;
    }

    public static void updateTasks() {
        session.beginTransaction();
        Transaction trans = session.getTransaction();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the task id you want do update: ");
        int id = scanner.nextInt();

        System.out.println("Enter the title: ");
        String title = scanner.next();

        System.out.println("Enter the description: ");
        String description = scanner.next();

        System.out.println("Enter the due date: ");
        String dueDate = scanner.next();

        System.out.println("Is the task finished?");
        boolean isFinished = scanner.nextBoolean(); // Fix

        Tasks tasks = session.get(Tasks.class, id);
        tasks.setTitle(title);
        tasks.setDescription(description);
        tasks.setDueDate(Date.valueOf(dueDate));
        tasks.setFinished(isFinished);

        try {
            session.merge(tasks);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }


    }

    public static void deleteTasks() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the task id you want to delete: ");
        int id = scanner.nextInt();

        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Tasks tasks = session.get(Tasks.class, id);
        try {
            session.delete(tasks);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void listTasks() {

        try {
            session.beginTransaction();
            List<Tasks> tasks = session.createQuery("from tasks").list();


            for (Tasks task : tasks) {
                System.out.println(task);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTasks() {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Tasks task = testTask();

        try {
            session.persist(task);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static Tasks testTask() {
        Tasks task = new Tasks();
        List<Tasks> criticalList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the task title: ");
        String title = scanner.nextLine();

        System.out.println("Enter the task description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the due date for the task (yyyy-MM-dd) : ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Is the task finished?");
        boolean isFinished = scanner.nextBoolean(); // Fix

        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(Date.valueOf(dueDate));
        task.setFinished(isFinished);

        java.sql.Date futureDate = java.sql.Date.valueOf(dueDate);
        java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());

        Period period = Period.between(currentDate.toLocalDate(), futureDate.toLocalDate());

        int days = period.getDays();
        int month = period.getMonths();
        int year = period.getYears();

        System.out.println("Number of days between current date and task due date: " + "days: " + days + " " + "months: " + month + " " + "years: " + year);
        if (days <= 4) {
            System.out.println("The task is due soon");
        } else {
            System.out.println("You have time to finish the task");
        }

        return task;
    }



//    public static Tasks addDueDate() {
//        Scanner scanner = new Scanner(System.in);
//        List<Tasks> criticalList = new ArrayList<>();
//
//        System.out.println("Enter the due date for the task (yyyy-MM-dd) : ");
//        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
//
//        Tasks task = testTask(); //get the task object
//        task.setDueDate(Date.valueOf(dueDate)); // set the due date
//
//
//        java.sql.Date futureDate = java.sql.Date.valueOf(dueDate);
//        java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
//
//        Period period = Period.between(currentDate.toLocalDate(), futureDate.toLocalDate());
//
//        int days = period.getDays();
//        int month = period.getMonths();
//        int year = period.getYears();
//
//        System.out.println("Number of days between current date and task due date: " + "days: " + days + "months: " + month + "years: " + year);
//        if (days <= 4) {
//            criticalList.add(task);
//            System.out.println(task);
//            System.out.println("The task is due soon");
//
//        } else {
//            System.out.println("You have time to finish the task");
//        }
//
//        try {
//            session.beginTransaction();
//            session.saveOrUpdate(task);
//            session.getTransaction().commit();
//            System.out.println("Task due date added successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return task;
//    }

    public static void criticalTasks() {
        Query query = session.createQuery("FROM tasks WHERE dueDate < current_date + 4");
        List dueDates = query.getResultList();
        dueDates.forEach(System.out::println);
        System.out.println("These tasks must be finished before 4 days!");
        // We should now take the elements from the list and compare them to current date, if less than 4 days then
        // add to critical list and print out these
    }

}






