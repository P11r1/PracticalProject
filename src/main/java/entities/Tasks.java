package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
        boolean isFinished = scanner.hasNext();

        Tasks tasks = session.get(Tasks.class, id); // correction by title
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
        Tasks tasks = session.get(Tasks.class, id); // correction by title
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

    public static void createTasks(Tasks tasks) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        try {
            session.persist(tasks);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void dueMethod() {
        long currentTime = System.currentTimeMillis();
        long plus47Hours = currentTime + (47 * 60 * 60 * 1000);
        Timestamp plus47HoursTS = new Timestamp(plus47Hours);

        long plus48Hours = currentTime + (48 * 60 * 60 * 1000);
        Timestamp plus48HoursTS = new Timestamp(plus48Hours);

        Query query = session.createQuery("from GroupNotes as gn where gn.zugwisenPersonId!=:val and gn.timeToend > :from and gn.timeToend < :to");
        query.setParameter("from", plus47HoursTS);
        query.setParameter("to", plus48HoursTS);
    }
}


