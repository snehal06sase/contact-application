package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Contact;
import com.example.repository.ContactRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactController {

	@Autowired
	private ContactRepo contactRepo;

	@GetMapping("/getAllContactsInfo")
	public ResponseEntity<List<Contact>> getAllContacts() {
		try {
			List<Contact> contactList = new ArrayList<>();
			contactRepo.findAll().forEach(contactList::add);

			if (contactList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(contactList, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getContactById/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
		Optional<Contact> contactObj = contactRepo.findById(id);
		if (contactObj.isPresent()) {
			return new ResponseEntity<>(contactObj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addContact")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		try {
			Contact contactObj = contactRepo.save(contact);
			return new ResponseEntity<>(contactObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/updateContact/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact newContactData) {
		try {
			Optional<Contact> contactData = contactRepo.findById(id);
			if (contactData.isPresent()) {
				Contact updatedContactData = contactData.get();
				updatedContactData.setFirstname(newContactData.getFirstname());
				updatedContactData.setFirstname(newContactData.getFirstname());
				updatedContactData.setEmail(newContactData.getEmail());

				Contact contactObj = contactRepo.save(updatedContactData);
				return new ResponseEntity<>(contactObj, HttpStatus.CREATED);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteContactById/{id}")
	public ResponseEntity<HttpStatus> deleteContact(@PathVariable Long id) {
		try {
			contactRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAllContacts")
	public ResponseEntity<HttpStatus> deleteAllContacts() {
		try {
			contactRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
