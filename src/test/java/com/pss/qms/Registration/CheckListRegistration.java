package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class CheckListRegistration extends RegistrationLoginDetails {
    
    @Test
    public void toCheckListRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div:nth-child(2) > input[name='loginUserName']")).sendKeys(properties.getProperty("ADMIN"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div.row.col-sm-12.password-container > input[name='loginPassword']")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a[href='qmsCheckListINRegPage.do']")).click();
        Thread.sleep(5000);
        todoCheckListRegistration();
   }
        public void todoCheckListRegistration() throws InterruptedException {   
        driver.findElement(By.id("newRSChkListRegAction")).click();
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,50)");
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("chkListNameInRSChkListReg")).sendKeys(properties.getProperty("CHECKLIST_NAME"));
        Thread.sleep(3000);
        int noofcheckpoints = Integer.parseInt(properties.getProperty("NoofCheckPoints"));
        for(int i=1;i<=noofcheckpoints;i++)
        {
        driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInRSChkListReg\"]/div/div[3]/div[2]/span/span[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("checkPointInAddChkPointINRS")).sendKeys("Checkpoint - "+i);
        Thread.sleep(2000);
        driver.findElement(By.id("addBtnInAddChkPointInRs")).click();
        Thread.sleep(2000);
        }
        Thread.sleep(3000);
        driver.findElement(By.id("checkListRegSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
        Thread.sleep(5000);
         boolean isRecordSelectedForAdmin = false;
             String ApproverECode = properties.getProperty("ApproverECode");
             int count3 = 0;
             isRecordSelectedForAdmin = selectingTheAdminUser(ApproverECode, isRecordSelectedForAdmin, count3);
             Thread.sleep(3000);
             driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
             Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
        Thread.sleep(5000);
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

    private boolean selectingTheAdminUser(String ApproverECode, boolean recordSelectedForAdmin, int count3) throws InterruptedException {
   
         WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
      
        if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
            if (((ApproverECode == null) || ("".equalsIgnoreCase(ApproverECode)))) {
                ApproverECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((ApproverECode == null) || ("".equalsIgnoreCase(ApproverECode))) {
                ApproverECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count3;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String ApproverECodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
//                        System.out.println("ApproverECodeSequence: "+ApproverECodeSequence);
                        if (ApproverECode.equalsIgnoreCase(ApproverECodeSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            recordSelectedForAdmin = true;
                            break;
                        }
                    }
                   
                } else {
                    String ApproverECodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (ApproverECode.equalsIgnoreCase(ApproverECodeSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        recordSelectedForAdmin = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!recordSelectedForAdmin) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return recordSelectedForAdmin;
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



    





    
    
