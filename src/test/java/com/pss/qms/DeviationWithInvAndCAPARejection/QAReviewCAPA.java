package com.pss.qms.DeviationWithInvAndCAPARejection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.DeviationLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;

public class QAReviewCAPA extends DeviationLoginDetails {
	  @Test
	    public void QAReview() throws InterruptedException, IOException, DocumentException, Exception {
//	    	try {

	    		document = new Document(PageSize.A4, 36, 36, 20, 20);
	    		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
	    		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewCAPA"
	    				+ (new Random().nextInt()) + ".pdf";
	    		fos = new FileOutputStream(output);

	    		writer = PdfWriter.getInstance(document, fos);

	    		writer.open();
	    		WebDriverWait wait1 = new WebDriverWait(driver, 240);
	    		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReviewCAPA", "PSS-QMS-001", "Pass");
	    		writer.setPageEvent(event);
	    		document.open();
	    		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CAPAQAREVIEWER"));
	    		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	    		Select module= new Select(driver.findElement(By.id("qmsModule")));
				module.selectByVisibleText("CAPA");
				input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
	    		Thread.sleep(3000);
	    		im = Image.getInstance(input);
	    		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
	    				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
	    		document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
	    				+ Utilities.prepareSSNumber(sno), font));
	    		document.add(im);
	    		document.add(new Paragraph("                                     "));
	    		document.add(new Paragraph("                                     "));
	    		sno++; 	  
//	        driver.findElement(By.xpath("//*[@id=\"capa_tile_Id\"]/div/div/div/h2/i")).click();
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CAPA Module",sno,false);
//	        Thread.sleep(15000);
//	        driver.findElement(By.id("deviationInDev")).click();
//	        Thread.sleep(15000);
	    		 wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='capaReviewPage.do']")));
	    	        driver.findElement(By.cssSelector("a[href='capaReviewPage.do']")).click();
	    	        Thread.sleep(5000);	    	        
	    	        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));    		
	        methodToDoQAReviewCAPA();
	        document.close();
	    	writer.close();
	    	Desktop desktop = Desktop.getDesktop();
	    	File file = new File(output);
	    	//desktop.open(file);
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }                  
	    }
	 private void methodToDoQAReviewCAPA() throws Exception {
	        int count = 0;
	        boolean isRecordSelected = false;
//	        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
	        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//	        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//	        String DevNoWithPlantCode = DevSequence + "/";
//	        Date date = new Date();
//	        String sdf = new SimpleDateFormat("YY").format(date);
//	        String DevId = "/0081";
//	        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//	      String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//	      String DeviationNumber = DeviationNumberToTrim.trim(); 
//	      System.out.println("Deviation Number is coming........:"+DeviationNumber);
	        isRecordSelected = selectRecdQAReviewCAPA(DeviationNumber, isRecordSelected, count);
	  if (isRecordSelected) {
		  	sno++;
	        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
	        sno++;
	        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
	        sno++;
	        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
	        sno++;
	        driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("Description_Of_Deviation"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
	        sno++;
	        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	        sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
	        sno++;
	        driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	        WebDriverWait wait = new WebDriverWait(driver, 70);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
	        sno++;
	        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
	        } else {
	            System.out.println("Record is not Selected");
	            Assert.assertTrue(false);
	        }
	    }
	 private boolean selectRecdQAReviewCAPA(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
	        WebElement table = driver.findElement(By.id("capaReviewTable"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");//*[@id="capaReviewTable"]/div/div[4]/div[2]/span
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[11]")).getText();//documentType
	            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[11]")).getText();//documentType

	            }//*[@id="capaReviewTable"]/div/table/tbody/tr[6]/td[9]
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[11]")).getText();//documentTypeName
	                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[11]")).click();
	                            isRecordSelected = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelected) {
	                        break;
	                    }
	                } else {
	                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[11]")).getText();
	                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[11]")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
	                   Helper.clickElement(driver, By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
//	                	driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(5000);
//	                    WebDriverWait wait1 = new WebDriverWait(driver, 240);
//	                    wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));
	                   Helper.waitLoadRecords(driver, By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']"));
	                    table = driver.findElement(By.id("capaReviewTable"));//Document Tree approve table
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
