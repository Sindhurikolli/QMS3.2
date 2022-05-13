
package com.pss.qms.CAPAMiscellaneous;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class CAPAClosureApproval extends CAorPALoginDetails {
     @Test
    public void CAPACompletionApproval() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(".//*[@id='capaClosureMainMenuId']/a")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"capaClosureApproveMenuId\"]/a")).click();
        Thread.sleep(20000);
        methodToDoCAPACompletionApproval();
    }

    private void methodToDoCAPACompletionApproval() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
        String capaSequence = properties.getProperty("CAPA_NUMBER");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = changeCtrlDept +  "-" + capaSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String CAPAId = properties.getProperty("CAPAId");
        String CAPANumber = capaSequence + sdf + CAPAId;
        isRecordSelected = selectRecdCAPACompletionApproval(CAPANumber, isRecordSelected, count);
        Thread.sleep(1000);
        if (isRecordSelected) {
            driver.findElement(By.id("capaCloserApprovalComments")).sendKeys(properties.getProperty("Task_Description"));
            Thread.sleep(1000);
            driver.findElement(By.id("closureCapaApproveSubmitBtn")).click();
            Thread.sleep(1000);
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
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdCAPACompletionApproval(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("capaCloseApprovalTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"capaCloseApprovalTableContainer\"]/div/div[4]/div[2]/span"));
            JavascriptExecutor jsshow = (JavascriptExecutor)driver;
            jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
            String a = driver.findElement(By.xpath("//*[@id=\"capaCloseApprovalTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaCloseApprovalTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaCloseApprovalTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='capaCloseApprovalTableContainer']/div/table/tbody/tr[ " + i + " ]/td[3]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='capaCloseApprovalTableContainer']/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='capaCloseApprovalTableContainer']/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='capaCloseApprovalTableContainer']/div/table/tbody/tr/td[3]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='capaCloseApprovalTableContainer']/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	WebElement elementnext = driver.findElement(By.cssSelector("#capaCloseApprovalTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                    driver.findElement(By.cssSelector("#capaCloseApprovalTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("capaCloseApprovalTableContainer"));//Document Tree approve table
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
