package entities;

import java.util.*;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;


@Entity
public class Home {
	//test
	@Id
	@GeneratedValue
	private long id;
	private String myHome;
	private double surface;
	private int numRooms;
	
	@ManyToOne
	@JsonBackReference
	private Person owner;
	
	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Heater> heaters;
	
	public Home(){
		heaters = new ArrayList<Heater>();
	}
	
	public Home(String myHome) {
		super();
		this.myHome = myHome;
	}

	public Home(String myHome, double surface, int numRooms, Person p) {
		super();
		this.myHome = myHome;
		this.surface = surface;
		this.numRooms = numRooms;
		this.owner = p;
		heaters = new ArrayList<Heater>();
	}
	public String getMyHome() {
		return myHome;
	}

	public void setMyHome(String myHome) {
		this.myHome = myHome;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Heater> getHeaters() {
		return heaters;
	}

	public void setHeaters(List<Heater> heaters) {
		this.heaters = heaters;
	}

	public void addHeater(Heater h)
	{
		heaters.add(h);
	}
	
}