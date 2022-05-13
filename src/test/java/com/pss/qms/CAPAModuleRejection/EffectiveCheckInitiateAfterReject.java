package com.pss.qms.CAPAModuleRejection;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class EffectiveCheckInitiateAfterReject extends CAorPALoginDetails {

	@Test
	public void EffectiveCheckInitiateAfterReject() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "EffectiveCheckInitiateAfterReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("EffectiveCheckInitiateAfterReject", "PSS-QMS-036",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME2"));
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
//        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
//        sno++;
//        Thread.sleep(3000);
//        driver.findElement(By.id("myActivitiesInCapa")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(15000);
//        driver.findElement(By.cssSelector("#capaEffChkMainMenuId > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Effective Check menu", sno,false);
//        sno++;
//        Thread.sleep(15000);
//        driver.findElement(By.cssSelector("#capaEffCheckInitiateMenuId > a")).click();
		driver.findElement(By.cssSelector("a[href='capaEffectiveChkInitiate.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Effectiveness check initiate", sno,
				false);
		sno++;
//        Thread.sleep(15000);
		WebDriverWait waitload = new WebDriverWait(driver, 240);
		waitload.until(ExpectedConditions.presenceOfElementLocated(By.id("rejectEffectiveInitiatedActions")));
		driver.findElement(By.id("rejectEffectiveInitiatedActions")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on reverted radio button", sno,
				false);
		Thread.sleep(5000);
		waitload.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("jtable-busy-message"))));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		toCAPAEffectiveCheckInitiate();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAEffectiveCheckInitiate() throws Exception {

		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String CAPANumber = properties.getProperty("CAPAId");
		isRecordSelected = selectRecdCAPAEffectiveCheckImp(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
//       	Robot robot = new Robot();
//       	for (int i = 0; i < 1; i++) {
//       		 robot.keyPress(KeyEvent.VK_CONTROL);
//       		 robot.keyPress(KeyEvent.VK_SUBTRACT);
//       		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
//       		 robot.keyRelease(KeyEvent.VK_CONTROL);
//       		 }
//           Thread.sleep(3000);
			sno++;
			WebElement element = driver
					.findElement(By.xpath("//*[@id=\"CapaEffInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
//           driver.findElement(By.xpath("//*[@id=\"CapaEffInitFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(4000);
			sno++;
			WebElement element1 = driver
					.findElement(By.xpath("//*[@id=\"CapaEffInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)");
			WebElement element2 = driver.findElement(By.cssSelector(
					"#effChkCARecsTable_CapaEffInitPage > div > table > tbody > tr > td:nth-child(11) > button > i"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", element2);
//           driver.findElement(By.cssSelector("#effChkCARecsTable_CapaEffInitPage > div > table > tbody > tr > td:nth-child(11) > button > i")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Effective check sub menu", sno,
					false);
			Thread.sleep(2000);
//           sno++;
//           driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the observation days", sno,false);
//           Thread.sleep(1000);
//           sno++;
//           driver.findElement(By.id("descriptionEffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("CAPA_3000"));
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the effective check descrption", sno,false);
			Thread.sleep(1000);
			sno++;
			WebElement element3 = driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].click();", element3);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on save button", sno, false);
			Thread.sleep(1000);
			sno++;
			WebElement element4 = driver
					.findElement(By.xpath("//*[@id=\"CapaEffInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js4 = (JavascriptExecutor) driver;
			js4.executeScript("arguments[0].click();", element4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on next button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("comments_CapaEffInitPage")).sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			WebElement element5 = driver.findElement(
					By.cssSelector("#CapaEffInitFormContentsDiv > div.actions.clearfix > ul > li:nth-child(3) > a"));
			JavascriptExecutor js5 = (JavascriptExecutor) driver;
			js5.executeScript("arguments[0].click();", element5);
//           driver.findElement(By.cssSelector("#CapaEffInitFormContentsDiv > div.actions.clearfix > ul > li:nth-child(3) > a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			WebElement element6 = driver.findElement(By.id("subBtnInValidateESign"));
			JavascriptExecutor js6 = (JavascriptExecutor) driver;
			js6.executeScript("arguments[0].click();", element6);
//           driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
//           driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//           Thread.sleep(3000);
//           String separateCAPANumber[] = CAPAString.split(" ");
//           System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//           String CAPANo = separateCAPANumber[5]; 
//           Thread.sleep(2000);
//           CAPANo = CAPANo.replace(")", ""); 
//           finalCAPANumber = CAPANo.trim();
//           System.out.println("finalCAPANumber: "+finalCAPANumber);
//           Thread.sleep(5000);
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
		WebElement table = driver.findElement(By.id("capaRecsTable_CapaEffInitReviewRejectPage"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
				CAPANumber = driver
						.findElement(By.xpath(
								"//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver
						.findElement(By.xpath(
								"//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr[ "
										+ i + " ]/td[3]"))
								.getText();// documentTypeName
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(By
									.xpath("//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"))
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
							.findElement(By.xpath(
									"//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"capaRecsTable_CapaEffInitReviewRejectPage\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#capaEffectivePostPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("capaEffectivePostPerformTable"));// Document Tree approve table
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
