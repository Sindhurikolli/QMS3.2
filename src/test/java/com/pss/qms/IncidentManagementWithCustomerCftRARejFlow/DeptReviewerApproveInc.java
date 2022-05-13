package com.pss.qms.IncidentManagementWithCustomerCftRARejFlow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
import com.pss.qms.login.IncidentManagementLoginDetails;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class DeptReviewerApproveInc extends IncidentManagementLoginDetails {
    
   @Test
    public void DeptReviewerIncident() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeptReviewerApproveInc"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeptReviewerApproveInc", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DepartmentHod"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText(properties.getProperty("IncidentModule"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Incident Management Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
	    Thread.sleep(4000);
        sno++;
        wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='incReview.do']")));
        driver.findElement(By.cssSelector("a[href='incReview.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review Tab",sno,false);
        Thread.sleep(2000);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#incReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
        methodToDoDeptReviewIncident();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }
      
    private void  methodToDoDeptReviewIncident() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String IncidentNumber = properties.getProperty("IncidentNo");
        isRecordSelected = selectRecdDeptReviewIncident(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {
        sno++;
            driver.findElement(By.id("comments_DevReview")).sendKeys(properties.getProperty("DeptHodComments_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments",sno,false);
            sno++;
              driver.findElement(By.id("immediateCAInIncidentDeptReview")).sendKeys(properties.getProperty("AdditionalComments_2000"));
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Additional Remedial Actions Proposed If Any",sno,false);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0, 400)");
//            WebElement element = driver.findElement(By.id("approve_DevReview"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            sno++;
           driver.findElement(By.id("incAddSuppDocs_DevReview")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
           sno++;
            driver.findElement(By.id("uploadSuppDoc_Dev_Review_1")).sendKeys(properties.getProperty("Document-2"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Document",sno,false); 
            sno++;
            driver.findElement(By.id("approve_DevReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false); 
            sno++;
            driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
            sno++;
            driver.findElement(By.id("subBtnInValESignInInc")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
           sno++;
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
        }

private boolean selectRecdDeptReviewIncident(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("incReviewTableContailner"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr[ " + i + " ]/td[43]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[43]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#incReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(5000);
                    table = driver.findElement(By.id("incReviewTableContailner"));//Document Tree approve table
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

    





