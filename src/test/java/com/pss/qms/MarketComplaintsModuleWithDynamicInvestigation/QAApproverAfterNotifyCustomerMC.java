
package com.pss.qms.MarketComplaintsModuleWithDynamicInvestigation;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

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

public class QAApproverAfterNotifyCustomerMC extends MCLoginDetails {
    @Test
    public void QAApproverAfterNotifyCustomer() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='marketComplaints_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("marketCompInMC")).click(); // //*[@id="mcMyActMenusListId"]/ul/li[2]/a
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"mcMyActMenusListId\"]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        methodToDoQAApproverAfterNotifyCustomer();
    }


    private void methodToDoQAApproverAfterNotifyCustomer() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String MCDept = properties.getProperty("CHG_CNTRL_DEPT");
        String changeCtrlSequence = properties.getProperty("MC_NUMBER");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String chgCtrlNoWithPlantCode = MCDept + "-" + changeCtrlSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "-0014";
        String MCNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdQAApproverNotifyCustomer(MCNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
            Thread.sleep(5000);
//            driver.switchTo().alert().accept();
//            Thread.sleep(5000);
            driver.findElement(By.id("commentsInMcQaFinal")).sendKeys(properties.getProperty("Compliant_Short_Description"));
            Thread.sleep(4000);
            driver.findElement(By.id("approveMcQaFinalReview")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(4000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(4000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
            Thread.sleep(4000);
            driver.findElement(By.className("modal-btn")).click();
            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdQAApproverNotifyCustomer(String MCNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("mcReviewTableContailner"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((MCNumber == null) || ("".equalsIgnoreCase(MCNumber)))) {
                MCNumber = driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((MCNumber == null) || ("".equalsIgnoreCase(MCNumber))) {
               MCNumber = driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='mcReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (MCNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='mcReviewTableContailner']/div/table/tbody/tr[ " + i + " ]/td[46]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='mcReviewTableContailner']/div/table/tbody/tr/td[3]")).getText();
                    if (MCNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='mcReviewTableContailner']/div/table/tbody/tr/td[46]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#mcReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("mcReviewTableContailner"));//Document Tree approve table
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

   

