package com.pss.qms.DeviationWithRAWithCAPA;

import java.awt.Desktop;
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
import com.pss.qms.util.Utilities;

public class DeviationInitiation extends DeviationLoginDetails {

	@Test
    public void methodtoinitiatedocument() throws InterruptedException, IOException, DocumentException, Exception {
		try {

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
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(3000);
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
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("deviationInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
        Thread.sleep(15000);
        toinitiateDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	} catch (Exception e) {
		e.printStackTrace();
	}
        
        
    }

    private void toinitiateDeviation() throws Exception {
    	sno++;
((JavascriptExecutor)driver).executeScript ("document.getElementById('devOccureInDevInit').removeAttribute('readonly',0);"); 
WebElement identifiedDate= driver.findElement(By.id("devOccureInDevInit")); 
identifiedDate.clear(); 
Date date = new Date();
String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
driver.findElement(By.id("devOccureInDevInit")).sendKeys(todaysDate);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Identified On",sno,false);
        driver.findElement(By.id("iniOrgintr")).click();
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
         driver.findElement(By.id("currStatusBatchInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation_1000"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Current Status",sno,false);
         sno++;
        driver.findElement(By.id("justificationForImmadAct")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Immediate actions taken",sno,false);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("productChkInDevInit"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
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
       driver.findElement(By.xpath("//*[@id=\"productJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Product Button",sno,false);
       sno++;
        driver.findElement(By.id("prodNameInDevProdDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Name",sno,false);
        sno++;
        Select producttype= new Select(driver.findElement(By.id("prodTypeInDevProdDlg")));
        producttype.selectByIndex(1);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Manufacturing Process",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInDevProdDlg")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
         driver.findElement(By.cssSelector("#documentJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Document",sno,false);
         sno++;
     driver.findElement(By.id("docTitleInDevDocDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Document Title",sno,false);
     sno++;
        driver.findElement(By.id("addDetailsInDevDocDlg")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Add Additional Details",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInDevDocDlg")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
      JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"materialJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
        sno++;
      driver.findElement(By.xpath("//*[@id=\"materialJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Material Button",sno,false);
      sno++;
     driver.findElement(By.id("materialNameInDevMaterDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Material Name",sno,false);
     sno++;
      driver.findElement(By.id("addBtnInDevMaterDlg")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
       JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"equipmentJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]"));
        jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
        sno++; 
         driver.findElement(By.xpath("//*[@id=\"equipmentJtableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Equipment Button",sno,false);
         sno++;
 driver.findElement(By.id("eqptNameInDevEqptDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Equipment Name",sno,false);
 sno++;
    driver.findElement(By.id("addBtnInDevEqptDlg")).click();
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
  JavascriptExecutor jse4 = (JavascriptExecutor) driver;
        WebElement element4 = driver.findElement(By.cssSelector("#specJtableInDevInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text"));
        jse4.executeScript("arguments[0].scrollIntoView(true);", element4);
        sno++;
 driver.findElement(By.xpath("//*[@id=\"specJtableInDevInit\"]/div/div[3]/div[2]/span")).click();
 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Specification Button",sno,false);
 sno++;
    driver.findElement(By.id("prodNameInDevSpecDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product/Material Name",sno,false);
    sno++;
       driver.findElement(By.id("strengthInDevSpecDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Strength",sno,false);
       sno++;
   driver.findElement(By.id("addBtnInDevSpecDlg")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
   sno++;
    driver.findElement(By.xpath("//*[@id=\"instrumentJTableInDevInit\"]/div/div[3]/div[2]/span/span[2]")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Instrument Button",sno,false);
    sno++;
  driver.findElement(By.id("instrNoInCCInstrumentDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument No",sno,false);
  sno++;
  driver.findElement(By.id("instrNameInCCInstrumentDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument Name",sno,false);
  sno++;
       driver.findElement(By.id("instrLocInCCInstrumentDlg")).sendKeys(properties.getProperty("Description_Of_Deviation_100"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument Location",sno,false);
       sno++;
   driver.findElement(By.id("addBtnInCCInstrmntDlg")).click();
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
        driver.findElement(By.id("observationsInDevAuditDlg")).sendKeys(properties.getProperty("Description_Of_Deviation"));
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
        driver.findElement(By.id("otherDetailsInDevOthersDlg")).sendKeys(properties.getProperty("Description_Of_Deviation"));
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
        deviationString = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).getText();
        Thread.sleep(5000);
    String separateDeviationNumber[] = deviationString.split(" ");
    Thread.sleep(5000);
        System.out.println("separateDeviationNumber: "+separateDeviationNumber[4]);
        String deviationNo = separateDeviationNumber[4]; 
        Thread.sleep(3000);
      deviationNo = deviationNo.replace(")", ""); 
//        finalDeviationNumber = deviationNo.trim();
//        System.out.println("finalDeviationNumber: "+finalDeviationNumber);
        Thread.sleep(9000);
        sno++;
   driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false); 
sno++;
Thread.sleep(3000);
driver.findElement(By.className("username")).click();
document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
sno++;
Thread.sleep(3000);
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

