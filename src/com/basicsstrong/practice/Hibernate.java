package com.basicsstrong.practice;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;

import com.basicsstrong.annotations.Column;
import com.basicsstrong.annotations.PrimaryKey;

public class Hibernate<T> {
	Connection con;
	
	public static <T> Hibernate<T> getConnection() throws SQLException{
		return new Hibernate<T>();
	}
	private  Hibernate() throws SQLException{
		this.con = DriverManager.getConnection("");
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
				System.out.println("The Primary Key is: "+field.getName()+" Values: "+field.get(t)+" the column fields are");
			}else if(field.isAnnotationPresent(Column.class)) {
				joiner.add(field.getName());
				columns.add(field);
				System.out.println(field.getName()+" values : "+field.get(t));
			}
		}
		int number = joiner.length();
		String qMarks = IntStream.range(0, number)
		.mapToObj(e -> "?")
		.collect(Collectors.joining(","));		
		String sql = "insert into"+clss.getSimpleName()+"( " +pkey.getName()+joiner.toString()+") "+ "values ("+qMarks+")";
		PreparedStatement stmt = con.prepareStatement(sql);
	}
	
}
