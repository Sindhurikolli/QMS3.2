package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInv;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
import com.pss.qms.login.IncidentManagementLoginDetails;
 
import com.pss.qms.login.IncidentManagementLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */

public class ActionItemPerformAssignedByQAHOD extends IncidentManagementLoginDetails {

	@Test
	public void toActionItemPerformAssignedByQAHOD() throws Exception {

//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ActionItemPerformAssignedByQAHOD"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("ActionItemPerformAssignedByQAHOD", "PSS-QMS-013", "Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Action_Item_Performer"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByVisibleText(properties.getProperty("IncidentModule"));
			Thread.sleep(1000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(
					sno + "." + "Enter the username, password Select Change Control Module and click on login button"
							+ Utilities.prepareSSNumber(sno),
					font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(10000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
WebDriverWait wait1 = new WebDriverWait(driver, 60);
 wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='incidentActionItemPerform.do']")));
			driver.findElement(By.cssSelector("a[href='incidentActionItemPerform.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Action Item Implementation menu", sno, false);
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccActItemListMenuId > ul > li:nth-child(1) > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Perform submenu", sno,false);
//        Thread.sleep(100000);
			 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#actItmPerformTable > div > div.jtable-busy-message[style='display: none;']")));
			methodToDoActionItemPerform();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}

//	}

	private void methodToDoActionItemPerform() throws Exception {

		Thread.sleep(1000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String Incno = properties.getProperty("IncidentNo");
		String AINumber = Incno+"/A2";

		Thread.sleep(2000);
		isRecordSelected = selectRecdActionItemPerform(AINumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(2000);
		if (isRecordSelected) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1000)");
			
			driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInIncActItemPerform\"]/div/div[3]/div[2]/span/span[1]")).click();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Upload Referance file", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInIncPerform")).sendKeys(properties.getProperty("Comments_1000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(3000);
			sno++;
			
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			WebElement element = driver.findElement(By.id("incactItmPerformSubmitBtn"));
//			jse.executeScript("arguments[0].scrollIntoView(true);", element);
//			Thread.sleep(2000);
			driver.findElement(By.id("incactItmPerformSubmitBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On SUBMIT button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValESignInInc")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			if(driver.findElement(By.className("modal-btn")).isDisplayed())
			{
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK button", sno, false);	
			}
			driver.findElement(By.className("modal-btn")).click();
			
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("body > div.container > header > nav > ul > li.dropdown > a > span"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(4000);
			driver.findElement(
					By.cssSelector("body > div.container > header > nav > ul > li.dropdown > ul > li:nth-child(3) > a"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdActionItemPerform(String AINumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("actItmPerformTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((AINumber == null) || ("".equalsIgnoreCase(AINumber)))) {
				AINumber = driver
						.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((AINumber == null) || ("".equalsIgnoreCase(AINumber))) {
				AINumber = driver
						.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String AINumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (AINumber.equalsIgnoreCase(AINumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i
											+ " ]/td[5]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String AINumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (AINumber.equalsIgnoreCase(AINumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#actItmPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(10000);
					table = driver.findElement(By.id("actItmPerformTable"));// Document Tree approve table
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



