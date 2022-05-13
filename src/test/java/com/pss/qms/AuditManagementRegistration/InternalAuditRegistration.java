package com.pss.qms.AuditManagementRegistration;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.Alert;
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

public class InternalAuditRegistration extends AMLoginDetails {

    @Test
    public void InternalAuditReg() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);	
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InternalAuditRegistration"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("InternalAuditRegistration", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
    	driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Admin"));
        driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
        Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("AMModule"));
		Thread.sleep(1000);
        input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(4000);
        im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
		document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("auditRegMenuInRegAM")));

        driver.findElement(By.id("auditRegMenuInRegAM")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Register Auditor",sno,false);
        sno++;
        Thread.sleep(4000);
        methodToDoInternalAuditRegistration();
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
    private void methodToDoInternalAuditRegistration() throws Exception  {
    	 String AuditorEcodes = properties.getProperty("InternalAuditorEcode");
         String [] AuditorEcode = AuditorEcodes.split(",");
        	sno++;
        	 for(int i=0;i<AuditorEcode.length;i++)
             {
        		 if(driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).isDisplayed())
        		 {
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;      
            Thread.sleep(5000);
        		 }
        		 Thread.sleep(3000);
            driver.findElement(By.id("selectBtnInAuditRegInAM")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(5000);
            driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("searchEmpCode")).sendKeys(AuditorEcode[i]);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            Thread.sleep(1000);
            driver.findElement(By.id("usersSearchBtnInRepProb")).click();
            Thread.sleep(5000);
            boolean isRecordSelectedForUser = false;
            String Auditor = AuditorEcode[i];
          int count1=0;
          isRecordSelectedForUser=selectingTheUserReview(Auditor,isRecordSelectedForUser,count1);
          sno++;
          driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false); 
            Thread.sleep(5000);
           sno++;
             driver.findElement(By.id("crtfcUploadInAmAuditRegPage")).sendKeys(properties.getProperty("Document-1"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Document",sno,false);
             sno++;
             Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent-p-1\"]/div[6]/div[2]/div[2]/button")).click();
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
         sno++;
         Thread.sleep(5000);
            driver.findElement(By.id("plantOnlyLocTreeContainer_2_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);   
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("selectBtnInLocSelect")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);   
            sno++; 
            driver.findElement(By.xpath("//*[@id=\"experienceDetailsGridContainer\"]/div/div[3]/div[2]/span/span[2]")).click();
            Thread.sleep(3000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Experiance Button",sno,false);   
            sno++; 
            driver.findElement(By.id("companyNameInExpDtlsGrid")).sendKeys("PSS");
            driver.findElement(By.id("noOfYearsInExpDtlsGrid")).sendKeys("5");
            driver.findElement(By.id("areaOfWorkInExpDtlsGrid")).sendKeys("Pharma");
            driver.findElement(By.id("addBtnInExpDtlsGrid")).click();
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,250)");
            driver.findElement(By.id("subjectMatterExpertIn")).sendKeys("Pharma Industry expert");
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
            }
             
        	 driver.findElement(By.className("username")).click();
 			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName", sno, false);
 			Thread.sleep(2000);
 			sno++;
 			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
 			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);
       
    }

 
    	
    	
    	
    	
    	
	

	private boolean selectingTheUserReview(String Auditor, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
    	 WebElement table = driver.findElement(By.id("usersTableContainer"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
	            if ((totalNoOfRecords > 1) && ((Auditor == null) || ("".equalsIgnoreCase(Auditor)))) {
	            	Auditor = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((Auditor == null) || ("".equalsIgnoreCase(Auditor))) {
	            	Auditor = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count1;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
	                        if (Auditor.equalsIgnoreCase(UserNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelectedForUser) {
	                        break;
	                    }
	                } else {
	                    String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
	                    if (Auditor.equalsIgnoreCase(UserNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
	                        isRecordSelectedForUser = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
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