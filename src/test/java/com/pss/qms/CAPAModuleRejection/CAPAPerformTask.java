package com.pss.qms.CAPAModuleRejection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class CAPAPerformTask extends CAorPALoginDetails {

	@Test
    public void toPerformCAPAImp() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPAPerformTask"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPAPerformTask", "PSS-QMS-005",
					"Pass");
			writer.setPageEvent(event);
			document.open();

//			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText("CAPA");
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			driver.findElement(By.cssSelector("#loginform > div.buttons.col-md-offset-2 > input:nth-child(1)")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
//			Thread.sleep(3000);
//        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
//        sno++;
//        Thread.sleep(10000);
//        driver.findElement(By.id("myActivitiesInCapa")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Activities Tab", sno,false);
//        sno++;
//        Thread.sleep(20000);
//        driver.findElement(By.cssSelector("#capaImplementationListMenuId > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA implementation menu", sno,false);
//        sno++;
//        Thread.sleep(10000);
//        driver.findElement(By.cssSelector("#capaCorrectiveActListMenuId > a")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA Action sub menu", sno,false);
//        sno++;
//        Thread.sleep(10000);
        driver.findElement(By.cssSelector("a[href='caPerformTaskPage.do']")).click();
        
//        driver.findElement(By.id("caPerformTaskId")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA perform", sno,false);
        WebDriverWait wait = new WebDriverWait(driver,70);
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("jtable-busy-message"))));
//        Thread.sleep(20000);
        toCAPAImplementationPerform();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

}

    private void toCAPAImplementationPerform() throws Exception {
        int count = 0;
        sno++;
//        WebDriver driver = new FirefoxDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        boolean isRecordSelected = false;                     
        String CAPANumber = properties.getProperty("CAPAId");
        isRecordSelected = selectRecdCAPerformTaskCAPA(CAPANumber, isRecordSelected, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno,false);
//        Thread.sleep(10000);
        if (isRecordSelected) {
        	
//            Thread.sleep(5000);
            sno++;
//            WebElement element = driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
//            JavascriptExecutor js = (JavascriptExecutor)driver;
//            js.executeScript("arguments[0].click();", element);
//            driver.findElement(By.xpath("//*[@id=\"capaReviewFormContentsDiv\"]/div[3]/ul/li[2]/a"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next button", sno,false);
//            sno++;
//            Thread.sleep(3000); 
//            WebElement element4 = driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[2]"));
//            JavascriptExecutor js4 = (JavascriptExecutor)driver;
//            js4.executeScript("arguments[0].click();", element4);
//            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[2]"));
//             jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
//             WebElement element4 = driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[2]"));
//             JavascriptExecutor js4 = (JavascriptExecutor)driver;
//             js4.executeScript("arguments[0].click();", element4);
          JavascriptExecutor jse9 = (JavascriptExecutor) driver;
          WebElement element9 = driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[1]"));
          jse9.executeScript("arguments[0].scrollIntoView(true);", element9);
          Thread.sleep(2000);
          WebDriverWait waitsupp = new WebDriverWait(driver,140);
          waitsupp.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#supportingDocsJtableInCaPerform > div > div.jtable-busy-message"))));
//            Thread.sleep(12000);
            driver.findElement(By.xpath("//*[@id=\"supportingDocsJtableInCaPerform\"]/div/div[3]/div[2]/span/span[1]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add supporting documents", sno,false);
//            Thread.sleep(12000);
            sno++;
            driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno,false);
//            Thread.sleep(1000);
            
            JavascriptExecutor jse8 = (JavascriptExecutor) driver;
            WebElement element8 = driver.findElement(By.id("selectApprovedFromInCaPerform"));
            jse8.executeScript("arguments[0].scrollIntoView(true);", element8);
//            Thread.sleep(1000);
            
            
            sno++;
            driver.findElement(By.id("selectApprovedFromInCaPerform")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select the button", sno,false);
//            Thread.sleep(2000);
            sno++;
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_ico")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location", sno,false);
//            Thread.sleep(12000);
            sno++;
            WebDriverWait waitload= new WebDriverWait(driver,240);
            waitload.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("jtable-busy-message"))));
            
            boolean isRecordSelectedForApprove = false;
            String CAPALastName =properties.getProperty("PERFORM_E-Code");
            int count4=0;
            isRecordSelectedForApprove = selectingTheResponsiblePerson(CAPALastName,isRecordSelectedForApprove, count4);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno,false);
