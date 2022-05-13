
package com.pss.qms.MarketComplaintsReports;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.MCLoginDetails;

import java.util.Set;
import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MCPieTrends extends MCLoginDetails {
    
    @Test
    public void MCReports() throws InterruptedException {
        Thread.sleep(8000);
       driver.findElement(By.xpath("//*[@id=\"marketComplaints_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInMC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"mcTrendsListMenuId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"mcTrendsListMenuId\"]/ul/li[2]/a")).click();
        Thread.sleep(9000);
        driver.findElement(By.id("locSelBtn_McPieTrendRepTSearch")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_3_ico")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(5000);
//        driver.findElement(By.id("invNumInInvRepForm")).sendKeys(properties.getProperty("MC_NUMBER2"));
//        Thread.sleep(3000);
//         Select changetype = new Select(driver.findElement(By.id("serverity_McHistoryReportSearch")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("mcTrendFromDate")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("mcTrendToDate")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(3000);
        driver.findElement(By.id("documentChkInMcTrends")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("searchBtnInMcTrends")).click();
        Thread.sleep(5000);
         driver.findElement(By.id("viewBtnInMcCompleted")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("/html/body/div[4]/div[1]/button")).click();
            Thread.sleep(5000);
            String applicationWindowOrPDF = driver.getWindowHandle();
//          driver.findElement(By.id("viewTrendBtnInMcCompleted")).click();
//            Thread.sleep(8000);    
//            String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(8000);
                 driver.findElement(By.id("viewTrendBtnInCcCompleted")).click();
                Thread.sleep(8000);
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
        

   

