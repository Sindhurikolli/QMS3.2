package com.pss.qms.AuditManagementAdmin;

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

public class RoleBasePageAccessInAuditManagement extends AMLoginDetails {
    
	 @Test
	    public void methodtodoRoleBaseAccess() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleBasePageAccessInAuditManagement"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleBasePageAccessInAuditManagement", "PSS-QMS-001",
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
	         driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[1]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RoleBaseAccess Menu",sno,false);  
	         sno++;
	         Select Role = new Select(driver.findElement(By.id("rolesInAmRoleBaseAccessPage")));
	         Role.selectByIndex(9);
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Role",sno,false);  
	        todoRoleBaseInAuditManagement();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }

	    private void todoRoleBaseInAuditManagement() throws Exception {
	        sno++;
	        driver.findElement(By.id("schdAuditMainMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditSchedule Preparation",sno,false); 
	        sno++;
	        driver.findElement(By.id("assingAuditMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditPlan RadioButton",sno,false); 
	        sno++;
	        driver.findElement(By.id("modifyAuditPlanMenuInAm")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On ModifyAuditPlan RadioButton",sno,false);
	        sno++;
	        driver.findElement(By.id("reAssingAuditMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On ReAssignInternalAuditor RadioButton",sno,false);
	        sno++;
	        driver.findElement(By.id("auditStatusMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditStatus RadioButton",sno,false);
	        sno++;
	        driver.findElement(By.id("externalAuditMainMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On ExternalAudit RadioButton",sno,false);
	        sno++;
	        driver.findElement(By.id("terminateMainMenuInAM")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Terminate RadioButton",sno,false);
	        JavascriptExecutor jse = (JavascriptExecutor) driver;
	        WebElement element = driver.findElement(By.id("amAddendumRequestPageMenu"));
	        jse.executeScript("arguments[0].scrollIntoView(true);", element);
	        sno++;
	        driver.findElement(By.id("amAddendumRequestPageMenu")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AddendumRequest RadioButton",sno,false);
	        sno++;
	        driver.findElement(By.id("amAddendumRejectPageMenu")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AddendumRevert RadioButton",sno,false);
	        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
	        WebElement element1 = driver.findElement(By.id("seletAllForAMAuditTrails"));
	        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
	        sno++;
	        driver.findElement(By.id("seletAllForAMAuditTrails")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditTrails RadioButton",sno,false);
	        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
	        WebElement element2 = driver.findElement(By.id("selectAllForAmReports"));
	        jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
	        sno++;
	        driver.findElement(By.id("selectAllForAmReports")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Reports RadioButton",sno,false);
	        JavascriptExecutor jse3 = (JavascriptExecutor) driver;
	        WebElement element3 = driver.findElement(By.id("submitButtonInAmRoleBaseAccess"));
	        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
	        sno++;
	        driver.findElement(By.id("submitButtonInAmRoleBaseAccess")).click();
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
	        driver.findElement(By.className("username")).click();
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


