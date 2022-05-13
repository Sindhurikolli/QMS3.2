
package com.pss.qms.CAPAMiscellaneous;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CAorPALoginDetails;
 
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class EffectiveCheckApproval extends CAorPALoginDetails {
   @Test
    public void CAPAEffectiveCheckImp() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath(".//*[@id='capa_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"capaEffChkMainMenuId\"]/a")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"effChkImplPostPerfomMenuId\"]/a")).click();
        Thread.sleep(20000);
        methodToDoCAPAEffectiveCheckImp();
    }

    private void methodToDoCAPAEffectiveCheckImp() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
        String capaSequence = properties.getProperty("CAPA_NUMBER");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + capaSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String CAPAId = properties.getProperty("CAPAId");
        String CAPANumber = capaSequence + sdf + CAPAId;
        isRecordSelected = selectRecdCAPAEffectiveCheckImp(CAPANumber, isRecordSelected, count);
        Thread.sleep(1000);
        if (isRecordSelected) {
//            driver.findElement(By.id("acceptRadioButton")).click();
//            Thread.sleep(1000);
            driver.findElement(By.id("commentsInEfctChkImplPostPerformForm")).sendKeys(properties.getProperty("Task_Description"));
            Thread.sleep(1000);  
            driver.findElement(By.id("appInCapaEffectiveCheckPostPerformImpl")).click();
            Thread.sleep(4000);
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

    private boolean selectRecdCAPAEffectiveCheckImp(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("effectiveCheckImplPostPerformTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[4]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[4]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#effectiveCheckImplPostPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("effectiveCheckImplPostPerformTable"));//Document Tree approve table
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
