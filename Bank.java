package com.jk;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Customer> customers = new ArrayList<>();
	private List<Account> accounts = new ArrayList<>();
	private List<Transaction> transactions = new ArrayList<>();

	public Customer createCustomer(String name, String email, String phoneNumber, String address, String password) {
		Customer customer = new Customer(name, email, phoneNumber, address, password);
		customers.add(customer);
		System.out.println("New customer created");
		return customer;
	}

	public Account createAccount(Customer customer, String accountType, String pin) {
		Account account;
		if (accountType.equalsIgnoreCase("savings")) {
			account = new SavingsAccount(customer, accountType, pin);
		} else {
			account = new CurrentAccount(customer, accountType, pin);
		}
		accounts.add(account);
		System.out.println(accountType + " account created");
		return account;
	}

	public Customer findCustomer(int customerId) {
		for (Customer c : customers) {
			if (c.getCustomerId() == customerId)
				return c;
		}
		return null;
	}

	public Account findAccount(int accountNumber) {
		for (Account a : accounts) {
			if (a.getAccountNumber() == accountNumber)
				return a;
		}
		return null;
	}

	public void deleteCustomer(Customer currentUser) {
		customers.remove(currentUser);
	}

	public void deleteAccount(int accountNumber) {
		for (Account a : accounts) {
			if (a.getAccountNumber() == accountNumber) {
				accounts.remove(a);
				break;
			}
		}
	}

	public Transaction deposit(int accountNumber, int amount, String transactionType) {
		Account account = this.findAccount(accountNumber);
		Transaction t = new Transaction(account, null, amount, transactionType);
		t.depositTransaction();
		System.out.println(t.getTransactionDetails());
		this.transactions.add(t);
		return t;
	}

	public Transaction withdraw(int accountNumber, int amount, String transactionType, String pin) {
		Account account = this.findAccount(accountNumber);
		Transaction t = new Transaction(account, null, amount, transactionType);
		t.withdrawTransaction(pin);
		System.out.println(t.getTransactionDetails());
		this.transactions.add(t);
		return t;
	}

	public void fundTransfer(int from, int to, int amount, String pin) {
		// TODO Auto-generated method stub
		Account faccount = this.findAccount(from);
		Account taccount = this.findAccount(to);
		Transaction t = new Transaction(faccount, taccount, amount, "Online transfer");
		t.createTransaction(pin);
		this.transactions.add(t);
		System.out.println(t.getTransactionDetails());

	}

	public void displayTransactionDetails(int id) {
		// TODO Auto-generated method stub
		for (Transaction t : transactions) {
			if (t.getTransactionId() == id) {
				System.out.println(t.getTransactionDetails());
				break;
			}
		}
	}

	public void viewTransactions(int accountNumber) {
		// TODO Auto-generated method stub
		System.out.println("  ID     From       To      Amount           Type                 Status          ");
		System.out.println("------------------------------------------------------------------------------------");
		for (Transaction t : transactions) {
			if (t.getFromAccount().getAccountNumber() == accountNumber) {
				System.out.printf("\n%-6d %-10d %-10s %10d %-20s %-20s", t.getTransactionId(),
						t.getFromAccount().getAccountNumber(), (String) null, t.getAmount(), t.getTransactionType(),
						t.getTransactionStatus());
			} else if (t.getToAccount() != null) {
				if (t.getToAccount().getAccountNumber() == accountNumber) {
					System.out.printf("\n%-6d %-10d %-10d %10d %-20s %-20s ", t.getTransactionId(),
							t.getFromAccount().getAccountNumber(), t.getToAccount().getAccountNumber(), t.getAmount(),
							t.getTransactionType(), t.getTransactionStatus());
				}
			}
		}
	}

}
