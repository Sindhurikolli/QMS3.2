package com.pss.qms.DeviationWithInvAndCAPA;

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
public class SubmitSaveAsRecordDev extends DeviationLoginDetails {
  
    @Test
    public void methodtoSubmitSaveAsDev() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "SubmitSaveAsRecordDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("SubmitSaveAsRecordDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName8"));
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
    	 driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
    	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.id("deviationInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
        Thread.sleep(8000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"devSaveAsDraftMenu_Id\"]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on SaveAsDraft Menu",sno,false);
        toSubmitSaveAsDev();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

    private void toSubmitSaveAsDev() throws Exception {
        Thread.sleep(5000);
        int count = 0;
        boolean isRecordSelected = false;
//        String Dept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String DeviationShortCode = properties.getProperty("DEVIATION_NO");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String NoWithPlantCode =Dept + "-" + DeviationShortCode;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
        String DevId = "1";
        String DeviationNumber = DevId;
//         String DeviationNumberToTrim = logindetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
//        Thread.sleep(4000);
        isRecordSelected = selectRecdDeviation(DeviationNumber, isRecordSelected, count);
        if (isRecordSelected) {
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            WebElement element1 = driver.findElement(By.id("submitIdInDecReject"));
            jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addDocumentsInDevReject")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Documents",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("locTreeForAddDocuments_2_switch")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("locTreeForAddDocuments_3_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"documentsWidowDetialsTableContainer\"]/div/table/tbody/tr/td[2]")).click();
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addBtnInDocumentAdd")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false); 
            String deviationString="";
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("submitIdInDecReject")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("subBtnInValESignInDev")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false); 
            deviationString = driver.findElement(By.cssSelector("#modal-window > div > div > div.modal-text > center")).getText();
            String separateDeviationNumber[] = deviationString.split(" ");
            System.out.println("separateDeviationNumber: "+separateDeviationNumber[4]);
            String deviationNo = separateDeviationNumber[4]; 
            deviationNo = deviationNo.replace(")", ""); 
//            finalDeviationNumber = deviationNo.trim();
//            System.out.println("finalDeviationNumber: "+finalDeviationNumber);
            sno++;
            Thread.sleep(2000);
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

private boolean selectRecdDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("devDraftTable"));
//        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = driver.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr[ " + i + " ]/td[5]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr[1]/td[1]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"devDraftTable\"]/div/table/tbody/tr[1]/td[5]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#devDraftTable> div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    table = driver.findElement(By.id("devDraftTable"));//Document Tree approve table
//                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = driver.findElements(By.tagName("tr")).size();
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

    






        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//      
//     