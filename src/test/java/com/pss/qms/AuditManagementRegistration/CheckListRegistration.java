package com.pss.qms.AuditManagementRegistration;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class CheckListRegistration extends AMLoginDetails {

    @Test
    public void CheckListReg() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);	
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CheckListRegistration"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("CheckListRegistration", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
    	driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Admin"));
    	Thread.sleep(1000);
        driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
        Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("AMModule"));
		Thread.sleep(1000);
        input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(4000);
        Thread.sleep(3000);
        im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
		document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("checkListAuditMenuInRegAM")));
        driver.findElement(By.id("checkListAuditMenuInRegAM")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Register Check List",sno,false);
        Thread.sleep(5000);
        methodToDoCheckListRegistration();
        Thread.sleep(1000);
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    private void methodToDoCheckListRegistration() throws Exception  {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)");
        	sno++;
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
       
         sno++;
            driver.findElement(By.id("chkListNameInAMChkListReg")).sendKeys(properties.getProperty("CHECK_LIST_NAME"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CheckList Name",sno,false);
             sno++;
             driver.findElement(By.id("referenceInAMChkListReg")).sendKeys(properties.getProperty("CHECK_LIST_REF"));
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CheckList Reference",sno,false);
                
            driver.findElement(By.id("selectBtnInAMChkListReg")).click();
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
         sno++;
         Thread.sleep(5000);
            driver.findElement(By.linkText(properties.getProperty("CheckList_Location"))).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);   
            sno++;
            driver.findElement(By.id("selectBtnInLocSelect")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false); 
          sno++;
          int n = Integer.parseInt(properties.getProperty("NoofCheckPoints"));
          for(int i=1;i<=n;i++)
          {
          driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInAMChkListReg\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add CheckPoints Button",sno,false); 
        sno++;
        driver.findElement(By.id("checkPointInAddChkPoint")).sendKeys("CheckPoint-"+i);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The CheckPoints",sno,false); 
         sno++; 
         driver.findElement(By.id("addBtnInAddChkPoint")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
            sno++; 
          }
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
          
            sno++;    
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);   
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
            sno++;
            driver.findElement(By.className("modal-btn")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[5]/a/span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[5]/ul/li[4]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);
       
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
    	
    	
    	
    	
	
