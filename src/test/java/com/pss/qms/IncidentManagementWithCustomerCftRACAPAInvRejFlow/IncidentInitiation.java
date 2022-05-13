package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInvRejFlow;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import com.pss.qms.login.IncidentManagementLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class IncidentInitiation extends IncidentManagementLoginDetails {
  
	@Test
	public void methodtoInitiateIncident() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "IncidentInitiation"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("IncidentInitiation", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			 module.selectByVisibleText(properties.getProperty("IncidentModule"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Incident Management Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			
        Thread.sleep(5000);
        sno++;
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='incInitiate.do']")));
        driver.findElement(By.cssSelector("a[href='incInitiate.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate",sno,false);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));
        Thread.sleep(10000);
        toinitiateIncident();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
        
        
//    }

    private void toinitiateIncident() throws Exception {
    	
    	Thread.sleep(4000);
    	sno++;
    	driver.findElement(By.id("incOccureInIncInit")).click();
    	driver.findElement(By.cssSelector("a.ui-state-default.ui-state-highlight")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Identified On",sno,false);
    	Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("descIncidentInIncInit")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description Of Incident",sno,false);
    	Thread.sleep(5000);
//        sno++;
//        Thread.sleep(5000);
//        driver.findElement(By.id("5WhysQsnCheckInIncInit")).click();
//    	Thread.sleep(5000);
//        
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select 5Why's",sno,false);
//        Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("answerForInc5WhysQsnInIncInit_1")).sendKeys(properties.getProperty("WhatIncident"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter What? Answer",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("answerForInc5WhysQsnInIncInit_2")).sendKeys(properties.getProperty("WhenIncident"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter When? Answer",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("answerForInc5WhysQsnInIncInit_3")).sendKeys(properties.getProperty("WhereIncident"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Where? Answer",sno,false);
//        sno++;
//        driver.findElement(By.id("answerForInc5WhysQsnInIncInit_4")).sendKeys(properties.getProperty("WhoatIncident"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Who? Answer",sno,false);
//        sno++;
//        driver.findElement(By.id("answerForInc5WhysQsnInIncInit_5")).sendKeys(properties.getProperty("WhyIncident"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Why? Answer",sno,false);
//    	Thread.sleep(2000);
        sno++;
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, 400)");
        driver.findElement(By.id("suspctedCauseInIncInit")).sendKeys(properties.getProperty("ReasonForIncident_2000"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reason for Incident",sno,false);
    	Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("impactAssessmentInIncInit")).sendKeys(properties.getProperty("InitialImpactAssessment_2000"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Initial Impact Assessment",sno,false);
    	Thread.sleep(2000);
        sno++;
         driver.findElement(By.id("currStatusBatchInIncInit")).sendKeys(properties.getProperty("CurrentStatus_1000"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Current Status",sno,false);
         sno++;
     	Thread.sleep(2000);
         driver.findElement(By.id("suppDocYesInIncInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes radio button",sno,false);
     	Thread.sleep(2000);
         sno++;
         driver.findElement(By.id("ccAddSuppDocs")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button",sno,false);
     	Thread.sleep(2000);
         sno++;
         driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document",sno,false);
     	Thread.sleep(2000);
         sno++;
        driver.findElement(By.id("justificationForImmadAct")).sendKeys(properties.getProperty("ImmediateActionsTaken_2000"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Immediate actions taken",sno,false);
    	Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 200)");
//        sno++;
//         driver.findElement(By.id("productChkInIncInit")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Product RadioButton",sno,false);
//     	Thread.sleep(2000);
//         sno++;
//         driver.findElement(By.id("specificationChkInIncInit")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification RadioButton",sno,false);
//     	Thread.sleep(2000);
         sno++;
         driver.findElement(By.id("facilityChkInIncInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Facility RadioButton",sno,false);
     	Thread.sleep(2000);
//         sno++;
//        driver.findElement(By.id("documentChkInIncInit")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Document RadioButton",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("instrumentChkInIncInit")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument RadioButton",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("equipmentChkInIncInit")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Equipment RadioButton",sno,false);
//    	Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("methodChkInIncInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select AnalyticalMethod RadioButton",sno,false);
    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("materialChkInIncInit")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Material RadioButton",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("auditChkInIncInit")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Audit RadioButton",sno,false);
//    	Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("othersChkInIncInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Others RadioButton",sno,false);	
        Thread.sleep(2000);
        sno++;
        Thread.sleep(1000);
       driver.findElement(By.id("continueBtnId")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false); 
          Thread.sleep(5000);
          sno++;          
        
        WebElement element = driver.findElement(By.xpath("//*[@id=\"sourcesGridForDeviationInit\"]/div/div[3]/div[2]/span/span[2]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//          Robot robot = new Robot();
//          robot.keyPress(KeyEvent.VK_CONTROL);
//          robot.keyPress(KeyEvent.VK_END);
//          robot.keyRelease(KeyEvent.VK_END);
//          robot.keyRelease(KeyEvent.VK_CONTROL);
          Thread.sleep(4000);  
        driver.findElement(By.xpath("//*[@id=\"sourcesGridForDeviationInit\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Sources Button",sno,false);
    	Thread.sleep(2000);
        sno++;
       driver.findElement(By.id("textAreaForBatchNumSrc")).sendKeys(properties.getProperty("BatchNumber_500"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch Number",sno,false);
   	Thread.sleep(2000);
       sno++;
        driver.findElement(By.id("textAreaForPrdctOrMatCodeSrc")).sendKeys(properties.getProperty("ProductMaterialCode_500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Code",sno,false);
    	Thread.sleep(2000);
        sno++;
     driver.findElement(By.id("textAreaForArNumSrc")).sendKeys(properties.getProperty("ARNumber_500"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter AR Number",sno,false);
 	Thread.sleep(2000);
     sno++;
        driver.findElement(By.id("textAreaForOthersSrc")).sendKeys(properties.getProperty("Others_500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Others",sno,false);
        sno++;     
       driver.findElement(By.id("submitBtnInSrcAddingDlg")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
       Thread.sleep(2000);
       jse.executeScript("window.scrollTo(0, 500)");
//       sno++;     
//       driver.findElement(By.xpath("//*[@id=\"productJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Product Button",sno,false);
//       sno++;
//   	Thread.sleep(2000);
//        driver.findElement(By.id("prodNameInDevProdDlg")).sendKeys(properties.getProperty("ProductName_100"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Name",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("strengthInDevProdDlg")).sendKeys(properties.getProperty("Strength_100"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Strength",sno,false);
//        sno++;
//        driver.findElement(By.id("prodCodeInDevProdDlg")).sendKeys(properties.getProperty("ProductCode_100"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Code",sno,false);
//        sno++;
//        Select producttype= new Select(driver.findElement(By.id("prodTypeInDevProdDlg")));
//        producttype.selectByVisibleText(properties.getProperty("MfgProcess"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Manufacturing Process",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.id("marketsInDevProdDlg")).sendKeys(properties.getProperty("Markets_100"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Markets",sno,false);
//        sno++;
//        driver.findElement(By.id("custsNameInDevProdDlg")).sendKeys(properties.getProperty("Customers_100"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customers",sno,false);
//        sno++;
//        driver.findElement(By.id("addDetailsInDevProdDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details",sno,false);
//        sno++;
//        driver.findElement(By.id("addBtnInDevProdDlg")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//        sno++;
//        jse.executeScript("window.scrollTo(0, 1000)");
//         driver.findElement(By.cssSelector("#documentJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Document",sno,false);
//         sno++;
//     driver.findElement(By.id("docTitleInDevDocDlg")).sendKeys(properties.getProperty("DocumentTitle_100"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Document Title",sno,false);
//     sno++;
//        driver.findElement(By.id("addDetailsInDevDocDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add Additional Details",sno,false);
//    	Thread.sleep(2000);
//        sno++;
//        js.executeScript("window.scrollBy(0, 100)");
//        Thread.sleep(1000);
//        driver.findElement(By.id("addBtnInDevDocDlg")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
////      JavascriptExecutor jse1 = (JavascriptExecutor) driver;
////        WebElement element9 = driver.findElement(By.xpath("//*[@id=\"materialJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
////        jse1.executeScript("arguments[0].scrollIntoView(true);", element9);
//    	Thread.sleep(2000);
//        sno++;
//        js.executeScript("window.scrollTo(0, 1500)");
//      driver.findElement(By.xpath("//*[@id=\"materialJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Material Button",sno,false);
//      sno++;
//     driver.findElement(By.id("materialNameInDevMaterDlg")).sendKeys(properties.getProperty("MaterialName_100"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Material Name",sno,false);
//     sno++;
//     Select materialtype= new Select(driver.findElement(By.id("materialTypeInDevMasterDlg")));
//     materialtype.selectByVisibleText(properties.getProperty("MaterialType"));
// 	Thread.sleep(2000);
//     
//     driver.findElement(By.id("materialCodeInDevMasterDlg")).sendKeys(properties.getProperty("MaterialCode_100"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Material code",sno,false);
//     sno++;
//     driver.findElement(By.id("custsNameInDevMaterDlg")).sendKeys(properties.getProperty("Customers_100"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter customers",sno,false);
//     sno++;
//     driver.findElement(By.id("marketsInDevMaterDlg")).sendKeys(properties.getProperty("Markets_100"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Markets",sno,false);
//     sno++;
//     driver.findElement(By.id("addDetailsInDevMaterDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details",sno,false);
// 	Thread.sleep(2000);
//     sno++;     
//      driver.findElement(By.id("addBtnInDevMaterDlg")).click();
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);	
//      Thread.sleep(2000);
////       JavascriptExecutor jse2 = (JavascriptExecutor) driver;
////        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"equipmentJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
////        jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//        sno++; 
//        js.executeScript("window.scrollTo(0, 1750)");
//         driver.findElement(By.xpath("//*[@id=\"equipmentJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Equipment Button",sno,false);
//     	Thread.sleep(2000);
//         sno++;
// driver.findElement(By.id("eqptNameInDevEqptDlg")).sendKeys(properties.getProperty("EquipmentName_100"));
// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Equipment Name",sno,false);
//	Thread.sleep(2000);
// sno++;
// driver.findElement(By.id("areaNameInDevEqptDlg")).sendKeys(properties.getProperty("AreaName_100"));
// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area Name",sno,false);
//	Thread.sleep(2000);
// sno++;
// driver.findElement(By.id("addDetailsInDevEqptDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details",sno,false);
// sno++;
//    driver.findElement(By.id("addBtnInDevEqptDlg")).click();
//    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//	Thread.sleep(2000);
//  JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//        WebElement element3 = driver.findElement(By.cssSelector("#facilityJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"));
//        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
    js.executeScript("window.scrollTo(0, 2000)");         
        sno++;
     driver.findElement(By.xpath("//*[@id=\"facilityJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Facility Button",sno,false);
 	Thread.sleep(2000);
     sno++;
        driver.findElement(By.id("areaNameInDevFacilityDlg")).sendKeys(properties.getProperty("AreaName_100"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area Name",sno,false);
        sno++;
        driver.findElement(By.id("addDetailsInDevFacilityDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details",sno,false);
        sno++;
         driver.findElement(By.id("addBtnInDevFacilityDlg")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
	Thread.sleep(2000);
//  JavascriptExecutor jse4 = (JavascriptExecutor) driver;
//        WebElement element4 = driver.findElement(By.cssSelector("#specJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"));
//        jse4.executeScript("arguments[0].scrollIntoView(true);", element4);
//        sno++;
//        js.executeScript("window.scrollTo(0, 2250)"); 
// driver.findElement(By.xpath("//*[@id=\"specJtableInDevInit\"]/div/div[3]/div[2]/span")).click();
// document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Specification Button",sno,false);
// sno++;
//    driver.findElement(By.id("prodNameInDevSpecDlg")).sendKeys(properties.getProperty("ProductName_100"));
//    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Name",sno,false);
//    sno++;
//       driver.findElement(By.id("strengthInDevSpecDlg")).sendKeys(properties.getProperty("Strength_100"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Strength",sno,false);
//   	Thread.sleep(2000);
//       sno++;
//       Select productmaterialtype= new Select(driver.findElement(By.id("pdctMatTypeInDevSpecDlg")));
//       productmaterialtype.selectByVisibleText(properties.getProperty("Productormaterialtype"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Product/materialtype",sno,false);
//       sno++;
//       driver.findElement(By.id("pdctMatCodeInDevSpecDlg")).sendKeys(properties.getProperty("ProductCode_100"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Code",sno,false);
//       sno++;
//       driver.findElement(By.id("custsNameInDevSpecDlg")).sendKeys(properties.getProperty("Customers_100"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer Name",sno,false);
//       sno++;
//       driver.findElement(By.id("marketsInDevSpecDlg")).sendKeys(properties.getProperty("Markets_100"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Market Name",sno,false);
//   	Thread.sleep(2000);
//       sno++;
//   driver.findElement(By.id("addBtnInDevSpecDlg")).click();
//   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//	Thread.sleep(2000);
//   sno++;
//   js.executeScript("window.scrollTo(0, 2500)"); 
//    driver.findElement(By.xpath("//*[@id=\"instrumentJTableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
//    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Instrument Button",sno,false);
//    sno++;
//  driver.findElement(By.id("instrNoInCCInstrumentDlg")).sendKeys(properties.getProperty("InstrumentNo_100"));
//  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument No",sno,false);
//  sno++;
//  driver.findElement(By.id("instrNameInCCInstrumentDlg")).sendKeys(properties.getProperty("InstrumentName_100"));
//  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument Name",sno,false);
//  sno++;
//       driver.findElement(By.id("instrLocInCCInstrumentDlg")).sendKeys(properties.getProperty("InstrumentLocation_100"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument Location",sno,false);
//       sno++;
//   driver.findElement(By.id("addBtnInCCInstrmntDlg")).click();
//   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//      JavascriptExecutor jse5 = (JavascriptExecutor) driver;
//        WebElement element5 = driver.findElement(By.xpath("//*[@id=\"analMethodJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
//        jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
	Thread.sleep(2000);
        sno++;
        js.executeScript("window.scrollTo(0, 2750)"); 
      driver.findElement(By.xpath("//*[@id=\"analMethodJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Analytical Method Button",sno,false);
      sno++;
     driver.findElement(By.id("prodNameInDevAnalMethDlg")).sendKeys(properties.getProperty("ProductName_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Name",sno,false);
     sno++;
     Select productormaterialtype= new Select(driver.findElement(By.id("prodTypeInDevAnalMethDlg")));
     productormaterialtype.selectByVisibleText(properties.getProperty("Productormaterialtype"));
     Thread.sleep(1000);
     driver.findElement(By.id("custsNameInDevAnalMethDlg")).sendKeys(properties.getProperty("Customers_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer Name",sno,false);
     sno++;
     driver.findElement(By.id("prodCodeInDevAnalMethDlg")).sendKeys(properties.getProperty("ProductCode_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Code",sno,false);
     sno++;
     driver.findElement(By.id("marketsInDevAnalMethDlg")).sendKeys(properties.getProperty("Markets_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Market Name",sno,false);
     sno++;
     driver.findElement(By.id("addBtnInDevAnalMethDlg")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
 	Thread.sleep(2000);
//     sno++;
//     js.executeScript("window.scrollTo(0, 3000)");
//       driver.findElement(By.xpath("//*[@id=\"auditJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Audit Button",sno,false);  
//       sno++;
//       driver.findElement(By.id("auditNumInDevAuditDlg")).sendKeys(properties.getProperty("AuditNumber"));
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Audit Number",sno,false);  
//       sno++;
//        driver.findElement(By.id("observationsInDevAuditDlg")).sendKeys(properties.getProperty("Observations_1500"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Observations",sno,false);  
//        sno++;
//       driver.findElement(By.id("addBtnInDevAuditDlg")).click();
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);  
//   	Thread.sleep(2000);
//     JavascriptExecutor jse6 = (JavascriptExecutor) driver;
//        WebElement element6 = driver.findElement(By.xpath("//*[@id=\"othersJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
//        jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
        sno++;
        js.executeScript("window.scrollTo(0, 3200)");
        driver.findElement(By.xpath("//*[@id=\"othersJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Others Button",sno,false);  
        sno++;
        driver.findElement(By.id("otherDetailsInDevOthersDlg")).sendKeys(properties.getProperty("Others_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Other Info",sno,false); 
        sno++;
        driver.findElement(By.id("addDetailsInDevOthersDlg")).sendKeys(properties.getProperty("AdditionalDetails_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Additional Details",sno,false); 
        sno++;
        driver.findElement(By.id("addBtnInDevOthersDlg")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
//      JavascriptExecutor jse7 = (JavascriptExecutor) driver;
//        WebElement element7 = driver.findElement(By.id("submitBtnInDevInitWin"));
//        jse7.executeScript("arguments[0].scrollIntoView(true);", element7);
//      String IncidentString="";
      sno++;
      js.executeScript("window.scrollTo(0, 3500)");
        driver.findElement(By.id("submitBtnInDevInitWin")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
    	Thread.sleep(2000);
       WebDriverWait wait = new WebDriverWait(driver, 60);
       sno++;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInIncWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
    	Thread.sleep(2000);
        sno++;
        Thread.sleep(3000);
      driver.findElement(By.id("subBtnInValESignInInc")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false); 
         Thread.sleep(10000);
//        IncidentString = driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-buttons > a")).getText();
//        Thread.sleep(5000);
//    String separateIncidentNumber[] = IncidentString.split(" ");
//    Thread.sleep(5000);
//        System.out.println("separateIncidentNumber: "+separateIncidentNumber[4]);
//        String IncidentNo = separateIncidentNumber[4]; 
//        Thread.sleep(3000);
//      IncidentNo = IncidentNo.replace(")", ""); 
//        finalIncidentNumber = IncidentNo.trim();
//        System.out.println("finalIncidentNumber: "+finalIncidentNumber);
//        Thread.sleep(9000);
        sno++;
        if(driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).isDisplayed()) {
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
        }
        String Incident=driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center")).getText();
        String[] parts = Incident.split(" : ");
        System.out.println(parts[1]);
        String IncidentNo = parts[1].replace(")","");
        System.out.println(IncidentNo);
        PropertiesConfiguration properties = new PropertiesConfiguration("src/test/java/QMSUIProperties/IncidentManagement.properties");
        properties.setProperty("IncidentNo", IncidentNo);
        properties.save();
        sno++;
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
   

Thread.sleep(3000);
driver.findElement(By.className("username")).click();
document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
sno++;
Thread.sleep(1000);
driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
    }
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
    } 

