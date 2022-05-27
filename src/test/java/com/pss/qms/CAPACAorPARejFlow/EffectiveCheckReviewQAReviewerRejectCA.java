package com.pss.qms.CAPACAorPARejFlow;

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
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

public class EffectiveCheckReviewQAReviewerRejectCA extends CAorPALoginDetails {

	@Test
	public void EffectiveCheckReviewQAReviewerRejectCA() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "EffectiveCheckReviewQAReviewerRejectCA"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("EffectiveCheckReviewQAReviewerRejectCA", "PSS-QMS-041",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QMSCoordinator"));
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
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='effChkImplPostPerfom.do']")));
		driver.findElement(By.cssSelector("a[href='effChkImplPostPerfom.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on Effectiveness check Review & approval", sno, false);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#effectiveCheckImplPostPerformTable > div > div.jtable-busy-message[style='display: none;']")));
		toCAPAEffectiveCheckReview();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAEffectiveCheckReview() throws Exception {

		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String CAPANumber = properties.getProperty("ActionIdCA");
		isRecordSelected = selectRecdCAPAEffectiveCheckImp(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(1000);
		if (isRecordSelected) {
			WebDriverWait loadqahod = new WebDriverWait(driver, 70);
			loadqahod.until(ExpectedConditions.invisibilityOf(driver
					.findElement(By.cssSelector("#capaWfReviewUserDetailsContainer > div > div.jtable-busy-message"))));
			sno++;
			driver.findElement(By.id("commentsInEfctChkImplPostPerformForm"))
					.sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			WebElement element1 = driver.findElement(By.id("rejInCapaEffectiveCheckPostPerformImpl"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", element1);
			driver.findElement(By.id("rejInCapaEffectiveCheckPostPerformImpl")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on reject button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature Password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(1000);
//	        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//	        Thread.sleep(3000);
//	        String separateCAPANumber[] = CAPAString.split(" ");
//	        System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//	        String CAPANo = separateCAPANumber[5]; 
			Thread.sleep(2000);
//	        CAPANo = CAPANo.replace(")", ""); 
//	        finalCAPANumber = CAPANo.trim();
//	        System.out.println("finalCAPANumber: "+finalCAPANumber);
//	        Thread.sleep(5000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
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

	private boolean selectRecdCAPAEffectiveCheckImp(String CAPANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("effectiveCheckImplPostPerformTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			WebElement element1 = driver
					.findElement(By.xpath("//*[@id=\'effectiveCheckImplPostPerformTable\']/div/div[4]/div[2]/span"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", element1);
			String a = driver
					.findElement(By.xpath("//*[@id=\'effectiveCheckImplPostPerformTable\']/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
				CAPANumber = driver
						.findElement(
								By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[7]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver
						.findElement(
								By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[7]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i
										+ " ]/td[7]"))
								.getText();// documentTypeName
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr[ " + i
											+ " ]/td[7]"))
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
							.findElement(By
									.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[7]"))
							.getText();
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						WebElement element14 = driver.findElement(
								By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[7]"));
						JavascriptExecutor js14 = (JavascriptExecutor) driver;
						js14.executeScript("arguments[0].scrollIntoView(true);", element14);
						driver.findElement(
								By.xpath("//*[@id=\"effectiveCheckImplPostPerformTable\"]/div/table/tbody/tr/td[7]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {

					Helper.clickElement(driver, By.cssSelector("#effnessCheckReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));// next page in Document approve list
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#effnessCheckReviewTable > div > div.jtable-busy-message[style='display: none;']"));
					table = driver.findElement(By.id("effectiveCheckImplPostPerformTable"));// Document Tree approve
																							// table
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
