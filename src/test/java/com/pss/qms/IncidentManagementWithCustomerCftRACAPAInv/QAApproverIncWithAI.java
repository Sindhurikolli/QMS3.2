package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInv;

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
import com.pss.qms.login.IncidentManagementLoginDetails;
import com.pss.qms.login.IncidentManagementLoginDetails;

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
 * @author ram
 */
//@Listeners(com.pss.qms.Listners.TestListenerCC.class)
public class QAApproverIncWithAI extends IncidentManagementLoginDetails {
    
  
	    
	  @Test
	    public void QAApproverIncident() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
				Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAApproverInc"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);

				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAApproverInc", "PSS-QMS-001", "Pass");
				writer.setPageEvent(event);
				document.open();
				 WebDriverWait wait1 = new WebDriverWait(driver, 240);
				driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("QAHod"));
				driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
				Select module= new Select(driver.findElement(By.id("qmsModule")));
				module.selectByVisibleText(properties.getProperty("IncidentModule"));
				input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
				
				Thread.sleep(3000);
				im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
						(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
				document.add(new Paragraph(sno + "." + "Enter the username, password, Select Incident Management Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
				sno++;  
	        Thread.sleep(3000);
	        wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='incReview.do']")));
	        driver.findElement(By.cssSelector("a[href='incReview.do']")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Review Tab",sno,false);
	        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#incReviewTableContailner > div > div.jtable-busy-message[style='display: none;']")));
	        methodToDoQAApproverDeviation();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
		}
//	    }

	    private void methodToDoQAApproverDeviation() throws Exception {
	    	WebDriverWait wait1 = new WebDriverWait(driver, 240);
	        int count = 0;
	        boolean isRecordSelected = false; 
	        String IncidentNumber = properties.getProperty("IncidentNo");
	        isRecordSelected = selectRecdQAApproveDeviation(IncidentNumber, isRecordSelected, count);
	      if (isRecordSelected) {
    	  sno++;
    	  Thread.sleep(2000);
    	  WebDriverWait wait = new WebDriverWait(driver, 70);
            driver.findElement(By.id("comments_DevReview")).sendKeys(properties.getProperty("QAHODComments_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("incAddSuppDocs_DevReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Supporting Documents",sno,false);
            sno++;
            Thread.sleep(2000);
         driver.findElement(By.id("uploadSuppDoc_Dev_Review_1")).sendKeys(properties.getProperty("Document-1"));
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Documents",sno,false);
         sno++;
         Thread.sleep(2000);
         JavascriptExecutor jse = (JavascriptExecutor)driver;
         jse.executeScript("window.scrollBy(0,250)");
         driver.findElement(By.id("actItmCheck_DevReview")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Action Items Check Box",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"actItemsDetailsContainer_DevReview\"]/div/div[3]/div[2]/span/span[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"actionItemOwnerDetailsTable\"]/div/div[3]/div[2]/span/span[2]")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add action item Owner",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"locationTreeForMultiUserSelect_2_switch\"]")).click();
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.linkText(properties.getProperty("Dept_Level_Text"))).click();
         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#MultiSelUsersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
         boolean isRecordSelectedForAIOwner = false;
         Thread.sleep(3000);
         String AIOwnerE_code = properties.getProperty("AIOwnerE_code");
         String AIOwner = AIOwnerE_code;
         int countai=0;
         isRecordSelectedForAIOwner=selectingTheAIOwner(AIOwner,isRecordSelectedForAIOwner,countai);
         Thread.sleep(3000);
         if(isRecordSelectedForAIOwner)
         {
        	 driver.findElement(By.id("selBtn_InMultiUserSelectDialog")).click();
        	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On select action item Owner",sno,false);
             sno++;
          }
         else
         {
        	 System.out.println("Action Item Owner not selected");
        	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "action item Owner not selected",sno,false);
             sno++;
         }
        
         driver.findElement(By.id("selectApproverInDevQaReview1")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on select action item Approver",sno,false);
         sno++;
         Thread.sleep(2000);
         driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
         Thread.sleep(2000);
         driver.findElement(By.linkText(properties.getProperty("Dept_Level_Text"))).click();
         Thread.sleep(2000);
         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
         boolean isRecordSelectedForAIApprover = false;
         Thread.sleep(3000);
         String AIApproverE_code = properties.getProperty("AIApproverE_code");
         String AIApprover = AIApproverE_code;
         int countAIA=0;
         isRecordSelectedForAIApprover=selectingTheAIApprover(AIApprover,isRecordSelectedForAIApprover,countAIA);
         if(isRecordSelectedForAIApprover)
         {
        	 driver.findElement(By.id("usersSelBtnInDevReview")).click();
        	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On select action item Approver",sno,false);
             sno++; 
         }
         else
         {
        	 System.out.println("Action Item Approver not selected");
        	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "action item Approver not selected",sno,false);
             sno++; 
         }
         driver.findElement(By.id("actItmDueDateInAddActItemDlg")).click();
         Thread.sleep(1000);
         driver.findElement(By.cssSelector("a.ui-state-default")).click();
         Select taskType= new Select(driver.findElement(By.id("actionItemTaskTypeInAddActItemDlg")));
         taskType.selectByIndex(1);
         Select completebefore= new Select(driver.findElement(By.id("cmpltActItemBeforeProceedInAddActItemDlg")));
         completebefore.selectByIndex(1);
         driver.findElement(By.id("actItemDescInAddActItemDlg")).sendKeys(properties.getProperty("ActionItemDescription_2000"));
         driver.findElement(By.id("addBtnInActionItemAddDlg")).click();
         Thread.sleep(2000);        
           driver.findElement(By.id("continueBtn_DevReview")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
        sno++;
        Thread.sleep(5000);
        driver.findElement(By.id("submit_IncReviewStageDetails")).click();
        Thread.sleep(3000);
           driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
           sno++;
           Thread.sleep(2000);
           driver.findElement(By.id("subBtnInValESignInInc")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(2000);
            if(driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).isDisplayed())
            {
            	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false); 
            }
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
           
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
  private boolean selectRecdQAApproveDeviation(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("incReviewTableContailner"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"incReviewTableContailner\"]/div/div[4]/div[2]/span"));
            JavascriptExecutor jsshow = (JavascriptExecutor)driver;
            jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
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
                    	
                    	
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='incReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    	
                        String IncNumberSequence = driver.findElement(By.xpath(".//*[@id='incReviewTableContailner']/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='incReviewTableContailner']/div/table/tbody/tr[ " + i + " ]/td[43]/button")).click();
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
                	
                	WebElement elementnext = driver.findElement(By.cssSelector("#incReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#incReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    table = driver.findElement(By.id("incReviewTableContailner"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
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
//                      System.out.println("cftReviewerFullName: "+AIOwnerecode);
                      if (AIOwner.equalsIgnoreCase(AIOwnerecode)) {
                          driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                          isRecordSelectedForAIOwner = true;
                          break;
                      }
                  }
                  if (isRecordSelectedForAIOwner) {
                      break;
                  }
                 
              } else {
                  String AIOwnerecode = driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                  if (AIOwner.equalsIgnoreCase(AIOwnerecode)) {
                      driver.findElement(By.xpath("//*[@id=\"MultiSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                      isRecordSelectedForAIOwner = true;
                      break;
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
//                      System.out.println("cftReviewerFullName: "+AIOwnerecode);
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

    


    





