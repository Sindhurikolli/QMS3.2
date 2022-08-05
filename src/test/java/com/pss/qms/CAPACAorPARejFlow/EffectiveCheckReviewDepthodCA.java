package com.pss.qms.CAPACAorPARejFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

public class EffectiveCheckReviewDepthodCA extends CAorPALoginDetails {

	@Test
    public void DeptHodEffectiveCheckReviewCA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "EffectiveCheckReviewDepthod"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("EffectiveCheckReviewDepthod", "PSS-QMS-040",
					"Pass");
			writer.setPageEvent(event);
			document.open();

//			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME2"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText("CAPA");
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			driver.findElement(By.cssSelector("#loginform > div.buttons.col-md-offset-2 > input:nth-child(1)")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
//			Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
//        sno++;
//        Thread.sleep(15000);
//        WebElement notification = driver.findElement(By.id("ui-id-51"));
//        WebDriverWait waitnoti = new WebDriverWait(driver,70);
//        if(notification.isDisplayed())
//        waitnoti.until(ExpectedConditions.invisibilityOf(notification));
//        driver.findElement(By.id("myActivitiesInCapa")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
       
//        Thread.sleep(15000);
//        driver.findElement(By.cssSelector("#capaEffChkMainMenuId > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Effective Check menu", sno,false);
//        sno++;
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#effChkImplPostPerfomMenuId > a")).click();
        driver.findElement(By.cssSelector("a[href='effChkImplPostPerfom.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Effectiveness check Review & approval", sno,false);
//        Thread.sleep(20000);
        Helper.waitLoadRecords(driver, By.cssSelector("#effectiveCheckImplPostPerformTable > div > div.jtable-busy-message[style='display: none;']"));
//        WebDriverWait load = new WebDriverWait(driver,240);
//        load.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("jtable-busy-message"))));
//        Thread.sleep(10000);
        
//        if(driver.findElement(By.id("ui-id-14")).isDisplayed())
//        {
//        	WebDriverWait waitnotiagain = new WebDriverWait(driver,70);
//        	waitnotiagain.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("ui-id-14"))));
//        }
        
        toCAPAEffectiveCheckReview();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

}

private void toCAPAEffectiveCheckReview() throws Exception {
	sno++;
    int count = 0;
    boolean isRecordSelected = false;
    String CAPANumber = properties.getProperty("CAPAId");
    isRecordSelected = selectRecdCAPAEffectiveCheckImp(CAPANumber, isRecordSelected, count);
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno,false);
//    Thread.sleep(1000);
    if (isRecordSelected) {
    	sno++;
        driver.findElement(By.id("commentsInEfctChkImplPostPerformForm")).sendKeys(properties.getProperty("CAPA_2000"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
//        Thread.sleep(1000);
        sno++;
        driver.findElement(By.id("appInCapaEffectiveCheckPostPerformImpl")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Accept button", sno,false);
//        Thread.sleep(1000);
        sno++;
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature Password", sno,false);
//        Thread.sleep(1000);
        sno++;
        driver.findElement(By.id("subBtnInValidateESign")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno,false);
//        Thread.sleep(1000);
        sno++;
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//        Thread.sleep(1000);
        if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Submitted Successfully ", sno,false);
        Thread.sleep(1000);
        sno++;
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,false);
//        Thread.sleep(4000);
        sno++;
//        Thread.sleep(4000);
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/ul/li[4]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
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
	            CAPANumber = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[6]")).getText();//documentType
	        } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
	            CAPANumber = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[6]")).getText();//documentType

	        }
	        ++count;
	    }
	    if (perPageNoOfRecordsPresent > 0) {
	        while (noOfRecordsChecked < totalNoOfRecords) {
	            if (totalNoOfRecords > 1) {
	                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).getText();//documentTypeName
	                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
	                if (isRecordSelected) {
	                    break;
	                }
	            } else {
	                String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[6]")).getText();
	                if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
	                    driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[6]")).click();
	                    isRecordSelected = true;
	                    break;
	                }
	            }
	            noOfRecordsChecked += perPageNoOfRecordsPresent;
	            if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
	            	Helper.clickElement(driver, By.cssSelector("#effnessCheckReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//	                driver.findElement(By.cssSelector("#effnessCheckReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//	            	driver.findElement(By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
	            	Thread.sleep(3000);
	            	 Helper.waitLoadRecords(driver, By.cssSelector("#effectiveCheckImplPostPerformTable > div > div.jtable-busy-message[style='display: none;']"));
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
