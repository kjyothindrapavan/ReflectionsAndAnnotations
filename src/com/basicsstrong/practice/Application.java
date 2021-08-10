package com.basicsstrong.practice;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
		TransactionHistory sangeetha = new TransactionHistory(15331, "Sangeetha", "Credit", 10000);
		TransactionHistory neha = new TransactionHistory(14531, "Neha", "Credit", 7000);
		TransactionHistory mohit = new TransactionHistory(19031, "Mohit", "Debit", 2000);
		TransactionHistory josh = new TransactionHistory(25389, "Josh", "Debit", 9000);
		
		Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
		
		hibernate.write(sangeetha);
		hibernate.write(neha);
		hibernate.write(mohit);
		hibernate.write(josh);
		
		TransactionHistory th = hibernate.read(sangeetha.getAccountNumber());
		TransactionHistory obj = hibernate.read(TransactionHistory.class, 1L);
		System.out.println("obj name    "+obj);
		System.out.println("name "+th.getName());
	}

}
