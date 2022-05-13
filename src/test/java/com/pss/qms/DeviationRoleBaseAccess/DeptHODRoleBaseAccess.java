package com.pss.qms.DeviationRoleBaseAccess;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;
 

public class DeptHODRoleBaseAccess extends DeviationLoginDetails {
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("adminInDev")).click();
        Thread.sleep(10000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
         Select Roles = new Select(driver.findElement(By.id("rolesInDevRoleBaseAccessPage")));
         Roles.selectByIndex(14);
         Thread.sleep(3000);
         driver.findElement(By.id("devReviewMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("viewDeviationsMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor scl = ((JavascriptExecutor) driver);
         scl.executeScript("window.scrollBy(0,500)");
         Thread.sleep(5000);
         driver.findElement(By.id("apporveDeviationApprovedActions")).click();
         Thread.sleep(3000);
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         WebElement element = driver.findElement(By.id("submitButtonInDevRoleBaseAdevess"));
         jse.executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(1000);
         driver.findElement(By.id("submitButtonInDevRoleBaseAdevess")).click();
        Thread.sleep(3000); 
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValESignInDev")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(1000);
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










