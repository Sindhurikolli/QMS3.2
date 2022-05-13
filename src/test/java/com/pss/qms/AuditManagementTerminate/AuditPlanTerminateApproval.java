package com.pss.qms.AuditManagementTerminate;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class AuditPlanTerminateApproval extends AMLoginDetails {
    
	   @Test
		    public void AuditPlanTerminationApproval() throws InterruptedException, IOException, DocumentException, Exception {
//				try {
					document = new Document(PageSize.A4, 36, 36, 20, 20);
				  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
					  document = new Document();
						output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AuditPlanTerminateApproval"
								+ (new Random().nextInt()) + ".pdf";
						fos = new FileOutputStream(output);

						writer = PdfWriter.getInstance(document, fos);
					
			            
						writer.open();
						HeaderFooterPageEvent event = new HeaderFooterPageEvent("AuditPlan Termination Approval", "PSS-QMS-011",
								"Pass");
						writer.setPageEvent(event);
						document.open();
				 driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
				 driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
				 input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				 driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
				 Thread.sleep(3000);
				 im = Image.getInstance(input);
					im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
					document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
					document.add(im);
					document.add(new Paragraph("                                     "));
					document.add(new Paragraph("                                     "));
					sno++;
					driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//		        sno++;
//		        driver.findElement(By.id("myActivitiesInAM")).click();
//		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
//		        sno++;
//		        driver.findElement(By.xpath("//*[@id=\"amActionItemsMenuId\"]/a")).click();
//		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Action Item Menu",sno,false);
		        sno++;
		        driver.findElement(By.xpath("//*[@id=\"auditApprovalMenus\"]/div[5]/a")).click();
		        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on AuditPlan Termination Menu",sno,false);
		        methodToDoAuditPlanTerminateApproval();
		        Thread.sleep(3000);
		        document.close();
		      		writer.close();
		      		Desktop desktop = Desktop.getDesktop();
		    		File file = new File(output);
		    		//desktop.open(file);
//		      	} catch (Exception e) {
//		      		e.printStackTrace();
//		      	}
		    }

		    private void methodToDoAuditPlanTerminateApproval() throws Exception {
		        int count = 0;
		        boolean isRecordSelected = false;
		       
		        String AuditPlanName = properties.getProperty("AUDIT_PLAN_NAME");
		        isRecordSelected = selectRecdAuditPlanTerminateApproval(AuditPlanName,isRecordSelected, count);
		        Thread.sleep(3000);
		        if (isRecordSelected) {
		            sno++;
		            driver.findElement(By.id("commentsInAuditTerminateApp")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
		            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments",sno,false);
		            sno++;
		            driver.findElement(By.id("approveBtnInAuditTerminateApp")).click();
		            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Approve Button",sno,false);
		            sno++;
		            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
		            sno++;
		            driver.findElement(By.id("subBtnInValidateESign")).click();
		            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
		            WebDriverWait wait = new WebDriverWait(driver, 70);
		            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
		            sno++;
		            driver.findElement(By.className("modal-btn")).click();
		            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
		            sno++;
			        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
			        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
			        sno++;
			        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
			        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
		        } else {
		            System.out.println("Record is not Selected");
		            Assert.assertTrue(false);
		        }
		    }

		    private boolean selectRecdAuditPlanTerminateApproval(String AuditPlanName, boolean isRecordSelected, int count) throws InterruptedException {
		        WebElement table = driver.findElement(By.id("auditTerminateAppTableContainer"));
		        WebElement tableBody = table.findElement(By.tagName("tbody"));
		        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		        int totalNoOfRecords = 0;
		        int noOfRecordsChecked = 0;
		        if (perPageNoOfRecordsPresent > 0) {
		            String a = driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
		            String[] parts = a.split(" of ");
		            try {
		                totalNoOfRecords = Integer.parseInt(parts[1].trim());
		            } catch (Exception e) {
		                System.out.println(e.getMessage());
		            }
		        }
		        if (perPageNoOfRecordsPresent > 0 && count == 0) {
		            if ((totalNoOfRecords > 1) && ((AuditPlanName == null) || ("".equalsIgnoreCase(AuditPlanName)))) {
		            	AuditPlanName = driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
		            } else if ((AuditPlanName == null) || ("".equalsIgnoreCase(AuditPlanName))) {
		            	AuditPlanName = driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

		            }
		            ++count;
		        }
		        if (perPageNoOfRecordsPresent > 0) {
		            while (noOfRecordsChecked < totalNoOfRecords) {
		                if (totalNoOfRecords > 1) {
		                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
		                        String ActionitemNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).getText();//documentTypeName
		                        if (AuditPlanName.equalsIgnoreCase(ActionitemNumberSequence)) {
		                            driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).click();
		                            isRecordSelected = true;
		                            break;
		                        }
		                    }
		                    if (isRecordSelected) {
		                        break;
		                    }
		                } else {
		                    String ActionitemNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr/td[4]")).getText();
		                    if (AuditPlanName.equalsIgnoreCase(ActionitemNumberSequence)) {
		                        driver.findElement(By.xpath("//*[@id=\"auditTerminateAppTableContainer\"]/div/table/tbody/tr/td[4]")).click();
		                        isRecordSelected = true;
		                        break;
		                    }
		                }
		                noOfRecordsChecked += perPageNoOfRecordsPresent;
		                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
		                    driver.findElement(By.cssSelector("#auditTerminateAppTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
		                    Thread.sleep(3000);
		                    table = driver.findElement(By.id("auditTerminateAppTableContainer"));//Document Tree approve table
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