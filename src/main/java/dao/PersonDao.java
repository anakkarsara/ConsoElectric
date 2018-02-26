package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.ElectronicDevice;
import entities.Home;
import entities.Person;

public class PersonDao {
	  EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	  EntityManager manager = factory.createEntityManager();
	  EntityTransaction tx = manager.getTransaction();

	  public Person addPerson(String nom, String prenom, String email) {
	    tx.begin();

	    Person p1 = new Person(nom, prenom, email);
	    try {
	      manager.persist(p1);

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    tx.commit();
	    return p1;
	  }
	  
	  public void addHome(Person person, Home home) {
		    tx.begin();
		    person.getHomes().add(home);
		    manager.merge(person);
		    tx.commit();
		  }

		  public void addED(Person person, ElectronicDevice elec) {
		    tx.begin();
		    person.geteDevices().add(elec);
		    manager.merge(person);
		    tx.commit();
		  }

	  public Person findById(int id) {
	    tx.begin();
	    return manager.find(Person.class, id);
	  }

	  public Person getPersonById(int id) {
	    return manager.createQuery("Select a From Person a where id =" + id, Person.class).getSingleResult();
	  }

	  public void deleteById(int id) {
	    List<Person> resultList = manager.createQuery("Select a From Person a where a.id= '" + id + "'", Person.class).getResultList();
	    for (Person x : resultList) {
	      tx.begin();
	      manager.remove(x);
	      tx.commit();
	    }
	  }
}
