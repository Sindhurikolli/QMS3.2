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
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.DeviationLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class QAReviewerDevWithoutAIAndINV extends DeviationLoginDetails {
    
    
	@Test
    public void toQAReviewerDev() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

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
//	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")));
//        driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
//        Thread.sleep(10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='devReview.do']")));
        sno++;
        driver.findElement(By.cssSelector("a[href='devReview.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review",sno,false);
//        Thread.sleep(10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
      
        methodToDoQAReviewerDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }

    private void methodToDoQAReviewerDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
             
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
////        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode =  DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0068";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//         String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdQAReviewDeviation(DeviationNumber, isRecordSelected, count);
        if (isRecordSelected) {
        sno++;
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 70);
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
         Thread.sleep(5000);
       driver.findElement(By.id("customerNotificationInDevQaReview")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer Notification",sno,false);
//       sno++;
//       Thread.sleep(3000);
//            driver.findElement(By.id("invChildReqInDevReview")).click();
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Investigation",sno,false);
            sno++;
            Thread.sleep(3000);
         driver.findElement(By.id("justifyForNoInv_DevQaPrimReview")).sendKeys(properties.getProperty("Description_Of_Deviation_500"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Request Details",sno,false);
//         sno++;
//         Thread.sleep(3000);
//     driver.findElement(By.id("selectInvestigationOwner")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
//     sno++;
//     Thread.sleep(3000);
//   driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
//   Thread.sleep(5000);
//           driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
//   WebElement element = driver.findElement(By.id("locTreeInQmsProdReg_5_span"));
//   JavascriptExecutor js = (JavascriptExecutor)driver;
//   js.executeScript("arguments[0].click();", element);  
//           Thread.sleep(3000);
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
//   boolean isRecordSelectedForInv = false;
//      String InvOwnerEcode =properties.getProperty("Ecodeinvestigationowner");
//     int count4=0;
//            isRecordSelectedForInv = selectingTheInvReview(InvOwnerEcode,isRecordSelectedForInv, count4);
//            sno++;
//            Thread.sleep(3000);
//             driver.findElement(By.id("usersSelBtnInDevReview")).click();
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            JavascriptExecutor jse0 = (JavascriptExecutor) driver;
            WebElement element0 = driver.findElement(By.id("cftChildReqInDevReview"));
            jse0.executeScript("arguments[0].scrollIntoView(true);", element0);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("cftChildReqInDevReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CFT CheckBox",sno,false);
            sno++;
            Thread.sleep(3000);
           driver.findElement(By.id("cftTeamAddInDevReview")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add CFT Button",sno,false);
           sno++;
           Thread.sleep(3000);
            driver.findElement(By.id("locTreeForDeptTeamInQmsDevReview_2_span")).click();
            Thread.sleep(3000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
              boolean isRecordSelectedForCFT = false;
          String CFTFirstName = properties.getProperty("CFT_Name");
        String CFTFullName = CFTFirstName;
        int count5=0;
        isRecordSelectedForCFT=selectingTheCFTReview(CFTFullName,isRecordSelectedForCFT,count5);
        sno++;
        Thread.sleep(3000);
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.id("addBtnInCftDeptAdd"));
        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
        Thread.sleep(1000);
        driver.findElement(By.id("addBtnInCftDeptAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        sno++;
        Thread.sleep(3000);
            driver.findElement(By.id("regulatoryChildReqInDevReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Regulatory CheckBox",sno,false);
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
            boolean isRecordSelectedForReg = false;
            Thread.sleep(3000);
            String regFirstName = properties.getProperty("First_Name5");
            String RegFullName = regFirstName;
            int count3=0;
            isRecordSelectedForReg=selectingTheRegReview(RegFullName,isRecordSelectedForReg,count3);
            sno++;
            Thread.sleep(3000);
     driver.findElement(By.id("selBtn_RglGrpSelWin")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
//     sno++;
//     driver.findElement(By.id("actItmCheckInDevQaReview")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Action Items Check Box",sno,false);
//     sno++;
//     driver.findElement(By.xpath("//*[@id=\"qaRevActItemsDetailsContainer\"]/div/div[3]/div[2]/span/span[2]")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
//     sno++;
//     driver.findElement(By.xpath("//*[@id=\"actionItemOwnerDetailsTable\"]/div/div[3]/div[2]/span/span[2]")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add action item Owner",sno,false);
//     sno++;
//     driver.findElement(By.xpath("//*[@id=\"locationTreeForMultiUserSelect_2_switch\"]")).click();
//     Thread.sleep(3000);
//     driver.findElement(By.linkText(properties.getProperty("Dept_Level_Text"))).click();
//     Thread.sleep(3000);
//     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#MultiSelUsersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
//     boolean isRecordSelectedForAIOwner = false;
//     Thread.sleep(3000);
//     String AIOwnerE_code = properties.getProperty("AIOwnerE_code");
//     String AIOwner = AIOwnerE_code;
//     int countai=0;
//     isRecordSelectedForAIOwner=selectingTheAIOwner(AIOwner,isRecordSelectedForAIOwner,countai);
//     Thread.sleep(3000);
//     if(isRecordSelectedForAIOwner)
//     {
//    	 driver.findElement(By.id("selBtn_InMultiUserSelectDialog")).click();
//    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On select action item Owner",sno,false);
//         sno++;
//      }
//     else
//     {
//    	 System.out.println("Action Item Owner not selected");
//    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "action item Owner not selected",sno,false);
//         sno++;
//     }
//    
//     driver.findElement(By.id("selectApproverInDevQaReview1")).click();
//     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on select action item Approver",sno,false);
//     sno++;
//     Thread.sleep(2000);
//     driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
//     Thread.sleep(2000);
//     driver.findElement(By.linkText(properties.getProperty("Dept_Level_Text"))).click();
//     Thread.sleep(2000);
//     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
//     boolean isRecordSelectedForAIApprover = false;
//     Thread.sleep(3000);
//     String AIApproverE_code = properties.getProperty("AIApproverE_code");
//     String AIApprover = AIApproverE_code;
//     int countAIA=0;
//     isRecordSelectedForAIApprover=selectingTheAIApprover(AIApprover,isRecordSelectedForAIApprover,countAIA);
//     if(isRecordSelectedForAIApprover)
//     {
//    	 driver.findElement(By.id("usersSelBtnInDevReview")).click();
//    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On select action item Approver",sno,false);
//         sno++; 
//     }
//     else
//     {
//    	 System.out.println("Action Item Approver not selected");
//    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "action item Approver not selected",sno,false);
//         sno++; 
//     }
//     driver.findElement(By.id("actItmDueDateInAddActItemDlg")).click();
//     Thread.sleep(1000);
//     driver.findElement(By.cssSelector("a.ui-state-default")).click();
//     Select taskType= new Select(driver.findElement(By.id("actionItemTaskTypeInAddActItemDlg")));
//     taskType.selectByIndex(1);
//     Select completebefore= new Select(driver.findElement(By.id("cmpltActItemBeforeProceedInAddActItemDlg")));
//     completebefore.selectByIndex(2);
//     driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ActionItemDescription_2000"));
//     driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
     Thread.sleep(2000);
     JavascriptExecutor jse = (JavascriptExecutor)driver;
     jse.executeScript("window.scrollBy(0,250)");

     driver.findElement(By.id("addSuppDocsCheckInDevQaQAPrimReview")).click();
     driver.findElement(By.id("devAddSuppDocsQAReview")).click();
     driver.findElement(By.id("uploadSuppDocQAReview_1")).sendKeys(properties.getProperty("Document-2"));
     
     
           driver.findElement(By.id("commentsInDeviationQaReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            WebElement element2 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
            jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//           JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//            WebElement element3 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
//            jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
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
   
     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(3000);
     driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
     
     sno++;
     Thread.sleep(3000);
     driver.findElement(By.className("username")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
     sno++;
     Thread.sleep(4000);
     driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
     
        }
        else
        {
        	System.out.println("Record is not selected");
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
                        String DevNumberSequence = driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[43]/button")).click();
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
                        driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[43]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
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
                        String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
                        System.out.println("cftReviewerFullName: "+cftReviewerFullName);
                        if (RegFullName.equalsIgnoreCase(cftReviewerFullName)) {
                            driver.findElement(By.xpath("//*[@id=\"ugRecsTable_RglGrpSelWin\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
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
    private boolean selectingTheInvReview(String InvFullName, boolean recordSelectedForInv, int count4) {
        WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
            if (((InvFullName == null) || ("".equalsIgnoreCase(InvFullName)))) {
            
                InvFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((InvFullName == null) || ("".equalsIgnoreCase(InvFullName))) {
                InvFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            //while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (InvFullName.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            recordSelectedForInv = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (InvFullName.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        recordSelectedForInv = true;
                        
                    }
                }
        }
        return recordSelectedForInv;
    }
    private boolean selectingTheAIOwner(String AIOwner, boolean isRecordSelectedForAIOwner, int countai) throws InterruptedException {
    	WebElement table = driver.findElement(By.id("MultiSelUsersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && countai == 0) {
            if (((AIOwner == null) || ("".equalsIgnoreCase(AIOwner)))) {
            	AIOwner = driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AIOwner == null) || ("".equalsIgnoreCase(AIOwner))) {
            	AIOwner = driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++countai;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AIOwnerecode= driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
//                        System.out.println("cftReviewerFullName: "+AIOwnerecode);
                        if (AIOwner.equalsIgnoreCase(AIOwnerecode)) {
                            driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForAIOwner = true;
                            break;
                        }
                    }
                   
                } else {
                    String AIOwnerecode = driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (AIOwner.equalsIgnoreCase(AIOwnerecode)) {
                        driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForAIOwner = true;
                        
                    }
                } 
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForAIOwner) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#MultiSelUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("MultiSelUsersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        }
        }
        }
        return isRecordSelectedForAIOwner;
    }
    private boolean selectingTheAIApprover(String AIApprover, boolean isRecordSelectedForAIApprover, int countAIA) throws InterruptedException {
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
        if (perPageNoOfRecordsPresent > 0 && countAIA == 0) {
            if (((AIApprover == null) || ("".equalsIgnoreCase(AIApprover)))) {
            	AIApprover = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AIApprover == null) || ("".equalsIgnoreCase(AIApprover))) {
            	AIApprover = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++countAIA;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AIApproverecode= driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
//                        System.out.println("cftReviewerFullName: "+AIOwnerecode);
                        if (AIApprover.equalsIgnoreCase(AIApproverecode)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForAIApprover = true;
                            break;
                        }
                    }
                   
                } else {
                    String AIApproverecode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (AIApprover.equalsIgnoreCase(AIApproverecode)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForAIApprover = true;
                        
                    }
                } 
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForAIApprover) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        }
        }
        }
        return isRecordSelectedForAIApprover;
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



