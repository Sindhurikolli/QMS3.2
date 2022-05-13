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

public class AuditManagementAssignRole extends QMSLoginDetails {

    @Test
    public void AuditManagementAssignRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("AuditManagementModule"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("assignAmRoleMenu")).click();
        Thread.sleep(2000);
        toCreateUsers();
        
            }

    private void toCreateUsers() throws InterruptedException {
        Thread.sleep(1000);
     
        driver.findElement(By.id("selectBtnInAssignAMRoles")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("locationTreeForSingleUserSelect_1_span")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchEmpCode_InSingleUserSelectDialog")).sendKeys(properties.getProperty("AuditManagementUserE_Code"));
        Thread.sleep(1000);
        driver.findElement(By.id("searchBtn_InSingleUserSelectDialog")).click();
        Thread.sleep(3000);
        if(driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).isDisplayed())
        {
        driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
        Thread.sleep(3000);
        String RoleName = properties.getProperty("AuditManagementRoleName");
        Select Roletoselect = new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_")));
        List<WebElement> allOptionstoselect = Roletoselect.getOptions();
        int jSize = allOptionstoselect.size();
        String [] arrrolestoselect= new String [jSize]; 
        Thread.sleep(3000);
        for (int j = 0; j < jSize; j++) 
        {
        	arrrolestoselect[j]=allOptionstoselect.get(j).getText();
        }
        Select Roleselected = new Select(driver.findElement(By.id("bootstrap-duallistbox-selected-list_")));
        List<WebElement> allOptionsselected = Roleselected.getOptions();
        int iSize = allOptionsselected.size();
        String [] arrrolesselected= new String [iSize]; 
        Thread.sleep(3000);
        for (int i = 0; i < iSize; i++) 
        {
        	arrrolesselected[i]=allOptionsselected.get(i).getText();
        }
        Thread.sleep(3000);
      if(ArrayUtils.contains( arrrolestoselect, RoleName ))
      {
    	  Roletoselect.selectByVisibleText(properties.getProperty("AuditManagementRoleName"));
    	  Thread.sleep(2000);
    	  JavascriptExecutor jse = (JavascriptExecutor)driver;
    	  jse.executeScript("window.scrollBy(0,250)");
    	  Thread.sleep(1000);
    	  driver.findElement(By.id("subBtnInAssignAMRoles")).click();
    	  Thread.sleep(3000);
       WebDriverWait wait = new WebDriverWait(driver, 60);
   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
    	  driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
    	  Thread.sleep(1000);
    	  driver.findElement(By.id("subBtnInValidateESign")).click();
    	  Thread.sleep(3000);
    	  driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        	  
      }
      else if(ArrayUtils.contains( arrrolesselected, RoleName ))
      {
    	  System.out.println("Role is Already Selected");
      }
      else
      {
    	  System.out.println("Role Not Available to Select");
      }  
 		
      driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
      Thread.sleep(1000);
      driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
      Thread.sleep(1000);
}
        else
        {
        	System.out.println("User Not Selected");
        	Assert.assertTrue(false);
        }
    }

	
}
