package com.pss.qms.DeviationWithInvAndCAPA;

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
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class InitiateSaveAsDraftDeviation extends DeviationLoginDetails  {
    
   @Test
    public void methodtoinitiateDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InitiateSaveAsDraftDeviation"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("InitiateSaveAsDraftDeviation", "PSS-QMS-001", "Pass");
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
			driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("deviationInDev")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
			toinitiateDeviation();
			document.close();
			writer.close();
      		Desktop desktop = Desktop.getDesktop();
      		File file = new File(output);
      		//desktop.open(file);
//      	} catch (Exception e) {
//      		e.printStackTrace();
//      	}
    }

    private void toinitiateDeviation() throws Exception {
    	sno++;
    	Thread.sleep(2000);
        ((JavascriptExecutor)driver).executeScript ("document.getElementById('devOccureInDevInit').removeAttribute('readonly',0);"); 
        WebElement identifiedDate= driver.findElement(By.id("devOccureInDevInit")); 
        identifiedDate.clear(); 
        Date date = new Date();
        String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        driver.findElement(By.id("devOccureInDevInit")).sendKeys(todaysDate);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Todays Date",sno,false);
        Thread.sleep(3000);
        driver.findElement(By.id("iniOrgintr")).click();
        sno++;
        driver.findElement(By.id("descDeviationInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Deviation Description",sno,false);
        sno++;
        Thread.sleep(2000);
        driver.findElement(By.id("suspctedCauseInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Suspected Cause",sno,false);
        sno++;
        Thread.sleep(2000);
        driver.findElement(By.id("impactAssessmentInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Impact Assessment",sno,false);
        sno++;
        Thread.sleep(2000);
        driver.findElement(By.id("currStatusBatchInDevInit")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Status Details",sno,false);
        sno++;
        Thread.sleep(2000);
        driver.findElement(By.id("justificationForImmadAct")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Justification",sno,false);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("documentChkInDevInit"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        sno++;
        driver.findElement(By.id("documentChkInDevInit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Source",sno,false);
        sno++;
        driver.findElement(By.id("continueBtnId")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"sourcesGridForDeviationInit\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Source Button",sno,false);
        sno++;
        driver.findElement(By.id("textAreaForBatchNumSrc")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch Number",sno,false);
        sno++;
        driver.findElement(By.id("textAreaForPrdctOrMatCodeSrc")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Material Code",sno,false);
        sno++;
        driver.findElement(By.id("textAreaForArNumSrc")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter ARNumber",sno,false);
        sno++;
        driver.findElement(By.id("textAreaForOthersSrc")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Others",sno,false);
        sno++;
        driver.findElement(By.id("submitBtnInSrcAddingDlg")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.id("saveAsDraftBtnInDevInitWin"));
        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
        sno++;
        driver.findElement(By.id("saveAsDraftBtnInDevInitWin")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On SaveAsDraft Button",sno,false);
        sno++;
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
        sno++;
        driver.findElement(By.id("subBtnInValESignInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
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




