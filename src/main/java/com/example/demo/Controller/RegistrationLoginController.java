package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.RegistrationLoginService;
import com.example.demo.model.RegistrationLogin;

@RestController
public class RegistrationLoginController {
	
	@Autowired
	private RegistrationLoginService registrationLoginService;
	
	 @PostMapping("/register")
	    public ResponseEntity<Void> registerUser(@RequestBody RegistrationLogin user) {
	        registrationLoginService.registerUser(user);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<RegistrationLogin> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
	        RegistrationLogin user = registrationLoginService.loginUser(email, password);
	        if (user == null) {
	            return new ResponseEntity<RegistrationLogin>(HttpStatus.UNAUTHORIZED);
	        }
	        return new ResponseEntity<RegistrationLogin>(user, HttpStatus.OK);
	    }
	
	
	

}
