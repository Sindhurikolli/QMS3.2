/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.PlannedSchdRejectFlow;

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

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class PlannedScheduleApprovalReject extends  AMLoginDetails {
    
	@Test
    public void PlannedSchdReject() throws InterruptedException, IOException, DocumentException, Exception {
//		try {
			document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlannedScheduleApprovalReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
		
            writer.open();
            HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlannedSchd Reject", "PSS-QMS-002",
					"Pass");
			writer.setPageEvent(event);
			document.open();
	  driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
     driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
      im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
		document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
	   driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
	   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module"+"  PSS-QMS-SS-002",sno,false);
	   sno++;
      driver.findElement(By.id("myActivitiesInAM")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab"+"  PSS-QMS-SS-003",sno,false);  
      sno++;
      driver.findElement(By.xpath("//*[@id=\"amMyActApprovalMenuId\"]/a")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approvals Menu "+"  PSS-QMS-SS-004",sno,false); 
      sno++;
      driver.findElement(By.xpath("//*[@id=\"amPlanOrAdhocAuditAppPageTag\"]/a")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Planned/Adhoc/External Audit Menu "+"  PSS-QMS-SS-005",sno,false); 
     methodToDoExternalAuditApprove();
     document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    

    private void methodToDoExternalAuditApprove() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String AuditId = properties.getProperty("PLAN_NAME_PLANNED_SCHEDULE");
        isRecordSelected = selectRecdExternalAuditApprove(AuditId, isRecordSelected, count);
        if (isRecordSelected) {
        	sno++;
            driver.findElement(By.id("commentsInAmAuditPlanApp")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false); 
            sno++;
            driver.findElement(By.id("rejectBtnInAuditPlanApp")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Reject Button",sno,false);
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
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            driver.findElement(By.xpath("html/body/div[1]/header/nav/ul[2]/li[9]/a/span/b")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username",sno,false);
            sno++;
            driver.findElement(By.xpath("html/body/div[1]/header/nav/ul[2]/li[9]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
        } else {
            System.out.println("Record is not Selected");
        }
    }

    private boolean selectRecdExternalAuditApprove(String AuditId, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("amPlanAuditAppDetailsContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((AuditId == null) || ("".equalsIgnoreCase(AuditId)))) {
            	AuditId = driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((AuditId == null) || ("".equalsIgnoreCase(AuditId))) {
            	AuditId = driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String AuditNumberSequence = driver.findElement(By.xpath(".//*[@id='amPlanAuditAppDetailsContainer']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (AuditId.equalsIgnoreCase(AuditNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String AuditNumberSequence = driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (AuditId.equalsIgnoreCase(AuditNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"amPlanAuditAppDetailsContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#amPlanAuditAppDetailsContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("amPlanAuditAppDetailsContainer"));//Document Tree approve table
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
    
 


