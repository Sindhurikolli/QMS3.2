package com.pss.qms.ChangeControlModule;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class CCStatus extends CCLoginDetails {
    
    @Test
    public void toCCStatus() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("myActivitiesInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"deviationStatusMenu_Id\"]/a")).click();
        Thread.sleep(10000);
         driver.findElement(By.id("searchStatusButtonId")).click();
        Thread.sleep(10000);
        methodToDoCCStatus();
    }

    private void methodToDoCCStatus() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("DEVIATION_NO");
        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
        String chgCtrlNoWithPlantCode = changeCtrlDept+ "-" + DepartmentCode;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "-09";  
        String CCNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdCCStatus(CCNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
            driver.findElement(By.id("closeBtnInCcCustDetWindow")).click();
            Thread.sleep(5000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdCCStatus(String CCNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("deviationStatusTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber)))) {
                CCNumber = driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
                CCNumber = driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String CCNumberSequence = driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[10]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String CCNumberSequence = driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"deviationStatusTableContainer\"]/div/div[4]/div[1]/span[1]/span[8]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("deviationStatusTableContainer"));//Document Tree approve table
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
 





  



