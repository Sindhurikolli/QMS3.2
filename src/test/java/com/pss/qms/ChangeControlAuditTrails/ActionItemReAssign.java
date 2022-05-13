package com.pss.qms.ChangeControlAuditTrails;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class ActionItemReAssign extends CCLoginDetails {
 
	@Test
    public void AuditTrailsRoles() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[9]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("actItemNumberInCcActItemReassignAt")).sendKeys(properties.getProperty("ACTION_ITEM_NUM_IN_REASSIGN"));
        Thread.sleep(3000);
        driver.findElement(By.id("searchBtnInCcActItemReassignAt")).click();
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

