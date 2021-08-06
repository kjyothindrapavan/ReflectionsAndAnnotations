package com.basicsstrong.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Entity e = new Entity(10,"id");
		Class<? extends Entity> clss = e.getClass();
		int modifierInt = clss.getModifiers();
		int i = modifierInt & Modifier.PUBLIC;
		boolean ispublicClass = Modifier.isPublic(modifierInt);
		System.out.println(modifierInt);
		System.out.println(i);
		System.out.println(ispublicClass);
		Method method = clss.getMethod("getVal");
		int methodModifier = method.getModifiers();
		int j = methodModifier & Modifier.PUBLIC;
		boolean ispublicMethod = Modifier.isPublic(methodModifier);
		System.out.println("-------------------------------------------------");
		System.out.println(methodModifier);
		System.out.println(j);
		System.out.println(ispublicMethod);
		
	}

}
