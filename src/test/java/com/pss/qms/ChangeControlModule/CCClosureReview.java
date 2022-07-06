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

//import org.apache.bcel.generic.Select;
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
public class CCClosureReview extends CCLoginDetails {

	@Test
	public void toCCClosureReview() throws Exception {

//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CCClosureReview"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CCClosureReview", "PSS-QMS-016", "Pass");
			writer.setPageEvent(event);
			document.open();
			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
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
WebDriverWait wait1 = new WebDriverWait(driver, 90);
 wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='ccClosureQAReviewPage.do']")));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.cssSelector("a[href='ccClosureQAReviewPage.do']"));
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
		//	driver.findElement(By.cssSelector("a[href='ccClosureQAReviewPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on closure review sub menu", sno,
					false);
			Helper.waitLoadRecords(driver, By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-busy-message[style='display: none;']"));
//			 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-busy-message[style='display: none;']")));
//        Thread.sleep(20000);
			methodToDoClosureReview();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void methodToDoClosureReview() throws Exception {

		Thread.sleep(6000);

//    	JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//        WebElement element8 = driver.findElement(By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/table/tbody/tr[5]/td[3]"));
//        jse8.executeScript("arguments[0].scrollIntoView(true);", element8);
//        Thread.sleep(2000);

		sno++;
		int count = 0;
		boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/";
//        Date date = new Date() ;
//        String sdf = new SimpleDateFormat("YY").format(date);
		String CCNumber = properties.getProperty("chgCtrlId");
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        String CCNumberToTrim = CCLoginDetails.finalCCNumber;
//        String CCNumber = CCNumberToTrim.trim(); 
//        System.out.println("CC Number is coming........:"+CCNumber);
		Thread.sleep(3000);
		isRecordSelected = selectRecdClosureReview(CCNumber, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(3000);
		if (isRecordSelected) {
			Thread.sleep(2000);
			Robot robot = new Robot();
			for (int i = 0; i < 1; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}

			sno++;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element1 = driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a"));
			js.executeScript("arguments[0].click();", element1);

//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(4000);
			
			sno++;
			driver.findElement(By.id("postRiskAssessmentDetailsInCCClosureRev")).sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the check list", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element2 = driver.findElement(By.id("preChkListBrowBtnInQAReviewer"));
			js.executeScript("arguments[0].click();", element2);
//            driver.findElement(By.id("preChkListBrowBtnInQAReviewer")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Browse button", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element3 = driver
					.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr/td[3]"));
			js.executeScript("arguments[0].click();", element3);

//            driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr/td[3]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the check list", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element4 = driver.findElement(By.id("selectChkListDetailsWindow"));
			js.executeScript("arguments[0].click();", element4);

//            driver.findElement(By.id("selectChkListDetailsWindow")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
			Thread.sleep(7000);
			sno++;
			WebElement element5 = driver.findElement(By.id("chkPointResultInCCClosureQARev1"));
			js.executeScript("arguments[0].click();", element5);
			Select DropDownCheckList = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev1")));
			Thread.sleep(2000);
			DropDownCheckList.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the check list status", sno,
					false);
			Thread.sleep(2000);
			sno++;
			WebElement element6 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev1"));
			js.executeScript("arguments[0].click();", element6);
			driver.findElement(By.id("chkPointJustificationInCCClosureQARev1"))
					.sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element7 = driver.findElement(By.id("chkPointResultInCCClosureQARev2"));
			js.executeScript("arguments[0].click();", element7);
			Select DropDownCheckList1 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev2")));
			Thread.sleep(2000);
			DropDownCheckList1.selectByIndex(2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the check list status", sno,
					false);
			Thread.sleep(2000);
			sno++;
			WebElement element8 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev2"));
			js.executeScript("arguments[0].click();", element8);
			driver.findElement(By.id("chkPointJustificationInCCClosureQARev2"))
					.sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element9 = driver.findElement(By.id("chkPointResultInCCClosureQARev3"));
			js.executeScript("arguments[0].click();", element9);
			Select DropDownCheckList2 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev3")));
			Thread.sleep(2000);
			DropDownCheckList2.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,
					false);
			Thread.sleep(2000);
			sno++;
			WebElement element10 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev3"));
			js.executeScript("arguments[0].click();", element10);
			driver.findElement(By.id("chkPointJustificationInCCClosureQARev3"))
					.sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			WebElement element11 = driver.findElement(By.id("chkPointResultInCCClosureQARev4"));
			js.executeScript("arguments[0].click();", element11);
			Select DropDownCheckList3 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev4")));
			DropDownCheckList3.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,
					false);
			Thread.sleep(2000);
			sno++;
			WebElement element12 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev4"));
			js.executeScript("arguments[0].click();", element12);
			driver.findElement(By.id("chkPointJustificationInCCClosureQARev4"))
					.sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(5000);
			sno++;
			// WebElement element13 = driver.findElement(By.id("chkPointResultInCCClosureQARev5"));
			// js.executeScript("arguments[0].click();", element13);
			// Select DropDownCheckList4 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev5")));
			// DropDownCheckList4.selectByIndex(1);
			// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,
					// false);
			// Thread.sleep(2000);
			// sno++;
			// WebElement element14 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev5"));
			// js.executeScript("arguments[0].click();", element14);
			// driver.findElement(By.id("chkPointJustificationInCCClosureQARev5"))
					// .sendKeys(properties.getProperty("CC_2000"));
			// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			// Thread.sleep(5000);
////            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
////            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno,false);
////            Thread.sleep(5000);
//            sno++;
//            WebElement element15 = driver.findElement(By.id("chkPointResultInCCClosureQARev6"));
//            js.executeScript("arguments[0].click();", element15);
//            Select DropDownCheckList5 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev6")));
//            DropDownCheckList5.selectByIndex(1);
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,false);
//            Thread.sleep(2000);
//            sno++;
//            WebElement element16 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev6"));
//            js.executeScript("arguments[0].click();", element16);
//            driver.findElement(By.id("chkPointJustificationInCCClosureQARev6")).sendKeys(properties.getProperty("CC_2000"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
//            Thread.sleep(5000);
//            sno++;
//            WebElement element17 = driver.findElement(By.id("chkPointResultInCCClosureQARev7"));
//            js.executeScript("arguments[0].click();", element17);
//            Select DropDownCheckList6 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev7")));
//            DropDownCheckList6.selectByIndex(1);
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,false);
//            Thread.sleep(2000);
//            sno++;
//            WebElement element18 = driver.findElement(By.id("chkPointJustificationInCCClosureQARev7"));
//            js.executeScript("arguments[0].click();", element18);
//            driver.findElement(By.id("chkPointJustificationInCCClosureQARev7")).sendKeys(properties.getProperty("CC_2000"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
//            Thread.sleep(5000);
//            sno++;
//            WebElement element19 = driver.findElement(By.id("chkPointResultInCCClosureQARev8"));
//            js.executeScript("arguments[0].click();", element19);
//            Select DropDownCheckList7 = new Select(driver.findElement(By.id("chkPointResultInCCClosureQARev8")));
//            DropDownCheckList7.selectByIndex(1);
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on the check list status", sno,false);
//            Thread.sleep(2000);
//            sno++;
//            WebElement element20= driver.findElement(By.id("chkPointJustificationInCCClosureQARev8"));
//            js.executeScript("arguments[0].click();", element20);
//            driver.findElement(By.id("chkPointJustificationInCCClosureQARev8")).sendKeys(properties.getProperty("CC_2000"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
			Thread.sleep(5000);
