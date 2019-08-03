package com.cg.service;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsuffecientFundException;
public class AccountService implements Gst,Transaction {

	
	AccountDAO dao = new AccountDAOImpl();
	@Override
	public double withdraw(Account ob, double amount) throws InsuffecientFundException {
		// TODO Auto-generated method stub
		
		double new_balance=ob.getBalance()-amount;
		if(new_balance<1000.0 || amount<0)
		{
			new_balance=ob.getBalance();
			
			throw new InsuffecientFundException("Insufficient fund. Can not process withdrawal",new_balance);
		}
		
		ob.setBalance(new_balance);
		dao.updateAccount(ob);
		
		return new_balance;
	}

	@Override
	public double calculateTax (double PCT, double amount) {
		// TODO Auto-generated method stub
		return amount*Gst.PCT_5;
	}

	@Override
	public double Deposit(Account ob, double amount) throws InsuffecientFundException {
		double new_balance=ob.getBalance()+amount;
		if(amount<0)
		{
			
			new_balance=ob.getBalance();
			throw new InsuffecientFundException("Invalid amount to be added",amount);
		}
		ob.setBalance(new_balance);
		dao.updateAccount(ob);
		
		return new_balance;
	}

	

	@Override
	public boolean addAccount(Account ob) {
		// TODO Auto-generated method stub
		return dao.addAccount(ob);
	}

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		return dao.deleteAccount(ob);
	}

	@Override
	public Account findAccount(Long mobileno) {
		// TODO Auto-generated method stub
		return dao.findAccount(mobileno);
	}

	@Override
	public Map<Long, Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return dao.getAllAccounts();
	}

	@Override
	public boolean updateAccount(Account ob) {
		// TODO Auto-generated method stub
		return dao.updateAccount(ob);
	}

	@Override
	public double TransferMoney(Account from, Account to, double amount) throws InsuffecientFundException {
		// TODO Auto-generated method stub
		
		return dao.TransferMoney(from, to, amount);
	}

	
	

}
