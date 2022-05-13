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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class CFTGroup extends RegistrationLoginDetails {
    
   @Test
    public void CFTGroupRegistration() throws InterruptedException {
	   Thread.sleep(2000);
       
       driver.findElement(By.cssSelector("#loginform > div:nth-child(2) > input[name='loginUserName']")).sendKeys(properties.getProperty("ADMIN"));
       Thread.sleep(2000);
       driver.findElement(By.cssSelector("#loginform > div.row.col-sm-12.password-container > input[name='loginPassword']")).sendKeys(properties.getProperty("Password"));
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
       Thread.sleep(5000);
       driver.findElement(By.cssSelector("a[href='createCFTGroup.do']")).click();
       Thread.sleep(5000);
       todoCFTGroupRegistration();
  }
       public void todoCFTGroupRegistration() throws InterruptedException {   
    	   JavascriptExecutor jse = (JavascriptExecutor)driver;
    	   jse.executeScript("window.scrollBy(0,50)");

    	   driver.findElement(By.linkText("Next")).click();
           Thread.sleep(5000);
        driver.findElement(By.id("selectBtnInCreateCFTGroups")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("searchBtnInPlantSelWin")).click();
        Thread.sleep(4000);
          boolean isRecordSelectedForPlant = false;
            String PlantFirstName = properties.getProperty("CFTPlantName");
            int count3=0;
            isRecordSelectedForPlant=selectingThePlant(PlantFirstName,isRecordSelectedForPlant,count3);
            Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInPlantSelWin")).click();
        Thread.sleep(5000);
        Select UG = new Select(driver.findElement(By.id("userGroupInCreateCftGroup")));
        UG.selectByVisibleText(properties.getProperty("UserGroupforCFT"));
        Thread.sleep(3000);
        Select Department = new Select(driver.findElement(By.id("deptIdInCreateCFTGrp")));
        Department.selectByVisibleText(properties.getProperty("DepartmentforCFT"));
        Thread.sleep(3000);
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
        Thread.sleep(3000);
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
      
    private boolean selectingThePlant(String PlantFirstName, boolean recordSelectedForCFT, int count3) throws InterruptedException {
        WebElement table = driver.findElement(By.id("plantCodeSelGrid"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
       
        if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
            if (((PlantFirstName == null) || ("".equalsIgnoreCase(PlantFirstName)))) {
                PlantFirstName = driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((PlantFirstName == null) || ("".equalsIgnoreCase(PlantFirstName))) {
                PlantFirstName = driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count3;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String PlantFirstNameSequence = driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("PlantFirstNameSequence: "+PlantFirstNameSequence);
                        if (PlantFirstName.equalsIgnoreCase(PlantFirstNameSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            recordSelectedForCFT = true;
                            break;
                        }
                    }
                   
                } else {
                    String PlantFirstNameSequence = driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr/td[3]")).getText();
                    if (PlantFirstName.equalsIgnoreCase(PlantFirstNameSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"plantCodeSelGrid\"]/div/table/tbody/tr/td[3]")).click();
                        recordSelectedForCFT = true;
                        
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!recordSelectedForCFT) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#plantCodeSelGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("plantCodeSelGrid"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return recordSelectedForCFT;
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

    




