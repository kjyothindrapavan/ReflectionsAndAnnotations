package com.basicsstrong.practice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

import com.basicsstrong.annotations.Column;
import com.basicsstrong.annotations.PrimaryKey;

public class Hibernate<T> {
	private Connection con;
	private AtomicLong id = new AtomicLong(0L);
	
	public static <T> Hibernate<T> getConnection() throws SQLException{
		return new Hibernate<T>();
	}
	private  Hibernate() throws SQLException{
		this.con = DriverManager.getConnection("jdbc:h2:C:\\Users\\JYOTHINDRA PAVAN\\eclipse-workspace1\\ReflectionAndAnnotation\\databaset","sa","");
	}
	public void write(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Class<? extends Object> clss = t.getClass();
		
		Field[] declaredFields = clss.getDeclaredFields();
		
		Field pkey = null;
		ArrayList<Field> columns = new ArrayList<>();
		StringJoiner joiner = new StringJoiner(",");
		
		for(Field field:declaredFields) {
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				pkey = field;
				//System.out.println("The Primary Key is: "+field.getName()+" Values: "+field.get(t)+" the column fields are");
			}else if(field.isAnnotationPresent(Column.class)) {
				joiner.add(field.getName());
				System.out.println(field.getName());
				columns.add(field);
				//System.out.println(field.getName()+" values : "+field.get(t));
			}
		}
		int number = columns.size()+1;
		System.out.println(joiner.length());
		String qMarks = IntStream.range(0, number)
		.mapToObj(e -> "?")
		.collect(Collectors.joining(","));		
		String sql = "insert into "+clss.getSimpleName()+"( " +pkey.getName()+","+joiner.toString()+") "+ "values ("+qMarks+");";
		String sql1 = "create table TransactionHistory(transactionId int,accountNumber int,name varchar(50),transactionType varchar(20),amount int);";
		Statement stm = con.createStatement();
		//stm.execute(sql1);
		PreparedStatement stmt = con.prepareStatement(sql);
		int index = 2;
		if(pkey.getType() == long.class) {
			stmt.setLong(1,id.incrementAndGet());
		}
		for(Field field :columns) {
			field.setAccessible(true);
			if(field.getType() == int.class) {
				stmt.setInt(index++, (int)field.get(t));
			} else if(field.getType() == String.class) {
				stmt.setString(index++, (String) field.get(t));
			}
		}
		stmt.executeUpdate();
	}
	
	public TransactionHistory read(int accountNumber) throws SQLException{
		String sql = "select * from TransactionHistory";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		TransactionHistory th = null;
		while(rs.next()) {
			if(rs.getLong("accountNumber") == accountNumber) {
				th = new TransactionHistory(rs.getInt("accountNumber"), rs.getString("name"), rs.getString("transactionType"), rs.getInt("amount"));
				return th;
			}
		}
		return th;
	}
	public T read(Class<T> clss, long l) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		Field pkey = null;
		Field[] declaredFields = clss.getDeclaredFields();
		for(Field field:declaredFields) {
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				pkey = field;
				break;
			}
		}
		String sql = "select * from "+clss.getSimpleName()+" where "+pkey.getName()+" = "+l+";";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		T t = clss.getConstructor().newInstance();
		pkey.setAccessible(true);
		pkey.set(t, rs.getLong(pkey.getName()));
		for(Field field:declaredFields) {
			if(field.isAnnotationPresent(Column.class)) {
				field.setAccessible(true);
				if(field.getType() == int.class) {
					field.setInt(t, rs.getInt(field.getName()));
				} else if(field.getType() == String.class) {
					field.set(t, rs.getString(field.getName()));
				}
			}
		}
		return t;
	}
	
}
