package br.com.humanhealth.persistence;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

import br.com.humanhealth.persistence.constant.PersistenceConstant;

/**
 * Interface to data access object implementations.
 * 
 * @author Deitos
 *
 * @param <E> Generic Entity type.
 */
public interface GCloudDAO<E> extends DAO<E> {
    
    /**
     * Save entity at parameterized NameSpace. After do the operation, set the
     * default NameSpace again.
     * 
     * @param entity
     *            Entity to be save.
     * 
     * @param nameSpace
     *            Specified NameSpace to save the entity.
     * 
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    E save(E entity, String nameSpace);
    
    /**
     * Load entity, by id, from parameterized NameSpace. Not safe to identify
     * solely the entity instance because in google DataStore API unique
     * identifier to a entity is the {@link Key}. Use preferably
     * {@link #loadFromKey(Key)} to do that. <br>
     * After complete the operation set default NameSpace again.
     * 
     * @param id
     *            Id to entity to be loaded.
     * 
     * @param nameSpace
     *            Specified NameSpace to load the entity.
     *
     * 
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    E load(Serializable id, String nameSpace);

    /**
     * Async method to save a entity in default system NameSpace.
     * 
     * @param entity
     *            Entity to be persisted.
     * 
     * @param nameSpace
     *            Specified NameSpace to save the entity.
     * @return {@link Result} of operation to manipulation.
     */
    Result<Key<E>> asyncSave(E entity);

    /**
     * Async method to save a entity in parameterized NameSpace.
     * 
     * @param entity
     *            Entity to be persisted.
     * 
     * @param nameSpace
     *            Specified NameSpace to save the entity.
     * 
     * @return {@link Result} of operation to manipulation.
     * 
     * @see PersistenceConstant#GOOGLE_DS_DEFAULT_NAME_SPACE
     */
    Result<Key<E>> asyncSave(E entity, String nameSpace);

    /**
     * Safe method to get a entity solely identified in default system
     * NameSpace.
     * 
     * @param key
     *            Key to identify solely a entity instance at google DataStore.
     * @return
     */
    E loadFromKey(Key<E> key);

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
    E loadFromKey(Key<E> key, String nameSpace);

}
