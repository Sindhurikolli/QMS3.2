package com.pss.qms.MarketComplaintsRejectionWithInvestigation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.MCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class SecondaryInvestigationReject extends MCLoginDetails {
    @Test
    public void InvestigationRejectMC() throws InterruptedException {
    	try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "SecondaryInvestigationReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("SecondaryInvestigationReject", "PSS-QMS-039",
					"Pass");
			writer.setPageEvent(event);
			document.open();
        Thread.sleep(2000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Invest_User"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);

		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(3000);
    driver.findElement(By.cssSelector("#investigation_tile_Id > div > div > div > h2")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation module",sno,false);
    Thread.sleep(10000);
    sno++;
//    WebElement element = driver.findElement(By.cssSelector("#invInvestigationMenu > a"));
//    JavascriptExecutor js = (JavascriptExecutor)driver;
//    js.executeScript("arguments[0].click();", element);
    
//  driver.findElement(By.cssSelector("a[href='invInvestigation.do']")).click();
  
    driver.findElement(By.cssSelector("#invInvestigationMenu > a")).click();
    Thread.sleep(10000);
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation menu",sno,false);
    WebDriverWait wait = new WebDriverWait(driver, 240);
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-busy-message"))));
    
    methodToDoInvestigationMC();
    document.close();
	writer.close();
	Desktop desktop = Desktop.getDesktop();
	File file = new File(output);
	//desktop.open(file);
} catch (Exception e) {
	e.printStackTrace();
}
    }

 
    private void  methodToDoInvestigationMC() throws Exception {
    	sno++;
        int count = 0;
//        Thread.sleep(2000);
        boolean isRecordSelected = false;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String MarketComplaintsId = properties.getProperty("MC_ID");
        isRecordSelected = selectRecdInvestigationMC(MarketComplaintsId, isRecordSelected, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno,false);
        Thread.sleep(8000);
        if (isRecordSelected) {
        	sno++;
        	JavascriptExecutor jse = (JavascriptExecutor) driver;
        	WebElement element = driver.findElement(By.id("rejectActivityInCheckInInvRev"));
        	 jse.executeScript("arguments[0].scrollIntoView(true);", element);
        	driver.findElement(By.id("rejectActivityInCheckInInvRev")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reject activity checkbox",sno,false);
            Thread.sleep(4000);
            sno++;
            driver.findElement(By.id("rejectedCommentsInInvActionsInInvPlan")).sendKeys(properties.getProperty("MC_994"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Investigation details", sno,false);
            Thread.sleep(4000);
//            sno++;
//            driver.findElement(By.id("summaryInPerformInvActionsInInvPlan")).sendKeys(properties.getProperty("MC_2000"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Summary and conclusion", sno,false);
//            Thread.sleep(4000);
//            
////            driver.findElement(By.id("chronologyofeventInPerformInvActionsInInvPlan")).sendKeys(properties.getProperty("Compliant_Short_Description"));
////            Thread.sleep(4000);
////            driver.findElement(By.id("suppDocumentInPerfromInvActInInvRevForm")).sendKeys(properties.getProperty("Document-1"));
////            Thread.sleep(4000);
//            sno++;
//            driver.findElement(By.id("addSupDocsBtn_ForInvActionUpload_InvReviewPage")).click();
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//            Thread.sleep(2000);
//            sno++;
//            driver.findElement(By.id("uploadSuppDocForInAct_1")).sendKeys(properties.getProperty("Document-1"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno,false);
//            Thread.sleep(2000);
            sno++;
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        	WebElement element1 = driver.findElement(By.id("submitBtnInRejectInvRev"));
        	 jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
            driver.findElement(By.id("submitBtnInRejectInvRev")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,false);
            Thread.sleep(2000);
            sno++;
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,false);
            Thread.sleep(4000);
            sno++;                       
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[6]/a/span")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//    		Thread.sleep(5000);
    		sno++;
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[6]/ul/li[3]/a")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,false);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

private boolean selectRecdInvestigationMC(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("investigationTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[1]/td[6]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[6]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr[ " + i + " ]/td[6]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr[ " + i + " ]/td[6]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr/td[6]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr/td[6]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("investigationTableContainer"));//Document Tree approve table
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
