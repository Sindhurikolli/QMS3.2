package com.pss.qms.DeviationWithRAWithoutCAPA;

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
import com.pss.qms.login.DeviationLoginDetails;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class InvestigationPlan extends DeviationLoginDetails {
 
	
	@Test
    public void InvestigationDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvestigationPlanDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvestigationPlanDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName9"));
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
        driver.findElement(By.xpath(".//*[@id='investigation_tile_Id']/div/div/div/h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Module",sno,false);
        Thread.sleep(30000);
        methodToDoInvestigationDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}
//    }

    private void methodToDoInvestigationDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode =DevSequence +"/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0068";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
         isRecordSelected = selectRecdInvestigationDeviation(DeviationNumber, isRecordSelected, count);
       if (isRecordSelected) {
    	   sno++;
    	   Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);
          sno++;
          Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id=\"userSelectionForInv\"]/div/div[3]/div[2]/span/span[2]")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button",sno,false);
          sno++;
          Thread.sleep(2000);
          driver.findElement(By.id("locTreeInQmsWfReg_2_switch")).click();
          Thread.sleep(2000);
//          driver.findElement(By.id("locTreeInQmsWfReg_3_span")).click();
          WebElement element = driver.findElement(By.id("locTreeInQmsWfReg_3_span"));
          JavascriptExecutor js = (JavascriptExecutor)driver;
          js.executeScript("arguments[0].click();", element);  
          
          Thread.sleep(2000);
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
      boolean isRecordSelectedForInv = false;
         String InvLastName =properties.getProperty("lastName_InCreateUser1");
            int count4=0;
            isRecordSelectedForInv = selectingTheInvReview(InvLastName,isRecordSelectedForInv, count4);
            sno++;
            Thread.sleep(2000);
        driver.findElement(By.id("selectBrowse1Button")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button",sno,false);
        sno++;
        Thread.sleep(2000);
             driver.findElement(By.id("submitBtn_InvPlan")).click();
             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit Button",sno,false);
             sno++;
             Thread.sleep(2000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(2000);
      driver.findElement(By.id("subBtnInValidateESign")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
         WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[6]/a/span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[6]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);      
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdInvestigationDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("investigationPlanTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='investigationPlanTableContainer']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer'\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer'\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer\"]/div/table/tbody/tr/td[6]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"investigationPlanTableContainer\"]/div/table/tbody/tr/td[6]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#investigationPlanTableContainer' > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();
                    table = driver.findElement(By.id("investigationPlanTableContainer'"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
    }

    private boolean selectingTheInvReview(String InvLastName, boolean recordSelectedForInv, int count4) {
          WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
            if (((InvLastName == null) || ("".equalsIgnoreCase(InvLastName)))) {
                InvLastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
            } else if ((InvLastName == null) || ("".equalsIgnoreCase(InvLastName))) {
                InvLastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            //while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (InvLastName.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                            recordSelectedForInv = true;
                            break;
                        }
                    }
                   
                } else {
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[4]/td[1]")).getText();
                    if (InvLastName.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[4]/td[1]")).click();
                        recordSelectedForInv = true;
                        
                    }
                }
        }
        return recordSelectedForInv;
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





