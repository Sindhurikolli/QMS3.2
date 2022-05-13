package com.pss.qms.InvestigationWithCFTCAPA;

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
import com.pss.qms.login.InvestigationLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class InvestigationRequest extends InvestigationLoginDetails {

	@Test
	public void RequestInvestigation() throws Exception {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvestigationRequest"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvestigationRequest", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("InvRequester"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("InvModule"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='investigationRequest.do']")));
		driver.findElement(By.cssSelector("a[href='investigationRequest.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Request menu", sno,
				false);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("initiateInvRequests_InvReqForm")));
		methodToDoInvestigationRequest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToDoInvestigationRequest() throws Exception {

		sno++;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
		Thread.sleep(2000);
		driver.findElement(By.id("initiateDepart")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(properties.getProperty("RequestDepartment"))).click();
//       driver.findElement(By.id("treeContainer_3_span")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("selectBtnInLocSelect")).click();
		Thread.sleep(4000);
		driver.findElement(
				By.cssSelector("#TotalContent-p-1 > div:nth-child(2) > div:nth-child(1) > div.col-sm-1>button"))
				.click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		driver.findElement(By.id("locationTreeForSingleUserSelect_2_span")).click();
		Thread.sleep(5000);
//       driver.findElement(By.id("locationTreeForSingleUserSelect_3_span")).click();
		Thread.sleep(2000);
		boolean isRecordSelectedForInvOwner = false;
		String InvLastName = properties.getProperty("Ecodeinvestigationowner");
		int count4 = 0;
		isRecordSelectedForInvOwner = selectingTheInvReview(InvLastName, isRecordSelectedForInvOwner, count4);
		sno++;
		Thread.sleep(5000);

//      JavascriptExecutor jse7 = (JavascriptExecutor) driver;
//      WebElement element7 = driver.findElement(By.id("selectBrowse1Button"));
//      jse7.executeScript("arguments[0].scrollIntoView(true);", element7);
//      
//      Thread.sleep(5000);
		if (isRecordSelectedForInvOwner) {
			driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
		} else {
			System.out.println("Investigation Owner Not Selected");
		}
		Thread.sleep(2000);
		Select ApproveFrom = new Select(driver.findElement(By.id("approveFromInInvRequestForm")));
		ApproveFrom.selectByVisibleText(properties.getProperty("ApproverSelection"));
		Thread.sleep(2000);
		Select Category = new Select(driver.findElement(By.id("categoryInInvRequestForm")));
		Category.selectByIndex(2);
		Thread.sleep(2000);
//		driver.findElement(By.id("documentChkInInvRequestForm")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//*[@id=\"documentJTableInInv\"]/div/div[3]/div[2]/span/span[2]")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("docTitleInInvReqFormDlg")).sendKeys(properties.getProperty("Document_Title"));
//		Thread.sleep(2000);
//		driver.findElement(By.id("addDetailsDocInInvReqFormDlg"))
//				.sendKeys(properties.getProperty("ADDITIONAL_DETAILS"));
//		Thread.sleep(2000);
//		driver.findElement(By.id("addBtnInInvReqDocFormDlg")).click();
//		Thread.sleep(2000);
		driver.findElement(By.id("facilityChkInInvRequestForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Faciity Checkbox", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"facilityJtableInInv\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("areaNameInInvReqFormFacilityDlg")).sendKeys(properties.getProperty("AreaName"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area Name", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("addDetailsInInvReqFormFacilityDlg")).sendKeys(properties.getProperty("ADDITIONAL_DETAILS"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("addBtnInInvReqFormFacilityDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button", sno, false);
		sno++;
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement elem = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		js.executeScript("arguments[0].scrollIntoView(true);", elem);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit Button", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(2000);
		String Investigation = driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center")).getText();
		String[] parts = Investigation.split(" : ");
		System.out.println(parts[1]);
		String InvestigationNo = parts[1].substring(0, 11);
		System.out.println(InvestigationNo);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src/test/java/QMSUIProperties/Investigation.properties");
		properties.setProperty("INV_NO", InvestigationNo);
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

	private boolean selectingTheInvReview(String InvLastName, boolean isRecordSelectedForInvOwner, int count4)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("singleSelUsersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if ((totalNoOfRecords > 1) && ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName)))) {
				InvLastName = driver
						.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName))) {
				InvLastName = driver
						.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String IncNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (InvLastName.equalsIgnoreCase(IncNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForInvOwner = true;
							break;
						}
					}
					if (isRecordSelectedForInvOwner) {
						break;
					}
				} else {
					String IncNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (InvLastName.equalsIgnoreCase(IncNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
						isRecordSelectedForInvOwner = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForInvOwner) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#singleSelUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(5000);
					table = driver.findElement(By.id("singleSelUsersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForInvOwner;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
