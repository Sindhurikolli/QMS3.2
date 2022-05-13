package com.pss.qms.DeviationWithRAWithCAPA;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
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

public class PostApprovalActionsInitiate extends DeviationLoginDetails {
 
	@Test
    public void PostApprovalActionsDeviation() throws InterruptedException, IOException, DocumentException, Exception {
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
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Deviation Module",sno,false);
        sno++;
        driver.findElement(By.id("deviationInDev")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On MyActivities Tab",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"devApprovedActionsId\"]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On PostApproval Actions Menu",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"devApprovedActionsId\"]/ul/li/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Initiate Menu",sno,false);
        Thread.sleep(10000);
        methodToDoPostApprovalActionsDeviationDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
	}      
//    }
      
    private void  methodToDoPostApprovalActionsDeviationDeviation() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
//        String Dept= properties.getProperty("CHG_CNTRL_DEPT"); 
        String DevSequence = properties.getProperty("DEVIATION_NO");
//      String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
        String DevNoWithPlantCode = DevSequence + "/";
        Date date = new Date();
        String sdf = new SimpleDateFormat("YY").format(date);
        String DevId = "/0081";
        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
//        String DeviationNumberToTrim = LoginDetails.finalDeviationNumber;
//        String DeviationNumber = DeviationNumberToTrim.trim(); 
//        System.out.println("Deviation Number is coming........:"+DeviationNumber);
        isRecordSelected = selectRecdPostApprovalActionsDeviationDeviation(DeviationNumber, isRecordSelected, count);
        if (isRecordSelected) {
        	sno++;
        	Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("noInvRootCauseInAppActAddCapaWin")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Root Cause",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources_devPostAppActionsForm\"]/div/table/tbody/tr[1]/td[1]/input")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources_devPostAppActionsForm\"]/div/table/tbody/tr[2]/td[1]/input")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Source",sno,false);
//            sno++;
//            Thread.sleep(2000);
//            driver.findElement(By.id("justificationInAppActAddCapaWin")).sendKeys(properties.getProperty("Description_Of_Deviation"));
//            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Justification",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("isNeedCapaChk_devPostAppActionsForm")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Is CAPA Required CheckBox",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"capaDetailsApprovedActionsTable\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("deptSelBtn_DevPostAppActAddCapaWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("capaTreeContainer_2_switch")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("capaTreeContainer_5_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("selectBtnInCapaLocSelect")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("capaDesc_DevPostAppActAddCapaWin")).sendKeys(properties.getProperty("Description_Of_Deviation"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CAPA Description",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addBtn_DevPostAppActAddCapaWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("subBtnInValESignInDev")).click();
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
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,false);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
        }

private boolean selectRecdPostApprovalActionsDeviationDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("devRecordsTable_devPostAppActionsForm"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath(".//*[@id='devRecordsTable_devPostAppActionsForm']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
  String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).getText();//documentTypeName
                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr[ " + i + " ]/td[16]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr[1]/td[16]")).getText();
                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"devRecordsTable_devPostAppActionsForm\"]/div/table/tbody/tr[1]/td[16]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#devRecordsTable_devPostAppActionsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    table = driver.findElement(By.id("devRecordsTable_devPostAppActionsForm"));//Document Tree approve table
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




