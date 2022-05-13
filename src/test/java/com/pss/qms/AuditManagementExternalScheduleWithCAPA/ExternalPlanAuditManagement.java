package com.pss.qms.AuditManagementExternalScheduleWithCAPA;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
public class ExternalPlanAuditManagement extends AMLoginDetails {

	 @Test
	    public void methodtodoExternalScheduleAM() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ExternalPlanAuditManagement"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("ExternalPlanAuditManagement", "PSS-QMS-001",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName10"));
	      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	     driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
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
	         driver.findElement(By.id("myActivitiesInAM")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"amSchedulePreparationMenus\"]/div[3]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on External Menu",sno,false);  
	        todoexternalScheduleAM();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }

	    private void todoexternalScheduleAM() throws Exception {
	    	sno++;
	        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);  
	        sno++;
	        driver.findElement(By.id("planNameInAmAdhocAuditForm")).sendKeys(properties.getProperty("EXTERNAL_NAME_EXTERNAL_SCHEDULE"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter External Name",sno,false); 
	        sno++;
	        driver.findElement(By.id("suppDocInPlannedAuditPage")).sendKeys(properties.getProperty("Document-1"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Browse Button",sno,false); 
	        sno++;
	        driver.findElement(By.id("suppDocInPlannedAuditPage")).sendKeys(properties.getProperty("Document-1"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Browse Button",sno,false); 
	        sno++;
	        driver.findElement(By.xpath("//*[@id=\"locationsListInAmPlannedSchedule\"]/div/div[3]/div[2]/span")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
	        driver.findElement(By.id("treeContainer_2_switch")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("treeContainer_3_span")).click();
	        sno++;
	        driver.findElement(By.id("selectBtnInLocSelect")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
	        sno++;
	        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	        WebDriverWait wait = new WebDriverWait(driver, 60);
	        sno++;
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
	        sno++;
	        driver.findElement(By.id("subBtnInValidateESign")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	        sno++;
	        driver.findElement(By.className("modal-btn")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Ok Button",sno,false);
	        sno++;
	        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[9]/a/span/b")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
	        sno++;
	        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
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


