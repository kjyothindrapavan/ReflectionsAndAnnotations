package com.basicsstrong.practice;

import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException {
		TransactionHistory sangeetha = new TransactionHistory(15331, "Sangeetha", "Credit", 10000);
		TransactionHistory neha = new TransactionHistory(14531, "Neha", "Credit", 7000);
		TransactionHistory mohit = new TransactionHistory(19031, "Mohit", "Debit", 2000);
		TransactionHistory josh = new TransactionHistory(25389, "Josh", "Debit", 9000);
		
		Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
		
		hibernate.write(sangeetha);
	}

}
