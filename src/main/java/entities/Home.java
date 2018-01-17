package entities;

import java.util.*;

import javax.persistence.*;


@Entity
public class Home {
	
	@Id
	@GeneratedValue
	private long id;
	private double surface;
	private int numRooms;
	
	@ManyToOne
	private Person owner;
	
	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
	private List<Heater> heaters;
	
	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
	private List<ElectronicDevice> eDevices;
	
	public Home(){}
	
	public Home(double surface, int numRooms, Person p ) {
		super();
		this.surface = surface;
		this.numRooms = numRooms;
		this.owner = p;
		heaters = new ArrayList<Heater>();
		eDevices = new ArrayList<ElectronicDevice>();
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

	
}
