package com.cg.pl;
import com.cg.bean.*;
import com.cg.service.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cg.exception.InsufficientFundException;
import com.cg.service.Accountservice;
import com.cg.service.Gst;
import com.cg.service.Validator;
public class mywallet {

	public static void main(String[] args) throws IOException, InsufficientFundException {
		Accountservice service=new Accountservice();
		Map<Long,Account> acc=new TreeMap<Long,Account>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice="";
		while(true)
		{
			 System.out.println("Menu");
		     System.out.println("=====");
		     System.out.println("1 Create new Account");
		     System.out.println("2 Print all Account");
		     System.out.println("3 withdraw Account");
		     System.out.println("4 delete Account");
		     System.out.println("5 transfer Account");
		     System.out.println("10 Exit");
		     System.out.println("Enter your choice");
		     choice =br.readLine();
		     switch(choice)
		     {
		     case "1": int id=0;
		               long mb=0L;
		               String ah="";
		               double bal=0.0;
		               //Accepting and validating input for account number 
		               System.out.println("Enter account number");
		               while(true)
		               {
		            	   String s_id=br.readLine();
		            	   boolean ch1=Validator.validatedata(s_id,Validator.aidpattern);
		            	   if(ch1==true)
		            	   {
		            		   try
		            		   {
		            			   id=Integer.parseInt(s_id);
		            			   break;
		            		   }
		            		   catch(NumberFormatException e)
		            		   {
		            			   System.out.println("Account Number must be numeric.Re Enter");
		            		   }
		            	   }
		            	   else
		            	   {
		            		   System.out.println("Re Enter Account Number in 10 digits");
		            	   }
		               }//end of account number while
		               System.out.println("Enter mobile number");
		               while(true)
		               {
		            	   String s_mb=br.readLine();
		            	   boolean ch1=Validator.validatedata(s_mb,Validator.mobpattern);
		            	   if(ch1==true)
		            	   {
		            		   try
		            		   {
		            			   mb=Long.parseLong(s_mb);
		            			   break;
		            		   }
		            		   catch(NumberFormatException e)
		            		   {
		            			   System.out.println("mobile Number must be numeric.Re Enter");
		            		   }
		            	   }
		            	   else
		            	   {
		            		   System.out.println("Re Enter mobile Number in 10 digits");
		            	   }
		            	   break;
		               }//end of mobile number while
	     
	            	   
		               System.out.println("Enter account holder name");
		               while(true)
		               {
		            	   String s_ah=br.readLine();
		            	   boolean ch1=Validator.validatedata(s_ah,Validator.accpattern);
		            	   if(ch1==true)
		            	   {
		            		   try
		            		   {
		            			   ah=s_ah;
		            			   break;
		            		   }
		            		   catch(NumberFormatException e)
		            		   {
		            			   System.out.println("Number must be string. Reenter");
		            		   }
		               }
		            	   else
		            	   {
		            		   System.out.println("ReEnter name");
		            	   }
		               }
		               System.out.println("Balance");
		               while(true)
		               {
		            	   bal=Double.parseDouble(br.readLine());
		            	   if(bal>0)
		            	   {
		            		   Account ob=new Account(id,mb,ah,bal);
		            		   acc.put((long)ob.getAid(), ob);
		            		   break;
		            	   }
		            	   else
		            	   {
		            		   System.out.println("Balance cant be less than zero");
		            		   System.out.println("ReEnter the balance");
		            	   }
		               }
		               break;
		   		       case "2":   Collection<Account>vc=acc.values();
		               List<Account> acclist=new ArrayList<Account>(vc);
		              // Collections.sort(acclist);
		               for(Account o:acclist)
		               {
		      	         System.out.println(o);
		      	        //serice.printStsement(o);
		               }
		     		   break;
		               case "3":System.out.println("enter account number");
		                   long accid=Long.parseLong(br.readLine());
		                   System.out.println("Enter amount");
		                   double am=Double.parseDouble(br.readLine());
		                   service.withdraw(acc.get(accid),am);
		                   break;
		               case "4":System.out.println("enter account number");
		                   long acccid=Long.parseLong(br.readLine());
		                   
		                   service.deleteAccount(acc.get(acccid));
		                   break;
		               case "5":System.out.println("enter account number");
		               long acid=Long.parseLong(br.readLine());
		               System.out.println("Enter to account number");
		               long tcid=Long.parseLong(br.readLine());
	                   System.out.println("Enter amount");
	                   double amt=Double.parseDouble(br.readLine());
		               service.transferMoney(acc.get(acid),acc.get(tcid),amt);
		               break;

		               case "10":System.out.println("Exiting program");
		               System.exit(0);
		               break;
		               default:System.out.println("Invalid choice");
		     }
		     
			}//end of menu
		     

		}
		
		// TODO Auto-generated method stub
	
		/*Account ob1=new SavingAccount(101,22222222,"Raja",25000.00);
		// ob1.setInterest(); 
		System.out.println(ob1);
		double b1=ob1.withdraw(24500);
		
		
		System.out.println(b1);
		SavingAccount s1=(SavingAccount)ob1;
		s1.setInterest();
		System.out.println(ob1);
		SavingsAccount ob2=new SavingsAccount(105,22262222,"Manjiri",55000.00);
		Accountservice service=new Accountservice();
		service.printStatement(ob2); //calling default method of Transaction 
		double b1=0.0;
		try {
	    b1=service.withdraw(ob2, 55000.00);
		System.out.println("After Withdraw balance is: "+b1);
		}
		catch(InsufficientFundException e) {
			System.err.println(e.getMessage());
			System.err.println(e);
		}
		double tax=service.calculateTax(Gst.PCT_5, b1);
		System.out.println("Gst: "+tax);

		//SavingsAccount ob3=new SavingsAccount(106,22262722,"Sahil",55000.00);
		//System.out.println(service.transferMoney(ob2, ob3, 10000));
		
		
		//System.out.println(ob2 instanceof SavingAccount);

		//System.out.println(ob2 instanceof Account);
		//System.out.println(ob2 instanceof Object);
		//System.out.println(ob2 instanceof String);//ERROR
		
		//Account ob3=new Account();// ABSTRACT CLASS CANNOT BE INSTANTIZED
		 * 
		 */
		
	}

