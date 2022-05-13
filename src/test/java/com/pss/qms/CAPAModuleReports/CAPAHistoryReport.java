package com.pss.qms.CAPAModuleReports;

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

public class CAPAHistoryReport extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPAHistoryReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPAHistoryReport", "PSS-QMS-064",
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
      driver.findElement(By.id("reportsInCapa")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Reports Tab",sno,false);
      Thread.sleep(20000);
      sno++;
      driver.findElement(By.cssSelector("#capaHistoryReportMenuId > a")).click(); 
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on capa History report",sno,false);      
      Thread.sleep(10000);
      sno++;
      driver.findElement(By.id("deptSelBtn_CapaHistoryReportSearch")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button",sno,false);
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_5_span")).click();
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("capaNo_CapaHistoryReportSearch")).sendKeys(properties.getProperty("CAPA_REPORT_NO"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the capa number",sno,false);
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("searchBtn_CapaHistoryReportSearch")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on search button",sno,false);
        Thread.sleep(4000);
        
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.id("saveAsExcelBtn_DeviationRepPage"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(8000);
      methodToDoCAPAHistoryReports();
      Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
  }

  private void methodToDoCAPAHistoryReports() throws Exception {
	  sno++;
      int count = 0;
      boolean isRecordSelected = false;
//      String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//      String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//      String DepartmentCode = properties.getProperty("CAPA_NUMBER");
//      String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + DepartmentCode;
//      Date date = new Date();
//      String sdf = new SimpleDateFormat("YY").format(date);
//      String CAPAId = "/0014";
      String CAPANumber = properties.getProperty("CAPA_REPORT_NO");
      isRecordSelected = selectRecdCAPAHistoryReports(CAPANumber, isRecordSelected, count);
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record",sno,false);
      Thread.sleep(15000);
      if (isRecordSelected) {
          
          Thread.sleep(10000);
          String applicationWindowOrPDF = driver.getWindowHandle();
              System.out.println(" applicationWindow: " + applicationWindowOrPDF);
              Thread.sleep(10000);
              sno++;
              driver.findElement(By.id("viewReportBtn_CapaHistoryReportPage")).click();
              document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on view Report button",sno,false);
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
                          Thread.sleep(5000);
                          //driver.manage().timeouts().implicitlyWait(0, MILLISECONDS);
                          driver.close();
                          Thread.sleep(5000);
                          driver.switchTo().window(applicationWindowOrPDF);
                          Thread.sleep(5000);
                          driver.findElement(By.id("myActivitiesInCapa")).click();
                          Thread.sleep(5000);
                          sno++;                       
                  		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
                  		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
                  		Thread.sleep(5000);
                  		sno++;
                  		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
                  		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
        
     }


             }
      }
      
  }
private boolean selectRecdCAPAHistoryReports(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
      WebElement table = driver.findElement(By.id("recordsTable_CapaHistoryReportPage"));
      WebElement tableBody = table.findElement(By.tagName("tbody"));
      int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
      int totalNoOfRecords = 0;
      int noOfRecordsChecked = 0;
      if (perPageNoOfRecordsPresent > 0) {
          String a = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
          String[] parts = a.split(" of ");
          try {
              totalNoOfRecords = Integer.parseInt(parts[1].trim());
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }
      if (perPageNoOfRecordsPresent > 0 && count == 0) {
          if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
              CAPANumber = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr/td[3]")).getText();//documentType
          } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
              CAPANumber = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr/td[3]")).getText();//documentType

          }
          ++count;
      }
      if (perPageNoOfRecordsPresent > 0) {
          while (noOfRecordsChecked < totalNoOfRecords) {
              if (totalNoOfRecords > 1) {
                  for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                      String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                      if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                          driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                          isRecordSelected = true;
                          break;
                      }
                  }
                  if (isRecordSelected) {
                      break;
                  }
              } else {
                  String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr/td[3]")).getText();
                  if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                      driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaHistoryReportPage\"]/div/table/tbody/tr/td[3]")).click();
                      isRecordSelected = true;     
                      break;
                  }
              }
              noOfRecordsChecked += perPageNoOfRecordsPresent;
              if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                  driver.findElement(By.cssSelector("#capaRecodsTable_CapaStatusReportPage > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number.jtable-page-number-active.jtable-page-number-disabled")).click();//next page in Document approve list
                  Thread.sleep(3000);
                  table = driver.findElement(By.id("capaRecodsTable_CapaStatusReportPage"));//Document Tree approve table
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
