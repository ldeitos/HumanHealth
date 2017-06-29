package br.com.humanhealth.persistence.impl;

import static br.com.humanhealth.persistence.constant.PersistenceConstant.GOOGLE_DS_DEFAULT_NAME_SPACE;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;

import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;

import br.com.humanhealth.persistence.GCloudDAO;
import br.com.humanhealth.persistence.constant.PersistenceConstant;

/**
 * Abstract {@link GCloudDAO} where {@link #update(Object)} method call
 * {@link #save(Object)}.
 * 
 * @author Deitos
 *
 * @param <E>
 *            Generic Entity Type
 */
public abstract class GCloudDAOBase<E> extends DAOBase<E> implements GCloudDAO<E> {
    
    @SuppressWarnings("unchecked")
    private Class<E> entityClass(E... e) {
        return (Class<E>) e.getClass().getComponentType();
    }

    /**
     * Save entity in default system NameSpace.
     * 
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    @Override
    public E save(E entity) {
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return loadFromKey(asyncSave(entity).now());
    }
    
    @Override
    public E save(E entity, String nameSpace) {
	NamespaceManager.set(nameSpace);
	E toReturn = loadFromKey(asyncSave(entity).now());
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return toReturn;
    }

    /**
     * Load entity, by id, from default system NameSpace. Not safe to identify
     * solely the entity instance because in google DataStore API unique
     * identifier to a entity is the {@link Key}. Use preferably
     * {@link #loadFromKey(Key)} to do that.
     *
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    @Override
    public E load(Serializable id) {
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);

	@SuppressWarnings("unchecked")
	// TODO = get id fieldname
	Query<E> q = ofy().load().type(entityClass()).filter("vin >", String.valueOf(id));
	
	E result = q.list().stream().findFirst().orElse(null);
	
	return result;
    }

    /**
     * Load entity, by id, from parameterized NameSpace. Not safe to identify
     * solely the entity instance because in google DataStore API unique
     * identifier to a entity is the {@link Key}. Use preferably
     * {@link #loadFromKey(Key)} to do that. <br>
     * After complete the operation set default NameSpace again.
     * 
     * @param entity
     *            Entity to be save.
     * 
     * @param nameSpace
     *            Specified NameSpace to save the entity.
     * 
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    @Override
    public E load(Serializable id, String nameSpace) {
	NamespaceManager.set(nameSpace);

	@SuppressWarnings("unchecked")
	Query<E> q = ofy().load().type(entityClass()).filter("vin >", String.valueOf(id));

	E result = q.list().stream().findFirst().orElse(null);
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return result;
    }

    @Override
    public Result<Key<E>> asyncSave(E entity) {
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return ofy().save().entity(entity) ;
    }

    @Override
    public Result<Key<E>> asyncSave(E entity, String nameSpace) {
	NamespaceManager.set(nameSpace);
	Result<Key<E>> toReturn = ofy().save().entity(entity);
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return toReturn;
    }

    /**
     * Safe method to get a entity solely identified in default system
     * NameSpace.
     * 
     * @param key
     *            Key to identify solely a entity instance at google DataStore.
     * @return
     */
    @Override
    public E loadFromKey(Key<E> key) {
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	E toReturn = ofy().load().key(key).now();
	return toReturn;
    }

    /**
     * Safe method to get a entity solely identified in default system
     * NameSpace.
     * 
     * @param key
     *            Key to identify solely a entity instance at google DataStore.
     * 
     * @param nameSpace
     *            Specified NameSpace load the entity.
     * @return
     */
    @Override
    public E loadFromKey(Key<E> key, String nameSpace) {
	NamespaceManager.set(nameSpace);
	E toReturn = ofy().load().key(key).now();
	NamespaceManager.set(GOOGLE_DS_DEFAULT_NAME_SPACE);
	return toReturn;
    }

}
