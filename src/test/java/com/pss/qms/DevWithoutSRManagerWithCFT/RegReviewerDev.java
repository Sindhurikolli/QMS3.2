package com.pss.qms.DevWithoutSRManagerWithCFT;

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

public class RegReviewerDev extends DeviationLoginDetails {
 
	@Test
    public void RegReviewerDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RegReviewDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RegReviewDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName13"));
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
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(40000);
//        driver.findElement(By.id("deviationInDev")).click();
//        Thread.sleep(25000);
        methodToDoRegReviewerDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}     
//    }

    private void methodToDoRegReviewerDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0068";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//         String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdRegReviewDeviation(DeviationNumber, isRecordSelected, count);
       if (isRecordSelected) {
    	   sno++;
    	   Thread.sleep(3000);
            driver.findElement(By.id("imp_Yes_DevRequlatoryReviewPage")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("impactDesc_DevRequlatoryReviewPage")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Describe Impact",sno,false);
            sno++;
            Thread.sleep(3000);
           driver.findElement(By.id("comments_DevRequlatoryReviewPage")).sendKeys(properties.getProperty("Description_Of_Deviation"));
           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
           JavascriptExecutor jse = (JavascriptExecutor) driver;
           WebElement element = driver.findElement(By.id("submitBtn_DevRequlatoryReviewPage"));
           jse.executeScript("arguments[0].scrollIntoView(true);", element);
           Thread.sleep(1000);
           sno++;
           Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInDevRGLReview\"]/div/div[3]/div[2]/span/span[2]/b")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Supporting Documents",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("uploadSuppDocRGLRev_1")).sendKeys(properties.getProperty("Document-1"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload The Document",sno,false);
          JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            WebElement element1 = driver.findElement(By.id("submitBtn_DevRequlatoryReviewPage"));
            jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
            sno++;
            Thread.sleep(3000);
         driver.findElement(By.id("submitBtn_DevRequlatoryReviewPage")).click();
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
             Thread.sleep(3000);
             driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);         
         } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
  private boolean selectRecdRegReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("devRequlatoryReviewRecordsTable"));
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
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if (((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr/td[2]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            //while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr[ " + i + " ]/td[18]")).getText();//documentTypeName
                        System.out.println("cftReviewerFullName: "+cftReviewerFullName);
                        if (DeviationNumber.equalsIgnoreCase(cftReviewerFullName)) {
                            driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr[ " + i + " ]/td[18]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                   
                } else {
                    String cftReviewerFullName = driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr/td[18]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(cftReviewerFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"devRequlatoryReviewRecordsTable\"]/div/table/tbody/tr/td[18]")).click();
                        isRecordSelected = true;
                        
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

