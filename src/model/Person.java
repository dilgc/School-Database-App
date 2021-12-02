package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
	private Name name;
	private String id;
	
	private static int idCount;

	public Person(Name name) {
		super();
		this.name = name;
		this.id = String.valueOf(idCount++);
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}
	
	

}
