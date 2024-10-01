package com.jk;

import java.util.Scanner;

public class Main {

	private static Customer currentUser;
	private static Scanner sc = new Scanner(System.in);
	private static Bank bank = new Bank();

	public static void main(String[] args) {

		Customer ram = bank.createCustomer("Ram", "ram", "4584575545", "cbe", "ram");
		Account ramaccount = bank.createAccount(ram, "savings", "123");
		ram.addAccount(ramaccount);
		System.out.println("Registration successful");
		ram.displayCustomerDetails();

		Customer sam = bank.createCustomer("Sam", "sam", "845875458", "cbe", "sam");
		Account samaccount = bank.createAccount(sam, "current", "456");
		sam.addAccount(samaccount);
		System.out.println("Registration successful");
		sam.displayCustomerDetails();

		bank.deposit(1, 5000, "Bank deposit");
		bank.deposit(2, 10000, "Bank deposit");
		bank.withdraw(1, 2000, "ATM withdraw", "123");
		bank.withdraw(1, 5000, "ATM withdraw", "123");
		bank.withdraw(2, 3000, "ATM withdraw", "456");
		bank.fundTransfer(2, 1, 2000, "456");
		bank.viewTransactions(2);

		while (true) {
			displayLR();
			System.out.print("===>");
			if (sc.nextInt() == 1) {
				if (login()) {
					while (currentUser != null) {
						displayHome();
						System.out.print("====>");
						switch (sc.nextInt()) {
						case 1:
							deposit();
							break;
						case 2:
							withdraw();
							break;
						case 3:
							fundTransfer();
							break;
						case 4:
							displayCustomerInfo();
							break;
						case 5:
							viewTransacitonDetails();
							break;
						case 6:
							viewTransactionHistory();
							break;
						case 7:
							addAccount();
							break;
						case 8:
							deleteAccount();
							break;
						case 9:
							deleteCustomer();
							break;
						case 10:
							logout();
							break;
						}
					}
				}
			} else {
				register();
			}
		}

	}

	private static void logout() {
		// TODO Auto-generated method stub
		System.out.println("Are you sure you want to logout.(y/n)");
		if (sc.next().equals("y")) {
			currentUser = null;
		}
	}

	private static void deleteCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Are you sure you want ot delete. (y/n)");
		if (sc.next().equals("y")) {
			bank.deleteCustomer(currentUser);
			currentUser = null;
		}
	}

	private static void deleteAccount() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the account number:");
		int accountNum = sc.nextInt();
		for (Account a : currentUser.getAccounts()) {
			if (a.getAccountNumber() == accountNum) {
				currentUser.getAccounts().remove(a);
				System.out.println("Account removed");
				break;
			}
		}
	}

	private static void addAccount() {
		// TODO Auto-generated method stub
		String accountType = getAccountTypeChoice();
		System.out.println("Set your account pin");
		Account account = bank.createAccount(currentUser, accountType, sc.next());
		currentUser.addAccount(account);

	}

	private static void viewTransactionHistory() {
		// TODO Auto-generated method stub
		int accountNumber;
		if (currentUser.getAccounts().size() > 1) {
			System.out.println("You have more than 1 account. Please enter the account number");
			accountNumber = sc.nextInt();
		} else {
			accountNumber = currentUser.getAccounts().get(0).getAccountNumber();
		}
		bank.viewTransactions(accountNumber);
	}

	private static void viewTransacitonDetails() {
		// TODO Auto-generated method stub
		System.out.println("Enter Transaction id");
		bank.displayTransactionDetails(sc.nextInt());
	}

	private static void displayCustomerInfo() {
		// TODO Auto-generated method stub
		currentUser.displayCustomerDetails();
	}

	private static void fundTransfer() {
		// TODO Auto-generated method stub
		System.out.println("Enter From account number:");
		int from = sc.nextInt();
		System.out.println("Enter to account number:");
		int to = sc.nextInt();
		System.out.println("Enter the amount:");
		int amount = sc.nextInt();
		System.out.println("Enter your pin");
		;
		String pin = sc.next();
		bank.fundTransfer(from, to, amount, pin);
	}

	private static void withdraw() {
		// TODO Auto-generated method stub
		System.out.println("Enter account number");
		int accountNumber = sc.nextInt();
		System.out.println("Enter the amount");
		int amount = sc.nextInt();
		System.out.println("Enter your pin");
		String pin = sc.next();
		bank.withdraw(accountNumber, amount, "ATM withdraw", pin);
	}

	private static void deposit() {
		System.out.println("Enter account number : ");
		int accountnumber = sc.nextInt();
		System.out.println("Enter amount : ");
		int amount = sc.nextInt();
		System.out.println("Enter Transaction type");
		sc.nextLine();
		String type = sc.nextLine();
		bank.deposit(accountnumber, amount, type);
	}

	private static void displayHome() {
		System.out.println("""
				Home
				----
				1  -> Deposit
				2  -> Withdraw
				3  -> Transfer amount
				4  -> Display Customer info
				5  -> View Transaction Details
				6  -> View Transaction History
				7  -> Add account
				8  -> Delete account
				9  -> Delete customer
				10 -> logout
						""");
	}

	private static boolean login() {
		System.out.print("Enter customer id:");
		int customerId = sc.nextInt();
		System.out.println("Enter your password");
		String password = sc.next();
		Customer customer = bank.findCustomer(customerId);
		if (customer != null) {
			if (customer.getPassword().equals(password)) {
				System.out.println("Login Successful");
				System.out.println("Hello " + customer.getName());
				currentUser = customer;
				return true;
			} else {
				System.out.println("Invalid password");
				return false;
			}
		}
		System.out.println("Customer id doesn't exist");
		return false;
	}

	private static String getAccountTypeChoice() {
		System.out.print("""
				Choose your account type:
				    1 -> Savings
				    2 -> current
						""");
		if (sc.nextInt() == 1)
			return "savings";
		else
			return "current";
	}

	private static void register() {
		System.out.print("Enter your name:");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("Enter your email:");
		String email = sc.next();
		System.out.print("Enter your phone number:");
		String phone = sc.next();
		System.out.print("Enter your address:");
		String address = sc.next();
		String accountType = getAccountTypeChoice();
		System.out.print("Set your password: ");
		String password = sc.next();
		System.out.println("Set your account pin");
		String pin = sc.next();

		Customer customer = bank.createCustomer(name, email, phone, address, password);
		Account account = bank.createAccount(customer, accountType, pin);
		customer.addAccount(account);
		System.out.println("Registration successful");
		customer.displayCustomerDetails();
	}

	private static void displayLR() {
		System.out.println();
		System.out.println();
		System.out.println("""
				Do you want to
				    1 -> Login
				    2 -> Register
						""");
	}

}
