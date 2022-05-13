package com.pss.qms.IncidentManagementWithCustomerCftRA;

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
import com.pss.qms.login.IncidentManagementLoginDetails;
import com.pss.qms.login.IncidentManagementLoginDetails;
 
 

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
public class QAReviewerInc extends IncidentManagementLoginDetails {
    
    
	@Test
    public void QAReviewerIncident() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
				Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewerInc"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);

				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewerInc", "PSS-QMS-001", "Pass");
				writer.setPageEvent(event);
				document.open();
				WebDriverWait wait = new WebDriverWait(driver, 240);
				driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QMSCoordinator"));
				driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
				Select module= new Select(driver.findElement(By.id("qmsModule")));
				module.selectByVisibleText(properties.getProperty("IncidentModule"));
				input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
				
				im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
						(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
				document.add(new Paragraph(sno + "." + "Enter the username, password, Select Incident Management Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
		        Thread.sleep(2000);
	        sno++;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='incReview.do']")));
        sno++;
        driver.findElement(By.cssSelector("a[href='incReview.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Review",sno,false);
//        Thread.sleep(10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#incReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
        methodToDoQAReviewerIncident();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

    private void methodToDoQAReviewerIncident() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
       WebDriverWait wait1 = new WebDriverWait(driver, 240);
       String IncidentNumber = properties.getProperty("IncidentNo");
        isRecordSelected = selectRecdQAReviewIncident(IncidentNumber, isRecordSelected, count);
//        if (isRecordSelected) {
        sno++;
        Thread.sleep(3000);
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
          Select Categoryofdeviation = new Select(driver.findElement(By.id("severityInIncPrim")));
            Categoryofdeviation.selectByVisibleText(properties.getProperty("Category_Of_Incident"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Category Of Incident",sno,false);
            sno++;
            Thread.sleep(2000);
         driver.findElement(By.id("repeatYesCheckBox")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Repeat Issue Yes RadioButton",sno,false);
         sno++;
         Thread.sleep(1000);
            driver.findElement(By.id("associatePriorIssueForRepeatedIssue")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Manual Entry RadioButton",sno,false);
            sno++;
            Thread.sleep(1000);
           driver.findElement(By.id("thresholdOfRepeatIssue")).sendKeys("Incident");
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Threshold Of Repeat Isuue",sno,false);
           sno++;
           Thread.sleep(1000);
            driver.findElement(By.id("thresholdMonthsInQAReview")).sendKeys(properties.getProperty("LAST_MONTHS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Months",sno,false);
           sno++;
           Thread.sleep(1000);
         driver.findElement(By.id("assosciatePriorIssuesInQAReview")).sendKeys(properties.getProperty("AssociatePriorIssues_2000"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Associate Prior Issues",sno,false);
         sno++;
         Thread.sleep(1000);
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         jse.executeScript("window.scrollBy(0, 500)");
       driver.findElement(By.id("customerNotificationInIncQaReview")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer Notification",sno,false);
       sno++;
       Thread.sleep(1000);
        driver.findElement(By.id("justifyForNoInv_IncQaPrimReview")).sendKeys(properties.getProperty("JustificationNoInv_3000"));
           sno++;
            Thread.sleep(1000);
            jse.executeScript("window.scrollBy(0, 400)");
           driver.findElement(By.id("cftChildReqInIncReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CFT CheckBox",sno,false);
            sno++;
            Thread.sleep(2000);
           driver.findElement(By.id("cftTeamAddInDevReview")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add CFT Button",sno,false);
           sno++;
           Thread.sleep(3000);
            driver.findElement(By.id("locTreeForDeptTeamInQmsDevReview_2_span")).click();
            Thread.sleep(3000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
              boolean isRecordSelectedForCFT = false;
          String CFTFirstName = properties.getProperty("CFT_DeptName");
        String CFTFullName = CFTFirstName;
        int count5=0;
        isRecordSelectedForCFT=selectingTheCFTReview(CFTFullName,isRecordSelectedForCFT,count5);
        sno++;
        Thread.sleep(3000);
//        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//        WebElement element1 = driver.findElement(By.id("addBtnInCftDeptAdd"));
//        jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
        Thread.sleep(1000);
        driver.findElement(By.id("addBtnInCftDeptAdd")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        sno++;
        Thread.sleep(3000);
        jse.executeScript("window.scrollBy(0, 500)");
            driver.findElement(By.id("regulatoryChildReqInIncReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Regulatory CheckBox",sno,false);
            
//            JavascriptExecutor jse5 = (JavascriptExecutor) driver;
//                WebElement element5 = driver.findElement(By.id("regulatoryTeamReviewAddButton"));
//                jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
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
            String regFirstName = properties.getProperty("RA_UserGroupName");
            String RegFullName = regFirstName;
            int count3=0;
            isRecordSelectedForReg=selectingTheRegReview(RegFullName,isRecordSelectedForReg,count3);
            sno++;
            Thread.sleep(3000);
     driver.findElement(By.id("selBtn_RglGrpSelWin")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
     sno++;
     jse.executeScript("window.scrollBy(0, 500)");
     Thread.sleep(2000);
     driver.findElement(By.id("addSuppDocsCheckInDevQaQAPrimReview")).click();
     Thread.sleep(2000);
     driver.findElement(By.id("incAddSuppDocsQAReview")).click();
    driver.findElement(By.id("uploadSuppDocQAReview_1")).sendKeys(properties.getProperty("Document-3"));
     Thread.sleep(2000);
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload supporting Document",sno,false);
     sno++;
           driver.findElement(By.id("commentsInIncidentQaReview")).sendKeys(properties.getProperty("Comments_QAReview_2000"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
//            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
//            WebElement element2 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
//            jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//           JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//            WebElement element3 = driver.findElement(By.id("continueBtselBtn_RglGrpSelWinn_QaPrimaryReview"));
//            jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("continueBtn_QaPrimaryReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
            sno++;
            Thread.sleep(3000);
          driver.findElement(By.id("submit_IncReviewStageDetails")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
          sno++;
          Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
            sno++;
            Thread.sleep(3000);
          driver.findElement(By.id("subBtnInValESignInInc")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
     WebDriverWait wait = new WebDriverWait(driver, 70);
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
     Thread.sleep(1000);
     driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
     
     
     
     
    }
    private boolean selectRecdQAReviewIncident(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("incReviewTableContailner"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String IncNumberSequence = driver.findElement(By.xpath(".//*[@id='incReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='incReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[43]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/table/tbody/tr/td[43]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#incReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("incReviewTableContailner"));//Document Tree approve table
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
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
    } 



