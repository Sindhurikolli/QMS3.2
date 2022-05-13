package com.pss.qms.CAPAModule;

import com.pss.qms.ExtentTestNGPkg.Utility;
  
 
 
 
import com.pss.qms.login.CAorPALoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CAPerformTask extends CAorPALoginDetails {

    @Test
    public void CAPerformTaskCAPA() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath(".//*[@id='capa_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(16000);
        driver.findElement(By.xpath("//*[@id=\"capaImplementationListMenuId\"]/a")).click();
        Thread.sleep(15000); 
        driver.findElement(By.xpath(".//*[@id='capaCorrectiveActListMenuId']/a")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"caPerformTaskId\"]")).click();
        Thread.sleep(20000);
        methodToDoCAPerformTaskCAPA();
    }

    private void methodToDoCAPerformTaskCAPA() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;                     
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
        String capaSequence = properties.getProperty("CAPA_NUMBER");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = capaSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String CAPAId = properties.getProperty("CAPAId");
        String CAPANumber = capaSequence + sdf + CAPAId;
//        String CAPANumberToTrim = CAPALoginDetailsDepthod.finalCAPANumber;
//        String CAPANumber = CAPANumberToTrim.trim();
//        System.out.println("CAPA Number is coming........:"+CAPANumber);
        isRecordSelected = selectRecdCAPerformTaskCAPA(CAPANumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            Thread.sleep(10000);
            driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[2]")).click();
            Thread.sleep(3000);       
//            driver.findElement(By.id("acceptTaskInCorrActPerform")).click();
//            Thread.sleep(1000);
            driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
            Thread.sleep(1000);
            driver.findElement(By.id("selectApprovedFromInCaPerform")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("commentsInCaPerform")).sendKeys(properties.getProperty("Task_Description"));
            Thread.sleep(1000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("corrActItmPerformSubmitBtn"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
            driver.findElement(By.id("corrActItmPerformSubmitBtn")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(1000);
            String CAPAString="";
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(1000);
//            driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//            Thread.sleep(3000);
//            String separateCAPANumber[] = CAPAString.split(" ");
//            System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//            String CAPANo = separateCAPANumber[5]; 
//            Thread.sleep(2000);
//            CAPANo = CAPANo.replace(")", ""); 
//            finalCAPANumber = CAPANo.trim();
//            System.out.println("finalCAPANumber: "+finalCAPANumber);
//            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdCAPerformTaskCAPA(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("actItmPerformTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#actItmPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("actItmPerformTable"));//Document Tree approve table
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
