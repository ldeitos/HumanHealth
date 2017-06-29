package br.com.humanhealth.persistence.impl;

import br.com.humanhealth.persistence.DAO;

/**
 * Abstract {@link DAO} where {@link #update(Object)} method call {@link #save(Object)}.
 *  
 * @author Deitos
 *
 * @param <E> Generic Entity Type
 */
public abstract class DAOBase<E> implements DAO<E> {

    @Override
    public E update(E entity) {
	return save(entity);
    }
    

}
