package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Entity(name = "accounts")
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    @Column(name = "age")
    private int age;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "admin")
    private boolean Admin;

    static Session session = Database.getHibSesh();

    Scanner scanner = new Scanner(System.in);

    public Users(int age, String name, String email, boolean isAdmin) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.Admin = Admin;
    }

    public static void updateUser(int id, String title, String description, String dueDate) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Users user = session.get(Users.class, id); // correction by title
        try {
            session.merge(user);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Users user = session.get(Users.class, id); // correction by title
        try {
            session.delete(user);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void listUser() {

        try {
            session.beginTransaction();
            List<Users> user = session.createQuery("from accounts").list();

            for (Users users : user) {
                System.out.println(users);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createUser(Users user) {
        session.beginTransaction();
        Transaction trans = session.getTransaction();
        try {
            session.persist(user);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }
}
