package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
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
public class MaterialModification extends RegistrationLoginDetails {
    
  @Test
    public void MaterialRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("modifySelectionInMaterial")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"existingMaterialGridId\"]/div/div[4]/table/tbody/tr[1]/td[3]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("materialCodeInRegisterMaterlInQms")).clear();
        Thread.sleep(4000);
        driver.findElement(By.id("materialCodeInRegisterMaterlInQms")).sendKeys(properties.getProperty("MATERIAL_CODE"));
        Thread.sleep(4000);
//        Select changetype = new Select(driver.findElement(By.id("materialTypeInRegisterMatrlInQms")));
//        changetype.selectByIndex(2);
//        Thread.sleep(4000);
//        driver.findElement(By.id("matrlDescriptionInRegisterMatrlInQms")).sendKeys(properties.getProperty("MATERIAL_DESC"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("materialRegSelect")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
//        Thread.sleep(5000);
        driver.findElement(By.id("regMtrlSubBtnId")).click();
        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
//        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
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




