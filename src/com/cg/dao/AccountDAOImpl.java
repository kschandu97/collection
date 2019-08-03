package com.cg.dao;

import java.util.HashMap;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;

public class AccountDAOImpl implements AccountDAO {

	static Map<Long,Account> accmap = new HashMap<Long,Account>();
	
	
	@Override
	public boolean addAccount(Account ob) {
		accmap.put(ob.getMobile(), ob);
	    return true;
		
	}

	@Override
	public boolean updateAccount(Account ob) {
		// TODO Auto-generated method stub
        long mobile=ob.getMobile();
        accmap.put(mobile, ob);
		return true;
	}

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		accmap.remove(ob);
		return true;
	}

	@Override
	public Account findAccount(Long mobileno) {
		// TODO Auto-generated method stub
		
		Account ob= accmap.get(mobileno);
		return ob;
	}

	@Override
	public Map<Long, Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accmap;
	}

	@Override
	public double TransferMoney(Account from, Account to, double amount) throws InsuffecientFundException {
		// TODO Auto-generated method stub
		double new_balance=from.getBalance()-amount;
		double new_balance1=to.getBalance()+amount;
		if(new_balance<1000.0 && amount>0)
		{
			new_balance=from.getBalance();
			new_balance1=to.getBalance();
			
			throw new InsuffecientFundException("Insufficient fund. Can not be transfered",amount);
		}
		from.setBalance(new_balance);
		to.setBalance(new_balance1);
		AccountDAOImpl dao = new AccountDAOImpl();
		dao.updateAccount(from);
		dao.updateAccount(to);
		return new_balance;
	}
	
	
	
	

}
