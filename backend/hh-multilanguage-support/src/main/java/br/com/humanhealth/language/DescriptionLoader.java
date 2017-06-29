package br.com.humanhealth.language;

public class DescriptionLoader extends PropertieLoader {
    
    private static final String FILE_NAME = "description.%s.properties";

    @Override
    protected String getPropertieFileName() {
	return String.format(FILE_NAME, getUserLanguage()) ;
    }

    private String getUserLanguage() {
	// TODO Auto-generated method stub
	return null;
    }

}
