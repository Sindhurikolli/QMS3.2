package com.pss.qms.ChangeControlRejection;

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
import com.pss.qms.login.CCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

public class ClosureApproveReject extends CCLoginDetails {

	@Test
	public void toClosureApproveReject() throws Exception {

//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ClosureApproveReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("ClosureApproveReject", "PSS-QMS-053", "Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME4"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByIndex(2);
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
			Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#changeControl_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Change Control module",sno,false);
//        sno++;
//        Thread.sleep(12000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccClosureListId > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Closure menu", sno,false);
//        sno++;
//        Thread.sleep(16000);
WebDriverWait wait1 = new WebDriverWait(driver, 60);
 wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccClosureApprovePage.do']")));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.cssSelector("a[href='ccClosureApprovePage.do']"));
			jse.executeScript("arguments[0].click();", element);

			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on closure Approve sub menu", sno,
					false);
			Thread.sleep(2000);
		 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#changeControlCloseApproveTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			methodToDoClosureApprove();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void methodToDoClosureApprove() throws Exception {

		Thread.sleep(5000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//	        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//	        String chgCtrlNoWithPlantCode = DepartmentCode + "/";
//	        Date date = new Date() ;
//	        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("chgCtrlId");
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//	        String CCNumberToTrim = CCLoginDetails.finalCCNumber;
//	        String CCNumber = CCNumberToTrim.trim(); 
//	        System.out.println("CC Number is coming........:"+CCNumber);
//	        Thread.sleep(4000);
		isRecordSelected = selectRecdClosureApprove(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(5000);
		if (isRecordSelected) {
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("ccCloserApprovalComments"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)");
			driver.findElement(By.id("closureChangeControlRejectSubmitBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reject button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(3000);
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

	private boolean selectRecdClosureApprove(String chgCtrlId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("changeControlCloseApproveTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"changeControlCloseApproveTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId)))) {
				Helper.scrollElement(driver, By.xpath(
								"//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr[1]/td[4]"));
				chgCtrlId = driver
						.findElement(By.xpath(
								"//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId))) {
				Helper.scrollElement(driver, By
								.xpath("//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"));
				chgCtrlId = driver
						.findElement(By
								.xpath("//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						Helper.scrollElement(driver, By.xpath(".//*[@id='changeControlCloseApproveTableContainer']/div/table/tbody/tr[ " + i
										+ "]/td[4]"));
						String CCNumberSequence = driver.findElement(
								By.xpath(".//*[@id='changeControlCloseApproveTableContainer']/div/table/tbody/tr[ " + i
										+ "]/td[4]"))
								.getText();// documentTypeName
						if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
							Helper.clickOnCCReViewButton(driver, By.xpath(".//*[@id='changeControlCloseApproveTableContainer']/div/table/tbody/tr[ "
											+ i + "]/td[4]"));
							Helper.waitUntilPageLoad(driver);
//							driver.findElement(
//									By.xpath(".//*[@id='changeControlCloseApproveTableContainer']/div/table/tbody/tr[ "
//											+ i + "]/td[4]"))
//									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					Helper.scrollElement(driver, By.xpath(
									"//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"));
					String CCNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
						Helper.clickOnCCReViewButton(driver, By
								.xpath("//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"));
						Helper.waitUntilPageLoad(driver);
//						driver.findElement(By
//								.xpath("//*[@id=\"changeControlCloseApproveTableContainer\"]/div/table/tbody/tr/td[4]"))
//								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					Helper.scrollElement(driver, By.cssSelector(
							"#changeControlCloseApproveTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					driver.findElement(By.cssSelector(
							"#changeControlCloseApproveTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					WebDriverWait wait1 = new WebDriverWait(driver, 60);
					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#changeControlCloseApproveTableContainer > div > div.jtable-busy-message[style='display: none;']")));
					table = driver.findElement(By.id("changeControlCloseApproveTableContainer"));// Document Tree
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
