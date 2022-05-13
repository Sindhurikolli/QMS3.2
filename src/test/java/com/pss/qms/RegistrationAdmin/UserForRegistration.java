package com.pss.qms.RegistrationAdmin;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.RegistrationLoginDetails;
 

public class UserForRegistration extends RegistrationLoginDetails {
  
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("adminInQmsReg")).click();
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
        driver.findElement(By.id("roleNameInCreateRoleInReg")).sendKeys(properties.getProperty("Role_Name1"));
        Thread.sleep(5000);
        driver.findElement(By.id("descriptionInCreateRoleInReg")).sendKeys(properties.getProperty("Description"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnIdInCreateCcRole")).click();
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






	

