package com.javacode.springbootstaterweb;

public class Contact {

	private String name;
	private String email;
	private String province;

	public Contact(String name, String email, String province) {
		super();
		this.name = name;
		this.email = email;
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
