/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.DeviationWithInvAndCAPARejection;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.DeviationLoginDetails;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ram
 */
public class InvRePerform extends DeviationLoginDetails {
    
    @Test
    public void toInvRePerform() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvRePerform"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvRePerform", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("INVEST_USER"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(5);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(5000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
//			sno++; 	
//         driver.findElement(By.xpath(".//*[@id='investigation_tile_Id']/div/div/div/h2")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Module",sno,false);
//        Thread.sleep(15000);
        sno++; 	
        driver.findElement(By.cssSelector("a[href='rePerformFullInvPage.do']")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RePerform Menu",sno,false);
        Thread.sleep(40000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/div[4]/div[1]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        methodToDoInvestigationDeviation();
        Thread.sleep(50000);
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}      
    }

    private void methodToDoInvestigationDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        WebDriverWait wait1 = new WebDriverWait(driver, 240);

        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#investigationRePerformTableContainer > div > div.jtable-busy-message"))));
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0071";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//        String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdInvestigationDeviation(DeviationNumber, isRecordSelected, count);
        
       if (isRecordSelected) {
    	   WebDriverWait wait = new WebDriverWait(driver, 70);
    	   Thread.sleep(7000);
//    	   Robot robot = new Robot();
//    	   robot.keyPress(KeyEvent.VK_CONTROL);
//    	   robot.keyPress(KeyEvent.VK_SUBTRACT);
//    	   robot.keyRelease(KeyEvent.VK_SUBTRACT);
//    	   robot.keyRelease(KeyEvent.VK_CONTROL);
    	   Actions actions = new Actions(driver);
    	   WebElement element = driver.findElement(By.id("modifyCommentsInFullInvReview"));
    	   actions.moveToElement(element);
    	   actions.perform();
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("modifyCommentsInFullInvReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            sno++;
            Thread.sleep(5000);
            WebElement element1 = driver.findElement(By.id("stageName1"));
     	   actions.moveToElement(element1);
     	   actions.perform();
            Select DHod = new Select(driver.findElement(By.id("stageName1")));
            DHod.selectByVisibleText(properties.getProperty("InvDHod"));
            Thread.sleep(2000);
//            WebElement element1 = driver.findElement(By.id("rootCauseTabId"));
//            jse.executeScript("arguments[0].click();", element1);
            WebElement element2 = driver.findElement(By.id("ui-id-391"));
      	   actions.moveToElement(element2).click();
      	   actions.perform();
//            driver.findElement(By.id("ui-id-387")).click();
            Thread.sleep(5000);
//            WebElement element2 = driver.findElement(By.id("impactAssessMentId"));
//            jse.executeScript("arguments[0].click();", element2);
            WebElement element3 = driver.findElement(By.id("ui-id-392"));
       	   actions.moveToElement(element3).click();
       	   actions.perform();
//            driver.findElement(By.id("ui-id-388")).click();
            Thread.sleep(5000);
            Helper.waitLoadRecords(driver, By.cssSelector("#rcaAnalysisInDevReview > div > div.jtable-busy-message[style='display: none;']"));
//            WebElement element3 = driver.findElement(By.id("invCapaTabId"));
//            jse.executeScript("arguments[0].click();", element3);
            WebElement element4 = driver.findElement(By.id("ui-id-393"));
       	   actions.moveToElement(element4).click();
       	   actions.perform();
//            driver.findElement(By.id("ui-id-390")).click();
            Thread.sleep(5000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaDetailsInFullInvPage > div > div.jtable-busy-message[style='display: none;']")));
//            WebElement element4 = driver.findElement(By.id("InvCommentsId"));
//            jse.executeScript("arguments[0].click();", element4);
//            WebElement element5 = driver.findElement(By.id("ui-id-390"));
//        	   actions.moveToElement(element5);
//        	   actions.perform();
//            driver.findElement(By.id("ui-id-390")).click();
//            Thread.sleep(5000);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#invCommentsInFullInvPage > div > div.jtable-busy-message[style='display: none;']")));
//            WebElement element5 = driver.findElement(By.id("submitBtnInRePerformfullInvReview"));
//            jse.executeScript("arguments[0].click();", element5);
//            robot.keyPress(KeyEvent.VK_CONTROL);
//     	   robot.keyPress(KeyEvent.VK_SUBTRACT);
//     	   robot.keyRelease(KeyEvent.VK_SUBTRACT);
//     	   robot.keyRelease(KeyEvent.VK_CONTROL);
            WebElement element6 = driver.findElement(By.cssSelector("#submitBtnIdInFullInvReview > div > button[id='submitBtnInRePerformfullInvReview']"));
     	   actions.moveToElement(element6).click();
     	   actions.perform();
            Thread.sleep(3000);
//            driver.findElement(By.cssSelector("#submitBtnIdInFullInvReview > div > button[id='submitBtnInRePerformfullInvReview']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(3000);
            WebElement element7 = driver.findElement(By.id("eSignPwdInWnd"));
      	   actions.moveToElement(element7);
      	   actions.perform();
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(3000);
            WebElement element8 = driver.findElement(By.id("subBtnInValidateESign"));
       	   actions.moveToElement(element8);
       	   actions.perform();
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
          
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(5000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();   
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);      
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdInvestigationDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("investigationRePerformTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/div[4]/div[2]/span"));
            JavascriptExecutor jsshow = (JavascriptExecutor)driver;
            jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
            String a = driver.findElement(By.xpath(".//*[@id='investigationRePerformTableContainer']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='investigationRePerformTableContainer']/div/table/tbody/tr[ " + i + "]/td[5]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    	
                        String DevNumberSequence = driver.findElement(By.xpath(".//*[@id='investigationRePerformTableContainer']/div/table/tbody/tr[ " + i + "]/td[5]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='investigationRePerformTableContainer']/div/table/tbody/tr[ " + i + "]/td[26]/button")).click();
                            isRecordSelected = true;
                            break;//*[@id="investigationRePerformTableContainer"]/div/table/tbody/tr[2]/td[5]
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[5]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"investigationRePerformTableContainer\"]/div/table/tbody/tr/td[26]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	WebElement elementnext = driver.findElement(By.cssSelector("#investigationRePerformTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#investigationRePerformTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("investigationRePerformTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
    }

    private boolean selectingTheInvReview(String InvLastName, boolean recordSelectedForInv, int count4) {
          WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            try {
//                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
        if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
            if (((InvLastName == null) || ("".equalsIgnoreCase(InvLastName)))) {
                InvLastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
            } else if ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName))) {
                InvLastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            //while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (InvLastName.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                            recordSelectedForInv = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[4]/td[1]")).getText();
                    if (InvLastName.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[4]/td[1]")).click();
                        recordSelectedForInv = true;
                        
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
        }
        return recordSelectedForInv;
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







