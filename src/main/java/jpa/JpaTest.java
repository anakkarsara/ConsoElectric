package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Home;
import entities.Person;
import test.testjpa.domain.Employee;
public class JpaTest {
	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =   
 			 Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createHomes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		test.listHomes();
			
   	 manager.close();
		System.out.println(".. done");
	}
	private void createHomes() {
		int numOfEmployees = manager.createQuery("Select a From Home a", Home.class).getResultList().size();
		if (numOfEmployees == 0) {
			Person person = new Person("sara", "anakkar", "anakkar.sara@gmail.com");
			manager.persist(person);
			manager.persist(new Home(63, 3, person));
			manager.persist(new Home(50, 2, person));
		}
	}
	private void listHomes() {
		List<Home> resultList = manager.createQuery("Select a From Home a", Home.class).getResultList();
		System.out.println("num of homes:" + resultList.size());
		for (Home next : resultList) {
			System.out.println("next home: " + next.getSurface());
		}
	}
}
