package com.pss.qms.AuditManagementAuditTrails;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AMLoginDetails;
 
import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class AuditStatusInAuditManagement extends AMLoginDetails {

    @Test
    public void methodtodoAuditStatus() throws InterruptedException {
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(15000);
        driver.findElement(By.id("auditTrailsInAM")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"amAuditStatusAuditForm\"]/a")).click();
        Thread.sleep(15000);
        todoAuditStatus();
    }

    private void todoAuditStatus() throws InterruptedException {
        Thread.sleep(10000);
//        driver.findElement(By.id("strtDateInAuditStatusAuditFormSrch")).sendKeys(properties.getProperty("START_DATE"));
//        Thread.sleep(5000);
//        driver.findElement(By.id("endDateInAuditStatusAuditFormSrch")).sendKeys(properties.getProperty("END_DATE"));
//        Thread.sleep(5000);
//        driver.findElement(By.id("auditNumberInAuditStatusAuditFormSrch")).sendKeys(properties.getProperty("CHECK_LIST_NAME"));
//        Thread.sleep(8000);
//        driver.findElement(By.id("locSelBtnInAuditStatusAuditFormSrch")).click();
//        Thread.sleep(8000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(8000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(8000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(8000);
        driver.findElement(By.id("searchBtnInAuditStatusAuditFormSrch")).click();
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
