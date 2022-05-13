
package com.pss.qms.ChangeControlAuditTrails;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 
import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class WorkFlows extends CCLoginDetails {
    
    @Test
    public void AuditTrailsRoles() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#side-menu > li:nth-child(3) > a")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("locSelBtn_CcWorkflowUsersATSearch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_3_span")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchBtnInWorkFlowsAudit")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='WorkFlowAuditTrailsGridId']/div/table/tbody/tr[2]/td[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='WorkFlowAuditTrailsGridId']/div/table/tbody/tr[2]/td[9]/button")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("closeBtnInwfStagesTable")).click();
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


