package com.pss.qms.RegistrationAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

public class RegAdminRoleBaseAccess extends RegistrationLoginDetails {
 
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("adminInQmsReg")).click();
        Thread.sleep(10000);
        createRolePageAccessMethod(driver);

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRolePageAccessMethod(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        Select rolesDropDownList = new Select(driver.findElement(By.id("rolesInRegRoleBaseAccPage")));
        rolesDropDownList.selectByVisibleText(properties.getProperty("ADMIN_USERNAME"));
        Thread.sleep(2000);
        if ("1".equalsIgnoreCase(properties.getProperty("ROLE_ID_QMS_ROLE_PAGE_ACCESS"))) {
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForRegistrationAdd")).click();//My Activities Tab
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForRegistrationApp")).click();//My Activities Tab
            Thread.sleep(1000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("selectAllForRegistrationRej"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForRegistrationRej")).click();//Registration Tab
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForRegistrationModify")).click();//Schedule Tab
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForREGAuditTrails")).click();//Schedule Tab
            Thread.sleep(1000);
            driver.findElement(By.id("selectAllForRegAdmin")).click();
            Thread.sleep(2000);
        }
        driver.findElement(By.id("submitButtonInCcRoleBaseAccess")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
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

	






	

