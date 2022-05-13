package com.pss.qms.ChangeControlAuditTrails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class CancelActionItems extends CCLoginDetails {
 
	@Test
    public void AuditTrailsRoles() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("auditTrailsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("locSelBtn_CcTemActItemATSearch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("treeContainer_3_a")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchButtonInCcDelActItemsAT")).click();
        Thread.sleep(5000);
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
        String chgCtrlNoWithPlantCode =  DepartmentCode;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "/0016";
        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdChangeControlDoctReports(chgControlNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
        Thread.sleep(5000); 
        }
    }
    

    private boolean selectRecdChangeControlDoctReports(String chgControlNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("jTableContainerInCcDelActItemsAT"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
                chgControlNumber = driver.findElement(By.xpath(".//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();
                        Thread.sleep(5000);
                        if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                            Thread.sleep(5000);
                            driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr[ " + i + " ]/td[5]/button")).click();
                            Thread.sleep(5000);
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr/td[3]")).getText();
                    if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/table/tbody/tr/td[5]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"jTableContainerInCcDelActItemsAT\"]/div/div[4]/div[1]/span[1]/span[6]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("jTableContainerInCcDelActItemsAT"));//Document Tree approve table
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

