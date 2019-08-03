package com.cg.service;
import java.util.Map;
import com.cg.bean.*;
import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;
public class Accountservice  implements Gst,transaction{
	//Alternate to multiple inheritance
     AccountDAO dao=new AccountDAOImpl();
     
	@Override
	public double withdraw(Account ob, double amount) throws InsufficientFundException {
		// TODO Auto-generated method stub
		double new_balance=ob.getBalance()-amount;
		if(new_balance<1000.00) {
					new_balance=ob.getBalance();
					//System.out.println("Insufficient Balance");
					//throw new RuntimeException("Insufficient fund.Cannot process withdrawl");
					throw new InsufficientFundException("Insufficient fund.Cannot process withdrawl",new_balance);
				}
				ob.setBalance(new_balance);
				return new_balance;
	}

	@Override
	public double deposit(Account ob, double amount) {
		// TODO Auto-generated method stub
		double new_balance=ob.getBalance()+amount;
		ob.setBalance(new_balance);
		return new_balance;
	}

	@Override
	public String transferMoney(Account from, Account to, double amount) {// INCOMPLETE
		// TODO Auto-generated method stub
		double new_balance=from.getBalance()-amount;
		if(new_balance<1000.00) {
			//new_balance=from.getBalance();
			System.out.println("Insufficient Balance");
			//from.setBalance(new_balance);
			return "Amount cannot be transfered insufficient balance";
		}
		from.setBalance(new_balance);
		double b2=to.getBalance()+amount;
		to.setBalance(b2);
		String ans="From Account: "+from.getAid()+" Balance: "+from.getBalance()+"\n"+"To Account: "+to.getAid()+" Balance "+to.getBalance();
		return ans;
	}

	@Override
	public double calculateTax(double PCT, double amount) {
		// TODO Auto-generated method stub
		return amount*Gst.PCT_5;
	}

	@Override
	public boolean addAccount(Account ob) {
		// TODO Auto-generated method stub
	 dao.addAccount(ob);
	 return true;
		
	}

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		dao.deleteAccount(ob);
		return true;
	}

	@Override
	public Account findAccount(Long mobileno) {
		// TODO Auto-generated method stub
		return dao.findAccount(mobileno);
	}

	@Override
	public Map<Long, Account> getAllAccount() {
		// TODO Auto-generated method stub
		return dao.getAllAccounts();
	}
	

}
