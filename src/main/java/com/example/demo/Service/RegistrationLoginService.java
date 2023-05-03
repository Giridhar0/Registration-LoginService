package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.RegistrationLoginRepository;
import com.example.demo.model.RegistrationLogin;

@Service
public class RegistrationLoginService {
	
	  @Autowired
	    private RegistrationLoginRepository registrationLoginRepository;

	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    public void registerUser(RegistrationLogin user) {
	        // Encrypt password before saving to database
	        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
	        user.setPassword(encryptedPassword);
	        registrationLoginRepository.save(user);
	    }

	    public RegistrationLogin loginUser(String email, String password) {
	        RegistrationLogin user = registrationLoginRepository.findByEmail(email);
	        if (user == null) {
	            return null;
	        }
	        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
	            return user;
	        }
	        return null;
	    }
}

	  
