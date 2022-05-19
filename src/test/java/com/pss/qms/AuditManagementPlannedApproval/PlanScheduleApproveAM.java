
package com.pss.qms.AuditManagementPlannedApproval;

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

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
@Listeners(com.pss.qms.Listners.TestListener.class)
public class PlanScheduleApproveAM extends AMLoginDetails {

	@Test
	public void toPlanScheduleApproveAM() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlanScheduleApproveAM"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlanScheduleApproveAM", "PSS-QMS-002", "Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(2000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QaHod"));
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
			Thread.sleep(10000);
//				sno++;
//		      driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
//		      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//			   sno++;
//		      driver.findElement(By.id("myActivitiesInAM")).click();
//		      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
//		      sno++;
//		      driver.findElement(By.xpath("//*[@id=\"amMyActApprovalMenuId\"]/a")).click();
//		      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approvals Menu",sno,false); 
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amPlanOrAdhocAuditAppPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,"Click on Planned/Adhoc/External Audit Menu", sno, false);
			Helper.waitLoadRecords(driver, By.cssSelector("#amPlanAuditAppDetailsContainer > div > div.jtable-busy-message[style='display: none;']"));
			methodToDoAdhocApprove();
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

	private void methodToDoAdhocApprove() throws Exception {
		
		int count = 0;
		boolean isRecordSelected = false;
		String AMId = properties.getProperty("PLAN_NAME_PLAN_SCHEDULE");
		isRecordSelected = selectRecdPlanApprove(AMId, isRecordSelected, count);
		Thread.sleep(1000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		if (isRecordSelected) {
			sno++;
			driver.findElement(By.id("commentsInAmAuditPlanApp")).sendKeys(properties.getProperty("AM_50"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("approveBtnInAmPlanAuditApp")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Approve Button", sno, false);
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

	private boolean selectRecdPlanApprove(String AMId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("amPlanAuditAppDetailsContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/div[4]/div[2]/span"))
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
						.findElement(
								By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((AMId == null) || ("".equalsIgnoreCase(AMId))) {
				AMId = driver
						.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String AMNumberSequence = driver.findElement(By.xpath(
								".//*[@id='amPlanAuditAppDetailsContainer']/div/table/tbody/tr[ " + i + "]/td[3]"))
								.getText();// documentTypeName
						if (AMId.equalsIgnoreCase(AMNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr[ " + i
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
					String AMNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (AMId.equalsIgnoreCase(AMNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#amPlanAuditAppDetailsContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("amPlanAuditAppDetailsContainer"));// Document Tree approve table
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
