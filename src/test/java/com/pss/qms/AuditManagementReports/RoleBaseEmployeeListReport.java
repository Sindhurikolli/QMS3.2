package com.pss.qms.AuditManagementReports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class RoleBaseEmployeeListReport extends AMLoginDetails {
    
   @Test
    public void RoleBaseEmployeeListReport() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			document = new Document();
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleBaseEmployeeListReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleBaseEmployeeList Report", "PSS-QMS-001",
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
        driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"reportsInAM\"]/div/a/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
        sno++;
         driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[15]/a")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RoleBaseEmployeeList Report Menu",sno,false);
         sno++;
       driver.findElement(By.id("locSelBtnInRoleBaseEmployeesListReport")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false);
       sno++;
       driver.findElement(By.id("treeContainer_2_switch")).click();
       Thread.sleep(2000);
       driver.findElement(By.id("treeContainer_3_span")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
       sno++;
       driver.findElement(By.id("selectBtnInLocSelect")).click();
       Thread.sleep(8000);
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        methodToDoRoleBaseEmployeeListReport();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    

    private void methodToDoRoleBaseEmployeeListReport() throws Exception {
      
 
             String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(3000);
                sno++;
                driver.findElement(By.id("viewBtnInRoleBaseEmployeesListReport")).click();
                document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View Button",sno,false);
                Thread.sleep(12000);
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
                	        driver.findElement(By.className("username")).click();
                	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
                	        sno++;
                	        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
                	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
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