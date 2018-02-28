package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PersonDao;
import entities.Person;
import jpa.DAO;

@Path("/person")
public class PersonRessource {
	
	private List<Person> persons = new ArrayList<Person>();
	EntityManagerFactory factory;
	EntityManager manager;
	private PersonDao pdao = new PersonDao();
	
	public PersonRessource()
	{
		factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		DAO dao = new DAO(manager);
		persons = pdao.allPersons();
	}
	
    private Response response(Object o){
        return Response.ok(o).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(Person p){
        this.pdao.addPerson(p);
        return this.list();
    }
	
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response list() {
        return this.response(this.pdao.allPersons());
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
    public Person deleteById(@PathParam("id") String id) {
    	EntityTransaction tx = manager.getTransaction();
    	tx.begin();
        Person p = persons.remove(Integer.parseInt(id));
        tx.commit();
        return p;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
}