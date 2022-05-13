package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class EquipmentModification extends RegistrationLoginDetails {
    
     @Test
    public void EquipmentModification() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[6]/a")).click();
        Thread.sleep(15000);
        driver.findElement(By.id("modifySelectionInEquipment")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"existingEquipmentsGridId\"]/div/div[4]/table/tbody/tr[4]/td[3]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        Select Equip_Type = new Select(driver.findElement(By.id("equpTypeInQmsReg")));
        Equip_Type.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("EqupRegSubBtnId")).click();
        Thread.sleep(4000);
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

   