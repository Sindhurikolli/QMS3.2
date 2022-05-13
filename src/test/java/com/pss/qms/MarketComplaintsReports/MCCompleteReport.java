
package com.pss.qms.MarketComplaintsReports;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MCCompleteReport extends MCLoginDetails {
    
      @Test
    public void MCReports() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInMC")).click();
        Thread.sleep(10000);
//         driver.findElement(By.id("//*[@id=\"mcInvestigationReportId\"]/a")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.id("invNumInInvRepForm")).sendKeys(properties.getProperty("MC_NUMBER2"));
//        Thread.sleep(3000);
//        driver.findElement(By.id("fromDateInCompRepForm")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("toDateInCompRepForm")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(3000);
//        Select changetype = new Select(driver.findElement(By.id("serverityInCompRepForm")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
        driver.findElement(By.id("locSelBtn_CcReportsPostClosure")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_3_span")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("documentChkInCompRepForm")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchBtn_McCompleteReportPage")).click();
        Thread.sleep(5000);
        methodToDoMCReports();
    }

    private void methodToDoMCReports() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
        String DepartmentCode = properties.getProperty("MC_NUMBER");
        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + DepartmentCode;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "-0008";
        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdMCReports(chgControlNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            Thread.sleep(6000);
            String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(8000);
                 driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[4]/td[10]/button")).click();
                Thread.sleep(8000);
                applicationWindowOrPDF = driver.getWindowHandle();
                Thread.sleep(8000);
                System.out.println("pdfWindow: " + applicationWindowOrPDF);
                Thread.sleep(15000);
                String indepPDFWindow = driver.getWindowHandle();
                System.out.println("indepPDFWindow: " + indepPDFWindow);
                Set <String> pdfWindow = driver.getWindowHandles();
                //Iterator iterator = pdfWindow.iterator();
               for( String pdfWindows : pdfWindow)  {
                      System.out.println(" pdfWindows: " +pdfWindows);
                        if(!applicationWindowOrPDF.equalsIgnoreCase(pdfWindows))   {
                            driver.switchTo().window(pdfWindows);
                            Thread.sleep(6000);
                            //driver.manage().timeouts().implicitlyWait(0, MILLISECONDS);
                            driver.close();
                            Thread.sleep(4000);
                            driver.switchTo().window(applicationWindowOrPDF);
                            Thread.sleep(8000);
                            
                        }
                    }
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
    

    private boolean selectRecdMCReports(String chgControlNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("completeMcReportTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
                chgControlNumber = driver.findElement(By.xpath(".//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[4]/td[3]")).getText();//documentTypeName
                        if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[4]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[4]/td[3]")).getText();
                    if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"completeMcReportTable\"]/div/table/tbody/tr[4]/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath(".//*[@id='completeMcReportTable']/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("completeMcReportTable"));//Document Tree approve table
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
