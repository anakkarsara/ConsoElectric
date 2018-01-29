package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.*;
public class JpaTest {
	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.addHomeToPerson(7, test.createHome("kherba", 60, 1, test.getPerson(7)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		test.listHomes();
			
   	 manager.close();
		System.out.println(".. done");
	}
	public void createHomes() {

			manager.persist(new Home("hebss",20, 1, createPerson("walid", "talal", "talal@gmail.com")));
			manager.persist(new Home("shrine",120, 4, createPerson("hirajuma", "kaziku", "kaziku@gmail.com")));
	}
	
	public Home createHome(String myHome, double surface, int numRooms, Person p)
	{
		Home h = new Home(myHome, surface, numRooms, p);
		manager.persist(h);
		return h;
	}
	
	public void createHome(String name){
		Home h =  new Home();
		h.setMyHome(name);
		manager.persist(h);
	}
	
	public Person createPerson(String firstName, String familyName, String mail)
	{
		Person p = new Person(firstName, familyName, mail);
		manager.persist(p);
		return p;
	}
	
	public void addHomeToPerson(long id,Home home){
		Person p  = manager.createQuery("Select a From Person a WHERE id="+ id , Person.class).getSingleResult();
		p.addHome(home);
	}
	
	public void createElectro(String name){
		ElectronicDevice e =  new ElectronicDevice();
		e.setEdName(name);
		manager.persist(e);
	}

	public void createHeater(String name){
		Heater h =  new Heater(name);
		h.sethName(name);
		manager.persist(h);
	}
	
	public void listHomes() {
		List<Home> resultList = manager.createQuery("Select a From Home a", Home.class).getResultList();
		System.out.println("num of homes:" + resultList.size());
		for (Home next : resultList) {
			System.out.println("next home: " + next);
		}
	}
	
	public List<Home> allHomes() {
		return manager.createQuery("Select a From Home a", Home.class).getResultList();
	}
	
	public List<Person> listPersons() {
		return manager.createQuery("Select a From Person a", Person.class).getResultList();
		}
	
	public Person getPerson(long id ){
		return  manager.createQuery("Select a From Person a WHERE id="+id, Person.class).getSingleResult();	
		}
}