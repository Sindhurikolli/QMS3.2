package com.pss.qms.RiskAssessmentRejection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class RiskIdentification extends RALoginDetails {

	@Test
	public void RiskIdentification() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RiskIdentification"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("RiskIdentification", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("QMS_Module_Selection"));
		Thread.sleep(2000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(3000);
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
//			sno++;
		Thread.sleep(4000);
//			driver.findElement(By.xpath("//*[@id=\"riskAsmt_tile_Id\"]/div/div/div/h2")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskAssessment Module", sno,
//					false);
//	         sno++;
//	         driver.findElement(By.id("myActivitiesInAM")).click();
//	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false); 
		Thread.sleep(4000);
		sno++;
		WebDriverWait wait1 = new WebDriverWait(driver, 240);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='raCreateRiskAsmtPage.do']")));
		driver.findElement(By.cssSelector("a[href='raCreateRiskAsmtPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Risk Identification Menu", sno,
				false);
		todoRiskIdentification();
		Thread.sleep(3000);
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void todoRiskIdentification() throws Exception {

		sno++;
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("raShortDescriptionInRaInit")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Short Description", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("productChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Product checbox", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("documentChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Document Checkbox", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("materialChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Material checkbox", sno, false);
		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("instrumentChkInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Instrument checkbox",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("equipmentChkInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Equipment checkbox",sno,false);
//		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("facilityChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Facility checkkbox", sno, false);
		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("specificationChkInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Specification checkbox",sno,false);
//		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("methodChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Analytical method checkbox", sno,
				false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("othersChkInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Others checkbox", sno, false);

		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addProductsInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Products", sno, false);
		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("productNameInAddProd")).sendKeys(properties.getProperty("PRO_NAME"));
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the product name", sno, false);
//		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"productDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Product details", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInProductAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addDocumentsInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Documents", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("locTreeForAddDocuments_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the documents", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInDocumentAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button", sno, false);
		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addEquipmentInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Equipment",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("locTreeForAddEquipments_2_span")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.xpath("//*[@id=\"equipmentWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Equipment",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addBtnInEqptAdd")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addInstrumentsInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Instrument",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("locTreeForAddInstruments_2_span")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.xpath("//*[@id=\"instrumentWidowDetialsTableContainer\"]/div/table/tbody/tr[1]/td[3]")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Equipment",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addButtonInInstmtAdd")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addSpecificationsInRaInit")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Specification",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("specificationNameInAddSpec")).sendKeys(properties.getProperty("SPEC_NAME"));
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the specification name",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.xpath("//*[@id=\"specificationDetialsTableContainer\"]/div/table/tbody/tr[2]/td[2]")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the specification details",sno,false);
//		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("addBtnInSpecAdd")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button",sno,false);
//		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addMaterialsInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Materials", sno, false);
		Thread.sleep(4000);
//		sno++;
//		driver.findElement(By.id("materialNameInAddMaterial")).sendKeys(properties.getProperty("MATERIAL_NAME"));
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Material name", sno, false);
//		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"materialDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the Material details", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInMaterialAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"facilityJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On  Add facility", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("areaNameInRAFacilityDlg")).sendKeys(properties.getProperty("AREA_NAME"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Area name", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addDetailsInRAFacilityDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Additional details", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInRAFacilityDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"analMethodJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add analytical method", sno,
				false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("prodNameInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Product name", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("strengthInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Strengths", sno, false);
		Thread.sleep(4000);
		sno++;
		Select MaterialType = new Select(driver.findElement(By.id("prodTypeInRAAnalMethDlg")));
		MaterialType.selectByIndex(4);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("prodCodeInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Product COde", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("marketsInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Markets name", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("custsNameInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_100"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Customers", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addDetailsInRAAnalMethDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Additional details", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInRAAnalMethDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"othersJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add others Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("othersDetailsInRAOthersDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Customers", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addDetailsInRAOthersDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Additional details", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInRAOthersDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"propRisksJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("identifyRiskInPropRiskDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Identify Risk", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("failureCauseInPropRiskDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Probable Cause of Failure", sno,
				false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("riskControlsInPropRiskDlg")).sendKeys(properties.getProperty("RA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Existing Risk Controls", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("refDocNameInPropRiskDlg")).sendKeys(properties.getProperty("REFERENCE_DOC"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Document", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("addBtnInPropRisksAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("suppDocYesInRaInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Supporting Documents Yes", sno,
				false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("raAddSuppDocs")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"assmntTeamJtableInRAInit\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Users", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("locTreeInQmsWfReg_2_span")).click();
		Thread.sleep(2000);
//		driver.findElement(By.id("locTreeInQmsWfReg_3_a")).click();
//		Thread.sleep(12000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
		boolean isRecordSelectedForUser = false;
		String UserFirstName = properties.getProperty("E_CODE");
		String UserFullName = UserFirstName;
		int count1 = 0;
		isRecordSelectedForUser = selectingTheUserReview(UserFullName, isRecordSelectedForUser, count1);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("selectBrowseButton")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(4000);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
		sno++;
		Thread.sleep(4000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 70);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(4000);
		String Risk = driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center")).getText();
		String[] parts = Risk.split(" : ");
		System.out.println(parts[1]);
		String RiskNo = parts[1].substring(0, 12);
		System.out.println(RiskNo);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src/test/java/QMSUIProperties/RAproperties.properties");
		properties.setProperty("RA_NUMBER", RiskNo);
		properties.save();
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
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
				UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
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
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
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
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]"))
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
