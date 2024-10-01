package com.jk;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private static int idTracker = 1;
	private int customerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String password;
	private List<Account> accounts = new ArrayList<>();

	public Customer(String name, String email, String phoneNumber, String address, String password) {
		this.customerId = idTracker++;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setPassword(password);
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public void removeAccount(Account account) {
		accounts.remove(account);
	}

	
	public void displayCustomerDetails() {
		System.out.println("Details\n" + "--------\n" + "    Name           : " + name + "\n" + "    Id             : " + customerId + "\n"
				+ "    Email          : " + email + "\n" + "    Phone          : " + phoneNumber + "\n" + "    Address        : "
				+ address + "\n" + "    Password       : " + this.password + "\n");
		
		this.accounts.forEach(account -> account.getAccountDetails());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
