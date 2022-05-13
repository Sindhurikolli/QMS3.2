/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.AMAdhocScheduleNonCompliance;

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
public class NonComplianceAdhocAM extends AMLoginDetails {
    
    @Test
    public void NonComplianceAdhoc() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);	
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "NonComplianceAdhocAM"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("NonCompliance Audit", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
    	driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName_AM"));
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
//        sno++;
//        driver.findElement(By.id("myActivitiesInAM")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"internalAuditPerformMenus\"]/div[1]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Enter Audit Observations Menu",sno,false);
        methodToDoNonComplianceAudit();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    private void methodToDoNonComplianceAudit() throws Exception  {
        int count = 0;
        boolean isRecordSelected = false;

        String AMId = properties.getProperty("ADHOC_NAME_ADHOC_SCHEDULE");

        isRecordSelected = selectRecdInternalAuditObs(AMId, isRecordSelected, count);
        if (isRecordSelected) {
        	sno++;
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            ((JavascriptExecutor)driver).executeScript ("document.getElementById('inpectionDateInStartAuditForm').removeAttribute('readonly',0);"); 
            WebElement identifiedDate= driver.findElement(By.id("inpectionDateInStartAuditForm")); 
            identifiedDate.clear(); 
            Date date = new Date();
            String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            driver.findElement(By.id("inpectionDateInStartAuditForm")).sendKeys(todaysDate);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Inspection Start Date",sno,false);
            sno++;
            ((JavascriptExecutor)driver).executeScript ("document.getElementById('inpectionEndDateInStartAuditForm').removeAttribute('readonly',0);"); 
            WebElement identifiedDate1= driver.findElement(By.id("inpectionEndDateInStartAuditForm")); 
            identifiedDate1.clear(); 
            Date date1 = new Date();
            String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
            driver.findElement(By.id("inpectionEndDateInStartAuditForm")).sendKeys(todaysDate1);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Inspection End Date",sno,false);
            sno++;
            driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[1]/td[11]/button/i")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Edit Button",sno,false);
            sno++;  
     
             Select ComplianceStatus = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
            ComplianceStatus.selectByIndex(2);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Compliance Status",sno,false);
            sno++;  
            Select Category = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
            Category.selectByIndex(1);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Category",sno,false);
            sno++; 
            Select Classification = new Select(driver.findElement(By.id("classificationOfObsrInStartAudit")));
            Classification.selectByIndex(1);
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Classification",sno,false);
           sno++;
            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            sno++;
            driver.findElement(By.id("addBtnInPerformChkList")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
             sno++;
   driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[2]/td[11]/button")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Edit Button 2",sno,false);
   sno++;
            Select ComplianceStatus1 = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
            ComplianceStatus1.selectByIndex(2);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Compliance Status",sno,false);  
            sno++;  
            Select Category2 = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
            Category2.selectByIndex(1);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Categry",sno,false); 
            sno++; 
            Select Classification1 = new Select(driver.findElement(By.id("classificationOfObsrInStartAudit")));
            Classification1.selectByIndex(1);
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Classification",sno,false);
           sno++; 
            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false); 
            sno++; 
            driver.findElement(By.id("addBtnInPerformChkList")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button "+"  PSS-QMS-SS-010",sno,false);   
//            driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[3]/td[9]/button")).click();
//            Thread.sleep(3000);
//            Select ComplianceStatus3 = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
//            ComplianceStatus3.selectByIndex(2);
//            Thread.sleep(3000);
//            Select Category1 = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
//            Category1.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("addBtnInPerformChkList")).click();
//            Thread.sleep(3000);
            sno++; 
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            driver.findElement(By.id("evalAreasInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Evaluated Area",sno,false);
            sno++;
            driver.findElement(By.id("evalDocsInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Evaluated Docs",sno,false);
            sno++;
            driver.findElement(By.id("persMetDuringAuditInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments In Personnel Met During Audit",sno,false);
            sno++;
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            driver.findElement(By.id("infoFromPrevAuditInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter In Previous Audit",sno,false);
            sno++;
            Select ComplianceStatus4 = new Select(driver.findElement(By.id("auditRatingInAmStartAuditForm")));
            ComplianceStatus4.selectByIndex(1);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Audit Rating",sno,false);
            sno++;
            driver.findElement(By.id("refDocInAmStartAuditForm")).sendKeys(properties.getProperty("Document-1"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Ref Doc",sno,false);
            sno++;
            driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditees\"]/div/div[3]/div[2]/span/span[2]")).click();
            Thread.sleep(3000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Auditee Button",sno,false);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            boolean isRecordSelectedForUser = false;
            String UserFirstName = properties.getProperty("UserName_Aud");
          String UserFullName = UserFirstName;
          int count1=0;
          isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
          driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);  
          sno++;
            driver.findElement(By.id("commentsInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);  
            sno++; 
            driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditObservationsReview\"]/div/div[3]/div[2]/span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Reviewer Button",sno,false);  
            sno++; 
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);  
            boolean isRecordSelectedForUser1 = false;
            String UserFirstName1 = properties.getProperty("AUDIT_REVIEWER");
          String UserFullName1 = UserFirstName1;
          int count2=0;
          isRecordSelectedForUser=selectingTheAuditReview(UserFullName1,isRecordSelectedForUser1,count2);
          sno++; 
          driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);  
          sno++; 
           driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditObservationsApproval\"]/div/div[3]/div[2]/span/span[2]")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Approver Button",sno,false);
           sno++; 
            driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);  
            boolean isRecordSelectedForUser2 = false;
            String UserFirstName2 = properties.getProperty("AUDIT_APPROVER");
          String UserFullName2 = UserFirstName2;
          int count3=0;
          isRecordSelectedForUser=selectingTheApproverReview(UserFullName2,isRecordSelectedForUser2,count3);
          sno++; 
          driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();   
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          sno++; 
          driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);   
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
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
            sno++;
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectingTheApproverReview(String UserFullName2, boolean isRecordSelectedForUser2, int count3) throws InterruptedException {
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
	        if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
	            if ((totalNoOfRecords > 1) && ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2)))) {
	            	UserFullName2 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2))) {
	            	UserFullName2 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count3;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        if (UserFullName2.equalsIgnoreCase(AMNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser2 = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelectedForUser2) {
	                        break;
	                    }
	                } else {
	                    String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                    if (UserFullName2.equalsIgnoreCase(AMNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
	                        isRecordSelectedForUser2 = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForUser2) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
	        }
	        return isRecordSelectedForUser2;
	    }
   private boolean selectingTheAuditReview(String UserFullName1, boolean isRecordSelectedForUser1, int count2) throws InterruptedException {
		
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
	        if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
	            if ((totalNoOfRecords > 1) && ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1)))) {
	            	UserFullName1 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1))) {
	            	UserFullName1 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count2;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        if (UserFullName1.equalsIgnoreCase(AMNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser1 = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelectedForUser1) {
	                        break;
	                    }
	                } else {
	                    String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                    if (UserFullName1.equalsIgnoreCase(AMNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
	                        isRecordSelectedForUser1 = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForUser1) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
	        }
	        return isRecordSelectedForUser1;
	    }
    	
    	
    	
    	
    	
    	
	

	private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
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
	            if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count1;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        if (UserFullName.equalsIgnoreCase(AMNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelectedForUser) {
	                        break;
	                    }
	                } else {
	                    String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                    if (UserFullName.equalsIgnoreCase(AMNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
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


    private boolean selectRecdInternalAuditObs(String AMId, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((AMId == null) || ("".equalsIgnoreCase(AMId)))) {
            	AMId = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AMId == null) || ("".equalsIgnoreCase(AMId))) {
            	AMId = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AMNumberSequence = driver.findElement(By.xpath(".//*[@id='auditsContainerInEnterAuditFindingsForm']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (AMId.equalsIgnoreCase(AMNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String AMNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();
                    if (AMId.equalsIgnoreCase(AMNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#auditsContainerInEnterAuditFindingsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));//Document Tree approve table
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
