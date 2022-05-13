package com.pss.qms.DeviationAdminTab;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class RoleBasePageAccess extends DeviationLoginDetails {
    
    

	@Test
    public void methodtoRoleBasePageAccess() throws InterruptedException {
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("adminInDev")).click();
        Thread.sleep(10000);
         todoRoleBasePageAccess();
    }

    private void todoRoleBasePageAccess() throws InterruptedException {
        Thread.sleep(4000);
        Select Roles= new Select(driver.findElement(By.id("rolesInDevRoleBaseAccessPage")));
        Roles.selectByVisibleText(properties.getProperty("Role_Name"));
        Thread.sleep(3000);
        driver.findElement(By.id("devReviewMenu")).click();
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("submitButtonInDevRoleBaseAdevess"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
        driver.findElement(By.id("submitButtonInDevRoleBaseAdevess")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnInValESignInDev")).click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
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

