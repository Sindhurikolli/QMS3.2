
package com.pss.qms.RiskAssessmentModule;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.RALoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RiskAssessmentReport extends RALoginDetails {
    
 @Test
    public void RAReport() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='riskAsmt_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath(".//*[@id='reportsInRA']")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("searchBtnInRaReportsPostClosure")).click();
        Thread.sleep(10000);
        methodToDoQAReviewRAReport();
    }

    private void methodToDoQAReviewRAReport() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String raSequence = properties.getProperty("RA_NUMBER");
        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
        String chgCtrlNoWithPlantCode = DepartmentCode + "/" + raSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "/005";
        String RANumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdQAReviewRAReport(RANumber, isRecordSelected, count);
        Thread.sleep(1000);
        if (isRecordSelected) {
         driver.findElement(By.id("viewBtnInRaReportsPostClosure")).click();
         Thread.sleep(10000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdQAReviewRAReport(String RANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("raReportsPostClosureResultsTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"raReportsPostClosureResultsTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((RANumber == null) || ("".equalsIgnoreCase(RANumber)))) {
                RANumber = driver.findElement(By.xpath("//*[@id=\"raReportsPostClosureResultsTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((RANumber == null) || ("".equalsIgnoreCase(RANumber))) {
                RANumber = driver.findElement(By.xpath("//*[@id=\"raReportsPostClosureResultsTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String RANumberSequence = driver.findElement(By.xpath(".//*[@id='raReportsPostClosureResultsTable']/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (RANumber.equalsIgnoreCase(RANumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='raReportsPostClosureResultsTable']/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String RANumberSequence = driver.findElement(By.xpath(".//*[@id='raReportsPostClosureResultsTable']/div/table/tbody/tr[4]/td[3]")).getText();
                    if (RANumber.equalsIgnoreCase(RANumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='raReportsPostClosureResultsTable']/div/table/tbody/tr[4]/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#raReportsPostClosureResultsTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("raReportsPostClosureResultsTable"));//Document Tree approve table
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
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}


