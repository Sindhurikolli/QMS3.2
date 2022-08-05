
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class QAReviewerAfterReject extends CAorPALoginDetails {

	@Test
	public void QAReviewerAfterReject() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerAfterReject"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerAfterReject", "PSS-QMS-024", "Pass");
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
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='capaReviewPage.do']")));
		driver.findElement(By.cssSelector("a[href='capaReviewPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Review/Approve", sno, false);
		Thread.sleep(2000);
		Helper.waitLoadRecords(driver, By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']"));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));
		toCAPAReviewAndApproval();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAReviewAndApproval() throws Exception {

		sno++;
//   	WebDriver driver = new FirefoxDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		int count = 0;
		boolean isRecordSelected = false;
		String CAPANumber = properties.getProperty("CAPAId");
		isRecordSelected = selectRecdQAReviewCAPA(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(6000);
		if (isRecordSelected) {
//       	Robot robot = new Robot();
//       	for (int i = 0; i < 1; i++) {
//       		 robot.keyPress(KeyEvent.VK_CONTROL);
//       		 robot.keyPress(KeyEvent.VK_SUBTRACT);
//       		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
//       		 robot.keyRelease(KeyEvent.VK_CONTROL);
//       		 }
//           Thread.sleep(5000);
			sno++;
			WebElement element = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
//           driver.findElement(By.id("rejectActivityInCheckInCapaHodRev")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element1 = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", element1);
//           driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on next button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element2 = driver
					.findElement(By.xpath(".//*[@id='capaReviewFormContentsDiv']/div[3]/ul/li[2]/a"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", element2);
//            driver.findElement(By.xpath(".//*[@id='capaReviewFormContentsDiv']/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on next button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(1000);
			sno++;
			WebElement element5 = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a"));
			JavascriptExecutor js5 = (JavascriptExecutor) driver;
			js5.executeScript("arguments[0].click();", element5);
//            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(1000);
			sno++;
			WebElement element3 = driver.findElement(By.id("subBtnInValidateESign"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].scrollIntoView(true);", element3);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='modal-window']/div/div/div[3]/a")));
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

	private boolean selectRecdQAReviewCAPA(String CAPANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("capaReviewTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			WebElement elementshowing = driver
					.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span"));
			JavascriptExecutor jsshow = (JavascriptExecutor) driver;
			jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
			String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For
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
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[6]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						WebElement elementsele = driver.findElement(
								By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[6]"));
						JavascriptExecutor jssel = (JavascriptExecutor) driver;
						jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
						String capaNumberSequence = driver
								.findElement(
										By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
								.getText();// documentTypeName
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
							driver.findElement(
									By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[6]"))
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
							.findElement(By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr/td[6]")).getText();
//                    Thread.sleep(5000);
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						Thread.sleep(5000);
						driver.findElement(By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr/td[6]")).click();
						Thread.sleep(2000);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//					WebElement elementnext = driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					JavascriptExecutor jsnext = (JavascriptExecutor) driver;
//					jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
//					driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Helper.clickElement(driver, By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']"));
//					WebDriverWait wait1 = new WebDriverWait(driver, 240);
//					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));
					table = driver.findElement(By.id("capaReviewTable"));// Document Tree approve table
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
