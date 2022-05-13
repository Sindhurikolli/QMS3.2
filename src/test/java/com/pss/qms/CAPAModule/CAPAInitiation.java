package com.pss.qms.CAPAModule;

import com.pss.qms.ExtentTestNGPkg.Utility;
  
 
import com.pss.qms.login.CAorPALoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CAPAInitiation extends CAorPALoginDetails {

    @Test
    public void methodtoinitiatedocumentCAPA() throws InterruptedException {
        Thread.sleep(6000);
        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
        Thread.sleep(8000);
        touploaddocumentCAPA();
    }

    private void touploaddocumentCAPA() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id("newCapaPlanInCapa")).click();
        Thread.sleep(5000);
        Select SourceDropDown = new Select(driver.findElement(By.id("capaSourceInCreateCapaPlan")));
        SourceDropDown.selectByIndex(4);
        Thread.sleep(4000);
        driver.findElement(By.id("referenceNumberInCreateCapaPlan")).sendKeys(properties.getProperty("REFERENCE_NO"));
//        driver.findElement(By.id("continueBtnIdInCreateCapaPlan")).click();
//        Thread.sleep(2000);
        driver.findElement(By.id("descriptionInCreateCapaPlan")).sendKeys(properties.getProperty("Task_Description"));
        Thread.sleep(5000);
        driver.findElement(By.id("taskDescInCapaInitiate")).sendKeys(properties.getProperty("Task_Description"));
        Thread.sleep(5000);
//        String CAPAString="";
        driver.findElement(By.id("submitIdInCreateCapaPlan")).click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(20000);
//        String separateCAPANumber[] = CAPAString.split(" ");
//        System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//        String CAPANo = separateCAPANumber[5]; 
//        Thread.sleep(2000);
//        CAPANo = CAPANo.replace(")", ""); 
//        finalCAPANumber = CAPANo.trim();
//        System.out.println("finalCAPANumber: "+finalCAPANumber);
//        Thread.sleep(5000);        
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(5000);

    }
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
