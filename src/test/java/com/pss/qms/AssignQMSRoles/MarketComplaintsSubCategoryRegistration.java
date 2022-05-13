package com.pss.qms.AssignQMSRoles;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.QMSLoginDetails;

public class MarketComplaintsSubCategoryRegistration extends QMSLoginDetails {

    @Test
    public void MarketComplaintsAssignRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("MarketComplaintsModule"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#cmGeneralBodySection > a[onclick='redirectToMcAdminTab(adminSetActiveItemIdInAdmin)']")).click();
        Thread.sleep(2000);
        toCreateUsers();
        
            }

    private void toCreateUsers() throws InterruptedException {
        Thread.sleep(1000);
     
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(5000);
        String Category = properties.getProperty("SubCategoryRegistration");
        String Subcategory [] =Category.split(",");
        for(int i=0;i<Subcategory.length;i++)
        {
        driver.findElement(By.id("subCategoryRegInMc")).sendKeys(Subcategory[i]);
        Thread.sleep(2000);
        driver.findElement(By.id("subBtnIdInCreateMcSubCategory")).click();
        Thread.sleep(3000);       
       WebDriverWait wait = new WebDriverWait(driver, 60);
   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
    	  driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
    	  Thread.sleep(1000);
    	  driver.findElement(By.id("subBtnInValidateESign")).click();
    	  Thread.sleep(3000);
    	  driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        	  
      }
     
 		
      driver.findElement(By.className("username")).click();
      Thread.sleep(1000);
      driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/ul/li[4]/a")).click();
      Thread.sleep(1000);
        }
        
}


