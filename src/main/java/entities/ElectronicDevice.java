package entities;

import javax.persistence.*;

@Entity
public class ElectronicDevice extends SmartDevice {

	@Id
	@GeneratedValue
	private long id;
	private String edName;
	private double consoMoy;
	@ManyToOne
	private Person owner;
	
	public double getConsoMoy() {
		return consoMoy;
	}
	public ElectronicDevice () {
	}
	public ElectronicDevice (String edName, double consoMoy) {
		this.edName = edName;
		this.consoMoy = consoMoy;
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