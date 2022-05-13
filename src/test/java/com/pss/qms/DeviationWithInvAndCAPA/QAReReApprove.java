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
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.DeviationLoginDetails;

public class QAReReApprove extends DeviationLoginDetails {
@Test
public void QAReReApproveDev() throws InterruptedException, IOException, DocumentException, Exception {
//	try {

		document = new Document(PageSize.A4, 36, 36, 20, 20);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReReApprove"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);

		writer = PdfWriter.getInstance(document, fos);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAReReApprove", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName11"));
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
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
    driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Deviation Module",sno,false);
    Thread.sleep(2000);
    sno++;
    driver.findElement(By.id("deviationInDev")).click();
    document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Review Menu",sno,false);
    Thread.sleep(300000);
    methodToDoQAReReApprove();
    document.close();
	writer.close();
	Desktop desktop = Desktop.getDesktop();
	File file = new File(output);
	//desktop.open(file);
//} catch (Exception e) {
//	e.printStackTrace();
//}      
}

private void   methodToDoQAReReApprove() throws Exception {
    int count = 0;
    boolean isRecordSelected = false;
//    String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//    String DevSequence = properties.getProperty("DEVIATION_NO");
////    String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//    String DevNoWithPlantCode = DevSequence + "/";
//    Date date = new Date();
//    String sdf = new SimpleDateFormat("YY").format(date);
//    String DevId = "/0071";
//    String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//     String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//    String DeviationNumber = DeviationNumberToTrim.trim(); 
    String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
    System.out.println("Deviation Number is coming........:"+DeviationNumber);
    isRecordSelected = selectRecdQAReReApprove(DeviationNumber, isRecordSelected, count);
    if (isRecordSelected) {
    	sno++;
        driver.findElement(By.id("comments_DevReview")).sendKeys(properties.getProperty("PROD_COMMENTS"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
        sno++;
        driver.findElement(By.id("continueBtn_DevReview")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
        sno++;
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
        sno++;
         driver.findElement(By.id("subBtnInValESignInDev")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
        sno++;
       driver.findElement(By.className("modal-btn")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
       sno++;
       driver.findElement(By.className("username")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
       sno++;
       driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);   
     } else {
        System.out.println("Record is not Selected");
        Assert.assertTrue(false);
    }
}

private boolean selectRecdQAReReApprove(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
    WebElement table = driver.findElement(By.id("devReviewTableContailner"));
    WebElement tableBody = table.findElement(By.tagName("tbody"));
    int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
    int totalNoOfRecords = 0;
    int noOfRecordsChecked = 0;
    if (perPageNoOfRecordsPresent > 0) {
        String a = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
        String[] parts = a.split(" of ");
        try {
            totalNoOfRecords = Integer.parseInt(parts[1].trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    if (perPageNoOfRecordsPresent > 0 && count == 0) {
        if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
            DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
        } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
            DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();//documentType

        }
        ++count;
    }
    if (perPageNoOfRecordsPresent > 0) {
        while (noOfRecordsChecked < totalNoOfRecords) {
            if (totalNoOfRecords > 1) {
                for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr[ " + i + " ]/td[43]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                if (isRecordSelected) {
                    break;
                }
            } else {
                String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[3]")).getText();
                if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                    driver.findElement(By.xpath("//*[@id=\"devReviewTableContailner\"]/div/table/tbody/tr/td[43]/button")).click();
                    isRecordSelected = true;
                    break;
                }
            }
            noOfRecordsChecked += perPageNoOfRecordsPresent;
            if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
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












