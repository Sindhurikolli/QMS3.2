package com.pss.qms.DeviationReports;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.DeviationLoginDetails;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class DeviationTrendsPieChartViewDetails extends DeviationLoginDetails {
    
    @Test
    public void DeviationReports() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"devTrendsListMenu\"]/li[2]/a")).click();
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
        driver.findElement(By.id("searchBtnInDevTrends")).click();
        Thread.sleep(10000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("viewBtnInDevCompleted"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);
        driver.findElement(By.id("viewBtnInDevCompleted")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/button/span[1]")).click();
        Thread.sleep(8000);
//          JavascriptExecutor jse = (JavascriptExecutor) driver;
//          WebElement element = driver.findElement(By.id("viewBtnInDevHistReport"));
//          jse.executeScript("arguments[0].scrollIntoView(true);", element);
//          Thread.sleep(8000);
  
     
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

 

