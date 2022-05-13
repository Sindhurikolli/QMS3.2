package com.pss.qms.MarketComplaintsRejectionWithInvestigation;

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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class CFTReviewMC extends MCLoginDetails {
	@Test

    public void CFTReviewMC() throws InterruptedException {
    	try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CFTReviewMC"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CFTReviewMC", "PSS-QMS-052",
					"Pass");
			writer.setPageEvent(event);
			document.open();
        Thread.sleep(2000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CFT_USER"));

		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		 Thread.sleep(5000);
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
		Thread.sleep(5000);
		sno++;                            
		driver.findElement(By.cssSelector("#marketComplaints_tile_Id > div > div > div > h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Market Complaints module",sno,false);
        Thread.sleep(6000);
        sno++;                         
    driver.findElement(By.cssSelector("a[href='mcMyActCftReviewPage.do']")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CFT Review menu",sno,false);
    Thread.sleep(20000);
    methodToDoCFTReviewerMC();
    document.close();
	writer.close();
	Desktop desktop = Desktop.getDesktop();
	File file = new File(output);
	//desktop.open(file);
} catch (Exception e) {
	e.printStackTrace();
}
    }

    private void methodToDoCFTReviewerMC() throws Exception {
    	sno++;
        int count = 0;
        Thread.sleep(2000);
        boolean isRecordSelected = false;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String MarketComplaintsId = properties.getProperty("MC_ID");
        isRecordSelected = selectRecdCFTReviewerMC(MarketComplaintsId, isRecordSelected, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno,false);
        Thread.sleep(8000);
        if (isRecordSelected) {
        	sno++;
            driver.findElement(By.id("mcAddSuppDocs")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("uploadSuppDocCFT_1")).sendKeys(properties.getProperty("Document-1"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno,false);
            Thread.sleep(2000);
        	sno++;
            driver.findElement(By.id("commentsInMcCFTReviewForm")).sendKeys(properties.getProperty("MC_994"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("submitBtnInMcCFTReviewForm")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,false);
            Thread.sleep(4000);
            sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,false);
            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,false);
            Thread.sleep(1000);
            sno++;
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,false);
            Thread.sleep(4000);
            sno++;                       
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/a/span")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
    		Thread.sleep(5000);
    		sno++;
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/ul/li[3]/a")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,false);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdCFTReviewerMC(String MCNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("mcCFTReviewTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((MCNumber == null) || ("".equalsIgnoreCase(MCNumber)))) {
                MCNumber = driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/table/tbody/tr[1]/td[16]")).getText();//documentType
            } else if ((MCNumber == null) || ("".equalsIgnoreCase(MCNumber))) {
                MCNumber = driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/table/tbody/tr/td[16]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='mcCFTReviewTable']/div/table/tbody/tr[ " + i + " ]/td[16]")).getText();//documentTypeName
                        if (MCNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[28]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    Thread.sleep(3000);
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/table/tbody/tr/td[16]")).getText();
                    Thread.sleep(3000);
                    if (MCNumber.equalsIgnoreCase(capaNumberSequence)) {
                        Thread.sleep(3000);
                        driver.findElement(By.xpath("//*[@id=\"mcCFTReviewTable\"]/div/table/tbody/tr/td[28]/button")).click();
                        Thread.sleep(3000);
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#mcCFTReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("mcCFTReviewTable"));//Document Tree approve table
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



    