//            Thread.sleep(8000);    
            sno++;
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the user", sno,false);
//            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("commentsInCaPerform")).sendKeys(properties.getProperty("CAPA_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the comments", sno,false);
//            Thread.sleep(1000);
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            WebElement element2 = driver.findElement(By.id("corrActItmPerformSubmitBtn"));
            jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
//            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("corrActItmPerformSubmitBtn")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click onSubmit button", sno,false);
//            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the e-Signature", sno,false);
//            Thread.sleep(1000);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno,false);
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
//            Thread.sleep(3000);
//            String separateCAPANumber[] = CAPAString.split(" ");
//            System.out.println("separateCAPANumber: "+separateCAPANumber[5]);
//            String CAPANo = separateCAPANumber[5]; 
//            Thread.sleep(2000);
//            CAPANo = CAPANo.replace(")", ""); 
//            finalCAPANumber = CAPANo.trim();
//            System.out.println("finalCAPANumber: "+finalCAPANumber);
//            Thread.sleep(5000);
            sno++;
            WebDriverWait wait = new WebDriverWait(driver,240);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//            Thread.sleep(1000);
            if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
                document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Submitted Successfully ", sno,false);
            Thread.sleep(1000);
            sno++;
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,false);
//            Thread.sleep(4000);
            sno++;                       
    		driver.findElement(By.className("username")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
//    		Thread.sleep(5000);
    		sno++;
    		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectingTheResponsiblePerson(String CAPALastName, boolean isRecordSelectedForApprove, int count4) throws InterruptedException {
		
   	 WebElement table = driver.findElement(By.id("usersTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");      
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
        if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
            if (((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName)))) {
           	 CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType
            } else if ((CAPALastName == null) || ("".equalsIgnoreCase(CAPALastName))) {
           	 CAPALastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count4;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (perPageNoOfRecordsPresent > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) { 
                        String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        System.out.println("InvReviewFullName: "+InvFirstName);
                        if (CAPALastName.equalsIgnoreCase(InvFirstName)) {
                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelectedForApprove = true;
                            
                            break;
                        }
                    }
                   
                } else {                                                    
                    String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();
                    if (CAPALastName.equalsIgnoreCase(InvReviewFullName)) {
                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelectedForApprove = true;
                        
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelectedForApprove) && (noOfRecordsChecked < totalNoOfRecords)) {
                  JavascriptExecutor jse8 = (JavascriptExecutor) driver;
                  WebElement element8 = driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"));
                  jse8.executeScript("arguments[0].scrollIntoView(true);", element8);
                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled")).click();//next page in Document approve list
                    
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelectedForApprove;
    }

	private boolean selectRecdCAPerformTaskCAPA(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("actItmPerformTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	JavascriptExecutor jse10 = (JavascriptExecutor) driver;
            WebElement element10 = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/div[4]/div[2]/span"));
            
            jse10.executeScript("arguments[0].scrollIntoView(true);", element10);
        	
            String a = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                    	
                    	
                    	
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"actItmPerformTable\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	JavascriptExecutor jse9 = (JavascriptExecutor) driver;
                    WebElement element9 = driver.findElement(By.cssSelector("#actItmPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    
                    jse9.executeScript("arguments[0].scrollIntoView(true);", element9);
                    driver.findElement(By.cssSelector("#actItmPerformTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("actItmPerformTable"));//Document Tree approve table
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
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
