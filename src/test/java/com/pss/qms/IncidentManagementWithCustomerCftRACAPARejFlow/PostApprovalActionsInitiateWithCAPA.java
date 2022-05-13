package com.pss.qms.IncidentManagementWithCustomerCftRACAPARejFlow;

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

import com.pss.qms.login.IncidentManagementLoginDetails;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class PostApprovalActionsInitiateWithCAPA extends IncidentManagementLoginDetails{
 
	@Test
    public void PostApprovalActionsIncident() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PostApprovalActionsInitiate"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PostApprovalActionsInitiate", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			 WebDriverWait wait1 = new WebDriverWait(driver, 240);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("PostApprovalActionsInitiator"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByVisibleText(properties.getProperty("IncidentModule"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password, Select Incident Management Module and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;  
		wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='initiateIncApprovedActions.do']")));
        driver.findElement(By.cssSelector("a[href='initiateIncApprovedActions.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Post Approval Actions initiate",sno,false);
        Thread.sleep(2000);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#incRecordsTable_incPostAppActionsForm > div > div.jtable-busy-message[style='display: none;']")));
        methodToDoPostApprovalActionsInitiation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}      
//    }
      
    private void  methodToDoPostApprovalActionsInitiation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        String IncidentNumber = properties.getProperty("IncidentNo");
        isRecordSelected = selectRecdPostApprovalActionsIncident(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {
        	sno++;
        	Thread.sleep(1000);
        	JavascriptExecutor jse = (JavascriptExecutor)driver;
        	jse.executeScript("window.scrollBy(0,500)");
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            Thread.sleep(1000);
            driver.findElement(By.id("noInvRootCauseInAppActAddCapaWin")).sendKeys(properties.getProperty("RootCause_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Root Cause",sno,false);
            sno++;
            Thread.sleep(1000);
            jse.executeScript("window.scrollBy(0,100)");
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources_incPostAppActionsForm\"]/div/table/tbody/tr[1]/td[1]/input")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources_incPostAppActionsForm\"]/div/table/tbody/tr[2]/td[1]/input")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Source",sno,false);
//            sno++;
//            Thread.sleep(2000);
//            driver.findElement(By.id("justificationInAppActAddCapaWin")).sendKeys(properties.getProperty("Description_Of_Deviation"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Justification",sno,false);
            sno++;
            Thread.sleep(2000);
            jse.executeScript("window.scrollBy(0,500)");
            driver.findElement(By.id("isNeedCapaChk_incPostAppActionsForm")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Is CAPA Required CheckBox",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"capaDetailsApprovedActionsTable\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("deptSelBtn_IncPostAppActAddCapaWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("capaTreeContainer_2_switch")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText(properties.getProperty("CAPADepartment"))).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("selectBtnInCapaLocSelect")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("capaDesc_IncPostAppActAddCapaWin")).sendKeys(properties.getProperty("CAPADescription_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CAPA Description",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addBtn_IncPostAppActAddCapaWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("eSignPwdInIncWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("subBtnInValESignInInc")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true); 
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
        }

private boolean selectRecdPostApprovalActionsIncident(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("incRecordsTable_incPostAppActionsForm"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='incRecordsTable_incPostAppActionsForm']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
                IncidentNumber = driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).getText();//documentTypeName
                        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr[1]/td[16]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"incRecordsTable_incPostAppActionsForm\"]/div/table/tbody/tr[1]/td[16]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#incRecordsTable_incPostAppActionsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    table = driver.findElement(By.id("incRecordsTable_incPostAppActionsForm"));//Document Tree approve table
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




