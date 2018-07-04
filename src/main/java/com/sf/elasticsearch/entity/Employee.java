package com.sf.elasticsearch.entity;

import java.util.Arrays;

/**
 * 雇员类
 * @author 80002888
 */
public class Employee {

	private String firstName;

	private String lastName;

	private Integer age;

	private String about;

	private String[] interests;

	public Employee() {
	}
	
	public Employee(String firstName, String lastName, Integer age, String about, String[] interests) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.about = about;
		this.interests = interests;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", about=" + about
				+ ", interests=" + Arrays.toString(interests) + "]";
	}

}
