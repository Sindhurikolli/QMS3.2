
package com.pss.qms.MarketComplaintsReports;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvestigationReportMC extends MCLoginDetails {
    
      @Test
    public void InvestigationReports() throws InterruptedException {
        Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
          Thread.sleep(10000);
          driver.findElement(By.id("reportsInMC")).click();
          Thread.sleep(10000);
          driver.findElement(By.xpath("//*[@id=\"mcInvestigationReportId\"]/a")).click();
          Thread.sleep(10000);
//          driver.findElement(By.id("invNumInInvRepForm")).sendKeys(properties.getProperty("mc_number01"));
//          Thread.sleep(3000);
          driver.findElement(By.id("searchBtn_McInvReportPage")).click();
          Thread.sleep(10000);
          driver.findElement(By.xpath("//*[@id=\"mcInvestgationReportTable\"]/div/table/tbody/tr[9]/td[3]")).click();
          Thread.sleep(10000);
//          driver.findElement(By.xpath("//*[@id=\"mcInvestgationReportTable\"]/div/table/tbody/tr[9]/td[9]/button")).click();
//          Thread.sleep(15000);
            String applicationWindowOrPDF = driver.getWindowHandle();
//          driver.findElement(By.id("viewTrendBtnInMcCompleted")).click();
//            Thread.sleep(8000);    
//            String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(8000);
              driver.findElement(By.xpath("//*[@id=\"mcInvestgationReportTable\"]/div/table/tbody/tr[9]/td[9]/button")).click();
          Thread.sleep(15000);
                applicationWindowOrPDF = driver.getWindowHandle();
                Thread.sleep(8000);
                System.out.println("pdfWindow: " + applicationWindowOrPDF);
                Thread.sleep(15000);
                String indepPDFWindow = driver.getWindowHandle();
                System.out.println("indepPDFWindow: " + indepPDFWindow);
                Set <String> pdfWindow = driver.getWindowHandles();
                //Iterator iterator = pdfWindow.iterator();
               for( String pdfWindows : pdfWindow)  {
                      System.out.println(" pdfWindows: " +pdfWindows);
                        if(!applicationWindowOrPDF.equalsIgnoreCase(pdfWindows))   {
                            driver.switchTo().window(pdfWindows);
                            Thread.sleep(6000);
                            //driver.manage().timeouts().implicitlyWait(0, MILLISECONDS);
                            driver.close();
                            Thread.sleep(4000);
                            driver.switchTo().window(applicationWindowOrPDF);
                            Thread.sleep(8000);
                            
                        
                    
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
            
   }
      @AfterMethod
  	public void testIT(ITestResult result)

  	{
  		if (ITestResult.FAILURE == result.getStatus()) {
  			Utility.captureScreenshot(driver, result.getName());
  		}

  	}
}
          
          
          
          
          
          
          
          
          
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("documentChkInCompRepForm")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("searchBtn_McCompleteReportPage")).click();
//        Thread.sleep(5000);;

    

   


                


            
        
    

  