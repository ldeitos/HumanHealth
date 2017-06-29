package br.com.humanhealth.security.context;

import br.com.humanhealth.entity.User;

/**
 * Application security context
 *  
 * @author Deitos
 *
 */

public interface SecurityContext {

    String getUserLanguage();
    
    String getUserNameSpace();
    
    User getUser();
    
    boolean isAtenticated();
    
    void login(String token);
}
