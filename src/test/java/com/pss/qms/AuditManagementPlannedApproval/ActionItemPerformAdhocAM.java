package com.pss.qms.AuditManagementPlannedApproval;

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
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
@Listeners(com.pss.qms.Listners.TestListener.class)
public class ActionItemPerformAdhocAM extends AMLoginDetails {
    
   @Test
    public void ActionItemPerform()  throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
	           document = new Document();
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlannedScheduleAM"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
			
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("ActionItem Perform", "PSS-QMS-001",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	    	  driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ActionItemOwner"));
	          driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	          Select module= new Select(driver.findElement(By.id("qmsModule")));
				 module.selectByVisibleText(properties.getProperty("AMModule"));
	          input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	         driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
	         Thread.sleep(4000);
	          im = Image.getInstance(input);
	  		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
	  		document.add(new Paragraph(sno+"."+"Enter the username, password, Select Audit Management Module and click on login button"+ Utilities.prepareSSNumber(sno), font));
	  		document.add(im);
	  		document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
	  		sno++;
//        driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//        sno++;
//        driver.findElement(By.id("myActivitiesInAM")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"amActionItemsMenus\"]/div[1]/a")).click();
        Thread.sleep(4000);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on ActionItem Perform Menu",sno,false);
//        sno++;
//        //Statically Selecting The Record Since Its Not Working Dynamically
//        driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[2]/td[3]")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Record",sno,false);
//        sno++;
//    	driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
//    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
//    	sno++;
//        driver.findElement(By.id("selectApprovedFromInAmActPerform")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
//        sno++;
//        driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
//        driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
//        sno++;
//        boolean isRecordSelectedForUser = false;
//        String UserFirstName = properties.getProperty("AUDIT_APPROVER");
//      String UserFullName = UserFirstName;
//      int count1=0;
//      isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
//      sno++;
//      driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
//      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
//      sno++;
//        driver.findElement(By.id("commentsInAmPerform")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
//        sno++;
////        driver.findElement(By.id("suppDocumentInAmActItmPerform")).sendKeys(properties.getProperty("Document-1"));
////        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments "+"  PSS-QMS-SS-032",sno,false);
//         driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//         sno++;
//        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
//        sno++;
//        driver.findElement(By.id("subBtnInValidateESign")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//        WebDriverWait wait = new WebDriverWait(driver, 70);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
//        sno++;
//        driver.findElement(By.className("modal-btn")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
//        sno++;
//        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username",sno,false);
//        sno++;
//        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
        Thread.sleep(4000);
        document.close();
		writer.close();
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

    private void methodToDoActionItem() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String ActionId = properties.getProperty("AI_NUMBER");
        String ActionItemNumber = ActionId;
        isRecordSelected = selectRecdActionItem(ActionItemNumber,isRecordSelected, count);
        Thread.sleep(4000);
        if (isRecordSelected) {
        	sno++;
        	driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
        	sno++;
            driver.findElement(By.id("selectApprovedFromInAmActPerform")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            boolean isRecordSelectedForUser = false;
            String UserFirstName = properties.getProperty("AUDIT_APPROVER");
          String UserFullName = UserFirstName;
          int count1=0;
          isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
          sno++;
          driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          sno++;
            driver.findElement(By.id("commentsInAmPerform")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            sno++;
//            driver.findElement(By.id("suppDocumentInAmActItmPerform")).sendKeys(properties.getProperty("Document-1"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments "+"  PSS-QMS-SS-032",sno,false);
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
            if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
	         {
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	         }
            sno++;
            driver.findElement(By.className("modal-btn")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username",sno,false);
            sno++;
            driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
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
 

	private boolean selectRecdActionItem(String ActionItemNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("actItmPerformTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((ActionItemNumber == null) || ("".equalsIgnoreCase(ActionItemNumber)))) {
            	ActionItemNumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((ActionItemNumber == null) || ("".equalsIgnoreCase(ActionItemNumber))) {
            	ActionItemNumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AINumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (ActionItemNumber.equalsIgnoreCase(AINumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String AINumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();
                    if (ActionItemNumber.equalsIgnoreCase(AINumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#actItmPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("actItmPerformTable"));//Document Tree approve table
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

 
 




