package com.pss.qms.MarketComplaintsWithCFTAndWithAIRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ActionItemRePerform extends MCLoginDetails {

	@Test
	public void ActionItemRePerform() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ActionItemPerformAfterReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("ActionItemPerformAfterReject", "PSS-QMS-011", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Invest_User"));
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
		Thread.sleep(3000);
//			sno++;
//			driver.findElement(By.cssSelector("#marketComplaints_tile_Id > div > div > div > h2")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Market Complaints module", sno,
//					false);
		Thread.sleep(6000);
		sno++;
		driver.findElement(By.cssSelector("a[href='mcActionItemReject.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Action Item Reject menu", sno,
				false);
		Thread.sleep(2000);
		methodToDoAIPerformMC();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoAIPerformMC() throws Exception {

		sno++;
		int count = 0;
		Thread.sleep(2000);
		boolean isRecordSelected = false;
//		Date date = new Date();
//		String sdf = new SimpleDateFormat("YY").format(date);
		String MarketComplaintsId = properties.getProperty("MC_ID");
		isRecordSelected = selectRecdAIPerformMC(MarketComplaintsId, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(2000);
		if (isRecordSelected) {
			JavascriptExecutor scl = ((JavascriptExecutor) driver);
			scl.executeScript("window.scrollBy(0,500)");
//			sno++;
//			Thread.sleep(2000); 
//			driver.findElement(By.id("mcAddSuppDocsInMcActItem")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Supprting documents", sno,
//					false);
//			sno++;
//			Thread.sleep(2000);
//			driver.findElement(By.id("uploadSuppDocInMcActionItem_1")).sendKeys(properties.getProperty("Document-2"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "upload the document", sno, false);
			sno++;
			Thread.sleep(2000);
			Select ApproveFromDropDown = new Select(driver.findElement(By.id("appFromInRejectForm")));
			ApproveFromDropDown.selectByIndex(3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Approval from", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("commentsInRejectForm")).sendKeys(properties.getProperty("TraderAddress_500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			sno++;
			JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
			scl1.executeScript("window.scrollBy(0,250)");
			Thread.sleep(2000);
			driver.findElement(By.id("submitBtnInDevActItmRej")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			sno++;
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 80);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
					.sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			sno++;
			Thread.sleep(1000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			sno++;
			Thread.sleep(2000);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(
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

		}
	}

	private boolean selectRecdAIPerformMC(String MarketComplaintsId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("actItmRejectTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"actItmRejectTable\"]/div/div[4]/div[2]/span")).getText();// For
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
			if ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr/td[3]")).getText();// documentType
			} else if ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < perPageNoOfRecordsPresent) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver
								.findElement(By
										.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (MarketComplaintsId.contains(capaNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					Thread.sleep(3000);
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr/td[3]")).getText();
					Thread.sleep(3000);
					if (MarketComplaintsId.contains(capaNumberSequence)) {
						Thread.sleep(3000);
						driver.findElement(By.xpath("//*[@id=\"actItmRejectTable\"]/div/table/tbody/tr/td[3]")).click();
						Thread.sleep(3000);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#actItmRejectTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("actItmRejectTable"));// Document Tree approve table
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
