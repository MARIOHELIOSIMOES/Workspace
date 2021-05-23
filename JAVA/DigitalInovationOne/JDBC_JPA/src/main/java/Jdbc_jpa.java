import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Jdbc_jpa {
    private static String URL_CONECTION = "jdbc:mysql://localhost/dio";
    private static String USER = "mario";
    private static String PASSWORD = "2108";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part1-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Aluno novo = new Aluno("mario JPA", 28, "SP");
        entityManager.getTransaction().begin();
        entityManager.persist(novo);
        entityManager.getTransaction().commit();


        /*
        List<Aluno> alunos = new ArrayList<>();
        alunos = new AlunoDAO().list();
        for(Aluno a : alunos){
            System.out.printf("%d - nome: %s, idade: %d, estado: %s\n", a.getId(), a.getNome(), a.getIdade(), a.getEstado());
        }
        Aluno novo = new Aluno("mario", 29, "SP");
        new AlunoDAO().createAluno(novo);


         */
    }
}
