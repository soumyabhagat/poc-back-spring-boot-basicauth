package com.forpoc.forpoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forpoc.forpoc.model.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Controller {
	private List<User> users = createList();

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> firstPage() {
		return users;
	}
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Userclass validateLogin() {
		return new Userclass("User successfully authenticated");
	}
	
	@DeleteMapping(path = { "/{id}" })
	public User delete(@PathVariable("id") int id) {
		User deletedUsr = null;
		for (User usr : users) {
			if (usr.getUsrId().equals(id)) {
				users.remove(usr);
				deletedUsr = usr;
				break;
			}
		}
		return deletedUsr;
	}
	
	@PostMapping
	public User create(@RequestBody User user) {
		users.add(user);
		System.out.println(users);
		return user;
	}
	
	private static List<User> createList() {
		List<User> tempusers = new ArrayList<>();
		User emp1 = new User();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setUsrId("1");

		User emp2 = new User();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setUsrId("2");
		tempusers.add(emp1);
		tempusers.add(emp2);
		return tempusers;
	}
}
