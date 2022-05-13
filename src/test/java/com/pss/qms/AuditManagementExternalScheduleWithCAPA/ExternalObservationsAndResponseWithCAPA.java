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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class ExternalObservationsAndResponseWithCAPA extends AMLoginDetails {

	 @Test
	    public void methodtodoExternalScheduleAM() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ExternalObservationsAndResponseWithCAPA"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("ExternalObservationsAndResponseWithCAPA", "PSS-QMS-004",
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
//     sno++;
//     driver.findElement(By.id("myActivitiesInAM")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
//     sno++;
//     driver.findElement(By.xpath("//*[@id=\"amExternalAuditMainMenu\"]/a/b")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on External Menu",sno,false);  
     sno++;
     driver.findElement(By.xpath("//*[@id=\"externalAuditPerformMenus\"]/div[1]/a")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on External Obs And Response Menu",sno,false);  
     sno++;
     methodToDoExternalObservationsAndResponseWithCAPA();
     document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
 }

 private void methodToDoExternalObservationsAndResponseWithCAPA() throws Exception {
     int count = 0;
     boolean isRecordSelected = false;
     String ExternalId =properties.getProperty("EXTERNAL_NAME_EXTERNAL_SCHEDULE");
     isRecordSelected = selectRecdExternalObservationsAndResponseWithCAPA(ExternalId, isRecordSelected, count);
     if (isRecordSelected) {
     	sno++;
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
         sno++;
         Thread.sleep(2000);
         ((JavascriptExecutor)driver).executeScript ("document.getElementById('inpectionDateInStartAuditForm').removeAttribute('readonly',0);"); 
         WebElement identifiedDate= driver.findElement(By.id("inpectionDateInStartAuditForm")); 
         identifiedDate.clear(); 
         Date date = new Date();
         String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
         driver.findElement(By.id("inpectionDateInStartAuditForm")).sendKeys(todaysDate);
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Start Date",sno,false);
           sno++;
         ((JavascriptExecutor)driver).executeScript ("document.getElementById('inpectionEndDateInStartAuditForm').removeAttribute('readonly',0);"); 
         WebElement identifiedDate1= driver.findElement(By.id("inpectionEndDateInStartAuditForm")); 
         identifiedDate1.clear(); 
         Date date1 = new Date();
         String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
         driver.findElement(By.id("inpectionEndDateInStartAuditForm")).sendKeys(todaysDate1);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On End Date",sno,false);
         sno++;
         ((JavascriptExecutor)driver).executeScript ("document.getElementById('auditReportRecieivedDate').removeAttribute('readonly',0);"); 
         WebElement identifiedDate2= driver.findElement(By.id("auditReportRecieivedDate")); 
         identifiedDate2.clear(); 
         Date date2 = new Date();
         String todaysDate2 = new SimpleDateFormat("dd/MM/yyyy").format(date2);
         driver.findElement(By.id("auditReportRecieivedDate")).sendKeys(todaysDate2);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditReport Date",sno,false);
         driver.findElement(By.id("inpectionDateInStartAuditForm")).click();
         sno++;    
         driver.findElement(By.xpath("//*[@id=\"addObservationsInExternalResp\"]/div/div[3]/div[2]/span/span[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Observations",sno,false);
         sno++; 
         driver.findElement(By.id("observationInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Observations",sno,false);
         sno++; 
        driver.findElement(By.id("areaInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area",sno,false);
        sno++;
         Select Category = new Select(driver.findElement(By.id("categoryInExternalWin")));
         Category.selectByIndex(1);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Categry",sno,false);
         sno++;
       driver.findElement(By.id("referenceInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Comments",sno,false);
       sno++;
         driver.findElement(By.id("rootCauseInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter RootCause",sno,false);
         sno++;
         driver.findElement(By.id("responseInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Response",sno,false);
         sno++;
         Select Category1 = new Select(driver.findElement(By.id("taskTypeInExternalWin")));
         Category1.selectByIndex(1);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType",sno,false);
         sno++;
         driver.findElement(By.id("addBtnInExternalWin")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
         JavascriptExecutor jse1 = (JavascriptExecutor) driver;
         WebElement element1 = driver.findElement(By.xpath("//*[@id=\"caNPAChkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr/td[12]/button"));
         jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
         Thread.sleep(1000);
       sno++;
          driver.findElement(By.xpath("//*[@id=\"caNPAChkPointsTableInAuditeeResponseForm\"]/div/table/tbody/tr/td[12]/button")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On ActionItem CheckBox",sno,false);
          sno++;
          Thread.sleep(2000);
          WebElement element = driver.findElement(By.id("selectBtnInDeptSelInAuditPlan"));
          JavascriptExecutor js = (JavascriptExecutor)driver;
          js.executeScript("arguments[0].click();", element);  
          Thread.sleep(2000);
//          driver.findElement(By.id("selectBtnInDeptSelInAuditPlan")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          sno++;
          driver.findElement(By.id("treeContainer_2_switch")).click();
          Thread.sleep(2000);
          driver.findElement(By.id("treeContainer_3_span")).click();
          Thread.sleep(2000);
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
          sno++;
          driver.findElement(By.id("selectBtnInLocSelect")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          sno++;
          driver.findElement(By.id("chkCAInStartAuditCaNPaDlg")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Corrective Action CheckBox",sno,false);
            sno++;
         driver.findElement(By.id("crctvActionInStartAuditCaNPaDlg")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CA Comments ",sno,false);
         sno++;
         Date date3 = new Date();
         String todaysDate3 = new SimpleDateFormat("dd/MM/yyyy").format(date3);
         Thread.sleep(3000);
         driver.findElement(By.id("caTargetDateInStartAudCaNPaDlg")).sendKeys(todaysDate3);
         Thread.sleep(3000);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CA TargetDate ",sno,false);
         
         sno++;
         driver.findElement(By.id("chkPAInStartAuditCaNPaDlg")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Preventive Action CheckBox",sno,false);
           sno++;
        driver.findElement(By.id("prvntvActionInStartAuditCaNPaDlg")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter PA Comments ",sno,false);
        sno++;
        Date date4 = new Date();
        String todaysDate4 = new SimpleDateFormat("dd/MM/yyyy").format(date4);
        Thread.sleep(3000);
        driver.findElement(By.id("paTargetDateInStartAudCaNPaDlg")).sendKeys(todaysDate4);
        Thread.sleep(3000);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter PA TargetDate ",sno,false);
          sno++;
         driver.findElement(By.id("addBtnInAudCaNPaDlg")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
         
         sno++;
         driver.findElement(By.xpath("//*[@id=\"selectUsersForExternalAuditObservationsReview\"]/div/div[3]/div[2]/span/span[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Reviewer Button",sno,false);
         sno++;
         driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
         Thread.sleep(3000);
         driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
         boolean isRecordSelectedForUser = false;
         String UserFirstName = properties.getProperty("AUDIT_REVIEWER");
       String UserFullName = UserFirstName;
       int count1=0;
       isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
       driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
       sno++;
        driver.findElement(By.xpath("//*[@id=\"selectUsersForExternalAuditObservationsApproval\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Approver",sno,false);
        sno++;
         driver.findElement(By.id("locTreeInQmsProdReg_2_a")).click();
         Thread.sleep(2000);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
         sno++;
         String UserFirstName1 = properties.getProperty("AUDIT_APPROVER");
       String UserFullName1 = UserFirstName1;
  
       isRecordSelectedForUser=selectingTheUserReview(UserFullName1,isRecordSelectedForUser,count1);
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User",sno,false);
       sno++;
       driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);   
       sno++;
       driver.findElement(By.id("commentsInExternalWin")).sendKeys(properties.getProperty("AUDIT_COMMENTS_1500"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments ",sno,false);
       sno++;
         
         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]")).click();
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
     } else {
         System.out.println("Record is not Selected");
         Assert.assertTrue(false);
     }
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
	                        String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelectedForUser) {
	                        break;
	                    }
	                } else {
	                    String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                    if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
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
 private boolean selectRecdExternalObservationsAndResponseWithCAPA(String ExternalId, boolean isRecordSelected, int count) throws InterruptedException {
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
         if ((totalNoOfRecords > 1) && ((ExternalId == null) || ("".equalsIgnoreCase(ExternalId)))) {
        	 ExternalId = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
         } else if ((ExternalId == null) || ("".equalsIgnoreCase(ExternalId))) {
        	 ExternalId = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();//documentType

         }
         ++count;
     }
     if (perPageNoOfRecordsPresent > 0) {
         while (noOfRecordsChecked < totalNoOfRecords) {
             if (totalNoOfRecords > 1) {
                 for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                     String ExternalNumberSequence = driver.findElement(By.xpath(".//*[@id='auditsContainerInEnterAuditFindingsForm']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                     if (ExternalId.equalsIgnoreCase(ExternalNumberSequence)) {
                         driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                         isRecordSelected = true;
                         break;
                     }
                 }
                 if (isRecordSelected) {
                     break;
                 }
             } else {
                 String ExternalNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();
                 if (ExternalId.equalsIgnoreCase(ExternalNumberSequence)) {
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
