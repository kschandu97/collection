package com.cg.service;

import java.util.Map;

import com.cg.bean.Account;
import com.cg.exception.InsufficientFundException;

public interface transaction extends AccountOperation {
	public double withdraw(Account ob, double amount) throws InsufficientFundException;
	 public double deposit(Account ob,double amount);
	 public String transferMoney(Account from,Account to,double amount);
	 public default void printStatement(Account ob) {
		 
		 System.out.println("========================================");
		 System.out.println("Statement for Account NO.: "+ob.getAid());

		 System.out.println("Account Holder: "+ob.getAccountholder());
		 System.out.println("Balance is => "+ob.getBalance());
		 System.out.println("========================================");
		 
	 }
	boolean addAccount(Account ob);
	boolean deleteAccount(Account ob);
	Account findAccount(Long mobileno);
	Map<Long, Account> getAllAccount();
	 
}

