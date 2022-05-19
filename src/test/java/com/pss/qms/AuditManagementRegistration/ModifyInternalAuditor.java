package com.pss.qms.AuditManagementRegistration;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class ModifyInternalAuditor extends AMLoginDetails {

    @Test
    public void ModifyInternalAuditReg() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);	
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ModifyInternalAuditor"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("ModifyInternalAuditor", "PSS-QMS-001",
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
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on AuditManagement Module",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"registrationInAM\"]/div/a/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Registration Tab",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on InternalAudit Registration Menu",sno,false);
        sno++;
        driver.findElement(By.id("rejectedAuditRegistrationsInAM")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Modify InternalAuditor Menu",sno,false);
        methodToDoModifyInternalAuditor();
        Thread.sleep(3000);
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    private void methodToDoModifyInternalAuditor() throws Exception  {
       
    	 boolean isRecordSelectedForUser = false;
    	 int count=0;
         String UserFirstName = properties.getProperty("MODIFY_INT_AUD_NAME");
       String UserFullName = UserFirstName;
       isRecordSelectedForUser=selectingTheUser(UserFullName,isRecordSelectedForUser,count);
         if(isRecordSelectedForUser) {
        	 sno++;
             driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
             sno++;    
             driver.findElement(By.id("crtfcUploadInAmAuditRegPage")).sendKeys(properties.getProperty("Document-1"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Document",sno,false);   
        	   sno++; 
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
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username",sno,false);
            sno++;
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
       
    }

 
    	
    	
    	
    }
    	
	

	private boolean selectingTheUser(String UserFullName, boolean isRecordSelectedForUser, int count) throws InterruptedException {
		  WebElement table = driver.findElement(By.id("rejectedAuditeeRegTablesContainer"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	        int totalNoOfRecords = 0;
//	        int noOfRecordsChecked = 0;
//	        if (perPageNoOfRecordsPresent > 0) {
//	            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//	            String[] parts = a.split(" of ");
//	            try {
//	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
//	        }
	        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if (((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            //while (noOfRecordsChecked < totalNoOfRecords) {
	                if (perPageNoOfRecordsPresent > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String ReviewerFullName = driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
	                        System.out.println("ReviewerFullName: "+ReviewerFullName);
	                        if (UserFullName.equalsIgnoreCase(ReviewerFullName)) {
	                            driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                   
	                } else {
	                    String ReviewerFullName = driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr/td[3]")).getText();
	                    if (UserFullName.equalsIgnoreCase(ReviewerFullName)) {
	                        driver.findElement(By.xpath("//*[@id=\"rejectedAuditeeRegTablesContainer\"]/div/table/tbody/tr/td[3]")).click();
	                        isRecordSelectedForUser = true;
	                        
	                    }
	                }
//	                noOfRecordsChecked += perPageNoOfRecordsPresent;
//	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//	                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//	                    Thread.sleep(3000);
//	                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//	                    tableBody = table.findElement(By.tagName("tbody"));
//	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	                }
	            //}
	        }
	        return isRecordSelectedForUser;
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