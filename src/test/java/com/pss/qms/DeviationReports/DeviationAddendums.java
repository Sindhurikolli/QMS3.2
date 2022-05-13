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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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
public class DeviationAddendums extends DeviationLoginDetails {
    
    @Test
        public void AddendumDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//    		try {

    			document = new Document(PageSize.A4, 36, 36, 20, 20);
    			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
    			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "DeviationAddendums"
    					+ (new Random().nextInt()) + ".pdf";
    			fos = new FileOutputStream(output);

    			writer = PdfWriter.getInstance(document, fos);

    			writer.open();
    			HeaderFooterPageEvent event = new HeaderFooterPageEvent("DeviationAddendums", "PSS-QMS-001", "Pass");
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
        Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
       Thread.sleep(10000);
       sno++;
       driver.findElement(By.id("reportsInDev")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
       Thread.sleep(10000);
       driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]/a")).click();
       Thread.sleep(10000);
       driver.findElement(By.id("searchBtn_DeviationRepPage")).click();
       Thread.sleep(10000);
        methodToDoDeviationAddendums();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }

    private void methodToDoDeviationAddendums() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
        String changeCtrlSequence = properties.getProperty("DEVIATION_NO");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String chgCtrlNoWithPlantCode = changeCtrlDept+ "-" + changeCtrlSequence;
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "0013";  
        String DeviationNumber =chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdDeviationAddendums(DeviationNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
            Thread.sleep(10000);
            driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr/td[10]/button")).click();
            Thread.sleep(8000);
            driver.findElement(By.id("closeBtn_DevAdndmListDtlViewDialog")).click();
            Thread.sleep(8000);
            driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr/td[11]/button")).click();
            Thread.sleep(8000);
            driver.findElement(By.id("closeBtn_DevAdndmListReviewDataViewDialog")).click();
            Thread.sleep(10000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdDeviationAddendums(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("addendumRecords_DevAddendumListPage"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr[ " + i + " ]/td[10]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr/td[4]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/table/tbody/tr/td[4]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"addendumRecords_DevAddendumListPage\"]/div/div[4]/div[1]/span[1]/span[8]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("addendumRecords_DevAddendumListPage"));//Document Tree approve table
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
 





  






