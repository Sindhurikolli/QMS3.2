
package com.pss.qms.CAPAModule;

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

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class DeptHODCAPAModule extends CAorPALoginDetails {

	@Test
	public void toReviewCAPA() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeptHODCAPAModule" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeptHODCAPAModule", "PSS-QMS-002", "Pass");
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
		document.add(
				new Paragraph(sno + "." + "Enter the username, password, Select CAPA Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(5000);
//        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
//        sno++;
		driver.findElement(By.cssSelector("a[href='capaReviewPage.do']")).click();
//        driver.findElement(By.id("myActivitiesInCapa")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Review/Approval", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("jtable-busy-message"))));
//        Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("newCapaPlanInCapa")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Plan New CAPA radio button", sno,false);
//       Thread.sleep(20000);
		toCAPAReviewAndApproval();
		Thread.sleep(2000);
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAReviewAndApproval() throws Exception {

//          WebDriver driver = new FirefoxDriver();
//    	  JavascriptExecutor jse = (JavascriptExecutor)driver;
//    	  jse.executeScript("window.scrollBy(0,1500)");
//    	JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[4]/td[9]"));
//        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
//        jse1.executeScript("arguments[0].click();", element1);
//    	
		Thread.sleep(7000);
		int count = 0;
		boolean isRecordSelected = false;
		String SNO = properties.getProperty("DEPT_SNO");
		String CAPANumber = SNO;
		sno++;
		isRecordSelected = selectRecdDepthod(CAPANumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
//        Thread.sleep(20000);
		if (isRecordSelected) {
//        	Robot robot = new Robot();
//        	for (int i = 0; i < 1; i++) {
//        		 robot.keyPress(KeyEvent.VK_CONTROL);
//        		 robot.keyPress(KeyEvent.VK_SUBTRACT);
//        		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
//        		 robot.keyRelease(KeyEvent.VK_CONTROL);
//        		 }
//            Thread.sleep(14000);
			sno++;
			WebElement element = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js8 = (JavascriptExecutor) driver;
			js8.executeScript("arguments[0].click();", element);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(5000);
			driver.findElement(By.id("capaTaskDueDateInCapReview")).click();
			sno++;
			String todaydate = driver.findElement(By.className("  ui-datepicker-today")).getText();
			System.out.println(todaydate);
			int daysafter = Integer.parseInt(properties.getProperty("DutedateAfterhowmanyDays"));
			int newdate = Integer.parseInt(todaydate) + daysafter;
			System.out.println(newdate);
			List<WebElement> dates = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
			int daysremaininginmonth = dates.size();
			String LastDayOfMonth = dates.get(daysremaininginmonth - 1).getText();
			System.out.println(LastDayOfMonth);
			if (newdate <= Integer.parseInt(LastDayOfMonth)) {
				List<WebElement> days = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
				days.get(daysafter).click();
			} else {
				int indextoselect = daysafter - daysremaininginmonth;
				System.out.println(indextoselect);
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
				Thread.sleep(5000);
				List<WebElement> days1 = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
				days1.get(indextoselect).click();
			}

//            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")); //click on next in calender

//            driver.findElement(By.className(" "));
//            List<WebElement> dates=driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]"));
//            int total_node=dates.size();
//            for(int i = 0;i<total_node;i++) {
//            	String date=dates.get(i).getText();
//            	if(date.equalsIgnoreCase("26"))
//            	{
//            		dates.get(i).click();
//            		break;
//            	}
//            }
//            SimpleDateFormat formattedDate = new SimpleDateFormat("d/M/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, 1); // number of days to add
//            String futureDate = (String) (formattedDate.format(c.getTime()));
//            driver.findElement(By.id("capaTaskDueDateInCapReview")).sendKeys(futureDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter due date", sno, false);
			Thread.sleep(6000);
			sno++;
			WebElement element2 = driver.findElement(By.id("selBtnForcapaTaskResponsePersonCapReview"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", element2);
//            driver.findElement(By.id("selBtnForcapaTaskResponsePersonCapReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(4000);
			WebElement element6 = driver.findElement(By.id("locTreeInQmsProdReg_2_switch"));
			JavascriptExecutor js6 = (JavascriptExecutor) driver;
			js6.executeScript("arguments[0].click();", element6);
//            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
			Thread.sleep(4000);
			sno++;
			WebElement element7 = driver.findElement(By.id("locTreeInQmsProdReg_3_span"));
			JavascriptExecutor js7 = (JavascriptExecutor) driver;
			js7.executeScript("arguments[0].click();", element7);
//            driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
			Thread.sleep(6000);
			sno++;
			boolean isRecordSelectedForResPerson = false;
			String CAPALastName = properties.getProperty("E-Code");
			int count4 = 0;
			WebDriverWait wait5 = new WebDriverWait(driver, 70);
			wait5.until(ExpectedConditions.invisibilityOf(
					driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-busy-message"))));
//          WebElement element8 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"));
//          JavascriptExecutor js8 = (JavascriptExecutor)driver;
//          js8.executeScript("arguments[0].click();", element8);
			isRecordSelectedForResPerson = selectingTheResponsiblePerson(CAPALastName, isRecordSelectedForResPerson,
					count4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element9 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			JavascriptExecutor js9 = (JavascriptExecutor) driver;
			js9.executeScript("arguments[0].click();", element9);
//            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the responsible person", sno,
					false);
			sno++;
			Thread.sleep(3000);
			WebElement element4 = driver.findElement(By.id("capaAddSuppDocsDept"));
			JavascriptExecutor js4 = (JavascriptExecutor) driver;
			js4.executeScript("arguments[0].click();", element4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add supporting documents", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element11 = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js11 = (JavascriptExecutor) driver;
			js11.executeScript("arguments[0].click();", element11);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element10 = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", element10);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("CAPA_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element3 = driver
					.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].click();", element3);
//            driver.findElement(By.xpath(".//*[@id='capaReviewFormContentsDiv']/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element12 = driver.findElement(By.id("subBtnInValidateESign"));
			JavascriptExecutor js12 = (JavascriptExecutor) driver;
			js12.executeScript("arguments[0].click();", element12);
//            driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
//            Thread.sleep(4000);
//            driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
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

	private boolean selectingTheResponsiblePerson(String CAPALastName, boolean isRecordSelectedForResPerson, int count4)
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
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if (((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName)))) {
				CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName))) {
				CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String InvFirstName = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						System.out.println("InvReviewFullName: " + InvFirstName);
						if (CAPALastName.equalsIgnoreCase(InvFirstName)) {
							WebElement element8 = driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"));
							JavascriptExecutor js8 = (JavascriptExecutor) driver;
							js8.executeScript("arguments[0].click();", element8);
//                             driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
							isRecordSelectedForResPerson = true;

							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CAPALastName.equalsIgnoreCase(InvReviewFullName)) {
						WebElement element8 = driver
								.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"));
						JavascriptExecutor js8 = (JavascriptExecutor) driver;
						js8.executeScript("arguments[0].click();", element8);
//                         driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
						isRecordSelectedForResPerson = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForResPerson) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
//                     Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForResPerson;
	}

	private boolean selectRecdDepthod(String CAPANumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("capaReviewTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For
																														// Ex:
																														// Showing
																														// 1-1
																														// of
																														// 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());

				List<WebElement> options = driver.findElements(By.cssSelector(
						"#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-size-change > select > option"));

				if (totalNoOfRecords > perPageNoOfRecordsPresent) {

					int j = 1;
					options.get(j).click();
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
					j++;

//             	 Thread.sleep(8000);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
				CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver
								.findElement(
										By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
//                        Thread.sleep(8000);
						if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
//                            Thread.sleep(8000);
							driver.findElement(
									By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
									.click();
//                            Thread.sleep(8000);
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[1]")).getText();
					if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[1]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
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
