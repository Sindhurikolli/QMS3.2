package com.pss.qms.DeviationWithInvAndCAPA;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.DeviationLoginDetails;

import java.awt.Desktop;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
 * @author likhitha
 */
public class CloseDeviation extends DeviationLoginDetails {
    
	 @Test
	    public void toCloseDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
				Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CloseDeviation"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);

				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("CloseDeviation", "PSS-QMS-001", "Pass");
				writer.setPageEvent(event);
				document.open();
				WebDriverWait wait = new WebDriverWait(driver, 60);
				driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
				driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
				Select module= new Select(driver.findElement(By.id("qmsModule")));
				module.selectByIndex(1);
				input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
				
				Thread.sleep(16000);
				im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
						(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
				document.add(new Paragraph(sno + "." + "Enter the username, password, Select Deviation Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
				sno++;	 
//	       driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
//	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
//	        Thread.sleep(10000);
	        sno++;
//	        driver.findElement(By.id("deviationInDev")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab",sno,false);
//	        Thread.sleep(10000);
	        sno++;
//	        driver.findElement(By.xpath("//*[@id=\"devCloseDeviationMenu_Id\"]/a")).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='devCloseDeviation.do']")));
	        driver.findElement(By.cssSelector("a[href='devCloseDeviation.do']")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close Deviation Menu",sno,false);
//	        Thread.sleep(10000);
//	        sno++;
//	        driver.findElement(By.id("reportsInDev")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
//	        Thread.sleep(10000);
//	        sno++;
//	        driver.findElement(By.id("deviationInDev")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
//	        Thread.sleep(10000);
//	        sno++;
//	        driver.findElement(By.xpath("//*[@id=\"devCloseDeviationMenu_Id\"]/a")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Close",sno,false);
//	        JavascriptExecutor jse = (JavascriptExecutor) driver;
//	        WebElement element = driver.findElement(By.id("submitBtnInCloseDeviationForm"));
//	        jse.executeScript("arguments[0].scrollIntoView(true);", element);
	         methodToDoCloseDeviation();
	         Thread.sleep(10000);
	         document.close();
	 		writer.close();
	 		Desktop desktop = Desktop.getDesktop();
	 		File file = new File(output);
	 		//desktop.open(file);
//	 	} catch (Exception e) {
//	 		e.printStackTrace();
//	 	}
	    }

	    private void methodToDoCloseDeviation() throws Exception {
	        int count = 0;
	        boolean isRecordSelected = false;
	        WebDriverWait wait1 = new WebDriverWait(driver, 600);      
	        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#devCloseDeviationTable > div > div.jtable-busy-message[style='display: none;']")));
	   //     String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
			 String DeviationNumber = null;
			  Thread.sleep(3000);
	        isRecordSelected = selectRecdCloseDeviation(DeviationNumber, isRecordSelected, count);
	  if (isRecordSelected) {
		   sno++;
		   Thread.sleep(3000);
	       driver.findElement(By.id("commentsInDevCloseDeviationForm")).sendKeys(properties.getProperty("Description_Of_Deviation"));
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
	       sno++;
	       Thread.sleep(3000);
	       JavascriptExecutor jse = (JavascriptExecutor) driver;
	       WebElement element = driver.findElement(By.id("addSupDocsBtn_DevClosurePage"));
	        jse.executeScript("arguments[0].scrollIntoView(true);", element);

	       driver.findElement(By.id("addSupDocsBtn_DevClosurePage")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
	       sno++;
		   Thread.sleep(3000);
	       driver.findElement(By.id("uploadSuppDocInDevClosureForm_1")).sendKeys(properties.getProperty("Document-2"));
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
	       sno++;
	       Thread.sleep(3000);
	       WebElement element1 = driver.findElement(By.id("submitBtnInCloseDeviationForm"));
	        jse.executeScript("arguments[0].scrollIntoView(true);", element1);
	       driver.findElement(By.id("submitBtnInCloseDeviationForm")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	       sno++;
	       Thread.sleep(3000);
	       driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
	       sno++;
	       Thread.sleep(3000);
	       driver.findElement(By.id("subBtnInValESignInDev")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	       WebDriverWait wait = new WebDriverWait(driver, 70);
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
	       sno++;
	       Thread.sleep(3000);
	       driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	       sno++;
	       Thread.sleep(3000);
	       driver.findElement(By.className("username")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
	       sno++;
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
	       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
	          } else {
	            System.out.println("Record is not Selected");
	            Assert.assertTrue(false);
	        }
	    }
    private boolean selectRecdCloseDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
	        WebElement table = driver.findElement(By.id("devCloseDeviationTable"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr[1]/td[16]")).getText();//documentType
	            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr/td[16]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).getText();//documentTypeName
	                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
								System.out.println(DevNumberSequence);
	                            driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).click();
	                            isRecordSelected = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelected) {
	                        break;
	                    }
	                } else {
	                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr/td[16]")).getText();
	                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
							System.out.println(DevNumberSequence);
	                        driver.findElement(By.xpath("//*[@id=\"devCloseDeviationTable\"]/div/table/tbody/tr/td[16]")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#devCloseDeviationTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("devCloseDeviationTable"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
	        }
	        return isRecordSelected;
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


    


    










   
        

