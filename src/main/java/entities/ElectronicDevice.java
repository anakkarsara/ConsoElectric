package entities;

import javax.persistence.*;

@Entity
public class ElectronicDevice extends SmartDevice {

	@Id
	@GeneratedValue
	private long id;
	private double consoMoy;
	@ManyToOne
	private Home home;
	
	public double getConsoMoy() {
		return consoMoy;
	}
	public void setConsoMoy(double consoMoy) {
		this.consoMoy = consoMoy;
	}
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}
	
}
