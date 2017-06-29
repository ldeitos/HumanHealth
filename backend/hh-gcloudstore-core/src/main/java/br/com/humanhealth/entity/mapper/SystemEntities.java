package br.com.humanhealth.entity.mapper;

import br.com.humanhealth.entity.Description;
import br.com.humanhealth.entity.User;

/**
 * Constant file to list Entity class to be loaded by GCloudStore persistence module.
 * 
 * @author Deitos
 *
 */
public interface SystemEntities {
    Class<?>[] ENTITIES = {Description.class, User.class}; 
}
