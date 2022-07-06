
package com.pss.qms.RiskAssessmentRejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.RALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class QAReviewerNotifyCustomer extends RALoginDetails {

	@Test
	public void RANotifyCustomer() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerNotifyCustomer"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerNotifyCustomer", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_Selection"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(3000);
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
//			sno++;
//			Thread.sleep(8000);
//			driver.findElement(By.xpath("//*[@id=\"riskAsmt_tile_Id\"]/div/div/div/h2")).click();
		Thread.sleep(3000);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskAssessment Module", sno,
//					false);
//         sno++;
//         driver.findElement(By.id("myActivitiesInAM")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false); 
		Thread.sleep(2000);
		sno++;
		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='raNotifyCustomerPage.do']")));
		driver.findElement(By.cssSelector("a[href='raNotifyCustomerPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on NotifyCustomer Menu", sno, false);
		Thread.sleep(3000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#riskAssmntNotifyCustomerContainer > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoRANotifyCustomer();
		Thread.sleep(3000);
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoRANotifyCustomer() throws Exception {

		Thread.sleep(5000);
		int count = 0;
		boolean isRecordSelected = false;
		String RASequence = properties.getProperty("RA_NUMBER");
		isRecordSelected = selectRecdRANotifyCustomer(RASequence, isRecordSelected, count);
		Thread.sleep(10000);
		if (isRecordSelected) {
			sno++;
			driver.findElement(By.id("notifyBtnInRaNotifyCustomer")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Customer Notification Button",
					sno, false);
//             sno++;
//             driver.findElement(By.id("customerResponseSkipInRaNotifyCust")).click();
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Customer Responce checkbox",sno,false);
			Thread.sleep(1000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id("custNotificationInRaNotifyCustWindow"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("custNotificationInRaNotifyCustWindow"))
					.sendKeys(properties.getProperty("RA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("notifyBtnInRaNotifyCustWindow")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(1000);
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

	private boolean selectRecdRANotifyCustomer(String RANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("riskAssmntNotifyCustomerContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((RANumber == null) || ("".equalsIgnoreCase(RANumber)))) {
				RANumber = driver
						.findElement(
								By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((RANumber == null) || ("".equalsIgnoreCase(RANumber))) {
				RANumber = driver
						.findElement(
								By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String RANumberSequence = driver.findElement(By.xpath("//*[@id='riskAssmntNotifyCustomerContainer']/div/table/tbody/tr[ " + i + " ]/td[2]")).getText(); // documentTypeName
						if (RANumber.equalsIgnoreCase(RANumberSequence)) {
							WebElement element = driver.findElement(By.xpath("//*[@id='riskAssmntNotifyCustomerContainer']/div/table/tbody/tr[ " + i + " ]/td[2]"));
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", element);
//                            driver.findElement(By.xpath(".//*[@id='riskAssmntNotifyCustomerContainer']/div/table/tbody/tr[ " + i + " ]/td[2]")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String RANumberSequence = driver.findElement(By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/table/tbody/tr/td[2]")).getText();
					if (RANumber.equalsIgnoreCase(RANumberSequence)) {
						WebElement element = driver.findElement(By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/table/tbody/tr/td[2]"));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", element);
//                        driver.findElement(By.xpath("//*[@id=\"riskAssmntNotifyCustomerContainer\"]/div/table/tbody/tr/td[2]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					Helper.clickElement(driver, By.cssSelector("#riskAssmntNotifyCustomerContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					driver.findElement(By.cssSelector("#riskAssmntNotifyCustomerContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#riskAssmntNotifyCustomerContainer > div > div.jtable-busy-message[style='display: none;']"));
					table = driver.findElement(By.id("riskAssmntNotifyCustomerContainer"));// Document Tree approve
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
