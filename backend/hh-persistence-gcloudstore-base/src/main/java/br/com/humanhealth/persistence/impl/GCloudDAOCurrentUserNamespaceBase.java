package br.com.humanhealth.persistence.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;

import javax.inject.Inject;

import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.cmd.Query;

import br.com.humanhealth.persistence.GCloudDAO;
import br.com.humanhealth.persistence.constant.PersistenceConstant;
import br.com.humanhealth.security.context.SecurityContext;

/**
 * Abstract {@link GCloudDAO} where default NamesSpace in use is User NameSpace
 * 
 * @author Deitos
 *
 * @param <E>
 *            Generic Entity Type
 * 
 * @see SecurityContext#getUserNameSpace()
 */
public abstract class GCloudDAOCurrentUserNamespaceBase<E> extends DAOBase<E> implements GCloudDAO<E> {
    
    @Inject
    private SecurityContext secCtx;

    @SuppressWarnings("unchecked")
    private Class<E> entityClass(E... e) {
        return (Class<E>) e.getClass().getComponentType();
    }

    /**
     * Save entity in default system NameSpace.
     * 
     * @see PersistenceConstant#secCtx.getUserNameSpace()
     */
    @Override
    public E save(E entity) {
	return save(entity, secCtx.getUserNameSpace());
    }
    
    @Override
    public E save(E entity, String nameSpace) {
	NamespaceManager.set(nameSpace);
	E toReturn = loadFromKey(asyncSave(entity).now());
	NamespaceManager.set(secCtx.getUserNameSpace());
	return toReturn;
    }

    /**
     * Load entity, by id, from default system NameSpace. Not safe to identify
     * solely the entity instance because in google DataStore API unique
     * identifier to a entity is the {@link Key}. Use preferably
     * {@link #loadFromKey(Key)} to do that.
     *
     * @see PersistenceConstant#secCtx.getUserNameSpace()
     */
    @Override
    public E load(Serializable id) {
	return load(id, secCtx.getUserNameSpace());
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
     * @see PersistenceConstant#secCtx.getUserNameSpace()
     */
    @Override
    public E load(Serializable id, String nameSpace) {
	NamespaceManager.set(nameSpace);

	@SuppressWarnings("unchecked")
	// TODO = get id fieldname
	Query<E> q = ofy().load().type(entityClass()).filter("vin >", String.valueOf(id));

	E result = q.list().stream().findFirst().orElse(null);
	NamespaceManager.set(secCtx.getUserNameSpace());
	return result;
    }

    @Override
    public Result<Key<E>> asyncSave(E entity) {
	return asyncSave(entity, secCtx.getUserNameSpace());
    }

    @Override
    public Result<Key<E>> asyncSave(E entity, String nameSpace) {
	NamespaceManager.set(nameSpace);
	Result<Key<E>> toReturn = ofy().save().entity(entity);
	NamespaceManager.set(secCtx.getUserNameSpace());
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
	return loadFromKey(key, secCtx.getUserNameSpace());
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
	NamespaceManager.set(secCtx.getUserNameSpace());
	return toReturn;
    }

}