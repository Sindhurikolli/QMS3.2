package com.pss.qms.DeviationWithoutInvWithCAPA;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.DeviationLoginDetails;

public class QMSCoOrdinatorDev extends DeviationLoginDetails {

	@Test
    public void QAReviewerDeviation() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QMSCoOrdinatorDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("QMSCoOrdinatorDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName10"));
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
			Thread.sleep(2000);
			sno++;	
			driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Deviation Module",sno,false);
			sno++;
			Thread.sleep(10000);
			driver.findElement(By.id("deviationInDev")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);
			Thread.sleep(50000);
			methodToDoQAReviewerDeviation();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//			} catch (Exception e) {
//			e.printStackTrace();
			}   
//	}

    private void methodToDoQAReviewerDeviation() throws Exception {
        int count = 0;
        Thread.sleep(2000);
        boolean isRecordSelected = false;
////        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
        String DevSequence = properties.getProperty("DEVIATION_NO");
//        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String DevNoWithPlantCode =  DevSequence + "/";
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String DevId = "/0058";
        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//         String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdQAReviewDeviation(DeviationNumber, isRecordSelected, count);
        Thread.sleep(8000);
        if (isRecordSelected) {
        	sno++;
        	Thread.sleep(3000);
        	driver.findElement(By.xpath("//*[@id=\"imptMrktsTableForAddInQAReview\"]/div/div[3]/div[2]/span/span[2]")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Market Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.id("searchBtnInAddMarketWin")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@id=\"marketTableInAddMarketWindow\"]/div/table/tbody/tr/td[1]/input")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Market",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.id("selBtnInAddMarketWin")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@id=\"imptCustsTableForAddInQAReview\"]/div/div[3]/div[2]/span")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Customer Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.id("searchBtnInAddCustomerWin")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Search Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@id=\"CustomersTableInAddCustomerWindow\"]/div/table/tbody/tr/td[1]/input")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Customer",sno,false);
            sno++;
        	Thread.sleep(2000);
        	driver.findElement(By.id("selBtnInAddCustomerWin")).click();
        	document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        	sno++;
        	Thread.sleep(2000);
            Select Categoryofdeviation = new Select(driver.findElement(By.id("severityInDevPrim")));
            Categoryofdeviation.selectByVisibleText(properties.getProperty("Category_Of_Change"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Category Of Deviation",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("repeatYesCheckBox")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Repeat Issue Yes RadioButton",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("associatePriorIssueForRepeatedIssue")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Manual Entry RadioButton",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("thresholdOfRepeatIssue")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Threshold Of Repeat Isuue",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("thresholdMonthsInQAReview")).sendKeys(properties.getProperty("LAST_MONTHS"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Months",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("assosciatePriorIssuesInQAReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Associate Prior Issues",sno,false);
            Thread.sleep(3000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("justifyForNoInv_DevQaPrimReview"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("justifyForNoInv_DevQaPrimReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Justification For No Investigation",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("commentsInDeviationQaReview")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments",sno,false);
            Thread.sleep(3000);
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            WebElement element2 = driver.findElement(By.id("continueBtn_QaPrimaryReview"));
            jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("continueBtn_QaPrimaryReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Continue Button",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("isOptionalStgRequiredChk_devReviewWfStageUsers")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Is Optional Stage Required CheckBox",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("submit_DevReviewStageDetails")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.id("subBtnInValESignInDev")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            Thread.sleep(3000);
            sno++;
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,false);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
    

    private boolean selectRecdQAReviewDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
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
                        String DevNumberSequence = driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath(".//*[@id='devReviewTableContailner']/div/table/tbody/tr[ " + i + "]/td[43]/button")).click();
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
                    Thread.sleep(3000);
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
  







