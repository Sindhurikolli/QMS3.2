package com.pss.qms.DeviationWorkFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;

public class ExistingWorkFlowUsers extends DeviationLoginDetails {

	@Test
    public void WorkFlowDeviation() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("workFlowInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(10000);
        methodToDoWorkFlowDeviation();
    }
    
    private void methodToDoWorkFlowDeviation() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("selLocBtn_DevExistWorkFlowForm")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_9_span")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("searchBtnInRegExistWorkFlows")).click();
        Thread.sleep(3000);
        boolean isRecordSelectedForUser = false;
//      String InvFirstName =properties.getProperty("First_Name1");
      String UserFirsrtName =properties.getProperty("EXISTING_WF_NAME");
      String UserFullName = UserFirsrtName;
      int count4=0;
      isRecordSelectedForUser = selectingTheUser(UserFullName,isRecordSelectedForUser, count4);
      Thread.sleep(3000);
//      driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
//      Thread.sleep(3000);
      if(isRecordSelectedForUser) {
    	Thread.sleep(3000);  
      
      driver.findElement(By.xpath("//*[@id=\"wfStagesTableContainer\"]/div/table/tbody/tr[1]/td[4]/button")).click();
      Thread.sleep(6000);
      driver.findElement(By.id("closeBtnInwfStageUsersTable")).click();
      Thread.sleep(6000);
      driver.findElement(By.xpath("//*[@id=\"wfStagesTableContainer\"]/div/table/tbody/tr[2]/td[4]/button")).click();
      Thread.sleep(6000);
      driver.findElement(By.id("closeBtnInwfStageUsersTable")).click();
      Thread.sleep(6000);
      driver.findElement(By.xpath("//*[@id=\"wfStagesTableContainer\"]/div/table/tbody/tr[3]/td[4]/button")).click();
      Thread.sleep(6000);
      driver.findElement(By.id("closeBtnInwfStageUsersTable")).click();
      Thread.sleep(6000);
      driver.findElement(By.xpath("//*[@id=\"wfStagesTableContainer\"]/div/table/tbody/tr[4]/td[4]/button")).click();
      Thread.sleep(6000);
      driver.findElement(By.id("closeBtnInwfStageUsersTable")).click();
      Thread.sleep(6000);
      driver.findElement(By.id("closeBtnInwfStagesTable")).click();
      Thread.sleep(6000);
    }
    }
private boolean selectingTheUser(String userFullName, boolean isRecordSelectedForUser, int count4) throws InterruptedException {
	  WebElement table = driver.findElement(By.id("existingWorkFlowGridId"));
      WebElement tableBody = table.findElement(By.tagName("tbody"));
      int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//      int totalNoOfRecords = 0;
//      int noOfRecordsChecked = 0;
//      if (perPageNoOfRecordsPresent > 0) {
//          String a = driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//          String[] parts = a.split(" of ");
//          try {
//              totalNoOfRecords = Integer.parseInt(parts[1].trim());
//          } catch (Exception e) {
//              System.out.println(e.getMessage());
//          }
//      }
      //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
      if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
          if (((userFullName == null) || ("".equalsIgnoreCase(userFullName)))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
          } else if ((userFullName == null) || ("".equalsIgnoreCase(userFullName))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr/td[2]")).getText();//documentType

          }
          ++count4;
      }
      if (perPageNoOfRecordsPresent > 0) {
          //while (noOfRecordsChecked < totalNoOfRecords) {
              if (perPageNoOfRecordsPresent > 1) {
                  for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                      String WFFirstName = driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr[ " + i + " ]/td[7]")).getText();//documentTypeName
                      System.out.println("WFReviewFullName: "+WFFirstName);
                      if (userFullName.equalsIgnoreCase(WFFirstName)) {
                          driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr[ " + i + " ]/td[9]/button")).click();
                          isRecordSelectedForUser = true;
                          break;
                      }
                  }
                 
              } else {
                  String WFReviewFullName = driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr[1]/td[7]")).getText();
                  if (userFullName.equalsIgnoreCase(WFReviewFullName)) {
                      driver.findElement(By.xpath("//*[@id=\"existingWorkFlowGridId\"]/div/table/tbody/tr/td[9]/button")).click();
                      isRecordSelectedForUser = true;
                      
                  }
              }
//              noOfRecordsChecked += perPageNoOfRecordsPresent;
//              if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
//                  driver.findElement(By.cssSelector("#existingWorkFlowGridId > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//                  Thread.sleep(3000);
//                  table = driver.findElement(By.id("existingWorkFlowGridId"));//Document Tree approve table
//                  tableBody = table.findElement(By.tagName("tbody"));
//                  perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//              }
//          }
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
	


	

