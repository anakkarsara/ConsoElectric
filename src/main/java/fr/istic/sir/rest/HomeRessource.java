package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.*;
import jpa.JpaTest;

@Path("/homes")
public class HomeRessource {
	
	private List<Home> homes = new ArrayList<Home>();
	
	public HomeRessource()
	{
		
	}
	
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Collection<Home> list() {
        return homes;
    }
    
    @GET 
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Home findById(@PathParam("id") String arg0) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		homes = test.allHomes();
		tx.commit();
 		manager.close();
        return homes.get(Integer.parseInt(arg0));
    }

    @DELETE 
    @Path("delete/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Home deleteById(@PathParam("id") String arg0) {
        return homes.remove(Integer.parseInt(arg0));
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
}