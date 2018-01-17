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
	private String mail;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	private List<Home> homes;
	
	@ManyToMany
	private List<Person> friends;
	
	public Person(){}
	
	public Person(String firstName, String familyName, String mail) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.mail = mail;
		homes = new ArrayList<Home>();
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
	
	
}
