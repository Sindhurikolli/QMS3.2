package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInv;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.IncidentManagementLoginDetails;
import com.pss.qms.login.IncidentManagementLoginDetails;
 
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
public class DeptHODReviewCAPAfromInc extends IncidentManagementLoginDetails {
    
	@Test
    public void DeptHODCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//    	try {

    		document = new Document(PageSize.A4, 36, 36, 20, 20);
    		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
    		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeptHODReviewCAPAfromInc"
    				+ (new Random().nextInt()) + ".pdf";
    		fos = new FileOutputStream(output);

    		writer = PdfWriter.getInstance(document, fos);

    		writer.open();
    		HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeptHODReviewCAPAfromInc", "PSS-QMS-001", "Pass");
    		writer.setPageEvent(event);
    		document.open();
    		WebDriverWait wait1 = new WebDriverWait(driver, 240);
    		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CAPADepartmentHod"));
    		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
    		Select module= new Select(driver.findElement(By.id("qmsModule")));
    		module.selectByVisibleText(properties.getProperty("CAPAModule"));
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
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='capaReviewPage.do']")));
        driver.findElement(By.cssSelector("a[href='capaReviewPage.do']")).click();
        Thread.sleep(5000);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));
        methodToDoDeptHODCAPA();
        document.close();
    	writer.close();
    	Desktop desktop = Desktop.getDesktop();
    	File file = new File(output);
    	//desktop.open(file);
//    } catch (Exception e) {
//    	e.printStackTrace();
    }            
//    }

    private void methodToDoDeptHODCAPA() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;   
        String IncidentNumber = properties.getProperty("IncidentNo");
        isRecordSelected = selectRecdDeptHODCAPA(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {           
           sno++;
           JavascriptExecutor jse = (JavascriptExecutor) driver;
           jse.executeScript("window.scrollBy(0, 1000)");
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            Thread.sleep(5000);
            driver.findElement(By.id("capaTaskDueDateInCapReview")).click();
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
             	System.out.println(indextoselect+1);
             	driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
             	Thread.sleep(5000);
             	List<WebElement> days1=driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
             	days1.get(indextoselect).click();
             }


            
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Due Date",sno,false);
            sno++;
            driver.findElement(By.id("selBtnForcapaTaskResponsePersonCapReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
            Thread.sleep(2000);
//            driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
            
//            WebElement element1 = driver.findElement(By.id("locTreeInQmsProdReg_3_span"));
//            JavascriptExecutor js = (JavascriptExecutor)driver;
//            js.executeScript("arguments[0].click();", element1);  
//            Thread.sleep(2000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
             boolean isRecordSelectedForResponsiblePerson = false;
            String ECode =properties.getProperty("CAPAResponsiblePersonE-code");
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The User",sno,false);
//            String InvLastName =properties.getProperty("UserName");
            String EmployeeCode = ECode;
            int count4=0;
            isRecordSelectedForResponsiblePerson = selectingTheResponsiblePersonCAPA(EmployeeCode,isRecordSelectedForResponsiblePerson, count4);
            sno++;
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            jse1.executeScript("window.scrollBy(0, 400)");
            driver.findElement(By.id("capaAddSuppDocsDept")).click();
            driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
            
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            jse1.executeScript("window.scrollBy(0, 400)");
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("CAPADhodComments_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            sno++;
            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
             WebDriverWait wait = new WebDriverWait(driver, 70);
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

    private boolean selectRecdDeptHODCAPA(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("capaReviewTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	 WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span"));
             JavascriptExecutor jsshow = (JavascriptExecutor)driver;
             jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
             
            String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[9]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                        
                    	
                        String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[9]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[9]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
//                	WebElement elementsele = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[9]"));
//                    JavascriptExecutor jssel = (JavascriptExecutor)driver;
//                    jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[9]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[9]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	WebElement elementnext = driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("capaReviewTable"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
    }

    private boolean selectingTheResponsiblePersonCAPA(String EmployeeCode, boolean isRecordSelectedForResponsiblePerson, int count4) throws InterruptedException {
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
        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
        if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
            if (((EmployeeCode == null) || ("".equalsIgnoreCase(EmployeeCode)))) {
                EmployeeCode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((EmployeeCode == null) || ("".equalsIgnoreCase(EmployeeCode))) {
                EmployeeCode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (EmployeeCode.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForResponsiblePerson = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (EmployeeCode.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForResponsiblePerson = true;
                        
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForResponsiblePerson) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelectedForResponsiblePerson;
    }
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}


    


  



