package com.pss.qms.InvestigationRoleCreation;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;


import com.pss.qms.login.InvestigationLoginDetails;


public class InitiatorRole extends InvestigationLoginDetails {
  
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"investigation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("invAdminIdInQms")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(5000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("roleNameInCreateRoleInInv")).sendKeys(properties.getProperty("Role_Name1"));
        Thread.sleep(5000);
        driver.findElement(By.id("descriptionInCreateRoleInInv")).sendKeys(properties.getProperty("Description"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnIdInCreateInvRole")).click();
        Thread.sleep(5000); 
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("modal-btn")).click();
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








