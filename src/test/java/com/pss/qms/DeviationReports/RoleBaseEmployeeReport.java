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
import org.openqa.selenium.JavascriptExecutor;
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
public class RoleBaseEmployeeReport extends DeviationLoginDetails {
   
    @Test
    public void RoleBaseEmployeeReport() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleBaseEmployeeReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleBaseEmployeeReport", "PSS-QMS-001", "Pass");
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
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.id("reportsInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RoleBaseEmployeeReport Menu",sno,false);
        Thread.sleep(10000);
//         driver.findElement(By.id("Deviation Number")).sendKeys(properties.getProperty("Deviation_Number1"));
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("locSelBtn_DeviationRepSearch")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.xpath("treeContainer_2_switch")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("treeContainer_3_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("selectBtnInLocSelect")).click();
//        Thread.sleep(5000);
//        Select changetype = new Select(driver.findElement(By.id("severity_DeviationRepSearch")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
//         driver.findElement(By.id("fromDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//         driver.findElement(By.id("toDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("documentChk_DeviationRepSearch")).click();
//        Thread.sleep(5000);
        sno++;
          driver.findElement(By.id("selectBtnForLocation")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false);
          Thread.sleep(10000);
          sno++;
          driver.findElement(By.id("treeContainer_2_switch")).click();
          Thread.sleep(3000);
          driver.findElement(By.id("treeContainer_3_span")).click();
          Thread.sleep(1000);
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
          sno++;
          driver.findElement(By.id("selectBtnInLocSelect")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          Thread.sleep(10000);
          sno++;
          driver.findElement(By.id("selectBtnForReport")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Report Button",sno,false);
          Thread.sleep(10000);
        methodToDoRoleBaseEmployeeReport();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        }

    private void methodToDoRoleBaseEmployeeReport() throws Exception {
//        int count = 0;
//        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEVIATION_NO");
//        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + DepartmentCode;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "-0018";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        isRecordSelected = selectRecdRoleBaseEmployeeReport(chgControlNumber, isRecordSelected, count);
//        if (isRecordSelected) {
            Thread.sleep(10000);
            String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(8000);
//                driver.findElement(By.id("")).click();
//                Thread.sleep(6000);
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
    @AfterMethod
    public void testIT(ITestResult result)
    {
    	if(ITestResult.FAILURE==result.getStatus())
    	{
    		Utility.captureScreenshot(driver, result.getName());
    	}
    	
    }
    } 
        
    

  