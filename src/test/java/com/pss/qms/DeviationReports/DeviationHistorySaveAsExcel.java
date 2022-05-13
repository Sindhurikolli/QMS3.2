package com.pss.qms.DeviationReports;

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
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class DeviationHistorySaveAsExcel extends DeviationLoginDetails {
    
    @Test
    public void DeviationHistory() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeviationHistorySaveAsExcel"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeviationHistorySaveAsExcel", "PSS-QMS-001", "Pass");
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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.id("reportsInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation History Report Menu",sno,false);
        Thread.sleep(10000);
//         driver.findElement(By.id("Deviation Number")).sendKeys(properties.getProperty("Deviation_Number1"));
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("selLocBtnInDevHistReport")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.xpath("treeContainer_2_switch")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("treeContainer_3_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("selectBtnInLocSelect")).click();
//        Thread.sleep(5000);
//        Select changetype = new Select(driver.findElement(By.id("severity_DeviationRepSearch")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
//         driver.findElement(By.id("fromDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//         driver.findElement(By.id("toDate_DeviationRepSearch")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("documentChk_DeviationRepSearch")).click();
//        Thread.sleep(5000);
        sno++;
        driver.findElement(By.id("searchBtn_DevHistoryRepSearch")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("saveAsExcrlBtnInDevHistReport"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(4000);
        methodToDoDeviationHistory();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }

    private void methodToDoDeviationHistory() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEVIATION_NO");
//        String chgCtrlNoWithPlantCode =  DepartmentCode + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0020";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        String DevId = properties.getProperty("DEVIATION_NO_REPORTS");
        String DeviationNumber = DevId;
        isRecordSelected = selectRecdDeviationReports(DeviationNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("saveAsExcrlBtnInDevHistReport"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(6000); 
            sno++;
            driver.findElement(By.id("saveAsExcrlBtnInDevHistReport")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Save As Excel Button",sno,false);
            Thread.sleep(10000);
            sno++;
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,false);
       }


            }
        
    

    private boolean selectRecdDeviationReports(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("devHistoryReportsTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
            	DeviationNumber = driver.findElement(By.xpath(".//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
            	DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr[4]/td[3]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"devHistoryReportsTable\"]/div/table/tbody/tr[4]/td[10]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath(".//*[@id='devHistoryReportsTable']/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("devHistoryReportsTable"));//Document Tree approve table
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
