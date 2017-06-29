package br.com.humanhealth.persistence.interceptor;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.googlecode.objectify.TxnType;
import com.googlecode.objectify.Work;

import br.com.humanhealth.persistence.TransactionType;
import br.com.humanhealth.persistence.Transactional;
import lombok.extern.log4j.Log4j;

@Interceptor
@Transactional
@Log4j
public class TransactionalInterceptor {
    public final Map<TransactionType, TxnType> txType = new HashMap<>();
    
    @PostConstruct
    public void init(){
	txType.put(TransactionType.MANDATORY, TxnType.MANDATORY);
	txType.put(TransactionType.NEVER, TxnType.NEVER);
	txType.put(TransactionType.NOT_SUPPORTED, TxnType.NOT_SUPPORTED);
	txType.put(TransactionType.REQUIRED, TxnType.REQUIRED);
	txType.put(TransactionType.REQUIRES_NEW, TxnType.REQUIRES_NEW);
	txType.put(TransactionType.SUPPORTS, TxnType.SUPPORTS);
    }

    @AroundInvoke
    public Object doTransact(final InvocationContext ic) throws Exception {
	log.debug("Start a transaction.");
	Object toReturn;
	TxnType type = getTransactionType(ic);
	
	toReturn = ofy().execute(type, new Work<Object>() {
	    @Override
	    public Object run() {
		try {
			
		    return ic.proceed();
		} catch (Exception e) {
		    String msg = "Error at run transactional request.";
		    log.error(msg, e);
		    throw new RuntimeException(msg, e);
		}
	    }
	});
	
	return toReturn;
    }

    private TxnType getTransactionType(InvocationContext ic) {
	Method invokedMehtod = ic.getMethod();
	Annotation interceptorBinding;
	
	if(invokedMehtod == null){
	    Object target = ic.getTarget();
	    
	    interceptorBinding = target.getClass().getAnnotation(Transactional.class);
	} else {
	    interceptorBinding = invokedMehtod.getAnnotation(Transactional.class);
	}
	
	TransactionType transactionType = ((Transactional)interceptorBinding).value();
	
	return this.txType.get(transactionType);
    }
}