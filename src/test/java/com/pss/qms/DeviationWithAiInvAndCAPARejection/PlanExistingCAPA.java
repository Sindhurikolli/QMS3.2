package com.pss.qms.DeviationWithAiInvAndCAPARejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.DeviationLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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
public class PlanExistingCAPA extends DeviationLoginDetails {
    
	@Test
    public void toPlanExistingCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//    	try {

    		document = new Document(PageSize.A4, 36, 36, 20, 20);
    		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
    		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlanExistingCAPA"
    				+ (new Random().nextInt()) + ".pdf";
    		fos = new FileOutputStream(output);

    		writer = PdfWriter.getInstance(document, fos);

    		writer.open();
    		HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlanExistingCAPA", "PSS-QMS-001", "Pass");
    		writer.setPageEvent(event);
    		document.open();
    		WebDriverWait wait1 = new WebDriverWait(driver, 240);
    		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CAPAInitiator"));
    		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
    		Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText("CAPA");
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
    		
    		Thread.sleep(3000);
    		im = Image.getInstance(input);
    		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
    				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
    		document.add(new Paragraph(sno + "." + "Enter the username, password, Select CAPA Module and click on login button"
    				+ Utilities.prepareSSNumber(sno), font));
    		document.add(im);
    		document.add(new Paragraph("                                     "));
    		document.add(new Paragraph("                                     "));
    		sno++; 
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='createCapaPlanPage.do']")));
        driver.findElement(By.cssSelector("a[href='createCapaPlanPage.do']")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("existingCapaPlanInCapa")).click();
        Thread.sleep(2000);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaDetailsTableContainerInCapa > div > div.jtable-busy-message[style='display: none;']")));
        methodToPlanExisingCAPA();
        document.close();
    	writer.close();
    	Desktop desktop = Desktop.getDesktop();
    	File file = new File(output);
    	//desktop.open(file);
