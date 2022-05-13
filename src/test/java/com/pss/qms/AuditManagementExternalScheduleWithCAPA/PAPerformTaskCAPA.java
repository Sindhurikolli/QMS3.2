package com.pss.qms.AuditManagementExternalScheduleWithCAPA;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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

public class PAPerformTaskCAPA extends AMLoginDetails {
	 
	 @Test
	    public void methodtodoPAPerformTaskCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PAPerformTaskCAPA"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("PAPerformTaskCAPA", "PSS-QMS-012",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
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
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"capaImplementationListMenuId\"]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Implementation Menu",sno,false);
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"capaPreventiveActListMenuId\"]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Preventive Actions Menu",sno,false);
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"paPerformTaskMenuId\"]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Perform Task Menu",sno,false);
	        todoPAPerformTaskCAPA();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }
	    

	    private void todoPAPerformTaskCAPA() throws Exception {
	      
	    	 int count = 0;
	         boolean isRecordSelected = false;
	         String CAPANumber = properties.getProperty("CAPA_NUMBER_AM");
	         isRecordSelected = selectRecdPAPerformTaskCAPA(CAPANumber, isRecordSelected, count);
	         if (isRecordSelected) {
	        	 sno++;
	             driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInPaPerform\"]/div/div[3]/div[2]/span")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button",sno,false);  
	             sno++;
	             driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Document",sno,false);
	             sno++;
	             driver.findElement(By.id("selectApprovedFromInPaPerform")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Document",sno,false);
	             sno++;
	             driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
	             Thread.sleep(2000);
	             driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
	             boolean isRecordSelectedForUser = false;
	             String UserFirstName = properties.getProperty("USER_CA");
	           String UserFullName = UserFirstName;
	           int count1=0;
	           isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
	           sno++;
	           driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
	           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false); 
	             sno++;
	             driver.findElement(By.id("commentsInPrevActPerform")).sendKeys(properties.getProperty("Task_Desc_2000"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);  
	             sno++;
	             driver.findElement(By.id("prevActPerformSubmitBtn")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
//	             WebDriverWait wait = new WebDriverWait(driver, 60);
	             sno++;
	             driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The E-Signature Password",sno,false);
	             sno++;
	             driver.findElement(By.id("subBtnInValidateESign")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
	             sno++;
	             driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             sno++;
	             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             sno++;
	             driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	             }
	       

	    }

		private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
			
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
		        if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
		            if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
		            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
		            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
		            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

		            }
		            ++count1;
		        }
		        if (perPageNoOfRecordsPresent > 0) {
		            while (noOfRecordsChecked < totalNoOfRecords) {
		                if (totalNoOfRecords > 1) {
		                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
		                        String UserNumberSequence = driver.findElement(By.xpath(".//*[@id='usersTableContainer']/div/table/tbody/tr[ " + i + "]/td[1]")).getText();//documentTypeName
		                        if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
		                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
		                            isRecordSelectedForUser = true;
		                            break;
		                        }
		                    }
		                    if (isRecordSelectedForUser) {
		                        break;
		                    }
		                } else {
		                    String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
		                    if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
		                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
		                        isRecordSelectedForUser = true;
		                        break;
		                    }
		                }
		                noOfRecordsChecked += perPageNoOfRecordsPresent;
		                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
		                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
		                    Thread.sleep(3000);
		                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
		                    tableBody = table.findElement(By.tagName("tbody"));
		                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		                }
		            }
		        }
		        return isRecordSelectedForUser;
		    }
		
		


		private boolean selectRecdPAPerformTaskCAPA(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
			  WebElement table = driver.findElement(By.id("prevActPerformTable"));
		        WebElement tableBody = table.findElement(By.tagName("tbody"));
		        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		        int totalNoOfRecords = 0;
		        int noOfRecordsChecked = 0;
		        if (perPageNoOfRecordsPresent > 0) {
		            String a = driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
		            String[] parts = a.split(" of ");
		            try {
		                totalNoOfRecords = Integer.parseInt(parts[1].trim());
		            } catch (Exception e) {
		                System.out.println(e.getMessage());
		            }
		        }
		        if (perPageNoOfRecordsPresent > 0 && count == 0) {
		            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
		            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
		            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
		            	CAPANumber = driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

		            }
		            ++count;
		        }
		        if (perPageNoOfRecordsPresent > 0) {
		            while (noOfRecordsChecked < totalNoOfRecords) {
		                if (totalNoOfRecords > 1) {
		                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
		                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='prevActPerformTable']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
		                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
		                            driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
		                            isRecordSelected = true;
		                            break;
		                        }
		                    }
		                    if (isRecordSelected) {
		                        break;
		                    }
		                } else {
		                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/table/tbody/tr/td[3]")).getText();
		                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
		                        driver.findElement(By.xpath("//*[@id=\"prevActPerformTable\"]/div/table/tbody/tr/td[3]")).click();
		                        isRecordSelected = true;
		                        break;
		                    }
		                }
		                noOfRecordsChecked += perPageNoOfRecordsPresent;
		                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
		                    driver.findElement(By.cssSelector("#prevActPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
		                    Thread.sleep(3000);
		                    table = driver.findElement(By.id("prevActPerformTable"));//Document Tree approve table
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