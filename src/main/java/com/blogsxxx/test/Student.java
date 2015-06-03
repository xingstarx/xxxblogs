package com.blogsxxx.test;

import java.util.Date;

public class Student {

	private Date birth;
	private int age;
	private String name;
	private String id;

	public void setBirth(Date date) {
		// TODO Auto-generated method stub
		this.birth=date;
	}

	public void setAge(int i) {
		// TODO Auto-generated method stub
		this.age=i;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		this.name=string;
	}

	@Override
	public String toString() {
		return "Student [birth=" + birth + ", age=" + age + ", name=" + name
				+ ", id=" + id + "]";
	}

	public Date getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String valueOf) {
		// TODO Auto-generated method stub
		this.id=valueOf;
	}

}
