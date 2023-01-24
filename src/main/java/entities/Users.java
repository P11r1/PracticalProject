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


    public Users(int age, String name, String email, boolean Admin) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.Admin = Admin;
    }

    public static void listUsers() {

        try {
            session.beginTransaction();
            List<Users> users = session.createQuery("from accounts").list();

            for (Users user : users) {
                System.out.println(user); // Fix
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user you want to delete: ");
        int id = scanner.nextInt();

        session.beginTransaction();
        Transaction trans = session.getTransaction();
        Users users = session.get(Users.class, id);
        try {
            session.delete(users);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static void updateUser() {
        session.beginTransaction();
        Transaction trans = session.getTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user you wish to update");
        int id = scanner.nextInt();

        System.out.println("Enter the full name of the user: ");
        String name = scanner.next();

        System.out.println("Please enter the new age for the user: ");
        int age = scanner.nextInt();

        System.out.println("Please enter the Email of the user: ");
        String email = scanner.next();

        System.out.println("Is the user an admin? Either true or false");
        boolean isAdmin = scanner.nextBoolean();

        Users users = session.get(Users.class, id);
        users.setName(name);
        users.setAge(age);
        users.setEmail(email);
        users.setAdmin(isAdmin);
        try {
            session.merge(users);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }

    }

    public static void createUser() {
        session.beginTransaction();
        Transaction trans = session.getTransaction();

        Users user = testUser();
        try {
            session.persist(user);
            session.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    public static Users testUser() {
        Users user = new Users();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the user full name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the user age: ");
        int age = scanner.nextInt();

        System.out.println("Enter the user email: ");
        String email = scanner.next();

        System.out.println("Is user admin? (true/false)");
        boolean admin = scanner.nextBoolean();

        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setAdmin(admin);
        return user;

    }
}
