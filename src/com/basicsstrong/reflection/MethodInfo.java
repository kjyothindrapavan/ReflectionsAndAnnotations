package com.basicsstrong.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInfo {
	
	public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Entity e = new Entity(10,"id");
		Class<? extends Entity> clss = e.getClass();
		Method[] methods = clss.getMethods();
		for(Method method:methods) {
			System.out.println(method.getName());
		}
		System.out.println("---------------------------------");
		Method[] declaredMethods = clss.getDeclaredMethods();
		for(Method method:declaredMethods) {
			System.out.println(method.getName());
		}
		Method method1 = clss.getMethod("setVal", int.class);
		method1.invoke(e, 19);
		
		Method method2 = clss.getMethod("getVal", null);
		System.out.println(method2.invoke(e, null));
 	}
}
