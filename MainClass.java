package week11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Choose an option: ");
			System.out.println("1) Add person to file");
			System.out.println("2) Display file information");
			System.out.println("3) Delete person");
			System.out.println("4) Update person");
			System.out.println("5) Exit");
			
			int choice = input.nextInt();
			
			switch(choice) {
			case 1:
				addPerson();
				break;
			case 2:
				displayInfo();
				break;
			case 3:
				deletePerson();
				break;
			case 4:
				updatePerson();
				break;
			case 5:
				return;
			}
			
			System.out.println();
		}
	}
	
	public static void addPerson() {
		String name = "", phoneNum = "", dateOfBirth = "", emailAddress = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		name = input.nextLine();
		System.out.println("Enter phone number: ");
		phoneNum = input.next();
		System.out.println("Enter date of birth (mm/dd/yyyy): ");
		dateOfBirth = input.next();
		System.out.println("Enter email address: ");
		emailAddress = input.next();
		
		Person p = new Person(name, phoneNum, dateOfBirth, emailAddress);
		
		File f = new File("Person.bin");
		if(f.exists()) {
			try {
				ArrayList<Person> persons = readFile();
				persons.add(p);
				writeToFile(persons);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else {
			ArrayList<Person> persons = new ArrayList<Person>();
			persons.add(p);
			try {
				writeToFile(persons);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void displayInfo() {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			persons = new ArrayList<Person>(readFile());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i).toString());
		}
	}
	
	public static void deletePerson() {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			persons = new ArrayList<Person>(readFile());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Select person to delete: ");
		for(int i = 0; i < persons.size(); i++) {
			System.out.println(i + ") " + persons.get(i).toString());
		}
		
		int choice = input.nextInt();
		persons.remove(choice);
		
		try {
			writeToFile(persons);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePerson() {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			persons = new ArrayList<Person>(readFile());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Select person to update: ");
		for(int i = 0; i < persons.size(); i++) {
			System.out.println(i + ") " + persons.get(i).toString());
		}
		
		int choice = input.nextInt();
		
		System.out.println("Enter new information: ");
		String name = "", phoneNum = "", dateOfBirth = "", emailAddress = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		name = input.nextLine();
		System.out.println("Enter phone number: ");
		phoneNum = input.next();
		System.out.println("Enter date of birth (mm/dd/yyyy): ");
		dateOfBirth = input.next();
		System.out.println("Enter email address: ");
		emailAddress = input.next();
		
		persons.get(choice).name = name;
		persons.get(choice).phoneNum = phoneNum;
		persons.get(choice).dateOfBirth = dateOfBirth;
		persons.get(choice).emailAddress = emailAddress;
		
		try {
			writeToFile(persons);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(ArrayList<Person> persons) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Person.bin"));
		objectOutputStream.writeObject(persons);
		objectOutputStream.close();
	}
	
	public static ArrayList<Person> readFile() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Person.bin"));
		ArrayList<Person> persons = (ArrayList<Person>) ois.readObject();
		return persons;
	}
}
