package com.pss.qms.ChangeControlAuditTrails;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class ActionItemOverDue extends CCLoginDetails {
 
	@Test
    public void AuditTrailsRoles() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[7]/a")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("locSelBtn_OverDueOrExtendDateAudit")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_3_span")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchBtnInOverDueOrExtendDateAudit")).click();
        Thread.sleep(6000);
//        driver.findElement(By.xpath("//*[@id=\"actItemOverDueAuditTrailsGridId\"]/div/table/tbody/tr/td[4]")).click();
//        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"actItemOverDueAuditTrailsGridId\"]/div/table/tbody/tr/td[10]/button")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
        Thread.sleep(5000);
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
	



