package com.javacode.springbootstaterweb;

import java.util.ArrayList;
import java.util.List;

public class ContactBussiness {

	public List<Contact> getAllContact() {
		List<Contact> listContact = new ArrayList<Contact>();

		listContact.add(new Contact("Tran Le Hoang Tu A", "tranlehoangtu@gmail.com", "dong thap"));
		listContact.add(new Contact("Tran Le Hoang Tu B", "tranlehoangtu@gmail.com", "dong thap"));
		listContact.add(new Contact("Tran Le Hoang Tu C", "tranlehoangtu@gmail.com", "dong thap"));

		return listContact;
	}

}
