package com.pss.qms.CAPAModuleAudittrails;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
 
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class CAPAstatus extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPAstatus"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPAstatus", "PSS-QMS-073",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(3000);
        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
        sno++;
        driver.findElement(By.id("auidtTrailsInCapa")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audittrails Tab",sno,false);
        Thread.sleep(16000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[9]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA status menu",sno,false);
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("capaNumberInCapaStatusAT")).sendKeys(properties.getProperty("CAPA_STATUS_NUMBER"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the capa number",sno,false);
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[2]/button")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button",sno,false);
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("treeContainer_5_span")).click();
        Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select the location",sno,false);
        Thread.sleep(6000);
//        driver.findElement(By.id("fromDateInCapaStatusAT")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(2000);
//        driver.findElement(By.id("toDateInCapaStatusAT")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("srchBtnInCapaStatusAT")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on search button",sno,false);
        Thread.sleep(10000);
        methodToDoCAPAstatus();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

    }

    private void methodToDoCAPAstatus() throws Exception {
    	sno++;
        int count = 0;
        boolean isRecordSelected = false;
////        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String capaSequence = properties.getProperty("CAPA_NUMBER");
////        String canumber    = properties.getProperty("CA_NUMBER");
////        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
////        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + capaSequence;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String CAPAId = "/0015";
        String CAPANumber = properties.getProperty("CAPAId");
        isRecordSelected = selectRecdCAPAstatus(CAPANumber, isRecordSelected, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Seelct the record",sno,false);
        Thread.sleep(10000);
        if (isRecordSelected) {
            Thread.sleep(3000);
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
                        Thread.sleep(6000);
                        sno++;                       
          	    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
          	    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
          	    		Thread.sleep(5000);
          	    		sno++;
          	    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
          	    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);


        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
           }
        }
    }

    private boolean selectRecdCAPAstatus(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("capaStatusAuditMenuDetailsGrid"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[11]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[1]/td[3]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/table/tbody/tr[1]/td[11]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"capaStatusAuditMenuDetailsGrid\"]/div/div[4]/div[1]/span[1]/span[5]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("capaStatusAuditMenuDetailsGrid"));//Document Tree approve table
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
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}

}
