package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	private RateDomainModel RDM;
	
	public RateException(RateDomainModel R){
		this.RDM = R;
	}
	
	public RateDomainModel getRDM(){
		return this.RDM;
	}
	
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
}
