package com.jk;

public class Transaction {
	private static int idTracker = 1;
	private int transactionId;
	private Account fromAccount;
	private Account toAccount;
	private int amount;
	private String transactionType;
	private String transactionStatus;
	private boolean isTransactionCompleted = false;

	public Transaction(Account fromAccount, Account toAccount, int amount, String transactionType) {
		this.transactionId = idTracker++;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transactionType = transactionType;

	}

	public void createTransaction(String pin) {
		if (this.fromAccount.getPin().equals(pin)) {
			if (this.fromAccount.isBalanceAvailable(amount)) {
				this.fromAccount.setBalance(this.fromAccount.getBalance() - this.amount);
				this.toAccount.setBalance(this.toAccount.getBalance() + this.amount);
				this.transactionStatus = "Successful fund transfer";
				this.isTransactionCompleted = true;
			} else {
				this.transactionStatus = "Cancelled due to insufficient balance";
			}
		} else {
			this.transactionStatus = "Invalid pin";
		}
		System.out.println(this.transactionStatus);
	}

	public String getTransactionDetails() {
		return "Transaction Details\n" + "--------------------\n" + "    Transaction ID     : " + this.transactionId
				+ "\n" + (this.toAccount == null ? "    Account            : " : "    Transferred from   : ")
				+ this.fromAccount.getAccountNumber() + "\n"
				+ (this.toAccount != null ? "    Transferred to     : " + this.toAccount.getAccountNumber() + "\n" : "")
				+ "    Amount             : " + this.amount + "\n" + "    Transaction type   : " + this.transactionType
				+ "\n" + "    Transaction status : " + this.transactionStatus + "\n" + "    Completed Status   : "
				+ this.isTransactionCompleted;
	}

	public void depositTransaction() {
		// TODO Auto-generated method stub
		this.fromAccount.deposit(this.amount);
		this.transactionStatus = "Amount Deposited";
		this.isTransactionCompleted = true;
		System.out.println(this.transactionStatus);
	}

	public void withdrawTransaction(String pin) {
		// TODO Auto-generated method stub
		String transact = this.fromAccount.withdraw(amount, pin);
		if (!transact.equals("successful")) {
			this.transactionStatus = transact;
		} else {
			this.transactionStatus = "Successfully Withdrawn";
			this.isTransactionCompleted = true;
		}
	}

	public boolean isTransactionCompleted() {
		return isTransactionCompleted;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public void setTransactionCompleted(boolean isTransactionCompleted) {
		this.isTransactionCompleted = isTransactionCompleted;
	}
	
	

}
