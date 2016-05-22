package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import rocketDomain.RateDomainModel;
import exceptions.RateException;
public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		ArrayList<RateDomainModel> Rates = RateDAL.getAllRates();
		for(int i = 0; i < Rates.size(); i++){
			if(Rates.get(i).getiMinCreditScore() <= GivenCreditScore){
				return(Rates.get(i).getdInterestRate());
			}
			}
	
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		
		//this is thrown if no rates are found because the credit score was too low
		throw new RateException(null);
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{	
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
