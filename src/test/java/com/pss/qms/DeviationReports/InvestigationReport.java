package com.pss.qms.DeviationReports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.DeviationLoginDetails;
 
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
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class InvestigationReport extends DeviationLoginDetails {
    
     @Test
    public void DeviationReports() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvestigationReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvestigationReport", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;   	
    	 
       Thread.sleep(2000);
       sno++;
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.id("reportsInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Reports Menu",sno,false);
        Thread.sleep(10000);
//      driver.findElement(By.id("investNumInInvestRepForm")).sendKeys(properties.getProperty("Deviation_Number1"));
//      Thread.sleep(10000);
        sno++;
        driver.findElement(By.id("searchBtnInInvestRepForm")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
        Thread.sleep(5000);
        methodToDoDeviationReports();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }

    private void methodToDoDeviationReports() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEVIATION_NO");
//        String chgCtrlNoWithPlantCode = DepartmentCode + "/" ;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0042";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        String DevId = properties.getProperty("DEVIATION_NO_REPORTS");
        String DeviationNumber = DevId;
        isRecordSelected = selectRecdDeviationReports(DeviationNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            Thread.sleep(8000); 
             String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(8000);
                applicationWindowOrPDF = driver.getWindowHandle();
                Thread.sleep(8000);
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
                            driver.findElement(By.className("username")).click();
                            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
                            sno++;
                            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
                            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,false);
          
       }
               }
       }
            }
    private boolean selectRecdDeviationReports(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("investigationReportTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
            	DeviationNumber = driver.findElement(By.xpath(".//*[@id=\"investigationReportTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
            	DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/table/tbody/tr[ " + i + " ]/td[9]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/table/tbody/tr/td[4]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"investigationReportTable\"]/div/table/tbody/tr/td[9]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath(".//*[@id='investigationReportTable']/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("investigationReportTable"));//Document Tree approve table
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


