package br.com.humanhealth.persistence;

/**
 * Transaction types mapped from Ejb model.
 * 
 * @author Deitos
 *
 */
public enum TransactionType {
    /**
     * If not already in a transaction, throw an exception. Otherwise, use the transaction.
     */
    MANDATORY,	
    /**
     * If there is already a transaction in progress, use it. If not, start a new transaction.
     */
    REQUIRED,
    /**
     * 	If there is already a transaction in progress, suspend it. Always start a new transaction.
     */
    REQUIRES_NEW,
    /**
     * 	If there is a transaction in progress, use it. Otherwise, execute without a transaction.
     */
    SUPPORTS,
    /**
     * 	If there is a transaction in progress, suspend it. Execute without a transaction.
     */
    NOT_SUPPORTED,
    
    /**
     * Doesn't support transaction. 
     */
    NEVER,
}