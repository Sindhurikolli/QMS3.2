package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class WorkFlowActive extends RegistrationLoginDetails {
    
   @Test
    public void WorkFlowRegistration() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regAddMenusListId\"]/ul/li[17]/a")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("groupDescInCreateGrp")).sendKeys(properties.getProperty("GROUP_NAME"));
//        Thread.sleep(4000);
        driver.findElement(By.id("locSelBtnInWFActOrInAct")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_8_span")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("searchBtnInWFActOrInAct")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"workFlowDeatilsJTable\"]/div/table/tbody/tr/td[2]")).click();
        Thread.sleep(4000);
        Select Activate = new Select(driver.findElement(By.id("activateInDetailsWFActOrInAct")));
        Activate.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("selectApprovedByDetailsWF")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[1]/span[1]/span[4]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[7]/td[1]")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
        Thread.sleep(4000);
         driver.findElement(By.id("commentsInWFActOrInAct")).sendKeys("Approve");
        Thread.sleep(4000);
        driver.findElement(By.id("submitBtnInWFActOrinAct")).click();
        Thread.sleep(4000);
       
//          boolean isRecordSelectedForAdmin = false;
//            String AdminFirstName = properties.getProperty("ADMIN_FIRST_NAME");
////            String regLastName  = "reviewer3";
//            String AdminFullName = AdminFirstName;
//            int count3=0;
//            isRecordSelectedForAdmin=selectingTheAdminUser(AdminFullName,isRecordSelectedForAdmin,count3);
//            Thread.sleep(3000);
////            driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTableInDev\"]/div/table/tbody/tr[2]/td[2]")).click();
////            Thread.sleep(3000);
//        driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@id=\"customerTableInProdRegnWin\"]/div/div[4]/table/tbody/tr[7]/td[1]/input")).click();
//        Thread.sleep(5000);
//         JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.id("SelBtnInCustomerWin"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(1000);
//        driver.findElement(By.id("plantCodeSubBtnId")).click();
//        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
//        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
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

//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
            //}
      
    
    










