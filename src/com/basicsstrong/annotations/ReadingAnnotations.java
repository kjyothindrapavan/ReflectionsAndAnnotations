package com.basicsstrong.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadingAnnotations {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class<?> clss = Class.forName("com.basicsstrong.annotations.Utility");
		Constructor<?> conns = clss.getConstructor();
		Utility u = (Utility) conns.newInstance();
		Method[] methods = clss.getDeclaredMethods();
		for(Method m:methods) {
			if(m.isAnnotationPresent(MostUsed.class)) {
				MostUsed annotation = m.getAnnotation(MostUsed.class);
				String val = annotation.value();
				m.invoke(u, val);
			}
		}
	}

}
