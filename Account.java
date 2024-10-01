package com.jk;

public abstract class Account {
	private static int accountNumberTracker=1;
	private int accountNumber;
	private int balance;
	private Customer customer;
	private String accountType;
	private String pin;
	
	
	
	protected Account(Customer customer, String accountType, String pin) {
		this.accountNumber = accountNumberTracker++;
		this.customer = customer;
		this.accountType = accountType;
		this.pin = pin;
	}
	
	

	public String getPin() {
		return pin;
	}



	public void setPin(String pin) {
		this.pin = pin;
	}



	public void deposit(int amount) {
		balance += amount;
	}
	
	public boolean isBalanceAvailable(int amount) {
		if(this.balance < amount) return false;
		return true;
	}
	
	public String withdraw(int amount, String pin) {
		if (this.pin.equals(pin)) {
			if (this.isBalanceAvailable(amount)) {
				this.balance -= amount;
				System.out.println("Successfully withdrawn");
				return "successful";
			} else {
				System.out.println("insufficient balance");
				return "insufficient balance";
			}
		} else {
			System.out.println("Invalid pin");
		}
		return "invalid pin";
	}

	
	
	public abstract double calculateInterest();

	public int getAccountNumber() {
		return accountNumber;
	}
	

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void getAccountDetails() {
		// TODO Auto-generated method stub
		System.out.println(this.accountType + " account:\n" + 
							"-----------------\n"+
							"    Account Number : " + this.accountNumber + "\n" + 
							"    Balance        : " + this.balance);
	}
	
	
	
	
}
