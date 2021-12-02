package model;

import java.io.Serializable;

public class PersonBag implements Serializable {
	private Person[] personArr;
	private int nElems;
	
	public PersonBag(int maxSize) {
		personArr = new Person[maxSize];
	}
	
	public void insert(Person person) {
		personArr[nElems++] = person;
	}
	
	public Person searchById(String id){
		int searched;
		for(searched = 0; searched < nElems; searched++) {
			if(personArr[searched].getId().equals(id)) {
				break;
			}
		}
		if(searched==nElems) {
			System.out.println("Not found");
			return null;
		} else {
			return personArr[searched];
		}
	}

	public Person deleteById(String id) {
		int searched;
		for(searched = 0; searched < nElems; searched++) {
			if(personArr[searched].getId().equals(id)) {
				break;
			}
		}
		if(searched==nElems) {
			System.out.println("Not found");
			return null;
		} else {
			Person temp = personArr[searched];
			for(int j = searched; j<nElems - 1; j++) {
				personArr[j] = personArr[j+1];
			}
			nElems--;
			return temp;
		}
	}

	public String display() {
		String str = "";
		for(int i = 0; i < nElems; i++) {
			str += personArr[i] + "\n";
		}
		return str;
	}
}
