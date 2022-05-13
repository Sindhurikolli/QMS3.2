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
public class AddendumRequest extends DeviationLoginDetails {
    
  @Test
    public void AddendumDeviation() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("deviationInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"devAddendumListId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"devAddendumListId\"]/ul/li[1]/a")).click();
        Thread.sleep(10000);
        methodToDoAddendumDeviation();
    }
      
    private void  methodToDoAddendumDeviation() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
        String changeCtrlSequence = properties.getProperty("DEVIATION_NO");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String chgCtrlNoWithPlantCode = changeCtrlSequence + "/";
        Date date = new Date();
        String sdf = new SimpleDateFormat("YYYY").format(date);
        String chgCtrlId = "/045";
        String DeviationNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdDeptReviewDeviation(DeviationNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(5000);
            driver.findElement(By.id("detailsOfAddendumInDevAddendumReq")).clear();
            Thread.sleep(5000);
            driver.findElement(By.id("detailsOfAddendumInDevAddendumReq")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            Thread.sleep(5000);
            driver.findElement(By.id("descOfAddendumInDevAddendumReq")).clear();
            Thread.sleep(5000);
            driver.findElement(By.id("descOfAddendumInDevAddendumReq")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            Thread.sleep(5000);
//             JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("submitBtnIdInDevDeptReview"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
//            Thread.sleep(5000);
            driver.findElement(By.id("ccAddSuppDocsInDevAddendumReq")).click();
            Thread.sleep(5000);
            driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(5000);
            Select DeptReview = new Select(driver.findElement(By.id("selectAddendumReviweStage2")));
            DeptReview.selectByIndex(2);
            Thread.sleep(4000);
            Select QAReview = new Select(driver.findElement(By.id("selectAddendumReviweStage3")));
            QAReview.selectByIndex(3);
            Thread.sleep(4000);
            Select DeptHOD = new Select(driver.findElement(By.id("selectAddendumReviweStage5")));
            DeptHOD.selectByIndex(3);
            Thread.sleep(4000);
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
            Thread.sleep(5000);
            driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(5000);
            driver.findElement(By.id("subBtnInValESignInDev")).click();
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            Thread.sleep(5000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
        }

private boolean selectRecdDeptReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("closedDevsTableForAddendum"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='closedDevsTableForAddendum']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum'\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum'\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum\"]/div/table/tbody/tr/td[3]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"closedDevsTableForAddendum\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#closedDevsTableForAddendum' > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("closedDevsTableForAddendum'"));//Document Tree approve table
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

    







