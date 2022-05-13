package com.pss.qms.DeviationReports;

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
 
import com.pss.qms.login.DeviationLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class PeriodicReviewReport extends DeviationLoginDetails {
  
	 @Test
	    public void PeriodicReviewReport() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
				Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PeriodicReviewReport"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);

				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("PeriodicReviewReport", "PSS-QMS-001", "Pass");
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
	        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Periodic Review Report",sno,false);
	        Thread.sleep(10000);
//	         driver.findElement(By.id("Deviation Number")).sendKeys(properties.getProperty("Deviation_Number1"));
//	        Thread.sleep(10000);
//	        driver.findElement(By.xpath("locSelBtn_DeviationRepSearch")).click();
//	        Thread.sleep(4000);
//	        driver.findElement(By.xpath("treeContainer_2_switch")).click();
//	        Thread.sleep(3000);
//	        driver.findElement(By.xpath("treeContainer_3_span")).click();
//	        Thread.sleep(3000);
//	        driver.findElement(By.xpath("selectBtnInLocSelect")).click();
//	        Thread.sleep(5000);
//	        Select changetype = new Select(driver.findElement(By.id("severity_DeviationRepSearch")));
//	        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//	        Thread.sleep(4000);
//	         driver.findElement(By.id("fromDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//	        Thread.sleep(4000);
//	         driver.findElement(By.id("toDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//	        Thread.sleep(4000);
//	        driver.findElement(By.id("documentChk_DeviationRepSearch")).click();
//	        Thread.sleep(5000);
	        sno++;
	          driver.findElement(By.id("selLocBtn_DevPeriodicRevRepPage")).click();
	          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
	          Thread.sleep(10000);
	          sno++;
	          driver.findElement(By.id("plantOnlyLocTreeContainer_2_span")).click();
	          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
	          Thread.sleep(10000);
	          sno++;
	          driver.findElement(By.id("selectBtnInLocSelect")).click();
	          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
	          Thread.sleep(10000);
	          sno++;
	          driver.findElement(By.id("viewReportBtn_DevPeriodicRevRepPage")).click();
	          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On ViewReport Button",sno,false);
	          Thread.sleep(10000);
	        methodToDoPeriodicReviewReport();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	    }

	    private void methodToDoPeriodicReviewReport() throws Exception {
//	        int count = 0;
//	        boolean isRecordSelected = false;
//	        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////	        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//	        String DepartmentCode = properties.getProperty("DEVIATION_NO");
//	        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + DepartmentCode;
//	        Date date = new Date();
//	        String sdf = new SimpleDateFormat("YY").format(date);
//	        String chgCtrlId = "-0018";
//	        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//	        isRecordSelected = selectRecdRoleBaseEmployeeReport(chgControlNumber, isRecordSelected, count);
	        Thread.sleep(15000);
//	        if (isRecordSelected) {
	            Thread.sleep(10000);
	            String applicationWindowOrPDF = driver.getWindowHandle();
	                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
	                Thread.sleep(8000);
//	                driver.findElement(By.id("")).click();
//	                Thread.sleep(6000);
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
	        
	    

	  

