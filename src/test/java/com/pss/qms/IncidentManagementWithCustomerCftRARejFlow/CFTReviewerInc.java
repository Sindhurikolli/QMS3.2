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
public class CFTReviewerInc extends IncidentManagementLoginDetails {
    
   @Test
    public void CFTReviewerIncident() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CFTReviewerInc"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CFTReviewerInc", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CFTReviewer"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText(properties.getProperty("IncidentModule"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(1000);
			
			
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Investigation Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			sno++;
        wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='incCFTReview.do']")));
        driver.findElement(By.cssSelector("a[href='incCFTReview.do']")).click();
        Thread.sleep(5000);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CFT Review",sno,false);
        sno++;
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#incCFTReviewTable > div > div.jtable-busy-message[style='display: none;']")));
        methodToDoCFTReviewerIncident();
        
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
        
        
        
//    }

    private void methodToDoCFTReviewerIncident() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;

        String IncidentNumber = properties.getProperty("IncidentNo");
        isRecordSelected = selectRecdCFTReviewDeviation(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {

            Thread.sleep(4000);   
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0, 200)");
             driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInIncCFTReview\"]/div/div[3]/div[2]/span/span[1]")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Supporting Documents Button",sno,false);
             sno++;
             Thread.sleep(2000);
             driver.findElement(By.id("uploadSuppDocCFT_2")).sendKeys(properties.getProperty("Document-1"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Document",sno,false);
             sno++;
             Thread.sleep(3000);
             driver.findElement(By.id("commentsInIncCFTReviewForm")).sendKeys(properties.getProperty("CommentsCFTReview_2000"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
             jse.executeScript("window.scrollBy(0, 400)");
             sno++;
             Thread.sleep(2000);
             driver.findElement(By.id("submitBtnInIncCFTReviewForm")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
             sno++;
             Thread.sleep(2000);
             driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
             sno++;
             Thread.sleep(2000);
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

    private boolean selectRecdCFTReviewDeviation(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("incCFTReviewTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	 WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"incCFTReviewTable\"]/div/div[4]/div[2]/span"));
             JavascriptExecutor jsshow = (JavascriptExecutor)driver;
             jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
            String a = driver.findElement(By.xpath("//*[@id=\"incCFTReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incCFTReviewTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incCFTReviewTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='incCFTReviewTable']/div/table/tbody/tr[ " + i + "]/td[20]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    	
                        String IncNumberSequence = driver.findElement(By.xpath(".//*[@id='incCFTReviewTable']/div/table/tbody/tr[ " + i + " ]/td[20]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='incCFTReviewTable']/div/table/tbody/tr[ " + i + " ]/td[20]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incCFTReviewTable\"]/div/table/tbody/tr/td[20]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='incCFTReviewTable']/div/table/tbody/tr/td[20]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	                	                	
                    driver.findElement(By.cssSelector("#incCFTReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(20000);
                    table = driver.findElement(By.id("incCFTReviewTable"));//Document Tree approve table
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
   


    



