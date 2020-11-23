package week11;

import java.io.Serializable;

public class Person implements Serializable {
	String name;
	String phoneNum;
	String dateOfBirth;
	String emailAddress;
	
	public Person(String name, String phoneNum, String dateOfBirth, String emailAddress) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", phoneNum=" + phoneNum + ", dateOfBirth=" + dateOfBirth + ", emailAddress="
				+ emailAddress + "]";
	}
	
	
}
