package com.pss.qms.DeviationWithInvAndCAPA;

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

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class QAApproverCAPA extends DeviationLoginDetails {
  @Test
    public void toQAApproverCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//    	try {

    		document = new Document(PageSize.A4, 36, 36, 20, 20);
    		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
    		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAApproverCAPA"
    				+ (new Random().nextInt()) + ".pdf";
    		fos = new FileOutputStream(output);

    		writer = PdfWriter.getInstance(document, fos);

    		writer.open();
    		HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAApproverCAPA", "PSS-QMS-001", "Pass");
    		writer.setPageEvent(event);
    		document.open();
    		WebDriverWait wait1 = new WebDriverWait(driver, 60);
    		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CAPAQAHOD"));
    		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
    		Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText("CAPA");;
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
    		
    		Thread.sleep(5000);
    		im = Image.getInstance(input);
    		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
    				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
    		document.add(new Paragraph(sno + "." + "Enter the username, password, Select CAPA Module and click on login button"
    				+ Utilities.prepareSSNumber(sno), font));
    		document.add(im);
    		document.add(new Paragraph("                                     "));
    		document.add(new Paragraph("                                     "));
    		sno++; 	  
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='capaReviewPage.do']")));
        driver.findElement(By.cssSelector("a[href='capaReviewPage.do']")).click();
        Thread.sleep(2000);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));       
        methodToDoQAApproverCAPA();
        document.close();
    	writer.close();
    	Desktop desktop = Desktop.getDesktop();
    	File file = new File(output);
    	//desktop.open(file);
//    } catch (Exception e) {
//    	e.printStackTrace();
//    }                  
    }
 private void methodToDoQAApproverCAPA() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
// WebDriverWait wait1 = new WebDriverWait(driver, 240);
//        
//        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message"))));
        String DeviationNumber = properties.getProperty("DEVIATION_NUMBER");
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String DevSequence = properties.getProperty("DEVIATION_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0040";
//        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//      String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//      String DeviationNumber = DeviationNumberToTrim.trim(); 
//      System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdQAApproveCAPA(DeviationNumber, isRecordSelected, count);
        if (isRecordSelected) {
	    sno++;
        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
        sno++;
        driver.findElement(By.id("commentsInCapaReviewForm")).sendKeys(properties.getProperty("Description_Of_Deviation"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
        Thread.sleep(2000);
        Robot robot = new Robot();
        for (int i = 0; i < 1; i++)
        { 
        robot.keyPress(KeyEvent.VK_CONTROL);
             robot.keyPress(KeyEvent.VK_SUBTRACT);
             robot.keyRelease(KeyEvent.VK_SUBTRACT);
             robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        Thread.sleep(4000);
        sno++;
        WebElement element = driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);

//        driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        sno++;
        WebElement element1 = driver.findElement(By.id("eSignPwdInWnd"));
//        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element1);
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
        sno++;
        WebElement element2 = driver.findElement(By.id("subBtnInValidateESign"));
//        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element2);
    //    driver.findElement(By.id("subBtnInValidateESign")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
        sno++;
        WebElement element3 = driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a"));
        js.executeScript("arguments[0].click();", element3);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
        Thread.sleep(3000);
        sno++;
        Thread.sleep(3000);
        WebElement element4 =   driver.findElement(By.className("username"));
        js.executeScript("arguments[0].click();", element4);
       
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
        sno++;
        Thread.sleep(3000);
        WebElement element5 = driver.findElement(By.cssSelector("a[href='Logout.do']"));
        js.executeScript("arguments[0].click();", element5);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);  
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }
 private boolean selectRecdQAApproveCAPA(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("capaReviewTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span"));
            JavascriptExecutor jsshow = (JavascriptExecutor)driver;
            jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
            String a = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[1]/td[10]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[10]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='capaReviewTable']/div/table/tbody/tr[ " + i + "]/td[10]"));
                        JavascriptExecutor jssel = (JavascriptExecutor)driver;
                        jssel.executeScript("arguments[0].scrollIntoView(true);", elementsele);
                    	Thread.sleep(4000);
                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[10]")).getText();//documentTypeName
                        Thread.sleep(4000);
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        	Thread.sleep(4000);
                            driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr[ " + i + " ]/td[10]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[10]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"capaReviewTable\"]/div/table/tbody/tr/td[10]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	
                	WebElement elementnext = driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#capaReviewTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(5000);
                    WebDriverWait wait1 = new WebDriverWait(driver, 240);
                    wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#capaReviewTable > div > div.jtable-busy-message[style='display: none;']")));
                    table = driver.findElement(By.id("capaReviewTable"));//Document Tree approve table
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
