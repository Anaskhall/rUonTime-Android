/**
 * 
 */
package se.kth.mobdev.ruontime.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
@Entity
public class Digest implements IEntity{


	private static final long serialVersionUID = 1917127002030900092L;

	@Id
	private UUID id;
	
	public Digest(List<CheckIn> checkIns){
		//here we extract the wanted kind of statistical information from the check ins
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

}
