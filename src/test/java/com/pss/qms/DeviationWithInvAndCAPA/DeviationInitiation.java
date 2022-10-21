package com.pss.qms.DeviationWithInvAndCAPA;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
 
import com.pss.qms.login.DeviationLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

public class DeviationInitiation extends DeviationLoginDetails {
  
	@Test
	public void toDeviationInitiation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeviationInitiation"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeviationInitiation", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(1);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);		
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Deviation Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			
        Thread.sleep(3000);
        sno++;
        WebDriverWait wait = new WebDriverWait(driver, 60);
//        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
//        Thread.sleep(6000);
//        sno++;
        driver.findElement(By.cssSelector("a[href='devInitiate.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Initiate",sno,false);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));
//        Thread.sleep(15000);
        toinitiateDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        
        
    }

    private void toinitiateDeviation() throws Exception {
    	
    	Thread.sleep(1000);
        sno++;
//        driver.findElement(By.id("initiateDepart")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Department Select button", sno,false);
//        Thread.sleep(2000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(2000);

//        List<WebElement>departmentlist=driver.findElements(By.className("node_name"));
//        for (int i=0; i<departmentlist.size();i++)
//        {
//           String registerdepartment=properties.getProperty("registerdepartment");
//         if ((departmentlist.get(i).getText()).equalsIgnoreCase(registerdepartment))
//           {
//               departmentlist.get(i).click();
//               break;
//           }
//        }
//        sno++;
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button", sno,false);
//        Thread.sleep(2000);
//    	sno++;
((JavascriptExecutor)driver).executeScript ("document.getElementById('devOccureInDevInit').removeAttribute('readonly',0);"); 
WebElement identifiedDate= driver.findElement(By.id("devOccureInDevInit")); 
identifiedDate.clear(); 
Date date = new Date();
String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
driver.findElement(By.id("devOccureInDevInit")).sendKeys(todaysDate);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Identified On",sno,false);
        driver.findElement(By.id("iniOrgintr")).click();
        sno++;
        driver.findElement(By.id("shortDescDeviationInDevInit")).sendKeys(properties.getProperty("DeviationShortDescription_300"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Deviation Short Description",sno,false);
        sno++;
        driver.findElement(By.id("descDeviationInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description Of Deviation",sno,false);
        sno++;
        driver.findElement(By.id("suspctedCauseInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reason for Deviation",sno,false);
        sno++;
        driver.findElement(By.id("impactAssessmentInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Initial Impact Assessment",sno,false);
        sno++;
         driver.findElement(By.id("currStatusBatchInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Current Status",sno,false);
         sno++;
         JavascriptExecutor js = ((JavascriptExecutor) driver);
         js.executeScript("window.scrollTo(0, 600)");
         Thread.sleep(3000);
         driver.findElement(By.cssSelector("label[for='suppDocYesInDevInit']")).click();
//         driver.findElement(By.xpath("//*[@id=\"content\"]/div[15]/div[1]/div[2]/label")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes radio button",sno,false);
         sno++;
         driver.findElement(By.id("ccAddSuppDocs")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button",sno,false);
         sno++;
         driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document",sno,false);
         sno++;
        driver.findElement(By.id("justificationForImmadAct")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Immediate actions taken",sno,false);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.id("productChkInDevInit"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element1);
        sno++;
         driver.findElement(By.cssSelector("#productChkInDevInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Product RadioButton",sno,false);
         sno++;
        driver.findElement(By.id("documentChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Document RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("materialChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Material RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("instrumentChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("equipmentChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Equipment RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("facilityChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Facility RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("specificationChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("methodChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select AnalyticalMethod RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("auditChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Audit RadioButton",sno,false);
        sno++;
        driver.findElement(By.id("othersChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Others RadioButton",sno,false);
        sno++;
        JavascriptExecutor jse8 = (JavascriptExecutor) driver;
        WebElement element8 = driver.findElement(By.id("continueBtnId"));
        jse8.executeScript("arguments[0].scrollIntoView(true);", element8);
        Thread.sleep(1000);
        
        
          driver.findElement(By.id("continueBtnId")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false); 
          Thread.sleep(2000);
          sno++;    
        driver.findElement(By.xpath("//*[@id=\"sourcesGridForDeviationInit\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Sources Button",sno,false);
        sno++;
       driver.findElement(By.id("textAreaForBatchNumSrc")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch Number",sno,false);
       sno++;
        driver.findElement(By.id("textAreaForPrdctOrMatCodeSrc")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Code",sno,false);
        sno++;
     driver.findElement(By.id("textAreaForArNumSrc")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter AR Number",sno,false);
     sno++;
        driver.findElement(By.id("textAreaForOthersSrc")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Others",sno,false);
        sno++;     
       driver.findElement(By.id("submitBtnInSrcAddingDlg")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
       sno++;     
       driver.findElement(By.id("addProductsInDevInit")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Product Button",sno,false);
       sno++;
       Helper.waitLoadRecords(driver, By.cssSelector("#productDetialsTableContainer > div > div.jtable-busy-message[style='display: none;']"));
        driver.findElement(By.xpath("//*[@id=\"productDetialsTableContainer\"]/div/table/tbody/tr[1]/td[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Product",sno,false);
        sno++;       
        driver.findElement(By.id("addBtnInProductAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
         driver.findElement(By.id("addDocumentsInDevInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Document",sno,false);
         sno++;
         driver.findElement(By.id("locTreeForAddDocuments_2_switch")).click();
         Thread.sleep(3000);
         driver.findElement(By.id("locTreeForAddDocuments_3_span")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Location in Document Tree",sno,false);
     sno++;
     Helper.waitLoadRecords(driver, By.cssSelector("#documentsWidowDetialsTableContainer > div > div.jtable-busy-message[style='display: none;']"));
        driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Document",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInDocumentAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
       Helper.scrollElement(driver, By.id("addEquipmentInDevInit"));        
         driver.findElement(By.id("addEquipmentInDevInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Equipment Button",sno,false);
         sno++;
         driver.findElement(By.id("locTreeForAddEquipments_2_switch")).click();
         Thread.sleep(2000);
         driver.findElement(By.id("locTreeForAddEquipments_3_span")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Department from the Tree",sno,false);
         sno++;
         Helper.waitLoadRecords(driver, By.cssSelector("#equipmentWidowDetialsTableContainer > div > div.jtable-busy-message[style='display: none;']"));
         driver.findElement(By.xpath("//*[@id=\"equipmentWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Equipment Name",sno,false);
         sno++;
         driver.findElement(By.id("addBtnInEqptAdd")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
         sno++;
         Helper.scrollElement(driver, By.id("addInstrumentsInDevInit"));
         driver.findElement(By.id("addInstrumentsInDevInit")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Instrument Button",sno,false);
         sno++;
         driver.findElement(By.id("locTreeForAddInstruments_2_switch")).click();
         driver.findElement(By.id("locTreeForAddInstruments_3_span")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Department from the tree ",sno,false);
       sno++;
       driver.findElement(By.xpath("//*[@id=\"instrumentWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument",sno,false);
       sno++;
        driver.findElement(By.id("addButtonInInstmtAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
        Helper.scrollElement(driver, By.id("addSpecificationsInDevInit"));
        driver.findElement(By.id("addSpecificationsInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Specification Button",sno,false);
        sno++;
        Helper.waitLoadRecords(driver, By.cssSelector("#specificationDetialsTableContainer > div > div.jtable-busy-message[style='display: none;']"));
        driver.findElement(By.xpath("//*[@id=\"specificationDetialsTableContainer\"]/div/table/tbody/tr[1]/td[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInSpecAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
        Helper.scrollElement(driver, By.id("addMaterialsInDevInit"));         
        driver.findElement(By.id("addMaterialsInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Material Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"materialDetialsTableContainer\"]/div/table/tbody/tr[1]/td[2]")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Material",sno,false);
     sno++;
      driver.findElement(By.id("addBtnInMaterialAdd")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
      
  JavascriptExecutor jse3 = (JavascriptExecutor) driver;
        WebElement element3 = driver.findElement(By.cssSelector("#facilityJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"));
        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);        
        sno++;
     driver.findElement(By.xpath("//*[@id=\"facilityJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Facility Button",sno,false);
     sno++;
        driver.findElement(By.id("areaNameInDevFacilityDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area Name",sno,false);
        sno++;
   driver.findElement(By.id("addBtnInDevFacilityDlg")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        JavascriptExecutor jse5 = (JavascriptExecutor) driver;
        WebElement element5 = driver.findElement(By.xpath("//*[@id=\"analMethodJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
        jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
        sno++;
      driver.findElement(By.xpath("//*[@id=\"analMethodJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Analytical Method Button",sno,false);
      sno++;
     driver.findElement(By.id("prodNameInDevAnalMethDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Name",sno,false);
     sno++;
     driver.findElement(By.id("addBtnInDevAnalMethDlg")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
     sno++;
       driver.findElement(By.xpath("//*[@id=\"auditJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Audit Button",sno,false);  
       sno++;
       driver.findElement(By.id("auditNumInDevAuditDlg")).sendKeys(properties.getProperty("AUD_NO_CC_INIT"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Audit Number",sno,false);  
       sno++;
        driver.findElement(By.id("observationsInDevAuditDlg")).sendKeys(properties.getProperty("Additional_Details_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Observations",sno,false);  
        sno++;
       driver.findElement(By.id("addBtnInDevAuditDlg")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);  
     JavascriptExecutor jse6 = (JavascriptExecutor) driver;
        WebElement element6 = driver.findElement(By.xpath("//*[@id=\"othersJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
        jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"othersJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Others Button",sno,false);  
        sno++;
        driver.findElement(By.id("otherDetailsInDevOthersDlg")).sendKeys(properties.getProperty("Additional_Details_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Other Info",sno,false); 
        sno++;
        driver.findElement(By.id("addBtnInDevOthersDlg")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
      JavascriptExecutor jse7 = (JavascriptExecutor) driver;
        WebElement element7 = driver.findElement(By.id("submitBtnInDevInitWin"));
        jse7.executeScript("arguments[0].scrollIntoView(true);", element7);
      String deviationString="";
      sno++;
        driver.findElement(By.id("submitBtnInDevInitWin")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
       WebDriverWait wait = new WebDriverWait(driver, 60);
       sno++;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInDevWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
        sno++;
        Thread.sleep(3000);
      driver.findElement(By.id("subBtnInValESignInDev")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false); 
         Thread.sleep(10000);
//        deviationString = driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-buttons > a")).getText();
//        Thread.sleep(5000);
//    String separateDeviationNumber[] = deviationString.split(" ");
//    Thread.sleep(5000);
//        System.out.println("separateDeviationNumber: "+separateDeviationNumber[4]);
//        String deviationNo = separateDeviationNumber[4]; 
//        Thread.sleep(3000);
//      deviationNo = deviationNo.replace(")", ""); 
//        finalDeviationNumber = deviationNo.trim();
//        System.out.println("finalDeviationNumber: "+finalDeviationNumber);
//        Thread.sleep(9000);
        sno++;
		  if(driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).isDisplayed()) {
         	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
         }
        sno++;
        String Deviation=driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center")).getText();
        String[] parts = Deviation.split(" : ");
        System.out.println(parts[1]);
        int OrgCodeDigits = Integer.parseInt(properties.getProperty("OrgCodeDigits"));
        int NumberDigits = Integer.parseInt(properties.getProperty("NumberDigits"));
        int autonumberlength = OrgCodeDigits+NumberDigits;
        String DeviationNo = parts[1].substring(0, autonumberlength);
        System.out.println(DeviationNo);
        PropertiesConfiguration properties = new PropertiesConfiguration("src/test/java/QMSUIProperties/Deviation.properties");
        properties.setProperty("DEVIATION_NUMBER", DeviationNo);
        properties.save();
   driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false); 
sno++;
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
    	if(ITestResult.FAILURE==result.getStatus())
    	{
    		Utility.captureScreenshot(driver, result.getName());
    	}
    	
    }
    } 

