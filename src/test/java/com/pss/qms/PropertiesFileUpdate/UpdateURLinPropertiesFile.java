package com.pss.qms.PropertiesFileUpdate;

	 
	import org.apache.commons.configuration.ConfigurationException;
	import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
	public class UpdateURLinPropertiesFile {
		
		
		@Test
		@Parameters({"ADMINURL", "QMSLoginUrl", "QMSRegistrationURL"})
		public static void main(String ADMINURL, String QMSLoginUrl, String QMSRegistrationURL ) {
	        try {
            PropertiesConfiguration adminproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\adminproperties.properties");
            adminproperties.setProperty("ADMINURL", ADMINURL);
            adminproperties.save();
	        System.out.println("adminproperties.properties updated Successfully!!");
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");	        
	        amproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        amproperties.save();
	        System.out.println("amproperties.properties updated Successfully!!");
	        
	        PropertiesConfiguration capaproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\CAPA.properties");	        
	        capaproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        capaproperties.save();
	        System.out.println("CAPA.properties updated Successfully!!");
	        
	        PropertiesConfiguration ccproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\ChangeControl.properties");	        
	        ccproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        ccproperties.save();
	        System.out.println("ChangeControl.properties updated Successfully!!");
	        
	        PropertiesConfiguration devproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\Deviation.properties");	        
	        devproperties.setProperty("URL", QMSLoginUrl);     
	        devproperties.save();
	        System.out.println("Deviation.properties updated Successfully!!");
	        
	        PropertiesConfiguration incproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\IncidentManagement.properties");	        
	        incproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        incproperties.save();
	        System.out.println("IncidentManagement.properties updated Successfully!!");
	        
	        PropertiesConfiguration invproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\Investigation.properties");	        
	        invproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        invproperties.save();
	        System.out.println("Investigation.properties updated Successfully!!");
	        
	        PropertiesConfiguration mcproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\MCproperties.properties");	        
	        mcproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        mcproperties.save();
	        System.out.println("MCproperties.properties updated Successfully!!");
	        
	        PropertiesConfiguration qmsproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\QMS.properties");	        
	        qmsproperties.setProperty("QMSURL", QMSLoginUrl);     
	        qmsproperties.save();
	        System.out.println("QMS.properties updated Successfully!!");
	        
	        PropertiesConfiguration raproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\RAproperties.properties");	        
	        raproperties.setProperty("QMSLoginUrl", QMSLoginUrl);     
	        raproperties.save();
	        System.out.println("RAproperties.properties updated Successfully!!");
	        
	        PropertiesConfiguration regproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\Registration.properties");	        
	        regproperties.setProperty("QMSRegistrationURL", QMSRegistrationURL);     
	        regproperties.save();
	        System.out.println("Registration.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
	 
}

