package com.pss.qms.WorkFlow;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.login.QMSLoginDetails;

public class ChangeControlPostCheckListMap extends QMSLoginDetails {

    @Test
    public void ChangeControlPostCheckListMapMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("ChangeControlModule"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("workFlowInCc")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("a[href='qmsCheckListSelPage.do']")).click();
        Thread.sleep(5000);
        toChangeControlPostCheckListMapMethod();
        
            }

    private void toChangeControlPostCheckListMapMethod() throws InterruptedException {
        Thread.sleep(1000);
        Select type = new Select(driver.findElement(By.id("type_AddCaPaTaskDialog")));
		Thread.sleep(1000);
		type.selectByIndex(2);
       driver.findElement(By.xpath("//*[@id=\"selectedCheckListsForCC\"]/div/div[3]/div[2]/span")).click();
       Thread.sleep(3000);           
       String PreCheckList = properties.getProperty("PostCheckList");
       boolean isrecordSelectedForCheckList = false;
       int count3 = 0;
       isrecordSelectedForCheckList = selectingTheCheckList(PreCheckList, isrecordSelectedForCheckList, count3);
       Thread.sleep(1000);
       if(isrecordSelectedForCheckList)
       {
       driver.findElement(By.id("selectChkListDetailsWindow")).click();
       Thread.sleep(1000);
       }
       else
       {
       	driver.findElement(By.id("closeChkListDetailsWindow")).click();
       	System.out.println("PreCheckList-"+PreCheckList+"Not Selected");
       }
            
        driver.findElement(By.id("submitInWfForCCImplForm")).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
    	   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
     	  driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("E_SignPassword"));
     	  Thread.sleep(1000);
     	  driver.findElement(By.id("subBtnInValidateESign")).click();
     	  Thread.sleep(3000);
     	  driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
     	  Thread.sleep(2000);
     	  driver.findElement(By.className("username")).click();
          Thread.sleep(1000);
          driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[3]/ul/li[4]/a")).click();
          Thread.sleep(1000);

    }
   
    private boolean selectingTheCheckList(String PreCheckList, boolean isrecordSelectedForCheckList, int count3) throws InterruptedException {
        WebElement table = driver.findElement(By.id("checkListTableInCcQAReviewer"));
       WebElement tableBody = table.findElement(By.tagName("tbody"));
       int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
       int totalNoOfRecords = 0;
       int noOfRecordsChecked = 0;
       if (perPageNoOfRecordsPresent > 0) {
           String a = driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
           String[] parts = a.split(" of ");
           try {
               totalNoOfRecords = Integer.parseInt(parts[1].trim());
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }
      
       if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
           if (((PreCheckList == null) || ("".equalsIgnoreCase(PreCheckList)))) {
               PreCheckList = driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
           } else if ((PreCheckList == null) || ("".equalsIgnoreCase(PreCheckList))) {
               PreCheckList = driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

           }
           ++count3;
       }
       if (perPageNoOfRecordsPresent > 0) {
           while (noOfRecordsChecked < totalNoOfRecords) {
               if (perPageNoOfRecordsPresent > 1) {
                   for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                       String PreCheckListSequence = driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
//                       System.out.println("PreCheckListSequence: "+PreCheckListSequence);
                       if (PreCheckList.equalsIgnoreCase(PreCheckListSequence)) {
                           driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                           isrecordSelectedForCheckList = true;
                           break;
                       }
                   }
                   if (isrecordSelectedForCheckList) {
						break;
					}
                  
               } else {
                   String PreCheckListSequence = driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr/td[3]")).getText();
                   if (PreCheckList.equalsIgnoreCase(PreCheckListSequence)) {
                       driver.findElement(By.xpath("//*[@id=\"checkListTableInCcQAReviewer\"]/div/table/tbody/tr/td[3]")).click();
                       isrecordSelectedForCheckList = true;
                       
                   }
               }
               noOfRecordsChecked += perPageNoOfRecordsPresent;
               if ((!isrecordSelectedForCheckList) && (noOfRecordsChecked < totalNoOfRecords)) {
                   driver.findElement(By.cssSelector("#checkListTableInCcQAReviewer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                   Thread.sleep(3000);
                   table = driver.findElement(By.id("checkListTableInCcQAReviewer"));//Document Tree approve table
                   tableBody = table.findElement(By.tagName("tbody"));
                   perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
               }
           }
       }
       return isrecordSelectedForCheckList;
   }
	
}
