package com.pss.qms.ChangeControlRejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.CCLoginDetails;

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
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
//@Listeners(com.pss.qms.Listners.TestListenerCC.class)
public class DeptReviewerCC extends CCLoginDetails {

	@Test
	public void toDeptReviewerCC() throws Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeptReviewerCC"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeptReviewerCC", "PSS-QMS-002", "Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME2"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByVisibleText(properties.getProperty("QMS_Module"));
			Thread.sleep(1000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(
					sno + "." + "Enter the username, password Select Change Control Module and click on login button"
							+ Utilities.prepareSSNumber(sno),
					font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(4000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(15000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(20000);
WebDriverWait wait1 = new WebDriverWait(driver, 60);
 wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccReviewPage.do']")));
			driver.findElement(By.cssSelector("a[href='ccReviewPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
        Thread.sleep(20000);
			 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			methodToDoDeptReviewChgControl();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void methodToDoDeptReviewChgControl() throws Exception {

		Thread.sleep(4000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		WebElement element = driver
//				.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"));
//		jse.executeScript("arguments[0].scrollIntoView(true);", element);

//	        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT");
//	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//	        String chgCtrlNoWithPlantCode = DepartmentCode + "/";
//	        Date date = new Date();
//	        String sdf = new SimpleDateFormat("YY").format(date);
//	        String chgCtrlId = "/0104";
		String CCNumber = properties.getProperty("chgCtrlId");
//	         String CCNumberToTrim = CCLoginDetails.finalCCNumber;
//	        String CCNumber = CCNumberToTrim.trim(); 
//	        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(4000);
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
		isRecordSelected = selectRecdDeptReviewChgControlDoctPositiveFlow(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate menu", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			sno++;
			Thread.sleep(5000);
			driver.findElement(By.id("ccAddSuppDocsDept")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("supptDocumentForTextDept1")).sendKeys(properties.getProperty("Document_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the document name", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("uploadSuppDocDept_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("commentsInHodCcReview")).sendKeys(properties.getProperty("CC_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("approveInHodCcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdDeptReviewChgControlDoctPositiveFlow(String chgControlNumber, boolean isRecordSelected,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlReviewTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
				chgControlNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
				chgControlNumber = driver
						.findElement(
								By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String CCNumberSequence = driver.findElement(By.xpath(
								".//*[@id='changeControlReviewTableContainer']/div/table/tbody/tr[ " + i + "]/td[3]"))
								.getText();// documentTypeName
						if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[56]/button"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String CCNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (chgControlNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"changeControlReviewTableContainer\"]/div/table/tbody/tr/td[56]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					 WebElement element5 = driver.findElement(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
			            JavascriptExecutor js5 = (JavascriptExecutor)driver;
			            js5.executeScript("arguments[0].click();", element5);

//					driver.findElement(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(3000);
					WebDriverWait wait1 = new WebDriverWait(driver, 240);
					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#changeControlReviewTableContainer > div > div.jtable-busy-message[style='display: none;']")));
					table = driver.findElement(By.id("changeControlReviewTableContainer"));// Document Tree approve																							// table
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
