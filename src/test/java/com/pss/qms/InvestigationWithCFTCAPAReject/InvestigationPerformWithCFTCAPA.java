package com.pss.qms.InvestigationWithCFTCAPAReject;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.pss.qms.login.InvestigationLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class InvestigationPerformWithCFTCAPA extends InvestigationLoginDetails {

	@Test
	public void PerformInvestigation() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvestigationPerformWithCFTCAPA"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvestigationPerformWithCFTCAPA", "PSS-QMS-001",
				"Pass");
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
		Thread.sleep(2000);
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='performFullInvPage.do']")));
		driver.findElement(By.cssSelector("a[href='performFullInvPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Perform menu", sno,
				false);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#investigationTableContainer > div > div.jtable-busy-message[style='display: none;']")));
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
			Thread.sleep(5000);
			driver.findElement(By.id("invDetailsInPerformInvActionsInInvPlan"))
					.sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);

			driver.findElement(By.id("summaryInPerformInvActionsInInvPlan"))
					.sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);
			driver.findElement(By.id("addSupDocsBtn_ForInvActionUpload_InvReviewPage")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("uploadSuppDocForInAct_1")).sendKeys(properties.getProperty("UPLOAD_DOCUMENT"));
			Thread.sleep(2000);
			Select LifeCycle = new Select(driver.findElement(By.id("lifeCycleIdInPerform")));
			LifeCycle.selectByVisibleText(properties.getProperty("LifeCycle"));
			;
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0, 1000)");
			driver.findElement(By.id("cftChildReqInInvReview")).click();
			jse.executeScript("window.scrollBy(0, 200)");
			driver.findElement(By.id("cftTeamAddInInvReview")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("locTreeForCftTeamInQmsInvReview_2_span")).click();
			Thread.sleep(2000);
			boolean isRecordSelectedForCFT = false;
			String CFTFirstName = properties.getProperty("CFT_Ecode");
			String CFTFullName = CFTFirstName;
			int count5 = 0;
			isRecordSelectedForCFT = selectingTheCFTReview(CFTFullName, isRecordSelectedForCFT, count5);
			if (isRecordSelectedForCFT) {
				jse.executeScript("window.scrollBy(0,250)");
				driver.findElement(By.id("addBtnInCftTeamAdd")).click();
			} else {
				System.out.println("CFT Department Not Selected");
			}
			 JavascriptExecutor jse90 = (JavascriptExecutor) driver;
	            WebElement element90 = driver.findElement(By.id("ui-id-389"));
	            jse90.executeScript("arguments[0].scrollIntoView(true);", element90);
	            jse90.executeScript("arguments[0].click();", element90);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"rcaAnalysisInDevReview\"]/div/div[3]/div[2]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("rootCauseInrootCauseDlg")).sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources\"]/div/table/tbody/tr[3]/td[1]/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("addBtnInRootCauseWndw")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("addSupDocsBtn_ForRootCauseUpload_InvReviewPage")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("uploadSuppDocForRootCause_1"))
					.sendKeys(properties.getProperty("UPLOAD_DOCUMENT"));
			Thread.sleep(2000);
			 WebElement element91 = driver.findElement(By.id("ui-id-390"));
	            jse90.executeScript("arguments[0].scrollIntoView(true);", element91);
	            jse90.executeScript("arguments[0].click();", element91);
			Thread.sleep(2000);
			driver.findElement(By.id("impactAssessmentDescription")).sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);
			driver.findElement(By.id("addSupDocsBtn_ForImpAsmtUpload_InvReviewPage")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("uploadSuppDocForImpAsmt_1")).sendKeys(properties.getProperty("UPLOAD_DOCUMENT"));
			Thread.sleep(2000);
			 WebElement element92 = driver.findElement(By.id("ui-id-391"));
	            jse90.executeScript("arguments[0].scrollIntoView(true);", element92);
	            jse90.executeScript("arguments[0].click();", element92);
			
			Thread.sleep(2000);
			driver.findElement(By.id("isNeedCapaInInvReview")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"capaDetailsInFullInvPage\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.id("deptSelBtn_IncPostAppActAddCapaWin")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("capaTreeContainer_2_switch")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText(properties.getProperty("CAPADepartment"))).click();
			Thread.sleep(2000);
			driver.findElement(By.id("selectBtnInCapaLocSelect")).click();
			Thread.sleep(2000);
			  driver.findElement(By.id("addCorrectiveActCktInInvCapaAddWin")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Corrective Actions Check Box",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"caDetialsJTableInInvCapaPerformDialog\"]/div/div[3]/div[2]/span/span[2]")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record Button",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.id("caDesciptionInCADetailsJtable1")).sendKeys(properties.getProperty("CA_Description"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CA Comments",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.id("addPreventiveActCktInInvCapaAddWin")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Preventive Actions Check Box",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//*[@id=\"paDetialsJTableInInvCapaPerformDialog\"]/div/div[3]/div[2]/span/span[2]")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record Button",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.id("paDesciptionInCADetailsJtable1")).sendKeys(properties.getProperty("PA_Description"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CAPA Comments",sno,false);
	            sno++;
	            Thread.sleep(2000);
	            driver.findElement(By.id("addBtnInInvCapaWinInPerform")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//			driver.findElement(By.id("capaDescInFullInvPage")).sendKeys(properties.getProperty("INV_2000"));
//			Thread.sleep(2000);
//			driver.findElement(By.id("addBtnInCaPaWndw")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select and add CAPA Details", sno,
//					false);
//			sno++;
//	   driver.findElement(By.id("justificationInAppActAddCapaWin")).sendKeys(properties.getProperty("INV_2000"));
			Thread.sleep(2000);
			driver.findElement(By.id("submitBtnInfullInvReview")).click();
			Thread.sleep(2000);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 70);
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
		WebElement table = driver.findElement(By.id("investigationTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType
			} else if ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName))) {
				InvLastName = driver
						.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String InvFirstName = driver.findElement(By.xpath(
								"//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						System.out.println("InvReviewFullName: " + InvFirstName);
						if (InvLastName.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(By.xpath(
									"//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[28]"))
									.click();
							recordSelectedForInv = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (InvLastName.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[28]")).click();
						recordSelectedForInv = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recordSelectedForInv) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#investigationTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(5000);
					table = driver.findElement(By.id("investigationTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}

		}
		return recordSelectedForInv;
	}

	private boolean selectingTheInvPlan(String InvName, boolean isRecordSelectedForInvTeam, int count)
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
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (((InvName == null) || ("".equalsIgnoreCase(InvName)))) {

				InvName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[]/td[5]"))
						.getText();// documentType
			} else if ((InvName == null) || ("".equalsIgnoreCase(InvName))) {
				InvName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String InvFirstName = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						System.out.println("InvReviewFullName: " + InvFirstName);
						if (InvName.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
									.click();
							isRecordSelectedForInvTeam = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
							.getText();
					if (InvName.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
								.click();
						isRecordSelectedForInvTeam = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForInvTeam) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(5000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForInvTeam;
	}

	private boolean selectingTheCFTReview(String cftFullName, boolean recordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("cftTeamTableWindowContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
//    	  int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
//          if (perPageNoOfRecordsPresent > 0) {
//              String a = driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//              String[] parts = a.split(" of ");
//              try {
//                  totalNoOfRecords = Integer.parseInt(parts[1].trim());
//              } catch (Exception e) {
//                  System.out.println(e.getMessage());
//              }
//          }
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (((cftFullName == null) || ("".equalsIgnoreCase(cftFullName)))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((cftFullName == null) || ("".equalsIgnoreCase(cftFullName))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (perPageNoOfRecordsPresent > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String cftReviewerFullName = driver
								.findElement(By.xpath(
										"//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[5]"))
								.getText();// documentTypeName
						System.out.println("cftReviewerFullName: " + cftReviewerFullName);
						if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
							driver.findElement(By.xpath(
									"//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[5]"))
									.click();
							recordSelected = true;
							break;
						}
					}

				} else {
					String cftReviewerFullName = driver
							.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[5]")).click();
						recordSelected = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#cftTeamTableWindowContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(5000);
					table = driver.findElement(By.id("cftTeamTableWindowContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return recordSelected;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
