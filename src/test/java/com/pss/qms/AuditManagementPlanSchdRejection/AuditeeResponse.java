package com.pss.qms.AuditManagementPlanSchdRejection;

import java.awt.Desktop;
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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class AuditeeResponse extends AMLoginDetails {

	@Test
	public void toAuditeeResponse() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AuditeeResponseAdhocAM"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("Auditee Response", "PSS-QMS-012", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Auditee"));
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
			Thread.sleep(3000);
//	  		sno++;
//	    	  driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
//	    	  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//	    	  sno++; 
//	        driver.findElement(By.id("myActivitiesInAM")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
//	    	  Thread.sleep(10000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amAuditeeResponsePage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Auditee Response Menu", sno,
					false);
			methodToDoAuditeeResponse();
			Thread.sleep(3000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
			
//		} catch (Exception e) {
//			e.printStackTrace();
		}
//	}

	private void methodToDoAuditeeResponse() throws Exception {
		
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String AMId = properties.getProperty("PLAN_NAME_PLAN_SCHEDULE_IN_REJECT_FLOW");
		isRecordSelected = selectRecdQAReviewChgControl(AMId, isRecordSelected, count);
		Thread.sleep(1000);
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
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
			Thread.sleep(5000);
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(
					By.cssSelector("#chkPointsTableInAuditeeResponseForm > div > div.jtable-busy-message"))));
			driver.findElement(
					By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr/td[17]/button"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select Task_Type = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
			Task_Type.selectByIndex(3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("addBtnInPerformChkList")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[2]/td[17]/button")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            Select Task_Type2 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
	            Task_Type2.selectByIndex(3);
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("addBtnInPerformChkList")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[3]/td[17]/button")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            Select Task_Type3 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
	            Task_Type3.selectByIndex(3);
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
	            Thread.sleep(2000);
	            sno++;
	            driver.findElement(By.id("addBtnInPerformChkList")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	            Thread.sleep(2000);
//	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[4]/td[17]/button")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select Task_Type4 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
//	            Task_Type4.selectByIndex(3);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("addBtnInPerformChkList")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//	            Thread.sleep(2000);
//	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[5]/td[17]/button")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select Task_Type5 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
//	            Task_Type5.selectByIndex(3);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("addBtnInPerformChkList")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//	            Thread.sleep(2000);
//	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[6]/td[17]/button")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select Task_Type6 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
//	            Task_Type6.selectByIndex(3);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("addBtnInPerformChkList")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//	            Thread.sleep(2000);
//	            driver.findElement(By.xpath("//*[@id=\"chkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr[7]/td[17]/button")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("referenceInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Reference",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("rootCauseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In RootCause",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("responseInAuditResponse")).sendKeys(properties.getProperty("AM_1500"));
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Response",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            Select Task_Type7 = new Select(driver.findElement(By.id("taskTypeInAuditResponse")));
//	            Task_Type7.selectByIndex(3);
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
//	            Thread.sleep(2000);
//	            sno++;
//	            driver.findElement(By.id("addBtnInPerformChkList")).click();
//	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(8000);
//			sno++;
			boolean isRecordSelectedForUser = false;
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditObservationsReview\"]/div/div[3]/div[2]/span"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Reviewer Button", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			Thread.sleep(2000);
//	            driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(2000);
			boolean isRecordSelectedForUser2 = false;
			String UserFirstName2 = properties.getProperty("AUDIT_REVIEWER");
			String UserFullName2 = UserFirstName2;
			int count3 = 0;
			isRecordSelectedForUser = selectingTheAuditReview(UserFullName2, isRecordSelectedForUser2, count3);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"userDetailsInCreateUserGroup\"]/div/div[3]/div[2]/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Approver Button", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(2000);
			boolean isRecordSelectedForUser3 = false;
			String UserFirstName3 = properties.getProperty("AUDIT_APPROVER");
			String UserFullName3 = UserFirstName3;
			int count4 = 0;
			isRecordSelectedForUser = selectingTheApproverReview(UserFullName3, isRecordSelectedForUser3, count4);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
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
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button", sno, false);
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

	private boolean selectingTheApproverReview(String UserFullName2, boolean isRecordSelectedForUser2, int count3)
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
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2)))) {
				UserFullName2 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2))) {
				UserFullName2 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName2.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser2 = true;
							break;
						}
					}
					if (isRecordSelectedForUser2) {
						break;
					}
				} else {
					String UserNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName2.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser2) && (noOfRecordsChecked < totalNoOfRecords)) {
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
		return isRecordSelectedForUser2;
	}

	private boolean selectingTheAuditReview(String UserFullName1, boolean isRecordSelectedForUser1, int count2)
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
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1)))) {
				UserFullName1 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1))) {
				UserFullName1 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName1.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser1 = true;
							break;
						}
					}
					if (isRecordSelectedForUser1) {
						break;
					}
				} else {
					String UserNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName1.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser1) && (noOfRecordsChecked < totalNoOfRecords)) {
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
		return isRecordSelectedForUser1;
	}

	private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1)
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
				UserFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
				UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
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
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
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
		return isRecordSelectedForUser;
	}

	private boolean selectRecdQAReviewChgControl(String AMId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("auditsContainerInReviewAuditFindingForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/div[4]/div[2]/span"))
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
				AMId = driver
						.findElement(By.xpath(
								"//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((AMId == null) || ("".equalsIgnoreCase(AMId))) {
				AMId = driver
						.findElement(By
								.xpath("//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String AMNumberSequence = driver.findElement(
								By.xpath(".//*[@id='auditsContainerInReviewAuditFindingForm']/div/table/tbody/tr[ " + i
										+ "]/td[3]"))
								.getText();// documentTypeName
						if (AMId.equalsIgnoreCase(AMNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/table/tbody/tr[ "
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
					String AMNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (AMId.equalsIgnoreCase(AMNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"auditsContainerInReviewAuditFindingForm\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#auditsContainerInReviewAuditFindingForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("auditsContainerInReviewAuditFindingForm"));// Document Tree
																									// approve table
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
