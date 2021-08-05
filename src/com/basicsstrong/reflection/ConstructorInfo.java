package com.basicsstrong.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorInfo {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clss = Class.forName("com.basicsstrong.reflection.Entity");
		Constructor<?>[] publicConslist = clss.getConstructors();
		Constructor<?>[] privateConslist = clss.getDeclaredConstructors();
		for(Constructor<?> constructor:publicConslist) {
			System.out.println(constructor.getName());
		}
		System.out.println("-------------------------");
		for(Constructor<?> constructor:privateConslist) {
			System.out.println(constructor.getName());
		}
		
		Constructor<?> publicConst = clss.getConstructor(int.class, String.class);
		Constructor<?> privateConst = clss.getDeclaredConstructor();
		privateConst.setAccessible(true);
		Entity e = (Entity) publicConst.newInstance(10,"id");
		Entity e1 = (Entity) privateConst.newInstance();
		System.out.println(e.getType()+" "+e.getVal());
		System.out.println(e1.getType()+" "+e1.getVal());
	}

}
