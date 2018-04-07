package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Heater;
import entities.Home;
import entities.Person;

public class HomeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction tx = manager.getTransaction();

	  public Home addHome(String n) {
	    tx.begin();
	    Home h = new Home(n);
	    try {
	      manager.persist(h);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    tx.commit();
	    return h;
	  }
	  
		public List<Home> allHomes() {
			return manager.createQuery("Select a From Home a", Home.class).getResultList();
		}

	  public Home getHomeById(long id) {
	    return manager.createQuery("Select a From Home a where id =" + id, Home.class).getSingleResult();
	  }
	  
	  public Heater getHeaterById(long homeId, long heaterId) {
		    return manager.createQuery("Select a From Heater a where id =" + heaterId+" and home_id = "+homeId, Heater.class).getSingleResult();
		  }

		  public Home getOwnerById(long personId, long homeId) {
		    return manager.createQuery("Select a From Home a where id =" + homeId + " and owner_id = " + personId, Home.class).getSingleResult();
		  }

	  public void deleteById(long id) {
	    Home result = manager.createQuery("Select a From Home a where a.id= '" + id + "'", Home.class).getSingleResult();
	    tx.begin();
	      manager.remove(result);
	      tx.commit();
	    }
	  }

	  public void addHeater(Heater heater, Home home) {
	    tx.begin();
	    home.getHeaters().add(heater);
	    manager.merge(home);
	    tx.commit();
	  }

}
