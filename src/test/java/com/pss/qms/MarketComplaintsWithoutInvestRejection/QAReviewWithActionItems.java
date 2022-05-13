package com.pss.qms.MarketComplaintsWithoutInvestRejection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.MCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class QAReviewWithActionItems extends MCLoginDetails {

	@Test
	public void QAReviewWithActionItems() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewWithActionItems"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewWithActionItems", "PSS-QMS-004", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QAHod"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_Selection"));
		Thread.sleep(2000);
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
//			sno++;
		Thread.sleep(3000);
//			driver.findElement(By.cssSelector("#marketComplaints_tile_Id > div > div > div > h2")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Market Complaints module", sno,
//					false);
		sno++;
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='mcMyActReviewPage.do']")));
		driver.findElement(By.cssSelector("a[href='mcMyActReviewPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
//    Thread.sleep(24000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#mcReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoQAReviewer();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	}

	private void methodToDoQAReviewer() throws Exception {

		sno++;
		int count = 0;
		Thread.sleep(2000);
		boolean isRecordSelected = false;
//		Date date = new Date();
//		String sdf = new SimpleDateFormat("YY").format(date);
		String MarketComplaintsId = properties.getProperty("MC_ID");
		isRecordSelected = selectRecdQAReview(MarketComplaintsId, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(2000);
		if (isRecordSelected) {
			WebElement ele = driver.findElement(By.id("dueDateInMcPrim"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", ele);
			Thread.sleep(2000);
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd/M/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 30); // number of days to add
			String futureDate = (String) (formattedDate.format(c.getTime()));
			driver.findElement(By.id("dueDateInMcPrim")).sendKeys(futureDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the due date", sno, false);
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//*[@id=\"qaRevSupportingDocsAddTable_McReviewPage\"]/div/table/tbody/tr/td[4]")).click();
			sno++;
			Thread.sleep(4000);
			driver.findElement(By.id("actItmCheckInMcQaReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on ActionItem checkbox", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//*[@id=\"qaRevActItemsAddTable_McReviewPage\"]/div/div[3]/div[2]/span/span[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add New Record", sno, false);
			sno++;
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#initiateDepartInMcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.id("treeContainer_2_switch")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText(properties.getProperty("AI_Department"))).click();
			// driver.findElement(By.id("treeContainer_6_span")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("selectBtnInLocSelect")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.cssSelector(
					"#ownersTable_McActionItemCreateOrEditDialog > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Action Item owner", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"userTableForActionItemOwnerSel\"]/div/table/tbody/tr/td[3]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the action item owner", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("addBtnInMcActionItemOwnerSelDlg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(3000);
			sno++;
			Select ActionItemApprover = new Select(driver.findElement(By.id("actItmAppproverNameInMcAddActItemDlg")));
			ActionItemApprover.selectByIndex(2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Select the action item approver from drop down list", sno, false);
			Thread.sleep(3000);
			sno++;
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('actItmDueDateInMcAddActItemDlg'). removeAttribute('readonly',0);");
			WebElement identifiedDate = driver.findElement(By.id("actItmDueDateInMcAddActItemDlg"));
			identifiedDate.clear();
			SimpleDateFormat formattedDate1 = new SimpleDateFormat("dd/M/yyyy");
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE, 30); // number of days to add
			String futureDate1 = (String) (formattedDate1.format(c1.getTime()));
			driver.findElement(By.id("actItmDueDateInMcAddActItemDlg")).sendKeys(futureDate1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Action Item due date", sno,
					false);
			driver.findElement(By.id("departmentNameInMcReview")).click();
			Thread.sleep(3000);
			sno++;
			Select CompleteActionItem = new Select(
					driver.findElement(By.id("cmpltActItemBeforeProceedInMcAddActItemDlg")));
			CompleteActionItem.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Select the  complete action item before proceding from drop down list", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.id("actItemDescInMcAddActItemDlg")).sendKeys(properties.getProperty("MC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature passwod", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("addBtnInMcActionItemAddDlg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.id("commentsInMcQaReview")).sendKeys(properties.getProperty("MC_994"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Comments", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("approveMcQaPrimReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature passwod", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
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
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected For CFT Review");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdQAReview(String MarketComplaintsId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("mcReviewTableContailner"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId)))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (MarketComplaintsId.contains(capaNumberSequence)) {
							driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr[ " + i
									+ " ]/td[51]/button")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (MarketComplaintsId.contains(capaNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[51]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#mcReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("mcReviewTableContailner"));// Document Tree approve table
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
