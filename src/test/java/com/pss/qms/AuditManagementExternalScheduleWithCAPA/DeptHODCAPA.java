package com.pss.qms.AuditManagementExternalScheduleWithCAPA;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class DeptHODCAPA extends AMLoginDetails {
 
	 @Test
	    public void methodtodoDeptHODCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeptHODCAPA"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeptHODCAPA", "PSS-QMS-008",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CAPA_HOD"));
	      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	     driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
	     im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
				document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
				sno++;
	          driver.findElement(By.xpath("//*[@id=\"capa_tile_Id\"]/div/div/div/h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Module",sno,false);
	         sno++;
	         driver.findElement(By.id("myActivitiesInCapa")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
	        todoDeptHODCAPA();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }
	    

	    private void todoDeptHODCAPA() throws Exception {
	      
	    	 int count = 0;
	         boolean isRecordSelected = false;
	         String CAPANumber = properties.getProperty("CAPA_NUMBER_AM");
	         isRecordSelected = selectRecdDeptHODCAPA(CAPANumber, isRecordSelected, count);
	         if (isRecordSelected) {
	        	 sno++;
	             driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);  
	             sno++;
	             driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);  
	             sno++;
	             driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("Task_Description"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);  
	             sno++;
	             driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	             WebDriverWait wait = new WebDriverWait(driver, 60);
	             sno++;
	             wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
	             sno++;
	             driver.findElement(By.id("subBtnInValidateESign")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	             sno++;
	             driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-buttons > a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             sno++;
	             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             sno++;
	             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             }
	       

	    }

		private boolean selectRecdDeptHODCAPA(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
			  WebElement table = driver.findElement(By.id("capaReviewTable"));
		        WebElement tableBody = table.findElement(By.tagName("tbody"));
		        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		        int totalNoOfRecords = 0;
		        int noOfRecordsChecked = 0;
		        if (perPageNoOfRecordsPresent > 0) {
		            String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
		            String[] parts = a.split(" of ");
		            try {
		                totalNoOfRecords = Integer.parseInt(parts[1].trim());
		            } catch (Exception e) {
		                System.out.println(e.getMessage());
		            }
		        }
		        if (perPageNoOfRecordsPresent > 0 && count == 0) {
		            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
		            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
		            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
		            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

		            }
		            ++count;
		        }
		        if (perPageNoOfRecordsPresent > 0) {
		            while (noOfRecordsChecked < totalNoOfRecords) {
		                if (totalNoOfRecords > 1) {
		                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
		                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[5]")).getText();//documentTypeName
		                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
		                            driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
		                            isRecordSelected = true;
		                            break;
		                        }
		                    }
		                    if (isRecordSelected) {
		                        break;
		                    }
		                } else {
		                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[5]")).getText();
		                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
		                        driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[5]")).click();
		                        isRecordSelected = true;
		                        break;
		                    }
		                }
		                noOfRecordsChecked += perPageNoOfRecordsPresent;
		                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
		                    driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
		                    Thread.sleep(3000);
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

