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

import entities.Person;
import jpa.DAO;

@Path("/person")
public class PersonRessource {
	
	private List<Person> persons = new ArrayList<Person>();
	EntityManagerFactory factory;
	EntityManager manager;
	
	public PersonRessource()
	{
		factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		DAO test = new DAO(manager);
		persons = test.listPersons();
	}
	
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Collection<Person> list() {
        return persons;
    }
    
    @GET 
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Person findById(@PathParam("id") String arg0) {
        return persons.get(Integer.parseInt(arg0));
    }

    @DELETE 
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Person deleteById(@PathParam("id") String arg0) {
    	EntityTransaction tx = manager.getTransaction();
    	tx.begin();
        Person p = persons.remove(Integer.parseInt(arg0));
        tx.commit();
        return p;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
}