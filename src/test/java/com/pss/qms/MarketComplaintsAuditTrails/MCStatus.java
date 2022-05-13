
package com.pss.qms.MarketComplaintsAuditTrails;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MCStatus extends MCLoginDetails {
     @Test
    public void MCStatus() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInMC")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[6]/a")).click();
        Thread.sleep(5000);
//        driver.findElement(By.id("mcNumberInMcAt")).sendKeys(properties.getProperty("MC_NUMBER2"));
//        Thread.sleep(1000);
//        driver.findElement(By.id("fromDateInMcStatusAT")).sendKeys(properties.getProperty("ROLES_FROM_DATE"));
//        Thread.sleep(1000);
//        driver.findElement(By.id("toDateInMcStatusAT")).sendKeys(properties.getProperty("ROLES_TO_DATE"));
//        Thread.sleep(1000);
//        driver.findElement(By.id("selLocBtn_McStatusATSearch")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(6000);
        driver.findElement(By.id("searchBtn_McStatusATPage")).click();
        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@id=\"WorkFlowAuditTrailsGridId\"]/div/table/tbody/tr/td[3]")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.xpath("//*[@id=\"WorkFlowAuditTrailsGridId\"]/div/table/tbody/tr/td[9]/button")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("closeBtnInwfStagesTable")).click();
//        Thread.sleep(2000);
    }
     @AfterMethod
 	public void testIT(ITestResult result)

 	{
 		if (ITestResult.FAILURE == result.getStatus()) {
 			Utility.captureScreenshot(driver, result.getName());
 		}

 	}

}

   

