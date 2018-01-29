package entities;

import javax.persistence.*;

@Entity
public class Heater extends SmartDevice {

	@Id
	@GeneratedValue
	private long id;
	private String hName;
	private int power;
	@ManyToOne
	private Home home;
	
	public Heater() { }

	public Heater(String hName) {
		super();
		this.hName = hName;
	}
	
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	
}