/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class EquipmentTypeReSubmitReject extends RegistrationLoginDetails {
    
    @Test
    public void ProductRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[5]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#rejectSelectionInEqptType")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"rejectedEquipmentTypesGridId\"]/div/div[4]/table/tbody/tr/td[3]")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        Thread.sleep(8000);
//        driver.findElement(By.id("productNameInRegisterProdInQms")).clear();
//        Thread.sleep(3000);
//        driver.findElement(By.id("productNameInRegisterProdInQms")).sendKeys(properties.getProperty("PRODUCT_NAME"));
//        Thread.sleep(3000);
//        driver.findElement(By.id("productCodeInRegisterProdInQms")).sendKeys(properties.getProperty("PRODUCT_CODE"));
//        Thread.sleep(4000);
//        Select changetype = new Select(driver.findElement(By.id("productTypeInRegisterProdInQms")));
//        changetype.selectByIndex(2);
//        Thread.sleep(4000);
//        driver.findElement(By.id("prodDescriptionInRegisterProdInQms")).sendKeys(properties.getProperty("PRODUCT_DESC"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("productRegSelect")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("selBtnToAddCustomersInProdReg")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@id=\"customerTableInProdRegnWin\"]/div/div[4]/table/tbody/tr[7]/td[1]/input")).click();
//        Thread.sleep(5000);
//         JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.id("SelBtnInCustomerWin"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(1000);
//        driver.findElement(By.id("SelBtnInCustomerWin")).click();
//        Thread.sleep(5000);
        driver.findElement(By.id("equpRegTypeSubBtnId")).click();
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







   

