package com.basicsstrong.annotations;

import java.util.ArrayList;

class Parent{
	public void m1() {
		System.out.println("m1 parent implementation");
	}
	
	@Deprecated
	public void m2(int a) {
		System.out.println("m2 parent implementation a value: "+a);
	}
}
public class GeneralPurpose extends Parent {

	@Override
	public void m1() {
		System.out.println("m1 child implentation");
	}
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		int a =10;
		
		@SuppressWarnings({"rawtypes", "unused"})
		ArrayList alist = new ArrayList();
		
		@SuppressWarnings("all")
		ArrayList blist = new ArrayList();
		
		GeneralPurpose obj = new GeneralPurpose();
		obj.m2(a);
		
		MyInterface mi = ()-> System.out.println("method implentation");
		mi.method();
	}
}

@FunctionalInterface
interface MyInterface{
	public void method();
}
