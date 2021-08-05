package com.basicsstrong.reflection;

import java.lang.reflect.Field;

public class FieldInfo {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Entity e = new Entity(10,"id");
		Class<? extends Entity> cls = e.getClass();
		Field[] fields = cls.getFields();
		for(Field field: fields) {
			System.out.println(field.getName());
		}
		System.out.println();
		Field[] declaredFields = cls.getDeclaredFields();
		for(Field field: declaredFields) {
			System.out.println(field.getName());
		}
		Field field = cls.getField("type");
		Field field2 = cls.getDeclaredField("val");
		field2.setAccessible(true);
		field2.set(e, 19);
		field.set(e, "rollno");
		System.out.println(e.getType()+" "+e.getVal());

	}

}
