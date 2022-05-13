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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class AuditPlanExternalSchedule extends AMLoginDetails {
    
	@Test
    public void AuditPlan() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AuditPlanExternalSchedule"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("Audit Plan", "PSS-QMS-003",
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
//	    sno++;
//         driver.findElement(By.id("myActivitiesInAM")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
         sno++;  
         driver.findElement(By.xpath("//*[@id=\"amMyActivitiesListMenuId\"]/div[1]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Plan Menu",sno,false);
        methodToDoAuditPlan();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    

    private void methodToDoAuditPlan() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
         String AMId = properties.getProperty("EXTERNAL_NAME_EXTERNAL_SCHEDULE");
        isRecordSelected = selectRecdAuditPlan(AMId, isRecordSelected, count);
        if (isRecordSelected) {
        	 sno++;  
        	 driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on next button",sno,false);    
             sno++;
            ((JavascriptExecutor)driver).executeScript ("document.getElementById('startDateInAssignAudit').removeAttribute('readonly',0);"); 
          WebElement identifiedDate= driver.findElement(By.id("startDateInAssignAudit")); 
          identifiedDate.clear(); 
          Date date = new Date();
          String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
           driver.findElement(By.id("startDateInAssignAudit")).sendKeys(todaysDate);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Start Date",sno,false);
            sno++;
            ((JavascriptExecutor)driver).executeScript ("document.getElementById('endDateInAssignAudit').removeAttribute('readonly',0);"); 
            WebElement identifiedDate1= driver.findElement(By.id("endDateInAssignAudit")); 
            identifiedDate1.clear(); 
            Date date1 = new Date();
            String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
            driver.findElement(By.id("endDateInAssignAudit")).sendKeys(todaysDate1);
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On End Date",sno,false);
              Thread.sleep(2000);
              driver.findElement(By.id("startDateInAssignAudit")).click();  
              sno++;
              driver.findElement(By.id("externalAuditorName")).sendKeys(properties.getProperty("AUDITOR_NAME"));
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Auditor Name",sno,false);    
              sno++; 
              driver.findElement(By.id("externalCustomer")).sendKeys(properties.getProperty("CUSTOMER_NAME"));
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer Name",sno,false);    
              sno++; 
              driver.findElement(By.id("regulatoryAuthority")).sendKeys(properties.getProperty("REG_AUTHORITY_NAME"));
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reg Name",sno,false);    
              sno++; 
              driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reg Name",sno,false);    
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
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    	  private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
    	        WebElement table = driver.findElement(By.id("regAuditeeUsersTableContainer"));
    	        WebElement tableBody = table.findElement(By.tagName("tbody"));
    	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    	        int totalNoOfRecords = 0;
    	        int noOfRecordsChecked = 0;
    	        if (perPageNoOfRecordsPresent > 0) {
    	            String a = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
    	            String[] parts = a.split(" of ");
    	            try {
    	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
    	            } catch (Exception e) {
    	                System.out.println(e.getMessage());
    	            }
    	        }
    	        if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
    	            if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
    	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
    	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
    	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

    	            }
    	            ++count1;
    	        }
    	        if (perPageNoOfRecordsPresent > 0) {
    	            while (noOfRecordsChecked < totalNoOfRecords) {
    	                if (totalNoOfRecords > 1) {
    	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
    	                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
    	                        if (UserFullName.equalsIgnoreCase(DevNumberSequence)) {
    	                            driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
    	                            isRecordSelectedForUser = true;
    	                            break;
    	                        }
    	                    }
    	                    if (isRecordSelectedForUser) {
    	                        break;
    	                    }
    	                } else {
    	                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
    	                    if (UserFullName.equalsIgnoreCase(DevNumberSequence)) {
    	                        driver.findElement(By.xpath("//*[@id=\"regAuditeeUsersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
    	                        isRecordSelectedForUser = true;
    	                        break;
    	                    }
    	                }
    	                noOfRecordsChecked += perPageNoOfRecordsPresent;
    	                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
    	                    driver.findElement(By.cssSelector("#regAuditeeUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
    	                    Thread.sleep(3000);
    	                    table = driver.findElement(By.id("regAuditeeUsersTableContainer"));//Document Tree approve table
    	                    tableBody = table.findElement(By.tagName("tbody"));
    	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    	                }
    	            }
    	        }
    	        return isRecordSelectedForUser;
    	    }
	

	private boolean selectRecdAuditPlan(String AMId, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("auditsListTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((AMId == null) || ("".equalsIgnoreCase(AMId)))) {
            	AMId = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AMId == null) || ("".equalsIgnoreCase(AMId))) {
            	AMId = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[  " + i + " ]/td[6]")).getText();//documentTypeName
                        if (AMId.equalsIgnoreCase(AMNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[6]")).getText();
                    if (AMId.equalsIgnoreCase(AMNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"auditsListTableContainer\"]/div/table/tbody/tr/td[6]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#auditsListTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(5000);
                    table = driver.findElement(By.id("auditsListTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
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
