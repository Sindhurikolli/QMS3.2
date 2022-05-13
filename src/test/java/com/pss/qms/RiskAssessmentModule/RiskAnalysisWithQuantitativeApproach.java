package com.pss.qms.RiskAssessmentModule;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
 
import com.pss.qms.login.RALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class RiskAnalysisWithQuantitativeApproach extends RALoginDetails {
  
	 @Test
	    public void RiskAnalysis() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RiskAnalysisWithQuantitativeApproach"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("RiskAnalysisWithQuantitativeApproach", "PSS-QMS-001",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
	      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
	     Thread.sleep(3000);
	     im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
				document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
				sno++;
	          driver.findElement(By.xpath("//*[@id=\"riskAsmt_tile_Id\"]/div/div/div/h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskAssessment Module",sno,false);
//	         sno++;
//	         driver.findElement(By.id("myActivitiesInAM")).click();
//	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"riskAssmntAndReEvalGroupMenuId\"]/div[2]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RiskAnalysis Menu",sno,false);
	         JavascriptExecutor jse = (JavascriptExecutor) driver;
	         WebElement element = driver.findElement(By.xpath("//*[@id=\"riskAnalysisReviewTableContainer\"]/div/div[4]/div[1]"));
	         jse.executeScript("arguments[0].scrollIntoView(true);", element);
	         Thread.sleep(1000);
	          methodToDoRiskAnalysis();
	        Thread.sleep(3000);
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	    }

	    private void methodToDoRiskAnalysis() throws Exception {
	        int count = 0;
	        boolean isRecordSelected = false;
	        String RASequence = properties.getProperty("RA_NUMBER");
	        isRecordSelected = selectRecdRiskAnalysis(RASequence, isRecordSelected, count);
	        Thread.sleep(1000);
	        if (isRecordSelected) {
	        	sno++;
	            driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.xpath("//*[@id=\"riskPriorNumJtableInRAInit\"]/div/table/tbody/tr/td[12]/button/i")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit Record",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.id("severityCtgTypeInRPNAnalWin")).sendKeys(properties.getProperty("ENTER_SEVERITY"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Severity",sno,false);
	            sno++;
	            driver.findElement(By.id("occuranceCtgTypeInRPNAnalWin")).sendKeys(properties.getProperty("ENTER_OCCURANCE"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Occurance",sno,false);
	            sno++;
	            driver.findElement(By.id("detectionCtgTypeInRPNAnalWin")).sendKeys(properties.getProperty("ENTER_DETECTION"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Detection",sno,false);
	            sno++;
	            driver.findElement(By.id("acceptRiskYesChkInRPNAnalWin")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Accept Risk Yes RadioButton",sno,false);
	            Thread.sleep(1000);
	            JavascriptExecutor jse = (JavascriptExecutor) driver;
	            WebElement element = driver.findElement(By.id("addBtnInRiskRPNWin"));
	            jse.executeScript("arguments[0].scrollIntoView(true);", element);
	            Thread.sleep(1000);  
	            sno++;
	            driver.findElement(By.id("addBtnInRiskRPNWin")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.id("evaluateRPNBtnInRiskAnal")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Evaluate Button",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[3]/a")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
	            Thread.sleep(1000);
	            sno++;
	            driver.findElement(By.id("subBtnInValidateESign")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(driver, 70);
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
	            Thread.sleep(3000);
	            sno++;
	            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
	            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	            Thread.sleep(4000);
	            sno++;
		        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/a")).click();
		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
		        sno++;
		        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[3]/ul/li[3]/a")).click();
		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
	        } else {
	            System.out.println("Record is not Selected");
	            Assert.assertTrue(false);
	        }
	    }

	    private boolean selectRecdRiskAnalysis(String RANumber, boolean isRecordSelected, int count) throws InterruptedException {
	        WebElement table = driver.findElement(By.id("riskAnalysisReviewTableContainer"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"riskAnalysisReviewTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if ((totalNoOfRecords > 1) && ((RANumber == null) || ("".equalsIgnoreCase(RANumber)))) {
	                RANumber = driver.findElement(By.xpath("//*[@id=\"riskAnalysisReviewTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((RANumber == null) || ("".equalsIgnoreCase(RANumber))) {
	                RANumber = driver.findElement(By.xpath("//*[@id=\"riskAnalysisReviewTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String RANumberSequence = driver.findElement(By.xpath(".//*[@id='riskAnalysisReviewTableContainer']/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
	                        if (RANumber.equalsIgnoreCase(RANumberSequence)) {
	                            driver.findElement(By.xpath(".//*[@id='riskAnalysisReviewTableContainer']/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
	                            isRecordSelected = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelected) {
	                        break;
	                    }
	                } else {
	                    String RANumberSequence = driver.findElement(By.xpath(".//*[@id='riskAnalysisReviewTableContainer']/div/table/tbody/tr/td[3]")).getText();
	                    if (RANumber.equalsIgnoreCase(RANumberSequence)) {
	                        driver.findElement(By.xpath(".//*[@id='riskAnalysisReviewTableContainer']/div/table/tbody/tr/td[3]")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#riskAnalysisReviewTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("riskAnalysisReviewTableContainer"));//Document Tree approve table
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
			if (ITestResult.FAILURE == result.getStatus()) {
				Utility.captureScreenshot(driver, result.getName());
			}

		}
	}
