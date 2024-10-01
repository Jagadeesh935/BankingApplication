package com.jk;

public class CurrentAccount extends Account {
	
	private int overdraftLimit;
	
	public boolean checkOverdraft() {
		if (this.getBalance() < 0) {
			return true;
		}
		return false;
	}

	public CurrentAccount(Customer customer, String accountType, String pin) {
		super(customer, accountType, pin);
	}

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return this.getBalance() * (1/100);
	}
	
}
