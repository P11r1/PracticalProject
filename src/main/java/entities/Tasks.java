package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

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

    public static void updateTasks(int id, String title, String description, Date dueDate) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Tasks tasks = session.get(Tasks.class, id);
        try {
            session.merge(tasks);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }

    }

    public static void deleteTasks(int id) {
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


}