//    } catch (Exception e) {
//    	e.printStackTrace();
//    }            
    }

    private void methodToPlanExisingCAPA() throws Exception {
    	 WebDriverWait wait = new WebDriverWait(driver, 70);
        int count = 0;
        boolean isRecordSelected = false;   
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
        isRecordSelected = selectRecdExistingCAPA(DeviationNumber, isRecordSelected, count);
        if (isRecordSelected) {           
           sno++;
            driver.findElement(By.id("continueBtnIdInCreateCapaPlan")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On continue Button",sno,false);
            sno++;
//            SimpleDateFormat formattedDate = new SimpleDateFormat("d/M/yyyy");
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, 30); // number of days to add 
//            String futureDate = (String) (formattedDate.format(c.getTime()));
//            driver.findElement(By.id("capaTaskDueDateInCapReview")).sendKeys(futureDate);
            Thread.sleep(5000);
            driver.findElement(By.id("dueDateInCreateCapaPlanDlg")).click();
            sno++;
            String todaydate=driver.findElement(By.className("ui-datepicker-today")).getText();
            System.out.println(todaydate);
            int daysafter = Integer.parseInt(properties.getProperty("DutedateAfterhowmanyDays"));
            int newdate=Integer.parseInt(todaydate)+daysafter;
            System.out.println(newdate);
             List<WebElement> dates=driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
            int daysremaininginmonth=dates.size();
			
            String LastDayOfMonth=dates.get(daysremaininginmonth-1).getText();
             System.out.println(LastDayOfMonth);
             if(newdate<=Integer.parseInt(LastDayOfMonth))
             {
             	List<WebElement> days=driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
             	days.get(daysafter).click();          	
             }
             else
             {
             	int indextoselect = daysafter-daysremaininginmonth; 
             	System.out.println(indextoselect);
             	driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
             	Thread.sleep(5000);
             	List<WebElement> days1=driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
             	days1.get(indextoselect).click();
             }


            
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Due Date",sno,false);
            sno++;
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,500)");
            driver.findElement(By.xpath("//*[@id=\"capaCaDetailsContainer\"]/div/table/tbody/tr/td[11]/button/i")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select edit button for Corrective action",sno,false);
            sno++;
            Thread.sleep(2000);
//            driver.findElement(By.id("dueDateInCaPaPlanWndw")).click();
            driver.findElement(By.className("ui-datepicker-today")).click();
            
            driver.findElement(By.id("ownerSelectBtnInCaPaPlanWndw")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
            Thread.sleep(2000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
             boolean isRecordSelectedForResponsiblePersonCA = false;
            String CAPerson =properties.getProperty("CAPAResponsiblePersonE-code");
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User",sno,false);
//            String InvLastName =properties.getProperty("UserName");
            String ResponsiblePersonCA = CAPerson;
            int count4=0;
            isRecordSelectedForResponsiblePersonCA = selectingTheResponsiblePersonCA(ResponsiblePersonCA,isRecordSelectedForResponsiblePersonCA, count4);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            Select TaskType = new Select(driver.findElement(By.id("taskTypeInCaPaPlanWndw")));
            TaskType.selectByIndex(3);
            Thread.sleep(2000);
            driver.findElement(By.id("addBtnInCaPaDueDtWndw")).click();
            js.executeScript("window.scrollBy(0,1000)");
            driver.findElement(By.xpath("//*[@id=\"capaPaDetailsContainer\"]/div/table/tbody/tr/td[11]/button/i")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select edit button for Preventive action",sno,false);
            sno++;
            Thread.sleep(2000);
//            driver.findElement(By.id("dueDateInCaPaPlanWndw")).click();
            driver.findElement(By.className("ui-datepicker-today")).click();
            
            driver.findElement(By.id("ownerSelectBtnInCaPaPlanWndw")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
            Thread.sleep(2000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
             boolean isRecordSelectedForResponsiblePersonPA = false;
            String PAPerson =properties.getProperty("CAPAResponsiblePersonE-code");
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User",sno,false);
//            String InvLastName =properties.getProperty("UserName");
            String ResponsiblePersonPA = PAPerson;
            int count5=0;
            isRecordSelectedForResponsiblePersonPA = selectingTheResponsiblePersonPA(ResponsiblePersonPA,isRecordSelectedForResponsiblePersonPA, count5);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            TaskType.selectByIndex(3);
            Thread.sleep(2000);
            driver.findElement(By.id("addBtnInCaPaDueDtWndw")).click();
            js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#createCapaPlanWindowMainDiv > div:nth-child(5) > div:nth-child(2) > div.form-group.col-sm-4 > div.radio_false_disable > label")).click();
			
         //   driver.findElement(By.id("suppDocYesInCapaPlan")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("capaAddSuppDocs")).click();
            driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
            sno++;
            driver.findElement(By.id("submitIdInCreateCapaPlan")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
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

    private boolean selectRecdExistingCAPA(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("capaDetailsTableContainerInCapa"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	 WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/div[4]/div[2]/span"));
             JavascriptExecutor jsshow = (JavascriptExecutor)driver;
             jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
             
            String a = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	// WebElement elementsele = driver.findElement(By.xpath(".//*[@id='capaDetailsTableContainerInCapa']/div/table/tbody/tr[ " + i + "]/td[3]"));
                        // JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        // jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                        
                    	
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
//                	WebElement elementsele = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[3]"));
//                    JavascriptExecutor jssel = (JavascriptExecutor)driver;
//                    jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[3]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"capaDetailsTableContainerInCapa\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	// WebElement elementnext = driver.findElement(By.cssSelector("#capaDetailsTableContainerInCapa > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    // JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    // jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#capaDetailsTableContainerInCapa > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
					WebDriverWait wait1 = new WebDriverWait(driver, 240);
					wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaDetailsTableContainerInCapa > div > div.jtable-busy-message[style='display: none;']")));
                    table = driver.findElement(By.id("capaDetailsTableContainerInCapa"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
    }

    private boolean selectingTheResponsiblePersonCA(String ResponsiblePersonCA, boolean isRecordSelectedForResponsiblePersonCA, int count4) throws InterruptedException{
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
            if (((ResponsiblePersonCA == null) || ("".equalsIgnoreCase(ResponsiblePersonCA)))) {
            	ResponsiblePersonCA = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((ResponsiblePersonCA == null) || ("".equalsIgnoreCase(ResponsiblePersonCA))) {
            	ResponsiblePersonCA = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (ResponsiblePersonCA.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForResponsiblePersonCA = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (ResponsiblePersonCA.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForResponsiblePersonCA = true;
                        
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForResponsiblePersonCA) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelectedForResponsiblePersonCA;
    }
    
    private boolean selectingTheResponsiblePersonPA(String ResponsiblePersonPA, boolean isRecordSelectedForResponsiblePersonPA, int count5) throws InterruptedException{
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
            if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
            if (((ResponsiblePersonPA == null) || ("".equalsIgnoreCase(ResponsiblePersonPA)))) {
            	ResponsiblePersonPA = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((ResponsiblePersonPA == null) || ("".equalsIgnoreCase(ResponsiblePersonPA))) {
            	ResponsiblePersonPA = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType

            }
            ++count5;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (ResponsiblePersonPA.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForResponsiblePersonPA = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (ResponsiblePersonPA.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForResponsiblePersonPA = true;
                        
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForResponsiblePersonPA) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelectedForResponsiblePersonPA;
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


    


  



