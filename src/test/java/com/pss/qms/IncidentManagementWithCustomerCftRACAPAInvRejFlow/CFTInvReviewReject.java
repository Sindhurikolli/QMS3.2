package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInvRejFlow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
 
//import com.pss.qms.login.IncidentLoginDetails;
import com.pss.qms.login.IncidentManagementLoginDetails;
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
import org.openqa.selenium.support.ui.Select;
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
public class CFTInvReviewReject extends IncidentManagementLoginDetails {
    
  @Test
    public void toCFTInvReviewReject() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CFTInvReviewReject"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CFTInvReviewReject", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CFTReviewer"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(5);
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
//			sno++;
//	   driver.findElement(By.xpath(".//*[@id='investigation_tile_Id']/div/div/div/h2")).click();
//	   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Investigation Module",sno,false);
        Thread.sleep(5000);
        sno++;
        driver.findElement(By.cssSelector("a[href='reviewFullInvPage.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Review Menu",sno,false);
        Thread.sleep(15000);
        methodToDoCFTInvestigationIncident();
        Thread.sleep(50000);
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }

    private void methodToDoCFTInvestigationIncident() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
//        WebDriverWait wait1 = new WebDriverWait(driver, 240);
//
//        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#invRecordsTable_FullInvReviewPage > div > div.jtable-busy-message"))));
        
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/div[4]/div[1]"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        String IncidentNumber = properties.getProperty("IncidentNo");
//        String DevSequence = properties.getProperty("Incident_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0071";
//        String IncidentNumber = DevNoWithPlantCode + sdf + DevId;
//        String IncidentNumberToTrim = LoginDetails.finalIncidentNumber;
//        String IncidentNumber = IncidentNumberToTrim.trim(); 
//        System.out.println("Incident Number is coming........:"+IncidentNumber);
        isRecordSelected = selectRecdCFTInvestigationIncident(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {
        	Thread.sleep(2000);
        	JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("rejActivity_FullInvReviewPage"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
        	sno++;
        	Thread.sleep(3000);
            driver.findElement(By.id("rejActivity_FullInvReviewPage")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Reject Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("comments_FullInvReviewPage")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
            Thread.sleep(2000);
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            WebElement element1 = driver.findElement(By.id("submitBtn_FullInvReviewPage"));
            jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
            Thread.sleep(1000);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("submitBtn_FullInvReviewPage")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
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
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
  private boolean selectRecdCFTInvestigationIncident(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("invRecordsTable_FullInvReviewPage"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	 WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/div[4]/div[2]/span"));
             JavascriptExecutor jsshow = (JavascriptExecutor)driver;
             jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
            String a = driver.findElement(By.xpath(".//*[@id='invRecordsTable_FullInvReviewPage']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr[1]/td[5]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr/td[5]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	//*[@id="invRecordsTable_FullInvReviewPage"]/div/table/tbody/tr/td[5]
                    	WebElement elementsele = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr[ " + i + " ]/td[5]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    	
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr/td[5]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"invRecordsTable_FullInvReviewPage\"]/div/table/tbody/tr/td[5]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#invRecordsTable_FullInvReviewPage > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("invRecordsTable_FullInvReviewPage"));//Document Tree approve table
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

