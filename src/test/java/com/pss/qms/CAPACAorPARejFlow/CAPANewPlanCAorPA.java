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
import com.pss.qms.login.CAorPALoginDetails;
// 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CAPANewPlanCAorPA extends CAorPALoginDetails {

	@Test
	public void CAPANewPlanCAorPA() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPANewPlanCAorPA" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPANewPlanCAorPA", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText("CAPA");
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Thread.sleep(1000);
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
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='createCapaPlanPage.do']")));
		driver.findElement(By.cssSelector("a[href='createCapaPlanPage.do']")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("newCapaPlanInCapa")));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Capa Plan", sno, false);
		sno++;
		driver.findElement(By.id("newCapaPlanInCapa")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Plan New CAPA radio button", sno,
				false);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("dialogForCapaPlanWindow")));
		toCAPAPlan();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAPlan() throws Exception {

		sno++;
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.findElement(By.id("dueDateInCreateCapaPlanDlg")).click();
		String todaydate = driver.findElement(By.className("ui-datepicker-today")).getText();
		System.out.println(todaydate);
		int daysafter = Integer.parseInt(properties.getProperty("DutedateAfterhowmanyDays"));
		int newdate = Integer.parseInt(todaydate) + daysafter;
		System.out.println(newdate);
		Helper.dueDateSelection(driver, daysafter, newdate);
//		List<WebElement> dates = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
//		int daysremaininginmonth = dates.size();
//		String LastDayOfMonth = dates.get(daysremaininginmonth - 1).getText();
//		System.out.println(LastDayOfMonth);
//		if (newdate <= Integer.parseInt(LastDayOfMonth)) {
//			List<WebElement> days = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
//			days.get(daysafter).click();
//		} else {
//			int indextoselect = daysafter - daysremaininginmonth;
//			System.out.println(indextoselect);
//			driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
//			Thread.sleep(5000);
//			List<WebElement> days1 = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
//			days1.get(indextoselect).click();
//		}
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Due Date", sno, false);
		sno++;
		Thread.sleep(2000);
		Select Source = new Select(driver.findElement(By.id("capaSourceInCreateCapaPlan")));
		Source.selectByIndex(2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Source", sno, false);
		sno++;
		String ref=properties.getProperty("Reference_Number");
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
		driver.findElement(By.id("referenceNumberInCreateCapaPlan"))
				.sendKeys(ref+timeStamp);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the reference number", sno, false);
		sno++;
		driver.findElement(By.id("descriptionInCreateCapaPlan")).sendKeys(properties.getProperty("CAPA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Enter the  source description in create capa plan", sno, false);
		sno++;
		driver.findElement(By.id("shortDescriptionInCreateCapaPlan")).sendKeys(properties.getProperty("CAPAShortDescription_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,"Enter the CAPA Short Description", sno, false);
		sno++;
		JavascriptExecutor scl = ((JavascriptExecutor) driver);
		scl.executeScript("window.scrollBy(0,400)");
		driver.findElement(By.id("chkAddCAInCapaReview")).click();
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select corrective Action Check Box", sno,
				false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"capaCaDetailsContainerInNew\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Add button", sno, false);
		sno++;
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div:nth-child(53)")));
		driver.findElement(By.id("taskDescriptionInAddCaDetails")).sendKeys(properties.getProperty("CAPA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Task Description", sno, false);
		sno++;
		driver.findElement(By.id("dueDateInAddCaDetails")).click();
		Thread.sleep(1000);
//		driver.findElement(By.className("ui-datepicker-today")).click();
		int daysafterCa = Integer.parseInt(properties.getProperty("DutedateAfterhowmanyDays"));
		int newdateCa = Integer.parseInt(todaydate) + daysafter;
		System.out.println(newdateCa);
		Helper.dueDateSelection(driver, daysafterCa, newdateCa);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Due Date", sno, false);
		sno++;
		Thread.sleep(1000);
		Select TaskType = new Select(driver.findElement(By.id("taskTypeInAddCaDetails")));
		String TasktypeCA = properties.getProperty("Task_Type_CA");
		TaskType.selectByVisibleText(TasktypeCA);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Task Type", sno, false);
		sno++;
		driver.findElement(By.id("addPersonInCaDetailsWindowId")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("locTreeInQmsProdReg_2_span")));
		driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		boolean isRecordSelectedForResponsiblePersonCA = false;
		String CAPerson = properties.getProperty("CAResponsiblePersonE-code");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User", sno, false);
		sno++;
//        String InvLastName =properties.getProperty("UserName");
		String ResponsiblePersonCA = CAPerson;
		int count4 = 0;
		isRecordSelectedForResponsiblePersonCA = selectingTheResponsiblePersonCA(ResponsiblePersonCA,
				isRecordSelectedForResponsiblePersonCA, count4);
		Thread.sleep(2000);
		driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addInCaDetailsWindowId")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Add Button", sno, false);
		sno++;
		Thread.sleep(2000);
		scl.executeScript("window.scrollBy(0,300)");
		driver.findElement(By.id("chkAddPAInCapaReview")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Preventive Action Check Box", sno,
				false);
		sno++;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"capaPaDetailsContainerInNew\"]/div/div[3]/div[2]/span/span[2]")).click();
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Add Button", sno, false);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div:nth-child(53)")));
		driver.findElement(By.id("taskDescriptionInAddCaDetails")).sendKeys(properties.getProperty("CAPA_2000"));
		Thread.sleep(1000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Task Description", sno, false);
		sno++;
		driver.findElement(By.id("dueDateInAddCaDetails")).click();
		Thread.sleep(3000);
//		driver.findElement(By.className("ui-datepicker-today")).click();
		int daysafterPa = Integer.parseInt(properties.getProperty("DutedateAfterhowmanyDays"));
		int newdatePa = Integer.parseInt(todaydate) + daysafter;
		System.out.println(newdatePa);
		Helper.dueDateSelection(driver, daysafterPa, newdatePa);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Due Date", sno, false);
		sno++;
		String TasktypePA = properties.getProperty("Task_Type_PA");
		TaskType.selectByVisibleText(TasktypePA);
		Thread.sleep(1000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Task Type", sno, false);
		sno++;
		driver.findElement(By.id("addPersonInCaDetailsWindowId")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("locTreeInQmsProdReg_2_span")));
		driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		boolean isRecordSelectedForResponsiblePersonPA = false;
		String PAPerson = properties.getProperty("PAResponsiblePersonE-code");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User", sno, false);
		sno++;
//    String InvLastName =properties.getProperty("UserName");
		String ResponsiblePersonPA = PAPerson;
		int count5 = 0;
		isRecordSelectedForResponsiblePersonPA = selectingTheResponsiblePersonPA(ResponsiblePersonPA,
				isRecordSelectedForResponsiblePersonPA, count5);
		Thread.sleep(2000);
		driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addInCaDetailsWindowId")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button", sno, false);
		sno++;
		Thread.sleep(5000);
		scl.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.cssSelector(
				"#createCapaPlanWindowMainDiv > div:nth-child(5) > div:nth-child(2) > div.form-group.col-sm-4 > div.radio_false_disable > label"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on supporing dosuments Yes radio button", sno, false);
		sno++;
		driver.findElement(By.id("capaAddSuppDocs")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
		sno++;
		scl.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
		sno++;
		scl.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.id("submitIdInCreateCapaPlan")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the  E-Signature Password", sno,
				false);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
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

	private boolean selectingTheResponsiblePersonCA(String ResponsiblePersonCA,
			boolean isRecordSelectedForResponsiblePersonCA, int count4) throws InterruptedException {
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
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if (((ResponsiblePersonCA == null) || ("".equalsIgnoreCase(ResponsiblePersonCA)))) {
				ResponsiblePersonCA = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((ResponsiblePersonCA == null) || ("".equalsIgnoreCase(ResponsiblePersonCA))) {
				ResponsiblePersonCA = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
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
						if (ResponsiblePersonCA.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForResponsiblePersonCA = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (ResponsiblePersonCA.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForResponsiblePersonCA = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForResponsiblePersonCA) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForResponsiblePersonCA;
	}

	private boolean selectingTheResponsiblePersonPA(String ResponsiblePersonPA,
			boolean isRecordSelectedForResponsiblePersonPA, int count5) throws InterruptedException {
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
		if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
			if (((ResponsiblePersonPA == null) || ("".equalsIgnoreCase(ResponsiblePersonPA)))) {
				ResponsiblePersonPA = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((ResponsiblePersonPA == null) || ("".equalsIgnoreCase(ResponsiblePersonPA))) {
				ResponsiblePersonPA = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType

			}
			++count5;
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
						if (ResponsiblePersonPA.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForResponsiblePersonPA = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (ResponsiblePersonPA.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForResponsiblePersonPA = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForResponsiblePersonPA) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForResponsiblePersonPA;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
