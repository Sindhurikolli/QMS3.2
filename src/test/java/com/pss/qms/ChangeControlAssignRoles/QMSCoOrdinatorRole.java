package com.pss.qms.ChangeControlAssignRoles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.CCLoginDetails;
 

public class QMSCoOrdinatorRole extends CCLoginDetails {
  
	
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("adminInCC")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
        Thread.sleep(10000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
         driver.findElement(By.id("selectBtnInAssignRole")).click(); 
         Thread.sleep(3000);
         driver.findElement(By.id("locationTreeForSingleUserSelect_2_switch")).click();
         Thread.sleep(5000);
         driver.findElement(By.id("locationTreeForSingleUserSelect_9_span")).click();
         Thread.sleep(3000);
         boolean isRecordSelectedForUser = false;
//       String InvFirstName =properties.getProperty("First_Name1");
       String UserFirsrtName =properties.getProperty("FIRST_NAME2");
       String UserFullName = UserFirsrtName;
       int count4=0;
       isRecordSelectedForUser = selectingTheUser(UserFullName,isRecordSelectedForUser, count4);
       Thread.sleep(3000);
//       driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
//       Thread.sleep(3000);
        driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
       Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[9]")).click();
         Thread.sleep(3000);
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         WebElement element = driver.findElement(By.id("subBtnInAssignRole"));
         jse.executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(1000);
         driver.findElement(By.id("subBtnInAssignRole")).click();
        Thread.sleep(3000); 
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(1000);
    }
private boolean selectingTheUser(String userFullName, boolean isRecordSelectedForUser, int count4) throws InterruptedException {
	  WebElement table = driver.findElement(By.id("singleSelUsersTableContainer"));
      WebElement tableBody = table.findElement(By.tagName("tbody"));
      int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
      int totalNoOfRecords = 0;
      int noOfRecordsChecked = 0;
      if (perPageNoOfRecordsPresent > 0) {
          String a = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
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
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
          } else if ((userFullName == null) || ("".equalsIgnoreCase(userFullName))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

          }
          ++count4;
      }
      if (perPageNoOfRecordsPresent > 0) {
//          while (noOfRecordsChecked < totalNoOfRecords) {
              if (perPageNoOfRecordsPresent > 1) {
                  for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                      String userFullName1 = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                      System.out.println("InvReviewFullName: "+userFullName1);
                      if (userFullName.equalsIgnoreCase(userFullName1)) {
                          driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                          isRecordSelectedForUser = true;
                          break;
                      }
                  }
                 
              } else {
                  String userFullName1 = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();
                  if (userFullName.equalsIgnoreCase(userFullName1)) {
                	  Thread.sleep(3000);
                      driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).click();
                      isRecordSelectedForUser = true;
                      
                  }
              }
              noOfRecordsChecked += perPageNoOfRecordsPresent;
              if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
                  driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/div[4]/div[1]/span[1]/span[5]")).click();//next page in Document approve list
                  Thread.sleep(8000);
                  table = driver.findElement(By.id("singleSelUsersTableContainer"));//Document Tree approve table
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










	






