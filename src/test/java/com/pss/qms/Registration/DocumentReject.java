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
public class DocumentReject extends RegistrationLoginDetails {
    
    @Test
    public void DocumentRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("reportsInMC")).click();
//        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAppMenusListId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAppMenusListId\"]/ul/li[4]/a")).click();
        Thread.sleep(10000);
        methodToDoProductRegistration();
    }

    private void methodToDoProductRegistration() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String MaterialName = properties.getProperty("DOC_NAME");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("CAPA_NUMBER");
        String chgCtrlNoWithPlantCode = MaterialName;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "-0013";
        String chgControlNumber = chgCtrlNoWithPlantCode;
        isRecordSelected = selectRecdMCReports(chgControlNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            driver.findElement(By.id("qmsDocumentApprovalComments")).sendKeys(properties.getProperty("PROD_COMMENTS"));
            Thread.sleep(4000);
            driver.findElement(By.id("rejBtnInRegDocApprovalWin")).click();
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

    private boolean selectRecdMCReports(String chgControlNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("qmsDocumentApprovalGridPanel"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
                chgControlNumber = driver.findElement(By.xpath(".//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr[ " + i + " ]/td[7]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr/td[3]")).getText();
                    if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"qmsDocumentApprovalGridPanel\"]/div/table/tbody/tr/td[7]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath(".//*[@id='qmsDocumentApprovalGridPanel']/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("qmsDocumentApprovalGridPanel"));//Document Tree approve table
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






