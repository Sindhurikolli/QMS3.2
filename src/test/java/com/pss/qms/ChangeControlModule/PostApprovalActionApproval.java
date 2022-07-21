package com.pss.qms.ChangeControlModule;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
import com.pss.qms.login.CCLoginDetails;
 
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
//@Listeners(com.pss.qms.Listners.TestListenerCC.class)
public class PostApprovalActionApproval extends CCLoginDetails {

	@Test
	public void toPostApprovalActionApproval() throws Exception {

//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionApproval"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionApproval", "PSS-QMS-012",
					"Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));
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
//        Thread.sleep(10000);
//        driver.findElement(By.id("myActivitiesInCC")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(16000);
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
 wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccPostAppActionsApprovePage.do']")));
			driver.findElement(By.cssSelector("a[href='ccPostAppActionsApprovePage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Post Approval Actions menu",
					sno, false);
//        sno++;
//        Thread.sleep(16000);
//        driver.findElement(By.cssSelector("#ccPostAppActionsListId > ul > li:nth-child(2) > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on approval submenu", sno,false);
//        Thread.sleep(100000);
			Helper.waitLoadRecords(driver, By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-busy-message[style='display: none;']"));
//			 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-busy-message[style='display: none;']")));
			methodToDoPostApprovalActionApproval();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void methodToDoPostApprovalActionApproval() throws Exception {

		Thread.sleep(6000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date() ;
//        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("chgCtrlId");
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        String CCNumberToTrim = CCLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(3000);
		isRecordSelected = selectRecdPostApprovalActionApproval(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(3000);
		if (isRecordSelected) {
			sno++;
			Robot robot = new Robot();
			for (int i = 0; i < 1; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
//            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next button", sno, false);
			Thread.sleep(4000);
			sno++;
			WebElement element1 = driver.findElement(By.xpath("//*[starts-with(@id, \"postAppAcceptStatus_\")]"));
			js.executeScript("arguments[0].click();", element1);
//            driver.findElement(By.xpath("//*[starts-with(@id, \"postAppAcceptStatus_\")]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Accept radio button", sno,
					false);
			Thread.sleep(4000);
			sno++;
			WebElement element2 = driver.findElement(By.id("commentsInPostActionsApp"));
			js.executeScript("arguments[0].click();", element2);
			driver.findElement(By.id("commentsInPostActionsApp"))
					.sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(4000);
			sno++;
			WebElement element3 = driver.findElement(By.id("closeButtonInPostApprovalActionsApprove"));
			js.executeScript("arguments[0].click();", element3);
//            driver.findElement(By.id("closeButtonInPostApprovalActionsApprove")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Close button", sno, false);
			Thread.sleep(4000);
			sno++;
			WebElement element4 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			js.executeScript("arguments[0].click();", element4);
//            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit button", sno, false);
			Thread.sleep(4000);
			sno++;
			WebElement element5 = driver.findElement(By.id("eSignPwdInWnd"));
			js.executeScript("arguments[0].click();", element5);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(4000);
			sno++;
			WebElement element6 = driver.findElement(By.id("subBtnInValidateESign"));
			js.executeScript("arguments[0].click();", element6);
//            driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			Thread.sleep(2000);
			WebElement element7 = driver.findElement(By.className("modal-btn"));
			js.executeScript("arguments[0].click();", element7);
//            driver.findElement(By.className("modal-btn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK button", sno, false);
			Thread.sleep(3000);
			sno++;
			//driver.findElement(By.className("username")).click();
			Helper.clickElement(driver, By.className("username"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(1000);
			//driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			Helper.clickElement(driver, By.cssSelector("a[href='Logout.do']"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);


		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdPostApprovalActionApproval(String chgCtrlId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("ccPostActionsApprovalTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println("Total No of Recors - "+totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId)))) {
				chgCtrlId = driver
						.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId))) {
				chgCtrlId = driver
						.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[5]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						Helper.waitLoadRecords(driver, By.xpath("//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]"));
						String CCNumberSequence = driver.findElement(By.xpath("//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]")).getText();// documentTypeName
						if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
							Helper.clickElement(driver, By.xpath("//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]"));
//							driver.findElement(By.xpath("//*[@id='ccPostActionsApprovalTable']/div/table/tbody/tr[ " + i + "]/td[5]")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[5]")).getText();
					if (chgCtrlId.equalsIgnoreCase(capaNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"ccPostActionsApprovalTable\"]/div/table/tbody/tr/td[5]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					Helper.clickElement(driver, By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					driver.findElement(By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(5000);
					Helper.waitLoadRecords(driver, By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-busy-message[style='display: none;']"));
//					WebDriverWait wait1 = new WebDriverWait(driver, 60);
//					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ccPostActionsApprovalTable > div > div.jtable-busy-message[style='display: none;']")));
					table = driver.findElement(By.id("ccPostActionsApprovalTable"));// Document Tree approve table
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
