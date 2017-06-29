package br.com.humanhealth.entity;

/**
 * System user types
 * 
 * @author Deitos
 *
 */
public enum UserType {
    PERSONAL("user.type.personal"), MEDICAL("user.type.medical");
    
    String descKey;
    
    private UserType(String descKey){
	this.descKey = descKey;
    }
}
