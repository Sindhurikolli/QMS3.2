package com.pss.qms.InvestigationReject;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.pss.qms.login.InvestigationLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class InvestigationRePerform extends InvestigationLoginDetails {

	@Test
	public void RePerformInvestigation() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvestigationRePerform"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvestigationRePerform", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("InvPerformer"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("InvModule"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='rePerformFullInvPage.do']")));
		driver.findElement(By.cssSelector("a[href='rePerformFullInvPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Re-Perform menu",
				sno, false);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#investigationRePerformTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoInvestigationPerform();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoInvestigationPerform() throws Exception {

		sno++;
		boolean isRecordSelectedForInv = false;
		String InvLastName = properties.getProperty("INV_NO");
		int count4 = 0;
		isRecordSelectedForInv = selectingTheInvPerform(InvLastName, isRecordSelectedForInv, count4);
//      sno++;
//  	JavascriptExecutor jse = (JavascriptExecutor) driver;
//  	WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
//  	 jse.executeScript("arguments[0].scrollIntoView(true);", element);
		if (isRecordSelectedForInv) {
			
			Thread.sleep(5000);
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#invTeamRecs_InPerformInvActionsInInvPlan > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			if(driver.findElement(By.cssSelector("#ui-id-211")).isDisplayed())
         {
        	 driver.findElement(By.cssSelector("body > div:nth-child(160) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button > span")).click();
         }
         Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#invTeamRecs_InPerformInvActionsInInvPlan > div > div.jtable-busy-message[style='display: none;']")));
			Select LifeCycle = new Select(driver.findElement(By.id("lifeCycleIdInPerform")));
			LifeCycle.selectByVisibleText(properties.getProperty("LifeCycle"));
			;
			Thread.sleep(2000);
			driver.findElement(By.id("modifyCommentsInFullInvReview")).sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);
			driver.findElement(By.id("ui-id-389")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#rcaAnalysisInDevReview > div > div.jtable-busy-message[style='display: none;']")));
			driver.findElement(By.id("ui-id-390")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#supDocsJtable_ForImpAsmtUpload_InvReviewPage > div > div.jtable-busy-message[style='display: none;']")));
			driver.findElement(By.id("ui-id-391")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("#capaDetailsInFullInvPage > div > div.jtable-busy-message[style='display: none;']")));
			driver.findElement(By.id("submitBtnInRePerformfullInvReview")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
			Thread.sleep(2000);
			sno++;
			// WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);

			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
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

	private boolean selectingTheInvPerform(String InvLastName, boolean recordSelectedForInv, int count4)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("investigationRePerformTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if (((InvLastName == null) || ("".equalsIgnoreCase(InvLastName)))) {

				InvLastName = driver
						.findElement(
								By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType
			} else if ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName))) {
				InvLastName = driver
						.findElement(
								By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String InvFirstName = driver.findElement(
								By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr[ " + i
										+ " ]/td[4]"))
								.getText();// documentTypeName
						System.out.println("InvReviewFullName: " + InvFirstName);
						if (InvLastName.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr[ "
											+ i + " ]/td[26]/button"))
									.click();
							recordSelectedForInv = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath(
									"//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (InvLastName.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath(
								"//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[26]/button"))
								.click();
						recordSelectedForInv = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recordSelectedForInv) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#investigationRePerformTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(5000);
					table = driver.findElement(By.id("investigationRePerformTableContainer"));// Document Tree approve
																								// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return recordSelectedForInv;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
