package com.pss.qms.MarketComplaintsWithCFTAndWithAIApprovalFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.MCLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class MCRegister extends MCLoginDetails {

	@Test
	public void MCRegister() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MCRegister" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("MCRegister", "PSS-QMS-01", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(3000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QAReview"));
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
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.cssSelector("a[href='mcMyActRegPage.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Register menu", sno, false);
		Thread.sleep(2000);
//    WebDriverWait wait = new WebDriverWait(driver, 240);
//    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#ccClosureQaReviewDetailsTable > div > div.jtable-busy-message"))));
		toRegisterdocument();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toRegisterdocument() throws Exception {

		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("initiateDepartInMcReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Department Select button", sno,
				false);
		Thread.sleep(2000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(2000);
//       under
		List<WebElement> departmentlist = driver.findElements(By.className("node_name"));
		for (int i = 0; i < departmentlist.size(); i++) {
			String registerdepartment = properties.getProperty("registerdepartment");
			if ((departmentlist.get(i).getText()).equalsIgnoreCase(registerdepartment)) {
				departmentlist.get(i).click();
				break;
			}
		}

		sno++;
		driver.findElement(By.id("selectBtnInLocSelect")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("complaintReceiveDateInMcReg")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Complaint received date", sno,false);
		Thread.sleep(2000);
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('complaintReceiveDateInMcReg').removeAttribute('readonly',0);");
		WebElement identifiedDate = driver.findElement(By.id("complaintReceiveDateInMcReg"));
		identifiedDate.clear();
		identifiedDate.sendKeys("");
		Date date = new Date();
		String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		Thread.sleep(3000);
		driver.findElement(By.id("complaintReceiveDateInMcReg")).sendKeys(todaysDate);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Complaint received date", sno,
				false);
		Thread.sleep(3000);

//          Date date = new Date();
//        String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
//        Thread.sleep(3000);
//        driver.findElement(By.id("complaintReceiveDateInMcReg")).sendKeys(todaysDate);
//        Thread.sleep(3000);
//        sno++;
//        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[6]/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the complaint receive date", sno,false);
//        Thread.sleep(2000);
		sno++;
		Select ComplaintOn = new Select(driver.findElement(By.id("mcTabOrPfiInCompReg")));
		ComplaintOn.selectByVisibleText(properties.getProperty("Complaint_On"));
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Complaint Reported On", sno,
				false);
		sno++;
		driver.findElement(By.id("custmerReferenceNoInMcReg")).sendKeys(properties.getProperty("Customer_Ref_Number"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the customer reference number", sno,
				false);
		Thread.sleep(5000);
		sno++;
		Select Category = new Select(driver.findElement(By.id("severityInMcRegistration")));
		Category.selectByVisibleText(properties.getProperty("Category"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the category from drop down list",
				sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("mcShortDescriptionInMcReg"))
				.sendKeys(properties.getProperty("Compliant_Short_Description"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the complaint Short description", sno,
				false);
		Thread.sleep(5000);
		sno++;
		Select TypeOfComplaint = new Select(driver.findElement(By.id("typeOfComplaintInMcReg")));
		TypeOfComplaint.selectByVisibleText(properties.getProperty("Type_Of_Complaint"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Select the Type of complaint from drop down list", sno, false);
		Thread.sleep(3000);
		sno++;
		Select SourceofComplaint = new Select(driver.findElement(By.id("subCategoryInMcReg")));
		SourceofComplaint.selectByVisibleText(properties.getProperty("SubCategory_of_Complaint"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Select the sub category from drop down list", sno, false);
		Thread.sleep(3000);
		sno++;
		Select RouteofComplaintRecorded = new Select(driver.findElement(By.id("complaintRecSelInMcReg")));
		RouteofComplaintRecorded.selectByVisibleText(properties.getProperty("Route_of_Complaint_Recorded"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Select the route of complaint from drop down list", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("natureOfComplaintInMcReg"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.id("natureOfComplaintInMcReg")).sendKeys(properties.getProperty("MC_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the nature of complaint", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("additionalInfoRequestedInMcReg")).sendKeys(properties.getProperty("MC_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the additinal information requested",
				sno, false);
		Thread.sleep(2000);
		sno++;
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath(".//*[@id='batchDetailsTabInMcRegister']/div/div[3]/div[2]/span/span[2]"));
//         jse.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("//*[@id=\"batchDetailsTabInMcRegister\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("batchNumberInMcRegTable")).sendKeys(properties.getProperty("Batch_Number"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the batch number", sno, false);
		Thread.sleep(2000);
		sno++;
		Date date1 = new Date();
		String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
		Thread.sleep(3000);
		driver.findElement(By.id("manufacturedateInMcRegTable")).sendKeys(todaysDate1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Manufacturing date", sno, false);
		Thread.sleep(3000);
		sno++;
		SimpleDateFormat formattedDate = new SimpleDateFormat("d/M/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 90); // number of days to add
		String futureDate = (String) (formattedDate.format(c.getTime()));
		driver.findElement(By.id("expiryDateInMcRegTable")).sendKeys(futureDate);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the Expiry/retest date", sno, false);
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("batchNumberInMcRegTable")).click();
		sno++;
		Thread.sleep(2000);
		driver.findElement(By.id("addBtnInReasonsAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on add button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("nameInMcCustomerDlg")).sendKeys(properties.getProperty("Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("addrsInMcCustomerDlg")).sendKeys(properties.getProperty("MC_500"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the address", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("phoneNoInMcCustomerDlg")).sendKeys(properties.getProperty("Phone_No"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the phone number", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("emailInMcCustomerDlg")).sendKeys(properties.getProperty("EMail_Id"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-mail-id", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("faxInMcCustomerDlg")).sendKeys(properties.getProperty("Fax_no"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the fax number", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("traderNameInMcCustomerDlg")).sendKeys(properties.getProperty("Trader_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("traderAddrsInMcCustomerDlg")).sendKeys(properties.getProperty("TraderAddress_500"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the address", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("traderPhoneNoInMcCustomerDlg")).sendKeys(properties.getProperty("Trader_PhoneNo"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the phone number", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("traderEmailInMcCustomerDlg")).sendKeys(properties.getProperty("Trader_EMailId"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-mail-id", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("traderFaxInMcCustomerDlg")).sendKeys(properties.getProperty("Trader_FaxNo"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the fax number", sno, false);
		Thread.sleep(2000);
		driver.findElement(By.id("suppDocYesInMcReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on radio button Yes", sno, false);
		Thread.sleep(2000);
		JavascriptExecutor scl = ((JavascriptExecutor) driver);
		scl.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#supportingDocsJtableInMcReg > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add new record", sno, false);
		Thread.sleep(2000);
		driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add the supporting document", sno, false);
		Thread.sleep(2000);
		JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
		scl1.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("continueIdInMcReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Continue button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("addProductsInMcInit")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Product", sno, false);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"productDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the product", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("addBtnInProductAdd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("submitBtnInMcInitWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
				false);
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
		Thread.sleep(7000);
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

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
