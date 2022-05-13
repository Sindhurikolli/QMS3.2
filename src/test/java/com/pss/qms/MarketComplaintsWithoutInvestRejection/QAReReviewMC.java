
package com.pss.qms.MarketComplaintsWithoutInvestRejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QAReReviewMC extends MCLoginDetails {

	@Test
	public void QAReviewerMCModule() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReReviewMC" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReReviewMC", "PSS-QMS-006", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QAHod"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_Selection"));
		Thread.sleep(2000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
//			sno++;
		Thread.sleep(3000);
//			driver.findElement(By.cssSelector("#marketComplaints_tile_Id > div > div > div > h2")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Market Complaints module", sno,
//					false);
		Thread.sleep(3000);
		sno++;
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='mcMyActReviewPage.do']")));
		driver.findElement(By.cssSelector("a[href='mcMyActReviewPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
//    Thread.sleep(20000);
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#mcReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
		methodToDoQAReReviewerMC();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoQAReReviewerMC() throws Exception {

		sno++;
		int count = 0;
		Thread.sleep(2000);
		boolean isRecordSelected = false;
//		Date date = new Date();
//		String sdf = new SimpleDateFormat("YY").format(date);
		String MarketComplaintsId = properties.getProperty("MC_ID");
		isRecordSelected = selectRecdQAReReviewMC(MarketComplaintsId, isRecordSelected, count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		Thread.sleep(2000);
		if (isRecordSelected) {
//			sno++;
//			driver.findElement(By.id("customerNotificationInMcQaReview")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
//					"Click on customer notification checkbox", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.id("invChildReqInMcReview")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on investigation checkboox", sno,
//					false);
//			Thread.sleep(4000);
//			sno++;
//			driver.findElement(By.id("reqDetInMcReview"))
//					.sendKeys(properties.getProperty("Compliant_Short_Description"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
//					"Enter the investigation request details", sno, false);
//			Thread.sleep(4000);
//			sno++;
//			driver.findElement(By.id("selectInvestigationOwner")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
//			Thread.sleep(4000);
//			sno++;
//			driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
//			Thread.sleep(4000);
//			driver.findElement(By.id("locTreeInQmsProdReg_5_ico")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Location", sno, false);
//			Thread.sleep(3000);
//			sno++;
//			boolean isRecordSelectedForInv = false;
//			String InvFirstName = properties.getProperty("Invest_User_E-Code");
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Invetigation user", sno,
//					false);
//			int count4 = 0;
//			isRecordSelectedForInv = selectingTheInvReview(InvFirstName, isRecordSelectedForInv, count4);
//			Thread.sleep(3000);
//			driver.findElement(By.id("usersSelBtnInMcReview")).click();
//			Thread.sleep(3000);
//             JavascriptExecutor jse = (JavascriptExecutor) driver;
//           WebElement element = driver.findElement(By.id("cftTeamAddInMcReview"));
//           jse.executeScript("arguments[0].scrollIntoView(true);", element);
//			Thread.sleep(1000);
//			driver.findElement(By.id("cftChildReqInMcReview")).click();
//			Thread.sleep(3000);
//			JavascriptExecutor jse = (JavascriptExecutor) driver;
//			WebElement element = driver.findElement(By.id("cftTeamAddInMcReview"));
//			jse.executeScript("arguments[0].scrollIntoView(true);", element);
//			driver.findElement(By.id("cftTeamAddInMcReview")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CFT Add button", sno, false);
//			Thread.sleep(3000);
//			sno++;
//			driver.findElement(By.id("locTreeForCftTeamInQmsMcReview_2_span")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Location", sno, false);
//			Thread.sleep(3000);
//			sno++;
////           To Select CFT User
//			boolean isRecordSelectedForCFT = false;
//			String cftFirstName = properties.getProperty("CFT_User_E-Code");
//           JavascriptExecutor jse2 = (JavascriptExecutor)driver;
//           jse2.executeScript("window.scrollBy(0,500)");

//			int count2 = 0;
//			sno++;
//			isRecordSelectedForCFT = selectingTheCFTReview(cftFirstName, isRecordSelectedForCFT, count2);
			// driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[2]/td[2]")).click();

//			Thread.sleep(3000);
//			if (isRecordSelectedForCFT) {
//				Thread.sleep(3000);
//				sno++;
//				driver.findElement(By.id("addBtnInCftTeamAdd")).click();
//				Thread.sleep(3000);
//				sno++;
//             Thread.sleep(5000);
//             driver.findElement(By.id("addSupportingDocCheckInMcQAReview")).click();
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Supporting documents checkbox", sno,false);
//             sno++;
//             Thread.sleep(4000);     
//             JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//             WebElement element1 = driver.findElement(By.id("mcAddSuppDocsInQAReview"));
//             jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
//             Thread.sleep(5000);
//             driver.findElement(By.id("mcAddSuppDocsInQAReview")).click();
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno,false);
//             Thread.sleep(2000);
//             driver.findElement(By.id("uploadSuppDocQAReview_1")).sendKeys(properties.getProperty("Document-2"));
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add the supporting document", sno,false);
			sno++;
			driver.findElement(By.id("cftChildReqInMcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CFT Check Box", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id("cftTeamAddInMcReview"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("cftTeamAddInMcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CFT Add button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("locTreeForCftTeamInQmsMcReview_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Location", sno, false);
			Thread.sleep(3000);
			sno++;
			// To Select CFT User
			boolean isRecordSelectedForCFT = false;
			String cftFirstName = properties.getProperty("CFT_User_E-Code");
//             String cftLastName ="reviewer3";
//             String lastNameCFT ="reviewer3";
////             
//             String cftFullName = cftFirstName+" "+lastNameCFT;
//             System.out.println("cftFullName: "+cftFullName);
			int count2 = 0;
			sno++;
			isRecordSelectedForCFT = selectingTheCFTReview(cftFirstName, isRecordSelectedForCFT, count2);
//             driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[2]/td[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(3000);
			if (isRecordSelectedForCFT) {
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("addBtnInCftTeamAdd")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
				Thread.sleep(3000);

//             driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTableInDev\"]/div/table/tbody/tr[2]/td[2]")).click();
//             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.id("actItmCheckInMcQaReview")).click();
//             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.xpath("//*[@id=\"qaRevActItemsAddTable_McReviewPage\"]/div/div[3]/div[2]/span/span[2]")).click();
//             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.xpath("//*[@id=\"ownersTable_McActionItemCreateOrEditDialog\"]/div/div[3]/div[2]/span/span[2]")).click();
//             Thread.sleep(3000);
////             driver.findElement(By.id("selectOwnerInDevQaReview1")).click();
////             Thread.sleep(3000);
//             
//             sno++;
//             driver.findElement(By.id("locationTreeForMultiUserSelect_2_switch")).click();
//             Thread.sleep(3000);
//             driver.findElement(By.id("locationTreeForMultiUserSelect_3_span")).click();
//             Thread.sleep(3000);
//             sno++;
//              boolean isRecordSelectedForActionItemPerform = false;
//             String ActionItemPerform = properties.getProperty("First_Name");
////             String regLastName  = "reviewer3";
//             String ActionPerformUser = ActionItemPerform;
//             int count5=0;
//             isRecordSelectedForActionItemPerform=selectingActionItemPerform(ActionPerformUser,isRecordSelectedForActionItemPerform,count5);
//             Thread.sleep(3000);
////              driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
////             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.id("selBtn_InMultiUserSelectDialog")).click();
//             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.id("selectApproverInDevQaReview1")).click();
//             Thread.sleep(3000);
//             driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
//             Thread.sleep(3000);
//             driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
//             Thread.sleep(3000);
//             sno++;
//             boolean isRecordSelectedForActionItemApprove = false;
//             String ActionApproveFirstName =properties.getProperty("First_Name1");
//             String ActionApproveLastName ="reviewer3";
//             String ActionApproveFullName = ActionApproveFirstName+" "+ActionApproveLastName;
//             sno++;
//             int count6=0;
//             isRecordSelectedForCFT = selectingTheActionItemApprover(ActionApproveFullName,isRecordSelectedForActionItemApprove, count6);
//            driver.findElement(By.id("usersSelBtnInMcReview")).click();
//             Thread.sleep(3000);
//             sno++;
//             driver.findElement(By.id("actItmDueDateInMcAddActItemDlg")).click();
//             Thread.sleep(3000);
//             driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
//             Thread.sleep(4000);
//              driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[5]/td[4]/a")).click();
//             Thread.sleep(4000);
//             Select ActionItemBeforeProceeding = new Select(driver.findElement(By.id("cmpltActItemBeforeProceedInMcAddActItemDlg")));
//             ActionItemBeforeProceeding.selectByIndex(1);
//             Thread.sleep(3000);
//             driver.findElement(By.id("actItemDescInMcAddActItemDlg")).sendKeys(properties.getProperty("Compliant_Short_Description"));
//				Thread.sleep(3000);
//				driver.findElement(
//						By.xpath("//*[@id=\"qaRevSupportingDocsAddTable_McReviewPage\"]/div/table/tbody/tr/td[4]"))
//						.click();
//             driver.findElement(By.id("addBtnInMcActionItemAddDlg")).click();
//				sno++;
//				WebElement ele = driver.findElement(By.id("dueDateInMcPrim"));
//				((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", ele);
//				Thread.sleep(2000);
//				SimpleDateFormat formattedDate = new SimpleDateFormat("dd/M/yyyy");
//				Calendar c = Calendar.getInstance();
//				c.add(Calendar.DATE, 30); // number of days to add
//				String futureDate = (String) (formattedDate.format(c.getTime()));
//				driver.findElement(By.id("dueDateInMcPrim")).sendKeys(futureDate);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the due date", sno, false);
//				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commentsInMcQaReview")).sendKeys(properties.getProperty("MC_994"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
//             driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
				Thread.sleep(3000);
				JavascriptExecutor jse3 = (JavascriptExecutor) driver;
				WebElement element3 = driver.findElement(By.id("approveMcQaPrimReview"));
				jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.id("approveMcQaPrimReview")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno,
						false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature passwod", sno,
						false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno,
						false);
				Thread.sleep(3000);
				WebDriverWait wait = new WebDriverWait(driver, 70);
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(3000);
				if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
						.equalsIgnoreCase("Submitted successfully.")) {
					sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
							false);
					driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
				}
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.className("username")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

			} else {
				System.out.println("Record is not Selected For CFT Review");
				Assert.assertTrue(false);
			}
		}
	}

	private boolean selectRecdQAReReviewMC(String MarketComplaintsId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("mcReviewTableContailner"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId)))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType
			} else if ((MarketComplaintsId == null) || ("".equalsIgnoreCase(MarketComplaintsId))) {
				MarketComplaintsId = driver
						.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String capaNumberSequence = driver
								.findElement(By.xpath(
										".//*[@id='mcReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]"))
								.getText();// documentTypeName
						if (MarketComplaintsId.contains(capaNumberSequence)) {
							driver.findElement(By.xpath(
									".//*[@id='mcReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[51]/button"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String capaNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (MarketComplaintsId.contains(capaNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"mcReviewTableContailner\"]/div/table/tbody/tr/td[51]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#mcReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("mcReviewTableContailner"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	// Method To Select CFT User In The Table
	private boolean selectingTheCFTReview(String cftFirstName, boolean isRecordSelectedForCFT, int count2) {
		WebElement table = driver.findElement(By.id("cftTeamTableWindowContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//         int totalNoOfRecords = 0;
//         int noOfRecordsChecked = 0;
//         if (perPageNoOfRecordsPresent > 0) {
//             String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//             String[] parts = a.split(" of ");
//             try {
//                 totalNoOfRecords = Integer.parseInt(parts[1].trim());
//             } catch (Exception e) {
//                 System.out.println(e.getMessage());
//             }
//         }
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if (((cftFirstName == null) || ("".equalsIgnoreCase(cftFirstName)))) {
				cftFirstName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((cftFirstName == null) || ("".equalsIgnoreCase(cftFirstName))) {
				cftFirstName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String cftReviewerFullName = driver
							.findElement(By.xpath(
									"//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[4]"))
							.getText();// documentTypeName
					System.out.println("cftReviewerFullName: " + cftReviewerFullName);
					if (cftFirstName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(By
								.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.click();
						isRecordSelectedForCFT = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();
				if (cftFirstName.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
							.click();
					isRecordSelectedForCFT = true;

				}
			}
//                 noOfRecordsChecked += perPageNoOfRecordsPresent;
//                 if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                     driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                     Thread.sleep(3000);
//                     table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                     tableBody = table.findElement(By.tagName("tbody"));
//                     perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                 }
			// }
		}
		return isRecordSelectedForCFT;
	}

	// Method To Select Investigation User In The Table
	private boolean selectingTheInvReview(String InvFullName, boolean recordSelectedForInv, int count4)
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
//         *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if (((InvFullName == null) || ("".equalsIgnoreCase(InvFullName)))) {
				InvFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((InvFullName == null) || ("".equalsIgnoreCase(InvFullName))) {
				InvFullName = driver
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
						if (InvFullName.equalsIgnoreCase(InvFirstName)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							recordSelectedForInv = true;
							break;
						}
					}

				} else {
					String InvReviewFullName = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (InvFullName.equalsIgnoreCase(InvReviewFullName)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						recordSelectedForInv = true;

					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recordSelectedForInv) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("devReviewTableContailner"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return recordSelectedForInv;
	}

	// Method To Select ActionItemPerfromUser In The Table
	private boolean selectingActionItemPerform(String ActionPerformUser, boolean recordSelectedForActionItemPerform,
			int count5) {
		WebElement table = driver.findElement(By.id("MultiSelUsersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//         int totalNoOfRecords = 0;
//         int noOfRecordsChecked = 0;
//         if (perPageNoOfRecordsPresent > 0) {
//             String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//             String[] parts = a.split(" of ");
//             try {
//                 totalNoOfRecords = Integer.parseInt(parts[1].trim());
//             } catch (Exception e) {
//                 System.out.println(e.getMessage());
//             }
//         }
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
			if (((ActionPerformUser == null) || ("".equalsIgnoreCase(ActionPerformUser)))) {
				ActionPerformUser = driver
						.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((ActionPerformUser == null) || ("".equalsIgnoreCase(ActionPerformUser))) {
				ActionPerformUser = driver
						.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType

			}
			++count5;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String cftReviewerFullName = driver
							.findElement(By.xpath(
									"//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
							.getText();// documentTypeName
					System.out.println("cftReviewerFullName: " + cftReviewerFullName);
					if (ActionPerformUser.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(By
								.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.click();
						recordSelectedForActionItemPerform = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();
				if (ActionPerformUser.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[2]"))
							.click();
					recordSelectedForActionItemPerform = true;

				}
			}
//                 noOfRecordsChecked += perPageNoOfRecordsPresent;
//                 if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                     driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                     Thread.sleep(3000);
//                     table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                     tableBody = table.findElement(By.tagName("tbody"));
//                     perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                 }
			// }
		}
		return recordSelectedForActionItemPerform;
	}

	// Method To Select ActionItemApproveUser In The Table
	private boolean selectingTheActionItemApprover(String ActionApproveFullName,
			boolean recordSelectedForActionItemApprove, int count6) {

		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//         int totalNoOfRecords = 0;
//         int noOfRecordsChecked = 0;
//         if (perPageNoOfRecordsPresent > 0) {
//             String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//             String[] parts = a.split(" of ");
//             try {
//                 totalNoOfRecords = Integer.parseInt(parts[1].trim());
//             } catch (Exception e) {
//                 System.out.println(e.getMessage());
//             }
//         }
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count6 == 0) {
			if (((ActionApproveFullName == null) || ("".equalsIgnoreCase(ActionApproveFullName)))) {
				ActionApproveFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((ActionApproveFullName == null) || ("".equalsIgnoreCase(ActionApproveFullName))) {
				ActionApproveFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();// documentType

			}
			++count6;
		}
		if (perPageNoOfRecordsPresent > 0) {
			// while (noOfRecordsChecked < totalNoOfRecords) {
			if (perPageNoOfRecordsPresent > 1) {
				for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
					String cftReviewerFullName = driver
							.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
							.getText();// documentTypeName
					System.out.println("cftReviewerFullName: " + cftReviewerFullName);
					if (ActionApproveFullName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(
								By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.click();
						recordSelectedForActionItemApprove = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
				if (ActionApproveFullName.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).click();
					recordSelectedForActionItemApprove = true;

				}
			}
//                 noOfRecordsChecked += perPageNoOfRecordsPresent;
//                 if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                     driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                     Thread.sleep(3000);
//                     table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                     tableBody = table.findElement(By.tagName("tbody"));
//                     perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                 }
			// }
		}
		return recordSelectedForActionItemApprove;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
