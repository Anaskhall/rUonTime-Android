/**
 * 
 */
package se.kth.mobdev.ruontime.persistence.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
public class Digest implements IEntity{


	private static final long serialVersionUID = 1917127002030900092L;

	@Id
	private Integer id;
	
	public Digest(List<CheckIn> checkIns){
		//here we extract the wanted kind of statistical information from the check ins
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
