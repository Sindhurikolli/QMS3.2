package com.pss.qms.RiskAssessmentModule;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
 
import com.pss.qms.login.RALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class RiskIdentificationWithQuantitativeApproach extends RALoginDetails {
 
	@Test
	public void methodtodoRiskIdentification() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RiskIdentificationWithQuantitativeApproach"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RiskIdentificationWithQuantitativeApproach", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			driver.findElement(By.xpath("//*[@id=\"riskAsmt_tile_Id\"]/div/div/div/h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskAssessment Module",sno,false);
//	         sno++;
//	         driver.findElement(By.id("myActivitiesInAM")).click();
//	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno);  
			sno++;
			driver.findElement(By.xpath("//*[@id=\"riskAssmntAndReEvalGroupMenuId\"]/div[1]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskIdentification Menu",sno,false);
			todoRiskIdentification();
			Thread.sleep(3000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private void todoRiskIdentification() throws Exception {
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);
		sno++;
		driver.findElement(By.id("raShortDescriptionInRaInit"))
				.sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Short Description",sno,false);
		sno++;
		driver.findElement(By.id("documentChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Document RadioButton",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
		sno++;
		driver.findElement(By.id("addDocumentsInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Documents",sno,false);
		sno++;
		driver.findElement(By.id("locTreeForAddDocuments_2_switch")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("locTreeForAddDocuments_3_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Document",sno,false);
		sno++;
		driver.findElement(By.id("addBtnInDocumentAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"propRisksJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
		sno++;
		driver.findElement(By.id("identifyRiskInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Identify Risk",sno,false);
		sno++;
		driver.findElement(By.id("failureCauseInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Probable Cause of Failure",sno,false);
		sno++;
		driver.findElement(By.id("riskControlsInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Existing Risk Controls",sno,false);
		sno++;
		driver.findElement(By.id("refDocNameInPropRiskDlg")).sendKeys(properties.getProperty("Reference_Document"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Document",sno,false);
		sno++;
		driver.findElement(By.id("addBtnInPropRisksAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
		sno++;
		driver.findElement(By.id("suppDocYesInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Supporting Documents Yes",sno,false);
		sno++;
		driver.findElement(By.id("raAddSuppDocs")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add",sno,false);
		sno++;
		driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
		sno++;
		driver.findElement(By.id("quantAnalTypeChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On QuantitativeApproach Button",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"assmntTeamJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Users",sno,false);
		sno++;
		driver.findElement(By.id("locTreeInQmsWfReg_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("locTreeInQmsWfReg_3_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
		boolean isRecordSelectedForUser = false;
		String UserFirstName = properties.getProperty("First_Name");
		String UserFullName = UserFirstName;
		int count1 = 0;
		isRecordSelectedForUser = selectingTheUserReview(UserFullName, isRecordSelectedForUser, count1);
		sno++;
		driver.findElement(By.id("selectBrowseButton")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
		sno++;
		Thread.sleep(4000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
		sno++;
		Thread.sleep(5000);
		driver.findElement(By.className("modal-btn")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Ok Button",sno,false);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);

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
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
						if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
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
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
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
	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
