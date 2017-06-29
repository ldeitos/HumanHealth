package br.com.humanhealth.persistence.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import com.googlecode.objectify.ObjectifyService;

import br.com.humanhealth.entity.mapper.SystemEntities;
import lombok.extern.log4j.Log4j;

@Log4j
@ApplicationScoped
public class GCloudStoreLoader {

    
    public void loadEntities(@Observes @Initialized( ApplicationScoped.class ) Object init){
	log.info("Loading entities to ObjectifyService.");
	
	for(Class<?> entityClass : SystemEntities.ENTITIES){
	    log.debug(String.format("Loading [%s] class", entityClass.getName()));
	    ObjectifyService.register(entityClass);
	}
    }
    
    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object destroy) {
    }

}
