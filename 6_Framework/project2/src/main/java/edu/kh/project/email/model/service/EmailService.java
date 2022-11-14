package edu.kh.project.email.model.service;

public interface EmailService {
	
    String createAuthKey();
    
    String signUp(String email);
    
}
