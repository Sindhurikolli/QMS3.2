package com.pss.qms.ChangeControlAuditTrails;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class ExtensionOfCC extends CCLoginDetails {
 
	 @Test
	    public void AuditTrailsRoles() throws InterruptedException {
	        Thread.sleep(5000);
	        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("auditTrailsInCC")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("locSelBtn_CcExtDueDateATSearch")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("treeContainer_2_switch")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("treeContainer_3_a")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("selectBtnInLocSelect")).click();
	        Thread.sleep(5000);
	        
//	        driver.findElement(By.id("rolesNameInCcAt")).sendKeys(properties.getProperty("Role_Name"));
//	        Thread.sleep(2000);
	        driver.findElement(By.id("searchButtonInCcExtendDueDateAT")).click();
	        Thread.sleep(8000);
	    }
	 @AfterMethod
	 public void testIT(ITestResult result)
	 {
	 	if(ITestResult.FAILURE==result.getStatus())
	 	{
	 		Utility.captureScreenshot(driver, result.getName());
	 	}
	 	
	 }
	    } 
	

