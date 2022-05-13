
package com.pss.qms.MarketComplaintsAuditTrails;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelledMC extends MCLoginDetails {
    
      @Test
    public void CancelledMC() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInMC")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
        Thread.sleep(5000);
//        driver.findElement(By.id("mcNumnerInMcCanceledAt")).sendKeys(properties.getProperty("MC_NUMBER2"));
//        Thread.sleep(5000);
        driver.findElement(By.id("locSelBtn_McCancelledMCATSearch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_3_span")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
//        driver.findElement(By.id("fromDateInMcCanceledAt")).sendKeys(properties.getProperty("ROLES_FROM_DATE"));
//        Thread.sleep(5000);
//        driver.findElement(By.id("toDateInMcCanceledAt")).sendKeys(properties.getProperty("ROLES_TO_DATE"));
//        Thread.sleep(5000);
//        Select changetype = new Select(driver.findElement(By.id("severityInMcCanceledAt")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
        driver.findElement(By.id("searchCanceledAtButtonId")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"mcCanceledAtTableContainer\"]/div/table/tbody/tr[2]/td[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"mcCanceledAtTableContainer\"]/div/table/tbody/tr[2]/td[9]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
        Thread.sleep(5000);
    }
      @AfterMethod
  	public void testIT(ITestResult result)

  	{
  		if (ITestResult.FAILURE == result.getStatus()) {
  			Utility.captureScreenshot(driver, result.getName());
  		}

  	}

}

   

