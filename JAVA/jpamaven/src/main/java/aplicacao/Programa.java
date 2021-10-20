package aplicacao;

import dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {
    public static void main(String[] args) {
        pesquisarById(1);
        excluirById(1);
    }

    public static void pesquisarById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMaven");
        EntityManager em = emf.createEntityManager();

        Pessoa p = em.find(Pessoa.class, id);
        System.out.println(p);

        em.close();
        emf.close();
    }

    public static void salvar(Pessoa pessoa) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMaven");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Necessários para operações exceto consulta
        em.persist(pessoa);
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.println("Pronto");
    }
    public static void excluirById(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaMaven");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = em.find(Pessoa.class, id);
        em.getTransaction().begin();
        em.remove(pessoa);
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.println("Pronto");
    }
}
