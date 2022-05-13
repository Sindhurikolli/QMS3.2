package com.pss.qms.DeviationAuditTrails;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class CancelledDeviations extends DeviationLoginDetails {
 
	 @Test
	    public void DeviationAuditTrails() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
				Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CancelledDeviations"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);

				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("CancelledDeviations", "PSS-QMS-001", "Pass");
				writer.setPageEvent(event);
				document.open();
				driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
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
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
	    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
	    Thread.sleep(10000);
	    sno++;
	    driver.findElement(By.id("auditTrailsInDev")).click();
	    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on AuditTrails Tab",sno,false);
	    Thread.sleep(10000);
	    sno++;
	    driver.findElement(By.xpath("//*[@id=\"devTerminateDevATMenu_Id\"]/a/span")).click();
	    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Cancelled Deviation Menu",sno,false);
	    Thread.sleep(10000);
//	     driver.findElement(By.xpath("//*[@id=\"ccReportsListMenuId\"]/ul/li[1]/a")).click();
//	    Thread.sleep(10000);
//	    driver.findElement(By.id("selLocBtnInMisCCCompleted")).click();
//	    Thread.sleep(10000);
//	    driver.findElement(By.id("treeContainer_2_switch")).click();
//	    Thread.sleep(10000); 
//	    driver.findElement(By.id("treeContainer_5_span")).click();
//	    Thread.sleep(10000);   
//	    driver.findElement(By.id("selectBtnInLocSelect")).click();
//	    Thread.sleep(10000);
//	     driver.findElement(By.id("ccNumnerInMisCCCompleted")).sendKeys(properties.getProperty("CHANGE_CONTROL_NUMBER"));
//	      Thread.sleep(3000);
//	    Select changetype = new Select(driver.findElement(By.id("ccChangeInMisCCCompleted")));
//	    changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//	    Thread.sleep(4000);
//	    driver.findElement(By.id("statusInMisCCCompleted")).sendKeys(properties.getProperty("STATUS"));
	//   Thread.sleep(3000);
	//   driver.findElement(By.id("documentChkInMisCCCompleted")).click();
//	    Thread.sleep(5000);
	    sno++;
	    driver.findElement(By.id("searchBtn_TerminatedDevsATPage")).click();
	    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
	    Thread.sleep(10000);
	    document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
		sno++;
        driver.findElement(By.className("username")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
        sno++;
        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,false);
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
//	    methodToDoChangeControlReports();
	   

	

