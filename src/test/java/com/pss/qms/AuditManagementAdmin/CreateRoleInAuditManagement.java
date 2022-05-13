package com.pss.qms.AuditManagementAdmin;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class CreateRoleInAuditManagement extends AMLoginDetails {
    
	 @Test
	    public void methodtoCreateRole() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateRoleInAuditManagement"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("CreateRoleInAuditManagement", "PSS-QMS-001",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
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
	          driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"adminInAM\"]/div/a/div/div/div/h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin Tab",sno,false);  
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Role Menu",sno,false);  
	        todoCreateRoleInAuditManagement();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }

	    private void todoCreateRoleInAuditManagement() throws Exception {
	        sno++;
	        driver.findElement(By.id("roleNameInCreateRoleInAM")).sendKeys(properties.getProperty("ROLE_NAME_IN_AM"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Role Name",sno,false); 
	        sno++;
	        driver.findElement(By.id("descriptionInCreateRoleInAM")).sendKeys(properties.getProperty("ROLE_DESC_IN_AM"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Role Name",sno,false); 
	        sno++;
	        driver.findElement(By.id("subBtnIdInCreateAMRole")).click();
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


