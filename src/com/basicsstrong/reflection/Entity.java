package com.basicsstrong.reflection;

public class Entity {
	private int val;
	public String type;
	public Entity(int val, String type) {
		super();
		this.val = val;
		this.type = type;
	}
	private Entity() {
		this(200,"identity");
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
