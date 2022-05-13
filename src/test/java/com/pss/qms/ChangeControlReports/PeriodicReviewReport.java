package com.pss.qms.ChangeControlReports;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class PeriodicReviewReport extends CCLoginDetails {
  
	@Test
	public void ChangeControlReports() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("ccPeriodicReviewReportMenuId")).click();
        Thread.sleep(10000);
//    driver.findElement(By.id("changeControlNoInCcReportsPostClosure")).sendKeys(properties.getProperty("CHANGE_CONTROL_NUMBER"));
//    Thread.sleep(3000);
//        driver.findElement(By.id("fromDateInCcReportsPostClosure")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("toDateInCcReportsPostClosure")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(3000);
//        Select CategoryOfChange = new Select(driver.findElement(By.id("cateOfChangeInCcCompleteReport")));
//        CategoryOfChange.selectByVisibleText(properties.getProperty("Category_Of_Change"));
//        Thread.sleep(4000);
//        Select changetype = new Select(driver.findElement(By.id("changeTypeInCcReportsPostClosure")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("locSelBtn_CcReportsPostClosure")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("documentChkInCcReportsPostClosure")).click();
//        Thread.sleep(5000);
        driver.findElement(By.id("locSelBtn_periodicReviewReportSearch")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("plantOnlyLocTreeContainer_2_ico")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(8000);
        driver.findElement(By.id("viewReportBtn_periodicReviewReportSearch")).click();
        Thread.sleep(8000);
       
              String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(3000);
//                driver.findElement(By.id("saveAsExcelBtnInPostClosure")).click();
//                Thread.sleep(12000);
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
                            Thread.sleep(6000);
                            driver.findElement(By.id("myActivitiesInCC")).click();
                    	    Thread.sleep(5000); 
                        }
                    }
//                
        
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

