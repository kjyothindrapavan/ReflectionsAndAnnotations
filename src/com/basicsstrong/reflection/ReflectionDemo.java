package com.basicsstrong.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class MyClass{
	MyClass() {
		System.out.println("MyClass Object Created");
	}
}
public class ReflectionDemo {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		//MyClass obj = new MyClass();
		Class<?> clss = Class.forName("com.basicsstrong.reflection.MyClass");
		Constructor<?> con = clss.getDeclaredConstructor();
		con.setAccessible(true);
		MyClass newInstance = (MyClass) con.newInstance();
	}
}
