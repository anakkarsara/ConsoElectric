package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Person {
	
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String familyName;
	private String mail; //testing if working
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Home> homes;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<ElectronicDevice> eDevices;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Person> friends;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

	public List<ElectronicDevice> geteDevices() {
		return eDevices;
	}

	public void seteDevices(List<ElectronicDevice> eDevices) {
		this.eDevices = eDevices;
	}

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public Person(){}
	
	public Person(String firstName, String familyName, String mail) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.mail = mail;
		homes = new ArrayList<Home>();
		friends = new ArrayList<Person>();
		}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void addHome(Home h)
	{
		homes.add(h);
	}
	
	public void addEDevice(ElectronicDevice ed)
	{
		eDevices.add(ed);
	}
	
}