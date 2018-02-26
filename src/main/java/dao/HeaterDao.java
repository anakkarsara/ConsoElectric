package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Heater;
import entities.Home;

public class HeaterDao {
	  EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	  EntityManager manager = factory.createEntityManager();
	  EntityTransaction tx = manager.getTransaction();
	
	  public Heater createHeater(String name, int power) {
	    Heater heater = new Heater(name, power);
	    tx.begin();
	    try {
	      manager.persist(heater);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    tx.commit();
	    return heater;
	  }
	  
		public List<Heater> allHeaters() {
			return manager.createQuery("Select a From Heater a", Heater.class).getResultList();
		}
	
	
	  public Heater getHeaterById(long id) {
	    return manager.createQuery("Select a From Heater a where id =" + id, Heater.class).getSingleResult();
	  }
	
	  public void delete(long id) {
	    tx.begin();
	    try {
	      manager.remove(getHeaterById(id));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    tx.commit();
	  }

}
