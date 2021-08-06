package com.basicsstrong.annotations;

@MostUsed
public class Utility {

	void dostuff() {
		System.out.println("do some stuff here");
	}
	
	@MostUsed
	void dostuff(String arg) {
		System.out.println("do some stuff on : "+arg);
	}
	
	void dostuff(int i) {
		System.out.println("do some stuff on int: "+i);
	}
}
