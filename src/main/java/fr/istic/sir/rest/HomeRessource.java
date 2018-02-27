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
import jpa.DAO;

@Path("/home")
public class HomeRessource {
	
	private List<Home> homes = new ArrayList<Home>();
	EntityManagerFactory factory;
	EntityManager manager;
	
	public HomeRessource()
	{
		factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		DAO test = new DAO(manager);
		homes = test.allHomes();
	}
	
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Collection<Home> list() {
        return homes;
    }
    
    @GET 
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Home findById(@PathParam("id") String arg0) {
		DAO test = new DAO(manager);
		homes = test.allHomes();
        return homes.get(Integer.parseInt(arg0));
    }

    @DELETE 
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Home deleteById(@PathParam("id") String arg0) {
    	EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Home h = homes.remove(Integer.parseInt(arg0));
        tx.commit();
        return h;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
}