package application;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Pessoa;

public class Program {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Scanner sc;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		em = emf.createEntityManager();

		sc = new Scanner(System.in);
		String op = "0";
		while (op != "9") {
			System.out.println(
					"CRUD JPA" + "\n1 - Insert" + "\n2 - Update" + "\n3 - Find" + "\n4 - Delete" + "\n\n9 - Exit");
			System.out.print("Your choice: ");
			sc.nextLine();
			op = sc.nextLine();

			switch (op) {
			case "1":
				insert();
				break;
			case "2":
				update();
				break;
			case "3":
				find();
				break;
			case "4":
				delete();
				break;
			}
		}

		System.out.println("Exit program!");
		em.close();
		emf.close();
		sc.close();
	}

	private static void insert() {
		// Insert
		System.out.println("*****************INSERT*********************");

		if (em == null) {
			throw new IllegalArgumentException("Entity Manager was null");
		}
		System.out.print("Input name: ");
		String name = sc.nextLine();
		System.out.print("Input email: ");
		String email = sc.nextLine();

		Pessoa p1 = new Pessoa(null, name, email);

		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
		System.out.println(p1);
	}

	private static void update() {

		// update
		System.out.println("********UPDATE********");

		if (em == null) {
			throw new IllegalArgumentException("Entity Manager was null");
		}

		Pessoa p1 = find();
		sc.nextLine();
		System.out.print("Input new name: ");
		String name = sc.nextLine();
		System.out.print("Input new email: ");
		String email = sc.nextLine();

		p1.setName(name);
		p1.setEmail(email);

		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
		System.out.println(p1);

	}

	private static void delete() {
		// Delete
		System.out.println("********DELETE********");

		if (em == null) {
			throw new IllegalArgumentException("Entity Manager was null");
		}

		Pessoa p1 = find();

		em.getTransaction().begin();
		em.remove(p1);
		em.getTransaction().commit();
		if(p1!= null) {
			System.out.println("Removed: " + p1);
		}

	}

	private static Pessoa find() {
		// Find
		System.out.println("********FIND********");

		if (em == null) {
			throw new IllegalArgumentException("Entity Manager was null");
		}

		System.out.println("Input person ID: ");
		int id = sc.nextInt();
		
		Pessoa p1 = em.find(Pessoa.class, id);
		System.out.println(p1);
		return p1;
	}
}
