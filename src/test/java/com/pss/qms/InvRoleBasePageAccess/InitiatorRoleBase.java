package com.pss.qms.InvRoleBasePageAccess;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.InvestigationLoginDetails;


public class InitiatorRoleBase extends InvestigationLoginDetails {
  
	
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"investigation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("invAdminIdInQms")).click();
        Thread.sleep(10000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
         Select Roles = new Select(driver.findElement(By.id("rolesInInvRoleBaseAccessPage")));
         Roles.selectByVisibleText(properties.getProperty("Role_Name1"));;
         Thread.sleep(3000);
         driver.findElement(By.id("fullInvPlanMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("fullInvPerformMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("fullInvViewMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("fullInvReviewMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor scl = ((JavascriptExecutor) driver);
         scl.executeScript("window.scrollBy(0,500)");
         Thread.sleep(5000);
         driver.findElement(By.id("fullInvReassignAuditMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("statusAuditMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         WebElement element = driver.findElement(By.id("submitButtonInInvRoleBaseAccess"));
         jse.executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(1000);
         driver.findElement(By.id("submitButtonInInvRoleBaseAccess")).click();
        Thread.sleep(3000); 
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(3000);
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









	

