package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

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
public class UserGroup extends RegistrationLoginDetails {
    
   @Test
    public void UserGroupRegistration() throws InterruptedException {
        Thread.sleep(2000);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div:nth-child(2) > input[name='loginUserName']")).sendKeys(properties.getProperty("ADMIN"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#loginform > div.row.col-sm-12.password-container > input[name='loginPassword']")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[3]/button[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a[href='createUserGroup.do']")).click();
        Thread.sleep(5000);
        todoUserGroupRegistration();
   }
        public void todoUserGroupRegistration() throws InterruptedException {  ;
        Thread.sleep(2000);
        driver.findElement(By.id("groupNameInCreateGrp")).sendKeys(properties.getProperty("USERGROUP_NAME"));
        Thread.sleep(4000);
        driver.findElement(By.id("groupDescInCreateGrp")).sendKeys(properties.getProperty("USERGROUP_Description"));
        Thread.sleep(4000);
        driver.findElement(By.id("locSelInCreateUserGrp")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(4000);
        driver.findElement(By.linkText(properties.getProperty("UsergroupLocation"))).click();
        Thread.sleep(4000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(4000);
        String UsersE_Codes = properties.getProperty("UsersE_Codes");
        String[] Userarray = UsersE_Codes.split(",");
        for(int i=0; i<Userarray.length; i++)
        {
        driver.findElement(By.id("usersSelectInSelectionInUserGrp")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("locTreeInQmsProdReg_1_span")).click();
        Thread.sleep(4000);
        String UserEcode = Userarray[i].trim();
        System.out.println(UserEcode);
        driver.findElement(By.id("searchEmpCode")).sendKeys(UserEcode);
        Thread.sleep(1000);
        driver.findElement(By.id("usersSearchBtnInRepProb")).click();
        Thread.sleep(4000);
        boolean isrecordSelectedForUser = false;
        int count3 = 0;
        isrecordSelectedForUser = selectingTheUser(UserEcode, isrecordSelectedForUser, count3);
        Thread.sleep(4000);
        if(isrecordSelectedForUser)
        {
        driver.findElement(By.id("usersSelBtnRepProb")).click();
        Thread.sleep(4000);
        }
        else
        {
        	driver.findElement(By.id("usersCancelBtnInRepProb")).click();
        	Thread.sleep(3000);
        	System.out.println(UserEcode+"-User E-code not Selected for User Group");
        }
        }
        driver.findElement(By.id("submitInCreateUserGrp")).click();
        Thread.sleep(4000);        
        WebDriverWait wait = new WebDriverWait(driver, 60);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
      Thread.sleep(2000);
      driver.findElement(By.id("subBtnInValidateESign")).click();
      Thread.sleep(5000);
      driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
      Thread.sleep(2000);
      driver.findElement(By.className("username")).click();
      Thread.sleep(2000);
      driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
    }
        private boolean selectingTheUser(String UserEcode, boolean recordSelectedForUser, int count3) throws InterruptedException {
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
          
           if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
               if (((UserEcode == null) || ("".equalsIgnoreCase(UserEcode)))) {
                   UserEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]")).getText();//documentType
               } else if ((UserEcode == null) || ("".equalsIgnoreCase(UserEcode))) {
                   UserEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).getText();//documentType

               }
               ++count3;
           }
           if (perPageNoOfRecordsPresent > 0) {
               while (noOfRecordsChecked < totalNoOfRecords) {
                   if (perPageNoOfRecordsPresent > 1) {
                       for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                           String UserEcodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
                           System.out.println("UserEcodeSequence: "+UserEcodeSequence);
                           if (UserEcode.equalsIgnoreCase(UserEcodeSequence)) {
                               driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
                               recordSelectedForUser = true;
                               break;
                           }
                       }
                      
                   } else {
                       String UserEcodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).getText();
                       if (UserEcode.equalsIgnoreCase(UserEcodeSequence)) {
                           driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).click();
                           recordSelectedForUser = true;
                           
                       }
                   }
                   noOfRecordsChecked += perPageNoOfRecordsPresent;
                   if ((!recordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
                       driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                       Thread.sleep(3000);
                       table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                       tableBody = table.findElement(By.tagName("tbody"));
                       perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                   }
               }
           }
           return recordSelectedForUser;
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

      
    
    






