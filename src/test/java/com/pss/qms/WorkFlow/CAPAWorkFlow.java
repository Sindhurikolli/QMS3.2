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

public class CAPAWorkFlow extends QMSLoginDetails {

    @Test
    public void CAPAWorkFlowMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("CAPAModule"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("workFlowInCapa")).click();
        Thread.sleep(2000);
        toCAPAWorkFlowMethod();
        
            }

    private void toCAPAWorkFlowMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("regWorkFlowSelect")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText(properties.getProperty("WFLocation"))).click();   
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        String DhodE_Codes = properties.getProperty("CAPADepartmentHodE_Codes");
        String DhodEcodes [] =DhodE_Codes.split(",");
        for(int i=0; i<DhodEcodes.length; i++)
        {
        driver.findElement(By.id("workFlowUserBrowse1Button")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("locTreeInQmsCapaWfReg_1_a")).click();
        Thread.sleep(3000);
        String DhodEcode = DhodEcodes[i].trim();
        boolean isrecordSelectedForUser = false;
        int count3 = 0;
        isrecordSelectedForUser = selectingTheUser(DhodEcode, isrecordSelectedForUser, count3);
        Thread.sleep(1000);
        if(isrecordSelectedForUser)
        {
        driver.findElement(By.id("selectBrowse1Button")).click();
        Thread.sleep(1000);
        }
        else
        {
        	driver.findElement(By.id("cancelBrowse1Button")).click();
        	System.out.println("E-Code -"+DhodEcode+"Not Selected");
        }
        }
        Thread.sleep(3000);
        String QMSCoordinatorE_Codes = properties.getProperty("CAPAQMSCoordinatorE_Codes");
        String QMSCoordinatorEcodes [] =QMSCoordinatorE_Codes.split(",");
        for(int i=0; i<QMSCoordinatorEcodes.length; i++)
        {
        driver.findElement(By.id("workFlowUserBrowse2Button")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText(properties.getProperty("WFLocation"))).click();
        Thread.sleep(1000);
        String QMSCoordinatorEcode = QMSCoordinatorEcodes[i].trim();
        boolean isrecordSelectedForUser = false;
        int count3 = 0;
        isrecordSelectedForUser = selectingTheUser(QMSCoordinatorEcode, isrecordSelectedForUser, count3);
        Thread.sleep(1000);
        if(isrecordSelectedForUser)
        {
        driver.findElement(By.id("selectBrowse1Button")).click();
        Thread.sleep(1000);
        }
        else
        {
        	driver.findElement(By.id("cancelBrowse1Button")).click();
        	System.out.println("E-Code -"+QMSCoordinatorEcode+"Not Selected");
        }
        }
      
        jse.executeScript("window.scrollBy(0,250)");
        Thread.sleep(3000);
        String QAHODE_Codes = properties.getProperty("CAPAQAHODE_Codes");
        String QAHODECodes [] =QAHODE_Codes.split(",");
        for(int i=0; i<QAHODECodes.length; i++)
        {
        driver.findElement(By.id("workFlowUserBrowse3Button")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText(properties.getProperty("WFLocation"))).click();
        Thread.sleep(1000);
        String QAHODECode = QAHODECodes[i].trim();
        boolean isrecordSelectedForUser = false;
        int count3 = 0;
        isrecordSelectedForUser = selectingTheUser(QAHODECode, isrecordSelectedForUser, count3);
        Thread.sleep(1000);
        if(isrecordSelectedForUser)
        {
        driver.findElement(By.id("selectBrowse1Button")).click();
        Thread.sleep(1000);
        }
        else
        {
        	driver.findElement(By.id("cancelBrowse1Button")).click();
        	System.out.println("E-Code -"+QAHODECode+"Not Selected");
        }
        }
        
        jse.executeScript("window.scrollBy(0,300)");
        Thread.sleep(3000);
        String QualityHeadE_Codes = properties.getProperty("CAPAQualityHeadE_Codes");
        String QualityHeadECodes [] =QualityHeadE_Codes.split(",");
        for(int i=0; i<QualityHeadECodes.length; i++)
        {
        driver.findElement(By.id("workFlowUserBrowse4Button")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText(properties.getProperty("WFLocation"))).click();
        Thread.sleep(1000);
        String QualityHeadECode = QualityHeadECodes[i].trim();
        boolean isrecordSelectedForUser = false;
        int count3 = 0;
        isrecordSelectedForUser = selectingTheUser(QualityHeadECode, isrecordSelectedForUser, count3);
        Thread.sleep(1000);
        if(isrecordSelectedForUser)
        {
        driver.findElement(By.id("selectBrowse1Button")).click();
        Thread.sleep(1000);
        }
        else
        {
        	driver.findElement(By.id("cancelBrowse1Button")).click();
        	System.out.println("E-Code -"+QualityHeadECode+"Not Selected");
        }
        }
        jse.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        driver.findElement(By.id("regWfSubBtnId")).click();
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
    private boolean selectingTheUser(String DhodEcode, boolean isrecordSelectedForUser, int count3) throws InterruptedException {
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
           if (((DhodEcode == null) || ("".equalsIgnoreCase(DhodEcode)))) {
               DhodEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[5]")).getText();//documentType
           } else if ((DhodEcode == null) || ("".equalsIgnoreCase(DhodEcode))) {
               DhodEcode = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).getText();//documentType

           }
           ++count3;
       }
       if (perPageNoOfRecordsPresent > 0) {
           while (noOfRecordsChecked < totalNoOfRecords) {
               if (perPageNoOfRecordsPresent > 1) {
                   for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                       String DhodEcodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
//                       System.out.println("DhodEcodeSequence: "+DhodEcodeSequence);
                       if (DhodEcode.equalsIgnoreCase(DhodEcodeSequence)) {
                           driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
                           isrecordSelectedForUser = true;
                           break;
                       }
                   }
                   if (isrecordSelectedForUser) {
						break;
					}
                  
               } else {
                   String DhodEcodeSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).getText();
                   if (DhodEcode.equalsIgnoreCase(DhodEcodeSequence)) {
                       driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[5]")).click();
                       isrecordSelectedForUser = true;
                       break;
                   }
               }
               noOfRecordsChecked += perPageNoOfRecordsPresent;
               if ((!isrecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
                   driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                   Thread.sleep(3000);
                   table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                   tableBody = table.findElement(By.tagName("tbody"));
                   perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
               }
           }
       }
       return isrecordSelectedForUser;
   }
	
}
