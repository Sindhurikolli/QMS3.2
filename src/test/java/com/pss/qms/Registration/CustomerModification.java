package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class CustomerModification extends RegistrationLoginDetails {
    
     @Test
    public void ProductRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[9]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("modifySelectionInCustomer")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"existingCustomerGridId\"]/div/div[4]/table/tbody/tr/td[3]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        
        driver.findElement(By.id("custCodeInCustReg")).clear();
        Thread.sleep(4000);
        driver.findElement(By.id("custCodeInCustReg")).sendKeys(properties.getProperty("CUSTOMER_NO"));
        Thread.sleep(4000);
//        driver.findElement(By.id("selBtnToAddMarketsInCustReg")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.xpath("//*[@id=\"marketTableInCustRegnWin\"]/div/div[3]/table/tbody/tr[10]/td[1]/input")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("SelBtnInMarketWin")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("selLocBtnInRegDocumentAdd")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@id=\"customerTableInProdRegnWin\"]/div/div[4]/table/tbody/tr[7]/td[1]/input")).click();
//        Thread.sleep(5000);
//         JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.id("SelBtnInCustomerWin"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(1000);
        driver.findElement(By.id("CusCrtAppSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
        Thread.sleep(5000);
         boolean isRecordSelectedForAdmin = false;
             String AdminFirstName = properties.getProperty("ADMIN_FIRST_NAME");
//            String regLastName  = "reviewer3";
             String AdminFullName = AdminFirstName;
             int count3 = 0;
             isRecordSelectedForAdmin = selectingTheAdminUser(AdminFullName, isRecordSelectedForAdmin, count3);
             Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTableInDev\"]/div/table/tbody/tr[2]/td[2]")).click();
//            Thread.sleep(3000);
             driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
             Thread.sleep(5000);
        driver.findElement(By.id("customerRegSubBtnId")).click();
        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
//        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
    }

    private boolean selectingTheAdminUser(String AdminFullName, boolean recordSelectedForAdmin, int count3) {
        WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
        if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
            if (((AdminFullName == null) || ("".equalsIgnoreCase(AdminFullName)))) {
                AdminFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
            } else if ((AdminFullName == null) || ("".equalsIgnoreCase(AdminFullName))) {
                AdminFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

            }
            ++count3;
        }
        if (perPageNoOfRecordsPresent > 0) {
            //while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                        System.out.println("cftReviewerFullName: "+cftReviewerFullName);
                        if (AdminFullName.equalsIgnoreCase(cftReviewerFullName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                            recordSelectedForAdmin = true;
                            break;
                        }
                    }
                   
                } else {
                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
                    if (AdminFullName.equalsIgnoreCase(cftReviewerFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
                        recordSelectedForAdmin = true;
                        
                    }
                }
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
            //}
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


    




    





