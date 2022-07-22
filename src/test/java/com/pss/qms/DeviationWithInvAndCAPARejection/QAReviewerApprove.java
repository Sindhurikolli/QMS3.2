package com.pss.qms.DeviationWithInvAndCAPARejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.DeviationLoginDetails;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import org.testng.annotations.Test;

/**
 *
 * @author ram
 */
public class QAReviewerApprove extends DeviationLoginDetails {
    
	@Test
    public void toQAReviewerApprove() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			WebDriverWait wait = new WebDriverWait(driver, 240);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName10"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(1);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Deviation Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
	        Thread.sleep(2000);
        sno++;
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")));
//    driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
//    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
//    Thread.sleep(3000);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='devReview.do']")));
//    sno++;
    driver.findElement(By.cssSelector("a[href='devReview.do']")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
    Thread.sleep(8000);
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message"))));
    methodToDoQAReviewerDeviation();
    document.close();
	writer.close();
	Desktop desktop = Desktop.getDesktop();
	File file = new File(output);
	//desktop.open(file);
//} catch (Exception e) {
//	e.printStackTrace();
//}
}

private void methodToDoQAReviewerDeviation() throws Exception {
    int count = 0;
    boolean isRecordSelected = false;
//    WebDriverWait wait = new WebDriverWait(driver, 240);
//
//    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message"))));
    String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
////    String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//    String DevSequence = properties.getProperty("DEVIATION_NO");
////    String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//    String DevNoWithPlantCode =  DevSequence + "/";
//    Date date = new Date();
//    String sdf = new SimpleDateFormat("YY").format(date);
//    String DevId = "/0071";
//    String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//     String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//    String DeviationNumber = DeviationNumberToTrim.trim(); 
//    System.out.println("Deviation Number is coming........:"+DeviationNumber);
    isRecordSelected = selectRecdQAReviewDeviation(DeviationNumber, isRecordSelected, count);
    if (isRecordSelected) {
    sno++;
    Thread.sleep(7000);
    	driver.findElement(By.xpath("//*[@id=\"imptMrktsTableForAddInQAReview\"]/div/div[3]/div[2]/span/span[2]")).click();
    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Market Button",sno,false);
    	 sno++;
    	 Thread.sleep(3000);
    	driver.findElement(By.id("searchBtnInAddMarketWin")).click();
    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
    	sno++;
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id=\"marketTableInAddMarketWindow\"]/div/table/tbody/tr/td[1]/input")).click();
    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Market",sno,false);
    	sno++;
    	Thread.sleep(3000);
    	driver.findElement(By.id("selBtnInAddMarketWin")).click();
    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
    	sno++;
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id=\"imptCustsTableForAddInQAReview\"]/div/div[3]/div[2]/span")).click();
    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Customer Button",sno,false);
    	sno++;
    	Thread.sleep(3000);
    	driver.findElement(By.id("searchBtnInAddCustomerWin")).click();
    	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Search Button",sno,false);
    	sno++;
    	Thread.sleep(3000);
    driver.findElement(By.xpath("//*[@id=\"CustomersTableInAddCustomerWindow\"]/div/table/tbody/tr/td[1]/input")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Customer",sno,false);
    sno++;
    Thread.sleep(3000);
driver.findElement(By.id("selBtnInAddCustomerWin")).click();
document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
sno++;
Thread.sleep(3000);
      Select Categoryofdeviation = new Select(driver.findElement(By.id("severityInDevPrim")));
        Categoryofdeviation.selectByVisibleText(properties.getProperty("Category_Of_Change"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Category Of Deviation",sno,false);
        sno++;
        Thread.sleep(3000);
     driver.findElement(By.id("repeatYesCheckBox")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Repeat Issue Yes RadioButton",sno,false);
     sno++;
     Thread.sleep(3000);
        driver.findElement(By.id("associatePriorIssueForRepeatedIssue")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Manual Entry RadioButton",sno,false);
        sno++;
        Thread.sleep(3000);
       driver.findElement(By.id("thresholdOfRepeatIssue")).sendKeys("Deviation");
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Threshold Of Repeat Isuue",sno,false);
       sno++;
       Thread.sleep(3000);
        driver.findElement(By.id("thresholdMonthsInQAReview")).sendKeys(properties.getProperty("LAST_MONTHS"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Months",sno,false);
       sno++;
       Thread.sleep(3000);
     driver.findElement(By.id("assosciatePriorIssuesInQAReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Associate Prior Issues",sno,false);
     sno++;
     Thread.sleep(3000);
   driver.findElement(By.id("customerNotificationInDevQaReview")).click();
   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer Notification",sno,false);
   sno++;
   Thread.sleep(3000);
        driver.findElement(By.id("invChildReqInDevReview")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Investigation",sno,false);
        sno++;
        Thread.sleep(3000);
     driver.findElement(By.id("reqDetInDevReview")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Request Details",sno,false);
     sno++;
     Thread.sleep(3000);
 driver.findElement(By.id("selectInvestigationOwner")).click();
 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
 sno++;
 Thread.sleep(3000);
driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
Thread.sleep(3000);
//       driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();

//WebElement element0 = driver.findElement(By.id("locTreeInQmsProdReg_5_span"));
//JavascriptExecutor js0 = (JavascriptExecutor)driver;
//js0.executeScript("arguments[0].click();", element0);  
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
       Thread.sleep(3000);
boolean isRecordSelectedForInv = false;
  String InvOwnerEcode =properties.getProperty("Ecodeinvestigationowner");
 int count4=0;
        isRecordSelectedForInv = selectingTheInvReview(InvOwnerEcode,isRecordSelectedForInv, count4);
        sno++;
        Thread.sleep(3000);
         driver.findElement(By.id("usersSelBtnInDevReview")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("cftChildReqInDevReview"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        sno++;
        Thread.sleep(3000);
        driver.findElement(By.id("cftChildReqInDevReview")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CFT CheckBox",sno,false);
        sno++;
        Thread.sleep(6000);
       driver.findElement(By.id("cftTeamAddInDevReview")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add CFT Button",sno,false);
       sno++;
       Thread.sleep(6000);
        driver.findElement(By.id("locTreeForDeptTeamInQmsDevReview_2_span")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
        Thread.sleep(6000);
          boolean isRecordSelectedForCFT = false;
      String CFTFirstName = properties.getProperty("CFT_Name");
    String CFTFullName = CFTFirstName;
    int count5=0;
    isRecordSelectedForCFT=selectingTheCFTReview(CFTFullName,isRecordSelectedForCFT,count5);
    sno++;
    Thread.sleep(3000);
    driver.findElement(By.id("addBtnInCftDeptAdd")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
    sno++;
    Thread.sleep(3000);
    Helper.scrollElement(driver, By.id("regulatoryChildReqInDevReview"));
        driver.findElement(By.id("regulatoryChildReqInDevReview")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Regulatory CheckBox",sno,false);
        Thread.sleep(6000);
        JavascriptExecutor jse5 = (JavascriptExecutor) driver;
            WebElement element5 = driver.findElement(By.id("regulatoryTeamReviewAddButton"));
            jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
            sno++;
            Thread.sleep(3000);
         driver.findElement(By.id("regulatoryTeamReviewAddButton")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Regulatory Team Button",sno,false);
         sno++;
         Thread.sleep(3000);
        driver.findElement(By.id("locTreeContainer_RglGrpSelWin_2_span")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
        Thread.sleep(6000);
        boolean isRecordSelectedForReg = false;
        String regFirstName = properties.getProperty("First_Name5");
        String RegFullName = regFirstName;
        int count3=0;
        isRecordSelectedForReg=selectingTheRegReview(RegFullName,isRecordSelectedForReg,count3);
        sno++;
        Thread.sleep(3000);
 driver.findElement(By.id("selBtn_RglGrpSelWin")).click();
 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
 sno++;
 Thread.sleep(3000);
       driver.findElement(By.id("commentsInDeviationQaReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        WebElement element2 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
        jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//       JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//        WebElement element3 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
//        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
        sno++;
        Thread.sleep(3000);
        driver.findElement(By.id("continueBtn_QaPrimaryReview")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
        sno++;
        Thread.sleep(3000);
      driver.findElement(By.id("submit_DevReviewStageDetails")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
      sno++;
      Thread.sleep(3000);
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
        sno++;
        Thread.sleep(3000);
      driver.findElement(By.id("subBtnInValESignInDev")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
       WebDriverWait wait1 = new WebDriverWait(driver, 70);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
        sno++;
        Thread.sleep(3000);
 driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
 
			sno++;
            Thread.sleep(3000);
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
    }
    
    else    
    {
    	System.out.println("Record not selected"); 	
    	Assert.assertTrue(false);
    }
 
 
 
}
private boolean selectRecdQAReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
    WebElement table = driver.findElement(By.id("devReviewTableContailner"));
    WebElement tableBody = table.findElement(By.tagName("tbody"));
    int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    int totalNoOfRecords = 0;
    int noOfRecordsChecked = 0;
    if (perPageNoOfRecordsPresent > 0) {
        String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
        String[] parts = a.split(" of ");
        try {
            totalNoOfRecords = Integer.parseInt(parts[1].trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    if (perPageNoOfRecordsPresent > 0 && count == 0) {
        if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
            DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
        } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
            DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();//documentType

        }
        ++count;
    }
    if (perPageNoOfRecordsPresent > 0) {
        while (noOfRecordsChecked < totalNoOfRecords) {
            if (totalNoOfRecords > 1) {
                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                	Helper.scrollElement(driver, By.xpath("//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]"));
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                       Helper.clickElement(driver, By.xpath("//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[44]/button"));
//                    	driver.findElement(By.xpath("//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[44]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                if (isRecordSelected) {
                    break;
                }
            } else {
                String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();
                if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                    driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[44]/button")).click();
                    isRecordSelected = true;
                    break;
                }
            }
            noOfRecordsChecked += perPageNoOfRecordsPresent;
            if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
            	Helper.clickElement(driver, By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//            	driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                Thread.sleep(3000);
                Helper.waitLoadRecords(driver, By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message[style='display: none;']"));
                table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
                tableBody = table.findElement(By.tagName("tbody"));
                perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
            }
        }
    }
    return isRecordSelected;
}
private boolean selectingTheCFTReview(String cftFullName, boolean recordSelected, int count) {
    WebElement table = driver.findElement(By.id("cftDeptTableWindowContainer"));
    WebElement tableBody = table.findElement(By.tagName("tbody"));
    int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    if (perPageNoOfRecordsPresent > 0 && count == 0) {
        if (((cftFullName == null) || ("".equalsIgnoreCase(cftFullName)))) {
            cftFullName = driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
        } else if ((cftFullName == null) || ("".equalsIgnoreCase(cftFullName))) {
            cftFullName = driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

        }
        ++count;
    }
    if (perPageNoOfRecordsPresent > 0) {
        //while (noOfRecordsChecked < totalNoOfRecords) {
            if (perPageNoOfRecordsPresent > 1) {
                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[3]")).getText();//documentTypeName
                    System.out.println("cftReviewerFullName: "+cftReviewerFullName);
                    if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr[" + i + "]/td[3]")).click();
                        recordSelected = true;
                        break;
                    }
                }
               
            } else {
                String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr/td[3]")).getText();
                if (cftFullName.equalsIgnoreCase(cftReviewerFullName)) {
                    driver.findElement(By.xpath("//*[@id=\"cftDeptTableWindowContainer\"]/div/table/tbody/tr/td[3]")).click();
                    recordSelected = true;
                    
                }
            }
    }
    return recordSelected;
}
private boolean selectingTheRegReview(String RegFullName, boolean recordSelectedForReg, int count3) {
    WebElement table = driver.findElement(By.id("ugRecsTable_RglGrpSelWin"));
    WebElement tableBody = table.findElement(By.tagName("tbody"));
    int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
        if (((RegFullName == null) || ("".equalsIgnoreCase(RegFullName)))) {
            RegFullName = driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
        } else if ((RegFullName == null) || ("".equalsIgnoreCase(RegFullName))) {
            RegFullName = driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr/td[2]")).getText();//documentType

        }
        ++count3;
    }
    if (perPageNoOfRecordsPresent > 0) {
        //while (noOfRecordsChecked < totalNoOfRecords) {
            if (perPageNoOfRecordsPresent > 1) {
                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr[" + i + "]/td[2]")).getText();//documentTypeName
                    System.out.println("cftReviewerFullName: "+cftReviewerFullName);
                    if (RegFullName.equalsIgnoreCase(cftReviewerFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr[" + i + "]/td[2]")).click();
                        recordSelectedForReg = true;
                        break;
                    }
                }
               
            } else {
                String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr/td[5]")).getText();
                if (RegFullName.equalsIgnoreCase(cftReviewerFullName)) {
                    driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr/td[5]")).click();
                    recordSelectedForReg = true;
                    
                }
            }          
    }
    return recordSelectedForReg;
}
private boolean selectingTheInvReview(String InvFullName, boolean recordSelectedForInv, int count4) throws InterruptedException {
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
    if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
    	if ((totalNoOfRecords > 1) && ((InvFullName == null) || ("".equalsIgnoreCase(InvFullName)))) {
      //  if (((InvFullName == null) || ("".equalsIgnoreCase(InvFullName)))) {
        
            InvFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
        } else if ((InvFullName == null) || ("".equalsIgnoreCase(InvFullName))) {
            InvFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

        }
        ++count4;
    }
    if (perPageNoOfRecordsPresent > 0) {
        while (noOfRecordsChecked < totalNoOfRecords) {
            if (perPageNoOfRecordsPresent > 1) {
                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                    System.out.println("InvReviewFullName: "+InvFirstName);
                    if (InvFullName.equalsIgnoreCase(InvFirstName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                        recordSelectedForInv = true;
                        break;
                        
                       // break;
                    }
                }
                if (recordSelectedForInv) {
                    break;
                }
                   
            
               
            } else {
                String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                if (InvFullName.equalsIgnoreCase(InvReviewFullName)) {
                    driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                    recordSelectedForInv = true;
                    
                }
            }
            noOfRecordsChecked += perPageNoOfRecordsPresent;
            if ((!recordSelectedForInv) && (noOfRecordsChecked < totalNoOfRecords)) {
                driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                Thread.sleep(10000);
                table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                tableBody = table.findElement(By.tagName("tbody"));
                perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
            }
        }
    }
    return recordSelectedForInv;
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



   