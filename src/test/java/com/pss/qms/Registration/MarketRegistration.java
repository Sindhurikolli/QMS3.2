package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class MarketRegistration extends RegistrationLoginDetails {
    
    
   @Test
    public void toMarketRegistration() throws InterruptedException {
        Thread.sleep(2000);
        
        driver.findElement(By.cssSelector("#loginform > div:nth-child(2) > input[name='loginUserName']")).sendKeys(properties.getProperty("ADMIN"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div.row.col-sm-12.password-container > input[name='loginPassword']")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a[href='qmsAddMarketPage.do']")).click();
        Thread.sleep(5000);
        todoMarketRegistration();
   }
        public void todoMarketRegistration() throws InterruptedException {    
        	
        	JavascriptExecutor jse = (JavascriptExecutor)driver;
        	jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.linkText("Next")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("marketInCreateMarketRegn")).sendKeys(properties.getProperty("MARKET_NAME"));
        Thread.sleep(3000);
        driver.findElement(By.id("descInCreateMarketRegn")).sendKeys(properties.getProperty("MARKET_DESC"));
        Thread.sleep(4000);
        driver.findElement(By.id("marketRegSubBtnId")).click();
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(1000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("username")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
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



