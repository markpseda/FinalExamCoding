package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;


import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void Ratetest() {
		double IR = 0;
		try{
			//727 should be an interest rate of 4
			IR = RateBLL.getRate(727);
		} catch (RateException e) {
			e.printStackTrace();
		}
		System.out.println(IR);
		assertEquals(IR, 4.0, .01);
	}
	
	@Test(expected = RateException.class)
	public void RateExceptiontest() {
		try{
			//500 is too low of a credit score
			RateBLL.getRate(500);
			System.out.println("Failed Test, exception should be thrown");
		} catch (RateException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getPaymentTest() {
		double testPayment = RateBLL.getPayment(.04, 360, 300000, 0, true);
		System.out.println(testPayment);
		assertEquals(testPayment, 1432.25, .01);
	}

}
