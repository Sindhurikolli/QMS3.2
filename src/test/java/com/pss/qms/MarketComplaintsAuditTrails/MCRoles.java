
package com.pss.qms.MarketComplaintsAuditTrails;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MCRoles extends MCLoginDetails {
    
    @Test
    public void MCRoles() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("auditTrailsInMC")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("rolesNameInMcAt")).sendKeys(properties.getProperty("MC_ROLES"));
//        Thread.sleep(1000);
//        driver.findElement(By.id("fromDateInMcRoles")).sendKeys(properties.getProperty("ROLES_FROM_DATE"));
//        Thread.sleep(1000);
//        driver.findElement(By.id("toDateInMcRoles")).sendKeys(properties.getProperty("ROLES_TO_DATE"));
//        Thread.sleep(1000);
        driver.findElement(By.id("searchBtn_MCRolesATPage")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"mcRolesTableContainer\"]/div/table/tbody/tr[5]/td[3]")).click();
        Thread.sleep(8000);
          driver.findElement(By.xpath("//*[@id=\"mcRolesTableContainer\"]/div/table/tbody/tr[5]/td[8]/button")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("closeInButtonIdInMcRolesAudit")).click();
        Thread.sleep(2000);
    }
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}