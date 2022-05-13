package com.pss.qms.DeviationAddendum;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.DeviationLoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class AddendumStatus extends DeviationLoginDetails {
    
    @Test
    public void AddendumStatus() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("deviationInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"devAddendumListId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"devAddendumListId\"]/ul/li[4]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("searchAddendumStatusButtonId")).click();
        Thread.sleep(10000);
        methodToDoAddendumStatus();
    }
      
    private void  methodToDoAddendumStatus() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
        String changeCtrlSequence = properties.getProperty("DEVIATION_NO");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String chgCtrlNoWithPlantCode =changeCtrlDept + "-" + changeCtrlSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "-030";
        String DeviationNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdDeviationStatus(DeviationNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/div[8]/div[1]/button/span[1]")).click();
            Thread.sleep(5000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
        }

private boolean selectRecdDeviationStatus(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("addendumDetailsTableInAddendumStatus"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='addendumDetailsTableInAddendumStatus']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr[ " + i + " ]/td[2]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr[ " + i + " ]/td[9]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr/td[2]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"addendumDetailsTableInAddendumStatus\"]/div/table/tbody/tr/td[9]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#addendumDetailsTableInAddendumStatus > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("addendumDetailsTableInAddendumStatus"));//Document Tree approve table
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

    









