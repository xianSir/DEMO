package com.demo.test;

public enum DictType {
	TYPE_611("币种","611"),
	TYPE_612("单位类型","612");
	
	private String name;
	private String value;
	
	
	DictType(String name,String value) {
		this.name=name;
		this.value=value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

	
	
}
