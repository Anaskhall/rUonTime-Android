package se.kth.mobdev.ruontime.backend;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class WelcomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public WelcomeBean() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}