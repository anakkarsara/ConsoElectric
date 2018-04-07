package entities;

import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class ElectronicDevice extends SmartDevice {

	@Id
	@GeneratedValue
	private long id;
	private String edName;
	private double consoMoy;
	@ManyToOne
	@JsonBackReference
	private Person owner;
	
	public double getConsoMoy() {
		return consoMoy;
	}
	public ElectronicDevice () {
	}
	public ElectronicDevice (String edName, double consoMoy, Person owner) {
		this.edName = edName;
		this.consoMoy = consoMoy;
		this.owner = owner;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public String getEdName() {
		return edName;
	}
	public void setEdName(String edName) {
		this.edName = edName;
	}
	public void setConsoMoy(double consoMoy) {
		this.consoMoy = consoMoy;
	}


	
}
