package de.dis2023.data;

import de.dis2023.util.Helper;

/**
 * Purchase Contract-Bean
 */
public class PurchaseContract extends Contract {
	private int noOfInstallments;
	private int intrestRate;
	private House house;
	
	public PurchaseContract() {
		super();
	}
	
	public int getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public int getIntrestRate() {
		return intrestRate;
	}
	public void setIntrestRate(int intrestRate) {
		this.intrestRate = intrestRate;
	}
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getNoOfInstallments();
		result = prime * result + getIntrestRate();
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof PurchaseContract))
			return false;
	
		PurchaseContract other = (PurchaseContract)obj;
	
		if(other.getContractNo() != getContractNo() ||
				!Helper.compareObjects(this.getDate(), other.getDate()) ||
				!Helper.compareObjects(this.getPlace(), other.getPlace()) ||
				other.getNoOfInstallments() != getNoOfInstallments() ||
				other.getIntrestRate() != getIntrestRate())
		{
			return false;
		}
		
		return true;
	}
}
