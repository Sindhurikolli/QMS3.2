package com.pss.qms.DeviationWithInvAndCAPA;

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
import com.pss.qms.util.Utilities;
 

public class PostApprovalActionsReview extends DeviationLoginDetails {
 
	
	@Test
    public void toPostApprovalActionsReview()  throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionsReview"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionsReview", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			WebDriverWait wait1 = new WebDriverWait(driver, 240);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName9"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(1);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Deviation Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;  
//			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")));
//        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Deviation Module",sno,false);
//        Thread.sleep(10000);
        sno++;
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='devApprovedActions.do']")));
        driver.findElement(By.cssSelector("a[href='devApprovedActions.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On MyActivities Tab",sno,false);
//        sno++;
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("//*[@id=\"devApprovedActionsId\"]/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On PostApproval Actions Menu",sno,false);
//        Thread.sleep(10000);
//        sno++;
//        driver.findElement(By.xpath("//*[@id=\"devApprovedActionsId\"]/ul/li/a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Review Menu",sno,false);
//        Thread.sleep(10000);
//        WebDriverWait wait1 = new WebDriverWait(driver, 240);
        
        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#devApprovedActionsApprovalTable > div > div.jtable-busy-message"))));
        methodToDoDeptReviewDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}             
    }
     private void  methodToDoDeptReviewDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////      String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0081";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//        String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
         isRecordSelected = selectRecdDeptReviewDeviation(DeviationNumber, isRecordSelected, count);
       if (isRecordSelected) { 
//            driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr/td[23]/button")).click();
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            WebElement element = driver.findElement(By.id("closeBtn_DevPostApprovalActionViewWin"));
//            jse.executeScript("arguments[0].scrollIntoView(true);", element);
//            driver.findElement(By.id("closeBtn_DevPostApprovalActionViewWin")).click();
//            driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr/td[34]/button")).click();
//            driver.findElement(By.cssSelector("body > div:nth-child(43) > div.ui-dialog-titlebar.ui-widget-header.ui-corner-all.ui-helper-clearfix.ui-draggable-handle > button > span.ui-button-icon-primary.ui-icon.ui-icon-closethick")).click();
    	   sno++;
           driver.findElement(By.id("commentsInDevApprovedActionsForm")).sendKeys(properties.getProperty("Additional_Details_1500"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
           sno++;
           driver.findElement(By.id("approveBtnInApprovedActionsForm")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Accept Button",sno,false);
           sno++;
           driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
           sno++;
           driver.findElement(By.id("subBtnInValESignInDev")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
           WebDriverWait wait = new WebDriverWait(driver, 70);
           wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
           sno++;
           driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
           sno++;
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

private boolean selectRecdDeptReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("devApprovedActionsApprovalTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='devApprovedActionsApprovalTable']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr[1]/td[16]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"devApprovedActionsApprovalTable\"]/div/table/tbody/tr[1]/td[16]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#devApprovedActionsApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    
                    table = driver.findElement(By.id("devApprovedActionsApprovalTable"));//Document Tree approve table
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

    










	



