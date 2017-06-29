package br.com.humanhealth.persistence;

import static br.com.humanhealth.persistence.TransactionType.REQUIRED;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * Binder to transaction intercepter. By default uses {@link TransactionType#REQUIRED}.
 * 
 * @author Deitos
 * 
 * @see TransactionType
 *
 */
@InterceptorBinding
@Retention(RUNTIME)
public @interface Transactional {
    @Nonbinding public TransactionType value() default REQUIRED;
}