//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
//            Thread.sleep(5000);
//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
//            Thread.sleep(5000);
//            sno++;
//            driver.findElement(By.id("ccCloserComments")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on closure review sub menu", sno,false);
//            Thread.sleep(5000);
			sno++;
			WebElement element21 = driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a"));
			js.executeScript("arguments[0].click();", element21);
//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element22 = driver.findElement(By.id("ccCloserComments"));
			js.executeScript("arguments[0].click();", element22);
			driver.findElement(By.id("ccCloserComments")).sendKeys(properties.getProperty("CC_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element23 = driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[3]/a"));
			js.executeScript("arguments[0].click();", element23);
//            driver.findElement(By.xpath("//*[@id=\"steps-uid-0\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			WebElement element24 = driver.findElement(By.id("eSignPwdInWnd"));
			js.executeScript("arguments[0].click();", element24);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Siganture password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			WebElement element25 = driver.findElement(By.id("subBtnInValidateESign"));
			js.executeScript("arguments[0].click();", element25);
			//driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
			Thread.sleep(2000);
			Helper.clickElement(driver, By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a"));
//            driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
			sno++;
			Thread.sleep(2000);
			Helper.clickElement(driver, By.cssSelector("a[href='Logout.do']"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecdClosureReview(String CCNumber, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("ccClosureQaReviewDetailsTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			Helper.scrollElement(driver, By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/div[4]/div[2]/span"));
			String a = driver.findElement(By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber)))) {
				CCNumber = driver.findElement(By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/table/tbody/tr[1]/td[3]")).getText();// documentType
			} else if ((CCNumber == null) || ("".equalsIgnoreCase(CCNumber))) {
				CCNumber = driver.findElement(By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						Helper.scrollElement(driver, By.xpath("//*[@id='ccClosureQaReviewDetailsTable']/div/table/tbody/tr[ " + i + "]/td[3]"));
						String CCNumberSequence = driver.findElement(By.xpath("//*[@id='ccClosureQaReviewDetailsTable']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();// documentTypeName
						if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
							Helper.clickElement(driver, By.xpath("//*[@id='ccClosureQaReviewDetailsTable']/div/table/tbody/tr[ " + i + "]/td[3]"));
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
									By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (CCNumber.equalsIgnoreCase(CCNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"ccClosureQaReviewDetailsTable\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					Helper.clickElement(driver, By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//					driver.findElement(By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();// next page in Document approve list
					Thread.sleep(3000);
					Helper.waitLoadRecords(driver, By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-busy-message[style='display: none;']"));
					table = driver.findElement(By.id("ccClosureQaReviewDetailsTable"));// Document Tree approve table
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