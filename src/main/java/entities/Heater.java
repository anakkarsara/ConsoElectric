package entities;

import javax.persistence.*;

@Entity
public class Heater extends SmartDevice {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Home home;
	
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}
	
	
}
