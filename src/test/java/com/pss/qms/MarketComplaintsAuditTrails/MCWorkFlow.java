
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

public class MCWorkFlow extends MCLoginDetails {
    
     @Test
    public void MCWorkFlow() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInMC")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchBtnInWorkFlowsAudit")).click();
        Thread.sleep(5000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(6000);
//        driver.findElement(By.id("searchBtnInWorkFlowsAudit")).click();
//        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"WorkFlowAuditTrailsGridId\"]/div/table/tbody/tr[6]/td[3]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"WorkFlowAuditTrailsGridId\"]/div/table/tbody/tr[6]/td[10]/button")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("closeBtnInwfStagesTable")).click();
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
