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

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QAReReviewAfterCFTReject extends DeviationLoginDetails {
    
    @Test
    public void toQAReReviewAfterCFTReject() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReReviewAfterCFTReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReReviewAfterCFTReject", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName10"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(1);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password , Select Deviation Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++; 	
//			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")));
//        driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Deviation Module",sno,false);
//        Thread.sleep(6000);
//        sno++;
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='devReview.do']")));
         driver.findElement(By.cssSelector("a[href='devReview.do']")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On MyActivities Tab",sno,false);
         Thread.sleep(16000);
//         WebDriverWait wait1 = new WebDriverWait(driver, 240);

         wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
         sno++;
        methodToDoQAReReviewerDeviation();
        
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}       
    }

    private void methodToDoQAReReviewerDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        WebDriverWait wait1 = new WebDriverWait(driver, 240);

        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
////        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0071";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//        String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdQAReReviewDeviation(DeviationNumber, isRecordSelected, count);
      if (isRecordSelected) {
    	  sno++;
    	  Thread.sleep(10000);
    	  JavascriptExecutor jse = (JavascriptExecutor) driver;
          WebElement element = driver.findElement(By.id("cftChildReqInDevReview"));
          jse.executeScript("arguments[0].scrollIntoView(true);", element);
          sno++;
          Thread.sleep(5000);
          if(!driver.findElement(By.id("cftChildReqInDevReview")).isSelected())
          {
          driver.findElement(By.id("cftChildReqInDevReview")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CFT CheckBox",sno,false);
          sno++;
          }
          Thread.sleep(5000);
    	  JavascriptExecutor jse1 = (JavascriptExecutor) driver;
          WebElement element1 = driver.findElement(By.id("regulatoryChildReqInDevReview"));
          jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
          wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("regulatoryChildReqInDevReview")));
          
          Thread.sleep(5000);
          if(!driver.findElement(By.id("regulatoryChildReqInDevReview")).isSelected())
          {
          driver.findElement(By.id("regulatoryChildReqInDevReview")).click();
         // jse1.executeScript("arguments[0].click();", element1);
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Regulatory CheckBox",sno,false);
              Thread.sleep(6000);
    	 sno++;
          }
    	 Thread.sleep(3000);
    	       driver.findElement(By.id("commentsInDeviationQaReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
    	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
    	        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
    	        WebElement element2 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
    	        jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//    	       JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//    	        WebElement element3 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
//    	        jse3.executeScript("arguments[0].scrollIntoView(true);", element3);
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
//    	       WebDriverWait wait1 = new WebDriverWait(driver, 70);
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
         Thread.sleep(1000);
         driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);
    	 
      } 
    	 
    	 
    	}
   private boolean selectRecdQAReReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
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
                            driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + " ]/td[44]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr/td[3]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr/td[44]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	Helper.clickElement(driver, By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
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
	 @AfterMethod
	    public void testIT(ITestResult result)
	    {
	    	if(ITestResult.FAILURE==result.getStatus())
	    	{
	    		Utility.captureScreenshot(driver, result.getName());
	    	}
	    	
	    }
    } 

    






