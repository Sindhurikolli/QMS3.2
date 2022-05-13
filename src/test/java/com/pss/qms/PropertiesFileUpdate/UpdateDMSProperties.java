package com.pss.qms.PropertiesFileUpdate;

	 
	import org.apache.commons.configuration.ConfigurationException;
	import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
	public class UpdateDMSProperties {
		
		
		@Test
		@Parameters({"DOCUMENT_NAME_SOP_DOCT_REQUEST","DocumentType", "ProductCode", "DOCT_NO_INITIATE_NEW_DOCT_SOP"})
		public static void NewDocument(String DOCUMENT_NAME_SOP_DOCT_REQUEST, String DocumentType, String ProductCode, String DOCT_NO_INITIATE_NEW_DOCT_SOP ) {
	        try {
         
	        
	        PropertiesConfiguration dmsproperties = new PropertiesConfiguration("src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
	        dmsproperties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", DOCUMENT_NAME_SOP_DOCT_REQUEST);
	        dmsproperties.setProperty("DocumentType", DocumentType);
	        dmsproperties.setProperty("ProductCode", ProductCode);
	        dmsproperties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", DOCT_NO_INITIATE_NEW_DOCT_SOP);
	        	        
	        dmsproperties.save();
	        System.out.println("dmsproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
		
		@Test
		@Parameters({"DOCUMENT_NAME_SOP_DOCT_REQUEST", "DOCT_NO_INITIATE_NEW_DOCT_SOP"})
		public static void ChangeDocument(String DOCUMENT_NAME_SOP_DOCT_REQUEST, String DOCT_NO_INITIATE_NEW_DOCT_SOP ) {
	        try {
         
	        
	        PropertiesConfiguration dmsproperties = new PropertiesConfiguration("src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
	        dmsproperties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", DOCUMENT_NAME_SOP_DOCT_REQUEST);
	        dmsproperties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", DOCT_NO_INITIATE_NEW_DOCT_SOP);
	        	        
	        dmsproperties.save();
	        System.out.println("dmsproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
	 
		@Test
		@Parameters({"DOCUMENT_NAME_SOP_DOCT_REQUEST", "DOCT_NO_INITIATE_NEW_DOCT_SOP"})
		public static void TerminateDocument(String DOCUMENT_NAME_SOP_DOCT_REQUEST, String DOCT_NO_INITIATE_NEW_DOCT_SOP ) {
	        try {
         
	        
	        PropertiesConfiguration dmsproperties = new PropertiesConfiguration("src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
	        dmsproperties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", DOCUMENT_NAME_SOP_DOCT_REQUEST);
	        dmsproperties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", DOCT_NO_INITIATE_NEW_DOCT_SOP);
	        	        
	        dmsproperties.save();
	        System.out.println("dmsproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
	 
}

