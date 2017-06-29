package br.com.humanhealth.language;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class PropertieLoader {
    
    private  Properties prop;

    public  Properties getProperties() {
	if(prop == null){
	    loadPropertieFile();
	}
	
	return prop;
    }

    private void loadPropertieFile() {
	Properties prop = new Properties();

	try (InputStream input = new FileInputStream(getPropertieFileName());){
	    prop.load(input);
	    this.prop = prop;
	} catch (IOException ex) {
	    log.warn(String.format("Error at load %s properties file.", getPropertieFileName()), ex);
	} 
    }

    protected abstract String getPropertieFileName();
}