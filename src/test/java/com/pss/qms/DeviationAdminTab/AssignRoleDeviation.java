package com.pss.qms.DeviationAdminTab;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.DeviationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class AssignRoleDeviation extends DeviationLoginDetails  {
    
  @Test
    public void methodtoAssignRole() throws InterruptedException {
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("adminInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
        Thread.sleep(8000);
         toAssignRole();
    }

    private void toAssignRole() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.id("selectBtnInAssignRole")).click();
        Thread.sleep(3000);
         driver.findElement(By.id("locationTreeForSingleUserSelect_2_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("locationTreeForSingleUserSelect_9_a")).click();
        Thread.sleep(3000); 
        boolean isRecordSelectedForUser = false;
        String UserFirstName = properties.getProperty("USER");
//        String regLastName  = "reviewer3";
        String UserFullName = UserFirstName;
        int count=0;
         isRecordSelectedForUser=selectingTheUser(UserFullName,isRecordSelectedForUser,count);
        Thread.sleep(3000);
        driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[10]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInAssignRole")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnInValESignInDev")).click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(5000);
    
    }

	private boolean selectingTheUser(String UserFullName, boolean isRecordSelectedForUser, int count) throws InterruptedException {
	
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
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if (((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            //while (noOfRecordsChecked < totalNoOfRecords) {
	                if (perPageNoOfRecordsPresent > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String RegReviewerFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        System.out.println("RegReviewerFullName: "+RegReviewerFullName);
	                        if (UserFullName.equalsIgnoreCase(RegReviewerFullName)) {
	                            driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                   
	                } else {
	                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                    if (UserFullName.equalsIgnoreCase(cftReviewerFullName)) {
	                        driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
	                        isRecordSelectedForUser = true;
	                        
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#singleSelUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
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
		

	




