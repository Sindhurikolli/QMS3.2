package com.pss.qms.ChangeControlRoleBaseAccess;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.CCLoginDetails;
 

public class InitiatorRoleBase extends CCLoginDetails {
 
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("adminInCC")).click();
        Thread.sleep(10000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(3000);
         Select Roles = new Select(driver.findElement(By.id("rolesInCCRoleBaseAccessPage")));
         Roles.selectByVisibleText(properties.getProperty("Role_Name1"));;
         Thread.sleep(3000);
         driver.findElement(By.id("ccInitateMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("ccSaveAsDraftMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("ccRejectedMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("ccReviewApprovalMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor scl = ((JavascriptExecutor) driver);
         scl.executeScript("window.scrollBy(0,500)");
         Thread.sleep(5000);
         driver.findElement(By.id("ccPostAppActionsInitiateMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
         scl1.executeScript("window.scrollBy(0,500)");
         Thread.sleep(5000);
         driver.findElement(By.id("ccPerformMenu")).click();
         Thread.sleep(3000); 
         driver.findElement(By.id("ccActionItemExtInitiateMenu")).click();
         Thread.sleep(3000); 
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         WebElement element = driver.findElement(By.id("submitButtonInCcRoleBaseAccess"));
         jse.executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(3000);
         driver.findElement(By.id("submitButtonInCcRoleBaseAccess")).click();
        Thread.sleep(3000); 
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










