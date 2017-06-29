package br.com.humanhealth.security.interceptor.impl;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.humanhealth.security.interceptor.LoggedIn;

@Interceptor
@LoggedIn
public class LoggedInInterceptor {
    

    @AroundInvoke
    public Object check(InvocationContext ctx) throws Exception {  
	//TODO check
	return ctx.proceed();
    }
}
