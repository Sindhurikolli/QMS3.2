package com.pss.qms.AuditManagementAuditTrails;

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

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class CheckListAuditManagement extends AMLoginDetails {

    @Test
    public void methodtodoCheckListAudit() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			document = new Document();
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CheckListAuditManagement"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("CheckListAudit Management", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
    	driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
        driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
        input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
        Thread.sleep(3000);
        im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
		document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
        driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
        sno++;
        driver.findElement(By.id("auditTrailsInAM")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on AuditTrails Tab",sno,false);
        todochecklistaudit();
        Thread.sleep(3000);
        document.close();
      		writer.close();
      		Desktop desktop = Desktop.getDesktop();
      		File file = new File(output);
      		//desktop.open(file);
//      	} catch (Exception e) {
//      		e.printStackTrace();
//      	}
          }
    

    private void todochecklistaudit() throws Exception {
    	sno++;
//    	 driver.findElement(By.id("startDateInAmChkListAuditFormSrch")).sendKeys(properties.getProperty("START_DATE"));
//    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Start Date",sno,false);
//    	 sno++;
//    	   Date date = new Date();
//           String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
//           driver.findElement(By.id("endDateInAmChkListAuditFormSrch")).sendKeys(todaysDate);
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter End Date",sno,false);
//    	
////        driver.findElement(By.id("chkListNameInAmChkListAuditFormSrch")).sendKeys(properties.getProperty("CHECK_LIST_NAME"));
////        Thread.sleep(8000);
//    	
//    	sno++;
        driver.findElement(By.id("searchBtnInAmChkListAuditFormSrch")).click();
        Thread.sleep(3000);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
        sno++;
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
        sno++;
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
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
