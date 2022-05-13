package com.pss.qms.AuditManagementExternalScheduleWithCAPA;

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

public class PlanExistingCAPA extends AMLoginDetails {

    @Test
    public void methodtodoPlanExistingCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
	  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlanExistingCAPA"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlanExistingCAPA", "PSS-QMS-007",
					"Pass");
			writer.setPageEvent(event);
			document.open();
      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
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
          driver.findElement(By.xpath("//*[@id=\"capa_tile_Id\"]/div/div/div/h2")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Module",sno,false);
         sno++;
         driver.findElement(By.id("myActivitiesInCapa")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
         sno++;
         driver.findElement(By.id("existingCapaPlanInCapa")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Existing CAPA RadioButton",sno,false);  
        todoPlanExistingCAPA();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        }
    

    private void todoPlanExistingCAPA() throws Exception {
      
    	 int count = 0;
         boolean isRecordSelected = false;
         String CAPANumber = properties.getProperty("CAPA_NUMBER_AM");
         isRecordSelected = selectRecdExistingCAPA(CAPANumber, isRecordSelected, count);
         if (isRecordSelected) {
        	 sno++;
             driver.findElement(By.id("continueBtnIdInCreateCapaPlan")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Continue Button",sno,false);  
             sno++;
             driver.findElement(By.xpath("//*[@id=\"capaCaDetailsContainer\"]/div/table/tbody/tr/td[12]/button")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button1",sno,false);  
//             sno++;
//             Date date = new Date();
//             String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
//             driver.findElement(By.id("dueDateInCaPaPlanWndw")).sendKeys(todaysDate);
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Date",sno,false);  
             driver.findElement(By.xpath("//*[@id=\"addDueDateInCaPaPlanWndw\"]/div[4]")).click();
             sno++;
             driver.findElement(By.id("ownerSelectBtnInCaPaPlanWndw")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false); 
             sno++;
             driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
             Thread.sleep(2000);
             driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click(); 
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false); 
             boolean isRecordSelectedForUser = false;
             String UserFirstName = properties.getProperty("RES_PERSON");
           String UserFullName = UserFirstName;
           int count1=0;
           isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
           sno++;
           driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false); 
           sno++;
            Select TaskType = new Select(driver.findElement(By.id("taskTypeInCaPaPlanWndw")));
            TaskType.selectByIndex(4);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The TaskType",sno,false); 
            sno++;
             driver.findElement(By.id("addBtnInCaPaDueDtWndw")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
             
             sno++;
             driver.findElement(By.xpath("//*[@id=\"capaPaDetailsContainer\"]/div/table/tbody/tr/td[12]/button/i")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Button2",sno,false);  
//             sno++;
//             Date date1 = new Date();
//             String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
//             driver.findElement(By.id("dueDateInCaPaPlanWndw")).sendKeys(todaysDate1);
//             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Date",sno,false);  
             driver.findElement(By.xpath("//*[@id=\"addDueDateInCaPaPlanWndw\"]/div[4]")).click();
             sno++;
             driver.findElement(By.id("ownerSelectBtnInCaPaPlanWndw")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false); 
             sno++;
             driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
             Thread.sleep(2000);
             driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click(); 
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false); 
           isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
           sno++;
           driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false); 
           sno++;
            Select TaskType1 = new Select(driver.findElement(By.id("taskTypeInCaPaPlanWndw")));
            TaskType1.selectByIndex(4);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The TaskType",sno,false); 
            sno++;
             driver.findElement(By.id("addBtnInCaPaDueDtWndw")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);  
             JavascriptExecutor jse = (JavascriptExecutor) driver;
             WebElement element = driver.findElement(By.id("submitIdInCreateCapaPlan"));
             jse.executeScript("arguments[0].scrollIntoView(true);", element);
             Thread.sleep(1000);
             sno++;
             Select HOD = new Select(driver.findElement(By.id("hodWorkFlowUserInCapaPlan")));
             HOD.selectByIndex(4);
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The HOD",sno,false); 
             sno++;
             Select QA_Reviewer = new Select(driver.findElement(By.id("qaWorkFlowUserInCapaPlan")));
             QA_Reviewer.selectByIndex(3);
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Reviewer",sno,false);
             sno++;
             Thread.sleep(5000);
             driver.findElement(By.id("submitIdInCreateCapaPlan")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
             WebDriverWait wait = new WebDriverWait(driver, 60);
             sno++;
             wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
             sno++;
             driver.findElement(By.id("subBtnInValidateESign")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
             sno++;
             driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-buttons > a")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
             sno++;
             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
             sno++;
             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
             }
       

    }


	private boolean selectingTheUserReview(String userFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
	
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
	            if ((totalNoOfRecords > 1) && ((userFullName == null) || ("".equalsIgnoreCase(userFullName)))) {
	            	userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((userFullName == null) || ("".equalsIgnoreCase(userFullName))) {
	            	userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count1;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String UserNumberSequence = driver.findElement(By.xpath(".//*[@id='usersTableContainer']/div/table/tbody/tr[ " + i + "]/td[1]")).getText();//documentTypeName
	                        if (userFullName.equalsIgnoreCase(UserNumberSequence)) {
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
	                    if (userFullName.equalsIgnoreCase(UserNumberSequence)) {
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
	


	private boolean selectRecdExistingCAPA(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
		  WebElement table = driver.findElement(By.id("capaDetailsTableContainerInCapa"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
	            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
	            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='capaDetailsTableContainerInCapa']/div/table/tbody/tr[ " + i + "]/td[4]")).getText();//documentTypeName
	                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).click();
	                            isRecordSelected = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelected) {
	                        break;
	                    }
	                } else {
	                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[4]")).getText();
	                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[4]")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#capaDetailsTableContainerInCapa > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("capaDetailsTableContainerInCapa"));//Document Tree approve table
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

	

