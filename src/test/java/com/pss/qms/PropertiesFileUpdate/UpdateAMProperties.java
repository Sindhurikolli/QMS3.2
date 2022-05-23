package com.pss.qms.PropertiesFileUpdate;

	 
	import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
	import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
	public class UpdateAMProperties {
		
		
		@Test
		@Parameters({"PLAN_NAME_PLAN_SCHEDULE"})
		public static void PlannedSchedulePlanname(String PLAN_NAME_PLAN_SCHEDULE) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = PLAN_NAME_PLAN_SCHEDULE+timeStamp;
	        amproperties.setProperty("PLAN_NAME_PLAN_SCHEDULE", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
		@Test
		@Parameters({"PLAN_NAME_PLAN_SCHEDULE_IN_REJECT_FLOW"})
		public static void PlannedSchedulePlannamerej(String PLAN_NAME_PLAN_SCHEDULE_IN_REJECT_FLOW) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = PLAN_NAME_PLAN_SCHEDULE_IN_REJECT_FLOW+timeStamp;
	        amproperties.setProperty("PLAN_NAME_PLAN_SCHEDULE_IN_REJECT_FLOW", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
		
		@Test
		@Parameters({"PLAN_NAME_IN_AI_PLAN_SCHEDULE"})
		public static void PlannedScheduleAIPlanname(String PLAN_NAME_IN_AI_PLAN_SCHEDULE) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = PLAN_NAME_IN_AI_PLAN_SCHEDULE+timeStamp;
	        amproperties.setProperty("PLAN_NAME_IN_AI_PLAN_SCHEDULE", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}
		@Test
		@Parameters({"PLAN_NAME_IN_AI_REJECT_FLOW"})
		public static void PlannedScheduleAIPlannameRej(String PLAN_NAME_IN_AI_REJECT_FLOW) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = PLAN_NAME_IN_AI_REJECT_FLOW+timeStamp;
	        amproperties.setProperty("PLAN_NAME_IN_AI_REJECT_FLOW", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}
		@Test
		@Parameters({"ADHOC_NAME_IN_ADHOC_SCHEDULE"})
		public static void AdhocSchedule(String ADHOC_NAME_IN_ADHOC_SCHEDULE) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = ADHOC_NAME_IN_ADHOC_SCHEDULE+timeStamp;
	        amproperties.setProperty("ADHOC_NAME_IN_ADHOC_SCHEDULE", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}
		@Test
		@Parameters({"ADHOC_NAME_IN_ADHOC_SHCEDULE_REJECT_FLOW"})
		public static void AdhocScheduleRej(String ADHOC_NAME_IN_ADHOC_SHCEDULE_REJECT_FLOW) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = ADHOC_NAME_IN_ADHOC_SHCEDULE_REJECT_FLOW+timeStamp;
	        amproperties.setProperty("ADHOC_NAME_IN_ADHOC_SHCEDULE_REJECT_FLOW", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}
		@Test
		@Parameters({"ADHOC_NAME_WITH_AI_IN_ADHOC_SCHEDULE"})
		public static void AdhocScheduleAI(String ADHOC_NAME_WITH_AI_IN_ADHOC_SCHEDULE) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = ADHOC_NAME_WITH_AI_IN_ADHOC_SCHEDULE+timeStamp;
	        amproperties.setProperty("ADHOC_NAME_WITH_AI_IN_ADHOC_SCHEDULE", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}
		@Test
		@Parameters({"ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW"})
		public static void AdhocScheduleAIRej(String ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW) {
	        try {
         
	        
	        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	        String Plannamewithtime = ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW+timeStamp;
	        amproperties.setProperty("ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW", Plannamewithtime);        	        
	        amproperties.save();
	        System.out.println("AMproperties.properties updated Successfully!!");
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
		
		}

	
	@Test
	@Parameters({"EXTERNAL_AUDIT_NAME_WITH_AI"})
	public static void ExternalScheduleAIPlanname(String EXTERNAL_AUDIT_NAME_WITH_AI) {
        try {
     
        
        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String Plannamewithtime = EXTERNAL_AUDIT_NAME_WITH_AI+timeStamp;
        amproperties.setProperty("EXTERNAL_AUDIT_NAME_WITH_AI", Plannamewithtime);        	        
        amproperties.save();
        System.out.println("AMproperties.properties updated Successfully!!");
        
        } catch (ConfigurationException e) {
            System.out.println(e.getMessage());
        }
	
	}

	@Test
	@Parameters({"EXTERNAL_AUDIT_NAME_WITH_AI_REJECT_FLOW"})
	public static void ExternalScheduleAIPlannameRej(String EXTERNAL_AUDIT_NAME_WITH_AI_REJECT_FLOW) {
        try {
     
        
        PropertiesConfiguration amproperties = new PropertiesConfiguration("src\\test\\java\\QMSUIProperties\\AMproperties.properties");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String Plannamewithtime = EXTERNAL_AUDIT_NAME_WITH_AI_REJECT_FLOW+timeStamp;
        amproperties.setProperty("EXTERNAL_AUDIT_NAME_WITH_AI_REJECT_FLOW", Plannamewithtime);        	        
        amproperties.save();
        System.out.println("AMproperties.properties updated Successfully!!");
        
        } catch (ConfigurationException e) {
            System.out.println(e.getMessage());
        }
	
	}
}