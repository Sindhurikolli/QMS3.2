package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class ImpactAssessmentQuestions extends RegistrationLoginDetails {
 
    
     @Test
    public void ImpactAssessmentQuestions() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[13]/a")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("newRSChkListRegAction")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
//        Thread.sleep(10000);
        driver.findElement(By.id("questionInImpAssQueReg")).sendKeys(properties.getProperty("ASSESSMENT_QUES"));
        Thread.sleep(3000);
        driver.findElement(By.id("descriptionInImpAssQueReg")).sendKeys(properties.getProperty("ASSESSMENT_QUES"));
        Thread.sleep(3000);
        
//        driver.findElement(By.id("prodDescriptionInRegisterProdInQms")).sendKeys(properties.getProperty("PRODUCT_DESC"));
//        Thread.sleep(4000);
        driver.findElement(By.id("submitBtnIdInInImpAssQueReg")).click();
        Thread.sleep(4000);
         WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            Thread.sleep(4000);
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

//    private boolean selectingTheAdminReview(String cftFullName, boolean recordSelectedForCFT, int count2) {
//        WebElement table = driver.findElement(By.id("usersTableContainer"));
//        WebElement tableBody = table.findElement(By.tagName("tbody"));
//        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
//        if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
//            if (((cftFullName == null) || ("".equalsIgnoreCase(cftFullName)))) {
//                cftFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
//            } else if ((cftFullName == null) || ("".equalsIgnoreCase(cftFullName))) {
//                cftFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType
//
//            }
//            ++count2;
//        }
//        if (perPageNoOfRecordsPresent > 0) {
//            //while (noOfRecordsChecked < totalNoOfRecords) {
//                if (perPageNoOfRecordsPresent > 1) {
//                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
//                        String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
//                        System.out.println("cftReviewerFullName: "+cftReviewerFullName);
//                        if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
//                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
//                            recordSelectedForCFT = true;
//                            break;
//                        }
//                    }
//                   
//                } else {
//                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
//                    if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
//                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
//                        recordSelectedForCFT = true;
//                        
//                    }
//                
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!recordSelectedForCFT) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number.jtable-page-number-active.jtable-page-number-disabled")).click();//next page in Document approve list
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
//            }
//        }
//        return recordSelectedForCFT;
//    }
//    }





