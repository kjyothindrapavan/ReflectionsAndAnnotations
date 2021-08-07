package com.basicsstrong.practice;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.basicsstrong.annotations.Column;
import com.basicsstrong.annotations.PrimaryKey;

public class Hibernate<T> {
	Connection con;
	
	public static <T> Hibernate<T> getConnection() throws SQLException{
		return new Hibernate<T>();
	}
	private  Hibernate() throws SQLException{
		//this.con = DriverManager.getConnection("");
	}
	public void write(T t) {
		Class<? extends Object> clss = t.getClass();
		
		Field[] declaredFields = clss.getDeclaredFields();
		for(Field field:declaredFields) {
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				System.out.println("The Primary Key is: "+field.getName()+" the column fields are");
			}else if(field.isAnnotationPresent(Column.class)) {
				System.out.println(field.getName());
			}
		}
	}
	
}
