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
import com.pss.qms.util.Utilities;

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
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CAPACompletion extends CAorPALoginDetails {

	@Test
	public void CAPACompletion() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPACompletion" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPACompletion", "PSS-QMS-007", "Pass");
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
		document.add(
				new Paragraph(sno + "." + "Enter the username, password, Select CAPA Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='capaCmplInitiatePage.do']")));
		driver.findElement(By.cssSelector("a[href='capaCmplInitiatePage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Completion Initiation", sno,
				false);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#capaRecsTable_CapaCmplInitPage > div > div.jtable-busy-message[style='display: none;']")));
		toCAPACompletionInitiate();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPACompletionInitiate() throws Exception {

		int count = 0;
		sno++;
		boolean isRecordSelected = false;
		String CAPANumber = properties.getProperty("CAPAId");
		isRecordSelected = selectRecdCAPACompletion(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
//        	Robot robot = new Robot();
//        	for (int i = 0; i < 1; i++) {
//        		 robot.keyPress(KeyEvent.VK_CONTROL);
//        		 robot.keyPress(KeyEvent.VK_SUBTRACT);
//        		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
//        		 robot.keyRelease(KeyEvent.VK_CONTROL);
//        		 }
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			WebElement element = driver
					.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element1 = driver
					.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element2 = driver
					.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", element2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element3 = driver
					.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].click();", element3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(3000);
//            Select CompletionApproval = new Select(driver.findElement(By.id("cmplLCId_CapaCmplInitPage")));
//            CompletionApproval.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("needEffectvChk_CapaCmplInitPage")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
//            Thread.sleep(10000);
//            driver.findElement(By.xpath("//*[@id=\"effChkCARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[1]/td[9]/button")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck = new Select(driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")));
//            EffectiveCheck.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//             driver.findElement(By.xpath("//*[@id=\"effChkCARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[2]/td[9]/button")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck1 = new Select(driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")));
//            EffectiveCheck1.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"effChkPARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[1]/td[11]/button")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck2 = new Select(driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")));
//            EffectiveCheck2.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"effChkPARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[1]/td[11]/button")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck3 = new Select(driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")));
//            EffectiveCheck3.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"effChkPARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr/td[11]/button")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck4 = new Select(driver.findElement(By.id("effChkReq_EffChkCaOrPaEditDlgCmplInitPage")));
//            EffectiveCheck4.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("obsAfterDays_EffChkCaOrPaEditDlgCmplInitPage")).sendKeys(properties.getProperty("Observation_days"));            
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(3000);
//            driver.findElement(By.id("effChkLCId_CapaCmplInitPage")).click();
//            Thread.sleep(2000);            
//            driver.findElement(By.xpath("//*[@id=\"effChkPARecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[3]/td[11]/button")).click();
//            Thread.sleep(3000);
//            Select EffectiveCheck5 = new Select(driver.findElement(By.id("effChkLCId_CapaCmplInitPage")));
//            EffectiveCheck5.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("saveBtn_EffChkCaOrPaEditDlgCmplInitPage")).click();
//            Thread.sleep(5000);
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("effChkLCId_CapaCmplInitPage"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
//           Thread.sleep(1000);
//            Thread.sleep(4000);
//            WebElement mySelectElement = driver.findElement(By.name("effChkLCId_CapaCmplInitPage"));
//            Select dropdown= new Select(mySelectElement);
//            dropdown.selectByIndex(1);
//            Select EffectiveApprove = new Select(driver.findElement(By.id("effChkLCId_CapaCmplInitPage")));            
//            EffectiveApprove.selectByIndex(1);
//            Thread.sleep(5000);
//            driver.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("comments_CapaCmplInitPage")).sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			WebElement element4 = driver
					.findElement(By.xpath("//*[@id=\"capaCmplInitFormContentsDiv\"]/div[3]/ul/li[3]/a"));
			JavascriptExecutor js4 = (JavascriptExecutor) driver;
			js4.executeScript("arguments[0].click();", element4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the e-signature", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element5 = driver.findElement(By.id("subBtnInValidateESign"));
			JavascriptExecutor js5 = (JavascriptExecutor) driver;
			js5.executeScript("arguments[0].click();", element5);
//            driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
			Thread.sleep(3000);
//            String separateCAPANumber[] = CAPAString.split(" ");
//            System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//            String CAPANo = separateCAPANumber[5]; 
//            Thread.sleep(2000);
//            CAPANo = CAPANo.replace(")", ""); 
//            finalCAPANumber = CAPANo.trim();
//            System.out.println("finalCAPANumber: "+finalCAPANumber);
//            Thread.sleep(5000);
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

	private boolean selectRecdCAPACompletion(String CAPANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("capaRecsTable_CapaCmplInitPage"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver
						.findElement(By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr[ " + i
											+ " ]/td[3]"))
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
							.findElement(
									By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"capaRecsTable_CapaCmplInitPage\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#capaRecsTable_CapaCmplInitPage> div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
//                    Thread.sleep(3000);
					table = driver.findElement(By.id("capaRecsTable_CapaCmplInitPage"));// Document Tree approve table
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
