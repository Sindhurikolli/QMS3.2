package com.pss.qms.ChangeControlRejection;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 
import com.pss.qms.login.CCLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class InitiateDocument extends CCLoginDetails  {
    
	 @Test
	    public void methodtoinitiatedocument() throws InterruptedException {
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("myActivitiesInCC")).click();
	        Thread.sleep(2000);
	        touploaddocument();
	        }

	    private void touploaddocument() throws InterruptedException {
	        Thread.sleep(10000);
	        driver.findElement(By.id("ccShortDescriptionInCcInit")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("//*[@id=\"reasonJtableInCCInit\"]/div/div[3]/div[2]/span/span[2]")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("existMethodInReasonDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
	        driver.findElement(By.id("propChangeInReasonDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
	        driver.findElement(By.id("changeReasonInReasonDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
	          driver.findElement(By.id("addBtnInReasonsAdd")).click();
	        Thread.sleep(5000);
//	        driver.findElement(By.xpath("//*[@id=\"attatchmentsJtableInCCInit\"]/div/div[3]/div[2]/span")).click();
//	        Thread.sleep(5000);
//	        driver.findElement(By.id("attachmentFileInCCInit_1")).sendKeys(properties.getProperty("Document-1"));
//	        Thread.sleep(5000);
//	        driver.findElement(By.id("addBtnInReasonsAdd")).click();
//	        Thread.sleep(2000);
//	      driver.findElement(By.xpath(".//*[@id='attatchmentsJtableInCCInit']/div/div[3]/div[2]/span/span[2]")).click();
//	      Thread.sleep(2000);
//	      driver.findElement(By.id("attachmentFileInCCInit_1")).sendKeys(properties.getProperty("Document-1 "));
//	      Thread.sleep(2000);
	        Select changetype = new Select(driver.findElement(By.id("ccChangeTypeInCcInit")));
	        changetype.selectByIndex(2);
	        Thread.sleep(5000);
	        driver.findElement(By.id("implementationPlanInCcInit")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
//	        driver.findElement(By.id("safetyAspectsOfPropChngInCcInit")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
//	        Thread.sleep(5000);
//	        JavascriptExecutor js = ((JavascriptExecutor)driver);
//	        js.executeScript("window.scrollBy(0,1000)");
//	        Thread.sleep(5000);
//	        driver.findElement(By.id("suppDocYesInCcInit")).click();
//	        Thread.sleep(4000);
//	        driver.findElement(By.id("ccAddSuppDocs")).click();
//	        Thread.sleep(4000);
//	        driver.findElement(By.id("supptDocumentForText1")).sendKeys(properties.getProperty("Document_Name"));
//	        Thread.sleep(5000);
//	        driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
//	        Thread.sleep(5000);
	        JavascriptExecutor js = ((JavascriptExecutor) driver);
	        js.executeScript("window.scrollBy(0,500)");
	        Thread.sleep(5000);
//	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//	        JavascriptExecutor scl1 = ((JavascriptExecutor) driver);
//	        scl1.executeScript("window.scrollBy(0,-500)");
//	        Thread.sleep(5000);
	        driver.findElement(By.id("documentChkInCcInit")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.id("continueInitiateBtn")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.cssSelector("#documentJtableInCCInit > div > div.jtable-title > div.jtable-toolbar > span > span.jtable-toolbar-item-text")).click();
	        Thread.sleep(5000);
	         driver.findElement(By.id("docTitleInCCDocDlg")).sendKeys(properties.getProperty("Document_Name"));;
	        Thread.sleep(5000);
	         driver.findElement(By.id("addDetailsInCCDocDlg")).sendKeys(properties.getProperty("CHANGE_CONTROL_SHORT_DESC"));
	        Thread.sleep(5000);
//	         driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
//	        Thread.sleep(5000);
	        driver.findElement(By.id("addBtnInCCDocDlg")).click();
	        Thread.sleep(5000);
//	        driver.findElement(By.xpath(".//*[@id='deptReviewUserDetailsContainer']/div/table/tbody/tr[2]/td[1]/input")).click();
//	        Thread.sleep(2000);
//	        driver.findElement(By.id("addBtnInCCDocDlg")).click();
//	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"deptReviewUserDetailsContainer\"]/div/table/tbody/tr[2]/td[1]/input")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//*[@id=\"qaReviewUserDetailsContainer\"]/div/table/tbody/tr[3]/td[1]/input")).click();
	        Thread.sleep(2000);
	          String CCString="";
	        driver.findElement(By.id("submitBtnInCCInitWin")).click();
	        Thread.sleep(4000);
	        WebDriverWait wait = new WebDriverWait(driver, 60);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
	        Thread.sleep(8000);
	        driver.findElement(By.id("subBtnInValidateESign")).click();
	        Thread.sleep(15000);
//	        driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//	        Thread.sleep(6000);
	          CCString = driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-text > center")).getText();
//	          System.out.println("CCString: "+CCString);                                             
//	        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center/text()[2]"));
	        Thread.sleep(2000);
	        String separateCCNumber[] = CCString.split(" ");
	        System.out.println("separateCCNumber: "+separateCCNumber[5]);
	        String CCNo = separateCCNumber[5]; 
	        Thread.sleep(2000);
	        CCNo = CCNo.replace(")", ""); 
	        finalCCNumber = CCNo.trim();
	        System.out.println("finalCCNumber: "+finalCCNumber);
	        Thread.sleep(5000);
	        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
	        Thread.sleep(5000);

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