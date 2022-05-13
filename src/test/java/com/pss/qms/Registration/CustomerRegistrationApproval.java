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
public class CustomerRegistrationApproval extends RegistrationLoginDetails {
    
     @Test
    public void toCustomerRegistrationApproval() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div:nth-child(2) > input[name='loginUserName']")).sendKeys(properties.getProperty("ADMIN"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div.row.col-sm-12.password-container > input[name='loginPassword']")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.cssSelector("a[href='qmsCustomerAppPage.do']")).click();
        Thread.sleep(5000);
        methodToDoCustomerRegistrationApproval();
    }

    private void methodToDoCustomerRegistrationApproval() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String CustomerCode = properties.getProperty("CUSTOMER_CODE");
        isRecordSelected = selectRecdMCReports(CustomerCode, isRecordSelected, count);
        
        Thread.sleep(10000);
        if (isRecordSelected) {
            driver.findElement(By.id("customerCommentsInViewCustApp")).sendKeys(properties.getProperty("Approval_Comments"));
            Thread.sleep(4000);
            driver.findElement(By.id("appCusAppView")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(1000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
        }
    }

    private boolean selectRecdMCReports(String CustomerCode, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("customerAppTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CustomerCode == null) || ("".equalsIgnoreCase(CustomerCode)))) {
                CustomerCode = driver.findElement(By.xpath(".//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[1]/td[4]")).getText();//documentType
            } else if ((CustomerCode == null) || ("".equalsIgnoreCase(CustomerCode))) {
                CustomerCode = driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[4]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String CustomerCodeSequence = driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).getText();//documentTypeName
                        if (CustomerCode.equalsIgnoreCase(CustomerCodeSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[7]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String CustomerCodeSequence = driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[4]")).getText();
                    if (CustomerCode.equalsIgnoreCase(CustomerCodeSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[7]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#customerAppTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("customerAppTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
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





