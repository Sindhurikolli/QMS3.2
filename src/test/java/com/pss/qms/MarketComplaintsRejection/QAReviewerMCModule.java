package com.pss.qms.MarketComplaintsRejection;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

public class QAReviewerMCModule extends MCLoginDetails {

	@Test
	public void qaReviewerMCModule() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerMCModule"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerMCModule", "PSS-QMS-002", "Pass");
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
		Thread.sleep(1000);
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
		Thread.sleep(3000);
		sno++;
		Thread.sleep(4000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review menu", sno, false);
		driver.findElement(By.cssSelector("a[href='mcMyActReviewPage.do']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.invisibilityOf(
				driver.findElement(By.cssSelector("#mcReviewTableContailner > div > div.jtable-busy-message"))));
		methodToDoQAReviewer();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoQAReviewer() throws Exception {

		int count = 0;
		Thread.sleep(2000);
		boolean isRecordSelected = false;
		Date date = new Date();
		String sdf = new SimpleDateFormat("YY").format(date);
		String MarketComplaintsId = properties.getProperty("MC_ID");
		isRecordSelected = selectRecdQAReview(MarketComplaintsId, isRecordSelected, count);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("customerNotificationInMcQaReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on customer notification checkbox", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("invChildReqInMcReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on investigation checkboox", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("reqDetInMcReview"))
					.sendKeys(properties.getProperty("Compliant_Short_Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Enter the investigation request details", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("selectInvestigationOwner")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("locTreeInQmsProdReg_5_ico")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Location", sno, false);
			Thread.sleep(3000);
			sno++;
			boolean isRecordSelectedForInv = false;
			String InvFirstName = properties.getProperty("Invest_User_E-Code");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Invetigation user", sno,
					false);
			int count4 = 0;
			isRecordSelectedForInv = selectingTheInvReview(InvFirstName, isRecordSelectedForInv, count4);
			Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
//            Thread.sleep(3000);
			driver.findElement(By.id("usersSelBtnInMcReview")).click();
			Thread.sleep(3000);
			sno++;
//            Thread.sleep(5000);
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("addSupportingDocCheckInMcQAReview"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.id("addSupportingDocCheckInMcQAReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Supporting documents checkbox",
					sno, false);
			sno++;
			Thread.sleep(4000);
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("mcAddSuppDocsInQAReview"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
//            Thread.sleep(5000);
			driver.findElement(By.id("mcAddSuppDocsInQAReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("uploadSuppDocQAReview_1")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add the supporting document", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInMcQaReview")).sendKeys(properties.getProperty("MC_994"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno, false);
			Thread.sleep(3000);
//            driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.id("approveMcQaPrimReview"));
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(1000);
			driver.findElement(By.id("approveMcQaPrimReview")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature passwod", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
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
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected For CFT Review");
			Assert.assertTrue(false);
		}

	}

	private boolean selectRecdQAReview(String MarketComplaintsId, boolean isRecordSelected, int count)
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
						if (MarketComplaintsId.equalsIgnoreCase(capaNumberSequence)) {
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
					if (MarketComplaintsId.equalsIgnoreCase(capaNumberSequence)) {
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
	private boolean selectingTheCFTReview(String cftFullName, boolean recordSelected, int count) {
		WebElement table = driver.findElement(By.id("cftTeamTableWindowContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
		// *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if (((cftFullName == null) || ("".equalsIgnoreCase(cftFullName)))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((cftFullName == null) || ("".equalsIgnoreCase(cftFullName))) {
				cftFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();// documentType

			}
			++count;
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
					if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
						driver.findElement(By
								.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[4]"))
								.click();
						recordSelected = true;
						break;
					}
				}

			} else {
				String cftReviewerFullName = driver
						.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
						.getText();
				if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
					driver.findElement(By.xpath("//*[@id=\"cftTeamTableWindowContainer\"]/div/table/tbody/tr/td[4]"))
							.click();
					recordSelected = true;

				}
			}
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
			// }
		}
		return recordSelected;
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
//        *[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
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
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
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
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
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
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
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
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
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
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
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
