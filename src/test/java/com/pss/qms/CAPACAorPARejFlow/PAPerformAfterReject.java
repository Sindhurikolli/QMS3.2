
package com.pss.qms.CAPACAorPARejFlow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
  
 
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class PAPerformAfterReject extends CAorPALoginDetails {

	@Test
	public void PerformCAAfterReject() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPerformAfterReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPerformAfterReject", "PSS-QMS-028", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText("CAPA");
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='paRejectTaskPage.do']")));
		driver.findElement(By.cssSelector("a[href='paRejectTaskPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reverted Performed CAPAs", sno,
				false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#prevActRejectTable > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoCAPerformTaskCAPA();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoCAPerformTaskCAPA() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String SNO = properties.getProperty("ActionIdPA");
		String CAPANumber = SNO;
		sno++;
		isRecordSelected = selectRecdCAPerformTaskCAPA(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,3000)");
			WebDriverWait waitmenu = new WebDriverWait(driver, 240);
			waitmenu.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#supportingDocsJtableInPaPerform > div > div.jtable-busy-message")));
			driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInPaPerform\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on add supporting documents button ", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "upload the document", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("commentsInRejectForm")).sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			WebElement element = driver.findElement(By.id("selectApprovedFromInPaReject"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			sno++;
			driver.findElement(By.id("selectApprovedFromInPaReject")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on approve from select button",
					sno, false);
			Thread.sleep(2000);
			sno++;

			waitmenu.until(ExpectedConditions.presenceOfElementLocated(By.id("locTreeInQmsProdReg_2_span")));
			driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
			Thread.sleep(2000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			waitmenu.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(6000);
			sno++;
			boolean isRecordSelectedForApprove = false;
			String CAPALastName = properties.getProperty("PerformApprovalCA_E-Code");
			int count4 = 0;
			isRecordSelectedForApprove = selectingTheResponsiblePerson(CAPALastName, isRecordSelectedForApprove,
					count4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the user", sno, false);
			sno++;
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.id("submitBtnInDevActItmRej"));
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
//            Thread.sleep(1000);
			driver.findElement(By.id("submitBtnInDevActItmRej")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the e-signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on submit button", sno, false);
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 240);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(1000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
					.equalsIgnoreCase("Submitted successfully.")) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectingTheResponsiblePerson(String CAPALastName, boolean isRecordSelectedForApprove, int count4)
			throws InterruptedException {

		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if (((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName)))) {
				CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName))) {
				CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String InvFirstName = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						System.out.println("InvReviewFullName: " + InvFirstName);
						if (CAPALastName.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForApprove = true;

							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CAPALastName.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForApprove = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForApprove) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[1]/span[1]/span[5]"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForApprove;
	}

	private boolean selectRecdCAPerformTaskCAPA(String CAPANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("prevActRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"prevActRejectTable\"]/div/div[4]/div[2]/span")).getText();// For
																														// Ex:
																														// Showing
																														// 1-1
																														// of
																														// 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver
								.findElement(By
										.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"prevActRejectTable\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					Helper.clickElement(driver, By.cssSelector("#prevActRejectTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					driver.findElement(By.cssSelector("#prevActRejectTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#prevActRejectTable > div > div.jtable-busy-message[style='display: none;']"));
					table = driver.findElement(By.id("prevActRejectTable"));// Document Tree approve table
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
