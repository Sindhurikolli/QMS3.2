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

public class ActionItemPerformRAModule extends RALoginDetails {

	@Test
	public void ActionItemPerformRA() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ActionItemPerformRAModule"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ActionItemPerformRAModule", "PSS-QMS-013", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_Selection"));
		Thread.sleep(2000);
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
		Thread.sleep(3000);
		sno++;
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='raActItemPerformPage.do']")));
		driver.findElement(By.cssSelector("a[href='raActItemPerformPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on ActionItem Perform Menu", sno,
				false);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#riskAssessmentActItmPerFormTable > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoActionItemPerformRA();
		Thread.sleep(4000);
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoActionItemPerformRA() throws Exception {

		JavascriptExecutor scl = ((JavascriptExecutor) driver);
		scl.executeScript("window.scrollBy(0,500)");
		int count = 0;
		boolean isRecordSelected = false;
		String RASequence = properties.getProperty("RA_NUMBER");
		isRecordSelected = selectRecdActionItemPerformRA(RASequence, isRecordSelected, count);
		Thread.sleep(10000);
		if (isRecordSelected) {
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("refDocInRAActItmPerform")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add Reference Document", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("apprSelBtnInRaAiPerform")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(6000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(4000);
//			driver.findElement(By.id("searchEmpCode")).sendKeys(properties.getProperty("ACTION_ITEM_APPROVER_ECODE"));
//			Thread.sleep(2000);
//			driver.findElement(By.id("usersSearchBtnInRepProb")).click();
			Helper.waitLoadRecords(driver, By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']"));
			boolean isRecordSelectedForUser = false;
			String UserFirstName = properties.getProperty("ACTION_ITEM_APPROVER_ECODE");
			String UserFullName = UserFirstName;
			int count1 = 0;
			isRecordSelectedForUser = selectingTheUserReview(UserFullName, isRecordSelectedForUser, count1);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
//			driver.findElement(By.id("commentsInRAActItmPerform")).clear();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInRAActItmPerform")).sendKeys(properties.getProperty("RA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments", sno, false);
			Thread.sleep(1000);
			sno++;
//           JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[3]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element1);

			driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
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

	private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1)
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
				UserFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
				UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser = true;
							break;
						}
					}
					if (isRecordSelectedForUser) {
						break;
					}
				} else {
					String UserNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']"));
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForUser;
	}

	private boolean selectRecdActionItemPerformRA(String RANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("riskAssessmentActItmPerFormTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"riskAssessmentActItmPerFormTable\"]/div/div[4]/div[2]/span"))
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
								By.xpath("//*[@id=\"riskAssessmentActItmPerFormTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((RANumber == null) || ("".equalsIgnoreCase(RANumber))) {
				RANumber = driver
						.findElement(By.xpath("//*[@id=\"riskAssessmentActItmPerFormTable\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String RANumberSequence = driver.findElement(By.xpath(
								".//*[@id='riskAssessmentActItmPerFormTable']/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (RANumber.equalsIgnoreCase(RANumberSequence)) {
							driver.findElement(
									By.xpath(".//*[@id='riskAssessmentActItmPerFormTable']/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String RANumberSequence = driver
							.findElement(
									By.xpath(".//*[@id='riskAssessmentActItmPerFormTable']/div/table/tbody/tr/td[2]"))
							.getText();
					if (RANumber.equalsIgnoreCase(RANumberSequence)) {
						WebElement element = driver.findElement(
								By.xpath(".//*[@id='riskAssessmentActItmPerFormTable']/div/table/tbody/tr/td[2]"));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", element);

//                        driver.findElement(By.xpath(".//*[@id='riskAssessmentActItmPerFormTable']/div/table/tbody/tr/td[2]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#riskAssessmentActItmPerFormTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("riskAssessmentActItmPerFormTable"));// Document Tree approve table
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
