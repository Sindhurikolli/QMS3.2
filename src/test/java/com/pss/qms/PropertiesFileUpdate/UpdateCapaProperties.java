package com.pss.qms.PropertiesFileUpdate;

	 
	import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
	import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
	public class UpdateCapaProperties {
		
		
		@Test
		@Parameters({"CAPA_REF_No"})
		public static void capaRefNoUpdate(String CAPA_REF_No) {
	        try {
         
	        
	        PropertiesConfiguration capaproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\CAPA.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String ReferanceNo = CAPA_REF_No+timeStamp;
	        capaproperties.setProperty("Reference_Number", ReferanceNo);        	        
	        capaproperties.save();
	        System.out.println("CAPA.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
		



}