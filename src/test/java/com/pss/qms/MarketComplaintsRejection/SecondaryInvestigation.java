package com.pss.qms.MarketComplaintsRejection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.MCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class SecondaryInvestigation extends MCLoginDetails {

	@Test
	public void InvestigationMC() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "SecondaryInvestigation"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("SecondaryInvestigation", "PSS-QMS-041", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Invest_User"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_MCWith_Investigation"));
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
//		sno++;
		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("#investigation_tile_Id > div > div > div > h2")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation module", sno, false);
		Thread.sleep(5000);
		sno++;
//    WebElement element = driver.findElement(By.cssSelector("#invInvestigationMenu > a"));
//    JavascriptExecutor js = (JavascriptExecutor)driver;
//    js.executeScript("arguments[0].click();", element);

//  driver.findElement(By.cssSelector("a[href='invInvestigation.do']")).click();

		driver.findElement(By.cssSelector("#invInvestigationMenu > a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation menu", sno, false);
//    Thread.sleep(20000);
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.invisibilityOf(
				driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-busy-message"))));
		methodToDoInvestigationMC();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoInvestigationMC() throws Exception {

		sno++;
		int count = 0;
		Thread.sleep(2000);
		boolean isRecordSelected = false;
		Date date = new Date();
		String sdf = new SimpleDateFormat("YY").format(date);
		String MarketComplaintsId = properties.getProperty("MC_ID");
		isRecordSelected = selectRecdInvestigationMC(MarketComplaintsId, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(8000);
		if (isRecordSelected) {
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("invDetailsInPerformInvActionsInInvPlan"))
					.sendKeys(properties.getProperty("MC_20000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Investigation details", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("summaryInPerformInvActionsInInvPlan"))
					.sendKeys(properties.getProperty("MC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Summary and conclusion", sno,
					false);
			Thread.sleep(4000);

//            driver.findElement(By.id("chronologyofeventInPerformInvActionsInInvPlan")).sendKeys(properties.getProperty("Compliant_Short_Description"));
//            Thread.sleep(4000);
//            driver.findElement(By.id("suppDocumentInPerfromInvActInInvRevForm")).sendKeys(properties.getProperty("Document-1"));
//            Thread.sleep(4000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id("addSupDocsBtn_ForInvActionUpload_InvReviewPage"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);

			sno++;
			driver.findElement(By.id("addSupDocsBtn_ForInvActionUpload_InvReviewPage")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("uploadSuppDocForInAct_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitIdForDevInInvTabInvReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(2000);
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
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdInvestigationMC(String MarketComplaintsId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("investigationTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();// documentType
			} else if ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[6]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver.findElement(By
								.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr[ " + i + " ]/td[6]"))
								.getText();// documentTypeName
						if (MarketComplaintsId.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr[ "
									+ i + " ]/td[22]/button")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr/td[6]"))
							.getText();
					if (MarketComplaintsId.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(
								By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr/td[22]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#investigationTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("investigationTableContainer"));// Document Tree approve table
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
