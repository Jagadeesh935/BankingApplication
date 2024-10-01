package com.jk;

public class SavingsAccount extends Account {
	private double interestRate = 3;
	
	public SavingsAccount(Customer customer, String accountType, String pin) {
		super(customer, accountType, pin);
	}

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		
		return this.getBalance() * (this.interestRate/100);
	}


	public void getAccountDetails() {
		super.getAccountDetails();
		System.out.println("    Interest Rate  : " + this.interestRate);
	}
	
	
}
