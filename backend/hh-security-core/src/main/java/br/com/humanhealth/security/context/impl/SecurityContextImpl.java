package br.com.humanhealth.security.context.impl;

import javax.enterprise.context.RequestScoped;

import br.com.humanhealth.entity.User;
import br.com.humanhealth.security.context.SecurityContext;

@RequestScoped
public class SecurityContextImpl implements SecurityContext {
    
    private  String userLanguage;
    
    private String userNameDpace; 
    
    private User user;

    @Override
    public String getUserNameSpace() {
	return userNameDpace;
    }

    @Override
    public boolean isAtenticated() {
	return false;
    }

    @Override
    public void login(String token) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public String getUserLanguage() {
	return userLanguage;
    }

    @Override
    public User getUser() {
	return user;
    }

    

}
