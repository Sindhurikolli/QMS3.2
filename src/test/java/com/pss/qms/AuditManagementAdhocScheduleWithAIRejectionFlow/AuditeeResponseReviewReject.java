package com.pss.qms.AuditManagementAdhocScheduleWithAIRejectionFlow;

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
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;
@Listeners(com.pss.qms.Listners.TestListener.class)
public class AuditeeResponseReviewReject extends AMLoginDetails {

	@Test
	public void toAuditeeResponseReviewReject() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AuditeeResponseReviewReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("AuditeeResponseReview Reject", "PSS-QMS-013",
					"Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("DepartmentHod"));
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
//			 Thread.sleep(10000);
//			sno++;
//		   driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
//		   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//		   sno++;
//	      driver.findElement(By.id("myActivitiesInAM")).click();
//	      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amAuditeeResponseReviewPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Auditee Response Review Menu",
					sno, false);
			Helper.waitLoadRecords(driver, By.cssSelector("#auditsContainerInReviewAuditFindingAppForm > div > div.jtable-busy-message[style='display: none;']"));
			methodToDoAuditeeResponseReject();
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

	private void methodToDoAuditeeResponseReject() throws Exception {

		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String AuditId = properties.getProperty("ADHOC_NAME_IN_ADHOC_SCHEDULE_WITH_AI_REJECT_FLOW");
		isRecordSelected = selectRecdAuditeeResponseReject(AuditId, isRecordSelected, count);
		Thread.sleep(2000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
		if (isRecordSelected) {
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(2000);
			sno++;
//			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
//			Thread.sleep(2000);
//			sno++;
			driver.findElement(By.id("commentsInAmReviewAuditFindingAppForm"))
					.sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("rejectInReviewAuditForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Reject RadioButton", sno,
					false);
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
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdAuditeeResponseReject(String AuditId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("auditsContainerInReviewAuditFindingAppForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((AuditId == null) || ("".equalsIgnoreCase(AuditId)))) {
				AuditId = driver
						.findElement(By.xpath(
								"//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((AuditId == null) || ("".equalsIgnoreCase(AuditId))) {
				AuditId = driver
						.findElement(By.xpath(
								"//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String AuditNumberSequence = driver.findElement(
								By.xpath(".//*[@id='auditsContainerInReviewAuditFindingAppForm']/div/table/tbody/tr[ "
										+ i + "]/td[5]"))
								.getText();// documentTypeName
						if (AuditId.equalsIgnoreCase(AuditNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/table/tbody/tr[ " + i
											+ " ]/td[5]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String AuditNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (AuditId.equalsIgnoreCase(AuditNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"auditsContainerInReviewAuditFindingAppForm\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#auditsContainerInReviewAuditFindingAppForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("auditsContainerInReviewAuditFindingAppForm"));// Document Tree
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
