package com.cg.dao;
import java.util.Map;

import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;
public interface AccountDAO {
	
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(Account ob);
	public Account findAccount(Long mobileno);
	public Map<Long,Account> getAllAccounts();
    public double TransferMoney(Account from, Account to,double amount) throws InsuffecientFundException; 

}
