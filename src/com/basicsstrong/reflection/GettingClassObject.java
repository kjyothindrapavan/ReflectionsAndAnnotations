package com.basicsstrong.reflection;

public class GettingClassObject {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//forName
		Class<?> clss1 = Class.forName("java.lang.String");
		Class<?> clss2 = Class.forName("java.lang.String");
		System.out.println(clss1 == clss2);
		//className.class
		Class<?> clss3 = int.class;
		Class<?> clss4 = String.class;
		System.out.println(clss4 == clss2);
		//obj.getClass
		MyClass m = new MyClass();
		Class<? extends MyClass> class1 = m.getClass();
		Class<?> class2 = Class.forName("com.basicsstrong.reflection.MyClass");
		Class<? extends MyClass> class3 = m.getClass();
		System.out.println(class1 == class2);
		System.out.println(class2 == class3);
		//superClass
		Class<?> superClass = class1.getSuperclass();
		Class<?>[] interfaces = class1.getInterfaces();
		System.out.println(superClass.getName());
		System.out.println(interfaces);
	} 
}
