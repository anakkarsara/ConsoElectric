package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.ElectronicDevice;

public class ElectronicDeviceDao {
	 		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
			  EntityManager manager = factory.createEntityManager();
			  EntityTransaction tx = manager.getTransaction();

			  public void createED(String name, double conso)  {
			    tx.begin();
			    ElectronicDevice elec = new ElectronicDevice(name, conso) ;
			    try {
			      manager.persist(elec);
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			    tx.commit();
			  }

			  public void deleteED(long id) {
			    tx.begin();
			    try {
			      manager.remove(getEDById(id));
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			    tx.commit();
			  }
			  
				public List<ElectronicDevice> allEDs() {
					return manager.createQuery("Select a From ElectronicDevice a", ElectronicDevice.class).getResultList();
				}

			  public ElectronicDevice findById(long id) {
			    return manager.find(ElectronicDevice.class, id);
			  }


			  public ElectronicDevice getEDById(long id) {
			    return manager.createQuery("Select a From ElectronicDevice a where id =" + id, ElectronicDevice.class).getSingleResult();
			  }

}
