package com.pss.qms.DevAdminRoleCreation;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.DeviationLoginDetails;


public class InitiatorRole extends DeviationLoginDetails {
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("adminInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(5000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("roleNameInCreateRoleInDev")).sendKeys(properties.getProperty("Role_Name1"));
        Thread.sleep(3000);
        driver.findElement(By.id("descriptionInCreateRoleInDev")).sendKeys(properties.getProperty("Description"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnIdInCreateDevRole")).click();
        Thread.sleep(3000); 
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValESignInDev")).click();
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






