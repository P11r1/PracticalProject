package entities;

import db.Database;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

@Entity(name = "projects")
@Data
@NoArgsConstructor
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    static Session session = Database.getHibSesh();

    public Projects(String name) {
        this.name = name;
    }

    public static void listProjects() {

        try {
            session.beginTransaction();
            List<Projects> project = session.createQuery("from projects").list();

            for (Projects projects : project) {
                System.out.println(projects);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
