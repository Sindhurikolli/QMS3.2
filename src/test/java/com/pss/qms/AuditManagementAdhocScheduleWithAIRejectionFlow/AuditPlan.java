package com.pss.qms.AuditManagementAdhocScheduleWithAIRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AMLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
@Listeners(com.pss.qms.Listners.TestListener.class)
public class AuditPlan extends AMLoginDetails {

	@Test
	public void toAuditPlan() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AuditPlan" + (new Random().nextInt())
					+ ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("Audit Plan", "PSS-QMS-005", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("AuditCoordinator"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByVisibleText(properties.getProperty("AMModule"));
			Thread.sleep(1000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(4000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(
					sno + "." + "Enter the username, password, Select Audit Management Module and click on login button"
							+ Utilities.prepareSSNumber(sno),
					font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
//		Thread.sleep(10000);
//		sno++;
//	    driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
//	    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//	    sno++;
//         driver.findElement(By.id("myActivitiesInAM")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amAssignAuditPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Plan Menu", sno, false);
			methodToDoAuditPlan();
			Thread.sleep(5000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
		}
//	}

	private void methodToDoAuditPlan() throws Exception {

		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String AMId = properties.getProperty("ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW");
		isRecordSelected = selectRecdAuditPlan(AMId, isRecordSelected, count);
		Thread.sleep(2000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		if (isRecordSelected) {
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"referecneDocJtableInAmAssignAudit\"]/div/div[3]/div[2]/span/span[2]/b"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reference Add Button", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("uploadSuppDocCFT_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Choose File", sno, false);
			Thread.sleep(4000);
			sno++;
			((JavascriptExecutor) driver)
					.executeScript("document.getElementById('startDateInAssignAudit').removeAttribute('readonly',0);");
			WebElement identifiedDate = driver.findElement(By.id("startDateInAssignAudit"));
			identifiedDate.clear();
			Date date = new Date();
			String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
			driver.findElement(By.id("startDateInAssignAudit")).sendKeys(todaysDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Start Date", sno, false);
			sno++;
			((JavascriptExecutor) driver)
					.executeScript("document.getElementById('endDateInAssignAudit').removeAttribute('readonly',0);");
			WebElement identifiedDate1 = driver.findElement(By.id("endDateInAssignAudit"));
			identifiedDate1.clear();
			Date date1 = new Date();
			String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
			driver.findElement(By.id("endDateInAssignAudit")).sendKeys(todaysDate1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On End Date", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("startDateInAssignAudit")).click();
			sno++;
			driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditAssignment\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Users", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInQmsRegAuditeeUsers_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(2000);
			boolean isRecordSelectedForUser = false;
			String UserFirstName = properties.getProperty("AUDITOR_USER");
			String UserFullName = UserFirstName;
			int count1 = 0;
			isRecordSelectedForUser = selectingTheUserReview(UserFullName, isRecordSelectedForUser, count1);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("regAudUsersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
	         {
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	         }
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button", sno, false);
			String text = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText();
			String amno = text.substring(24, 38);
			System.out.println(amno);
			PropertiesConfiguration properties = new PropertiesConfiguration("src/test/java/QMSUIProperties/AMproperties.properties");
			properties.setProperty("ADHOC_SCHEDULE_AMNO", amno);
			properties.setProperty("ADHOC_NAME_WITH_AI_IN_ADHOC_SCHEDULE_AINO", amno+"/A1");
			properties.setProperty("ADHOC_NAME_WITH_AI2_IN_ADHOC_SCHEDULE_AINO", amno+"/A2");
			properties.save();	
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);
			
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("regAuditeeUsersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
				UserFullName = driver
						.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
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
							.findElement(
									By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#regAuditeeUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
//    	                    Thread.sleep(3000);
					table = driver.findElement(By.id("regAuditeeUsersTableContainer'"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForUser;
	}

	private boolean selectRecdAuditPlan(String AMId, boolean isRecordSelected, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("auditsListTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((AMId == null) || ("".equalsIgnoreCase(AMId)))) {
				AMId = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((AMId == null) || ("".equalsIgnoreCase(AMId))) {
				AMId = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String AMNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[  " + i + " ]/td[6]"))
								.getText();// documentTypeName
						if (AMId.equalsIgnoreCase(AMNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String AMNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[6]"))
							.getText();
					if (AMId.equalsIgnoreCase(AMNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[6]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#auditsListTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(6000);
					table = driver.findElement(By.id("auditsListTableContainer"));// Document Tree approve table
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
    	if(ITestResult.FAILURE==result.getStatus())
    	{
    		Utility.captureScreenshot(driver, result.getName());
    	}
    	
    }
}
