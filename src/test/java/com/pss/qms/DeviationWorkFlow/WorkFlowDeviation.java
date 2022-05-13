package com.pss.qms.DeviationWorkFlow;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.DeviationLoginDetails;
 

public class WorkFlowDeviation extends DeviationLoginDetails {
 
	@Test
    public void WorkFlowDeviation() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("workFlowInDev")).click();
        Thread.sleep(10000);
        methodToDoWorkFlowDeviation();
    }
    
    private void methodToDoWorkFlowDeviation() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("regWorkFlowSelect")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_3_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_7_span")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("workFlowUserBrowse1Button")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("locTreeInQmsWfReg_3_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("locTreeInQmsWfReg_7_span")).click();
        Thread.sleep(3000);
        boolean isRecordSelectedForUser = false;
//      String InvFirstName =properties.getProperty("First_Name1");
      String UserFirsrtName =properties.getProperty("UserName1");
      String UserFullName = UserFirsrtName;
      int count4=0;
      isRecordSelectedForUser = selectingTheUser(UserFullName,isRecordSelectedForUser, count4);
      Thread.sleep(3000);
//      driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
//      Thread.sleep(3000);
      driver.findElement(By.id("selectBrowse1Button")).click();
      Thread.sleep(3000);
       driver.findElement(By.id("workFlowUserBrowse2Button")).click();
      Thread.sleep(3000);
      driver.findElement(By.id("locTreeInQmsWfReg_3_switch")).click();
      Thread.sleep(3000);
      driver.findElement(By.id("locTreeInQmsWfReg_7_span")).click();
      Thread.sleep(3000);   
      boolean isRecordSelectedForUser1 = false;
//    String InvFirstName =properties.getProperty("First_Name1");
		String UserFirsrtName1 = properties.getProperty("UserName2");
		String UserFullName1 = UserFirsrtName1;
		int count5 = 0;
		isRecordSelectedForUser1 = selectingTheUser(UserFullName1, isRecordSelectedForUser1, count4);
		Thread.sleep(3000);
		driver.findElement(By.id("selectBrowse1Button")).click();
		Thread.sleep(3000);
		 JavascriptExecutor scl = ((JavascriptExecutor) driver);
         scl.executeScript("window.scrollBy(0,500)");
         Thread.sleep(5000);
         driver.findElement(By.id("workFlowUserBrowse3Button")).click();
         Thread.sleep(3000);
         driver.findElement(By.id("locTreeInQmsWfReg_3_switch")).click();
         Thread.sleep(3000);
         driver.findElement(By.id("locTreeInQmsWfReg_7_span")).click();
         Thread.sleep(3000);   
         boolean isRecordSelectedForUser2 = false;
//       String InvFirstName =properties.getProperty("First_Name1");
   		String UserFirsrtName2 = properties.getProperty("SRM_ROLE_NAME");
   		String UserFullName2= UserFirsrtName2;
   		int count6 = 0;
   		isRecordSelectedForUser2 = selectingTheUser(UserFullName2, isRecordSelectedForUser2, count6);
   		Thread.sleep(3000);
   		driver.findElement(By.id("selectBrowse1Button")).click();
   		Thread.sleep(3000);
   	 driver.findElement(By.id("workFlowUserBrowse4Button")).click();
     Thread.sleep(3000);
     driver.findElement(By.id("locTreeInQmsWfReg_3_switch")).click();
     Thread.sleep(3000);
     driver.findElement(By.id("locTreeInQmsWfReg_7_span")).click();
     Thread.sleep(3000);   
     boolean isRecordSelectedForUser3 = false;
//   String InvFirstName =properties.getProperty("First_Name1");
		String UserFirsrtName3 = properties.getProperty("FIRST_NAME3");
		String UserFullName3= UserFirsrtName3;
		int count7 = 0;
		isRecordSelectedForUser2 = selectingTheUser(UserFullName3, isRecordSelectedForUser3, count7);
		Thread.sleep(3000);
		driver.findElement(By.id("selectBrowse1Button")).click();
		Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("regWfSubBtnId"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
        driver.findElement(By.id("regWfSubBtnId")).click();
		Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(5000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
            Thread.sleep(5000);
            driver.findElement(By.className("modal-btn")).click();
            Thread.sleep(5000);
    }

private boolean selectingTheUser(String userFullName, boolean isRecordSelectedForUser, int count4) throws InterruptedException {
	  WebElement table = driver.findElement(By.id("usersTableContainer"));
      WebElement tableBody = table.findElement(By.tagName("tbody"));
      int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
      int totalNoOfRecords = 0;
      int noOfRecordsChecked = 0;
      if (perPageNoOfRecordsPresent > 0) {
          String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
          String[] parts = a.split(" of ");
          try {
              totalNoOfRecords = Integer.parseInt(parts[1].trim());
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }
      //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
      if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
          if (((userFullName == null) || ("".equalsIgnoreCase(userFullName)))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
          } else if ((userFullName == null) || ("".equalsIgnoreCase(userFullName))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

          }
          ++count4;
      }
      if (perPageNoOfRecordsPresent > 0) {
          //while (noOfRecordsChecked < totalNoOfRecords) {
              if (perPageNoOfRecordsPresent > 1) {
                  for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                      String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                      System.out.println("InvReviewFullName: "+InvFirstName);
                      if (userFullName.equalsIgnoreCase(InvFirstName)) {
                          driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                          isRecordSelectedForUser = true;
                          break;
                      }
                  }
                 
              } else {
                  String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
                  if (userFullName.equalsIgnoreCase(InvReviewFullName)) {
                      driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
                      isRecordSelectedForUser = true;
                      
                  }
              }
              noOfRecordsChecked += perPageNoOfRecordsPresent;
              if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
                  driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                  Thread.sleep(3000);
                  table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
                  tableBody = table.findElement(By.tagName("tbody"));
                  perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
              }
          }
      
      return isRecordSelectedForUser;
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
	

