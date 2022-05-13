
package com.pss.qms.AuditManagementReports;

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
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class InternalAuditObservationsReport extends AMLoginDetails {
    
   @Test
    public void InternalAuditObservationsReport() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			document = new Document();
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InternalAuditObservationsReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("InternalAuditObservations Report", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
    	driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName_AM"));
        driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
        input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
        Thread.sleep(3000);
        im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
		document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
        driver.findElement(By.xpath	("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"reportsInAM\"]/div/a/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on InternalAudit Obs Report",sno,false);
        sno++;
        driver.findElement(By.id("fromDateInAmReports")).sendKeys(properties.getProperty("START_DATE"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Start Date",sno,false);
        sno++;
        Date date = new Date();
        String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        Thread.sleep(3000);
        driver.findElement(By.id("toDateInAmReports")).sendKeys(todaysDate);
        Thread.sleep(3000);
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter To Date",sno,false);
        sno++;
        driver.findElement(By.id("searchBtnInAmReports")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Search Button",sno,false);
        methodToDoInternalAuditObservationsReport();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    

    private void methodToDoInternalAuditObservationsReport() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String AuditNumber = properties.getProperty("AUDIT_NUMBER_IN_REPORT");
        isRecordSelected = selectRecdInternalAuditObservationsReport(AuditNumber,isRecordSelected, count);
        if (isRecordSelected) {
//            driver.findElement(By.id("reportBtnInAmReports")).click();
//            Thread.sleep(3000);
             String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(3000);
                sno++;
                 driver.findElement(By.id("reportBtnInAmReports")).click();
                 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Reports",sno,false);
                Thread.sleep(3000);
                applicationWindowOrPDF = driver.getWindowHandle();
                Thread.sleep(2000);
                System.out.println("pdfWindow: " + applicationWindowOrPDF);
                Thread.sleep(12000);
                String indepPDFWindow = driver.getWindowHandle();
                System.out.println("indepPDFWindow: " + indepPDFWindow);
                Set <String> pdfWindow = driver.getWindowHandles();
                //Iterator iterator = pdfWindow.iterator();
               for( String pdfWindows : pdfWindow)  {
                      System.out.println(" pdfWindows: " +pdfWindows);
                        if(!applicationWindowOrPDF.equalsIgnoreCase(pdfWindows))   {
                            driver.switchTo().window(pdfWindows);
                            Thread.sleep(2000);
                            //driver.manage().timeouts().implicitlyWait(0, MILLISECONDS);
                            driver.close();
                            Thread.sleep(3000);
                            driver.switchTo().window(applicationWindowOrPDF);
                            Thread.sleep(2000);
                            sno++;
                	        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
                	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
                	        sno++;
                	        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
                	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
                        }
                    }
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdInternalAuditObservationsReport(String AuditNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("internalAuditObserDetailsTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((AuditNumber == null) || ("".equalsIgnoreCase(AuditNumber)))) {
            	AuditNumber = driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AuditNumber == null) || ("".equalsIgnoreCase(AuditNumber))) {
            	AuditNumber = driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AuditNumberSequence = driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
                        if (AuditNumber.equalsIgnoreCase(AuditNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String AuditNumberSequence = driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr/td[5]")).getText();
                    if (AuditNumber.equalsIgnoreCase(AuditNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"internalAuditObserDetailsTable\"]/div/table/tbody/tr/td[5]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#internalAuditObserDetailsTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("internalAuditObserDetailsTable"));//Document Tree approve table
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

 
 














