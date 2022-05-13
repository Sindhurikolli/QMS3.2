package com.pss.qms.ChangeControlReports;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 
import org.openqa.selenium.By;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class CCPieTrendViewDetails extends CCLoginDetails {
    
    @Test
    public void CCBarReports() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"ccTrendsListMenuId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"ccTrendsListMenuId\"]/ul/li[2]/a")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("selLocBtnInMisCCCompleted")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.id("treeContainer_3_ico")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(10000);
        driver.findElement(By.id("searchBtnInCcTrends")).click();
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
        driver.findElement(By.id("viewBtnInCcCompleted")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/button/span[1]")).click();
        Thread.sleep(8000);
//          JavascriptExecutor jse = (JavascriptExecutor) driver;
//          WebElement element = driver.findElement(By.id("viewBtnInDevHistReport"));
//          jse.executeScript("arguments[0].scrollIntoView(true);", element);
//          Thread.sleep(8000);
        methodToDoDeviationReports();
    }

    private void methodToDoDeviationReports() throws InterruptedException {
//        int count = 0;
//        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
////        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEVIATION_NO");
//        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + DepartmentCode;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
////        String chgCtrlId = "-0076";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
//        isRecordSelected = selectRecdDeviationReports(chgControlNumber, isRecordSelected, count);
//        Thread.sleep(15000);
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




