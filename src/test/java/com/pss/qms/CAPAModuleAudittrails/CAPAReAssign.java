
package com.pss.qms.CAPAModuleAudittrails;

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
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class CAPAReAssign extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPAReAssign"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPAReAssign", "PSS-QMS-072",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME3"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[5]/button[1]")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(3000);
        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
        sno++;
        driver.findElement(By.id("auidtTrailsInCapa")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on audittrails tab",sno,false);
        Thread.sleep(16000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[6]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on capa re-assign menu",sno,false);
        Thread.sleep(4000);
//        driver.findElement(By.id("searchCapaNo_CapaPlanReAssignAuditPage")).sendKeys(properties.getProperty("CAPA_NUMBER"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("deptSelBtn_CapaPlanReAssignAuditPage")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("treeContainer_5_span")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("searchFromDate_CapaPlanReAssignAuditPage")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(2000);
//        driver.findElement(By.id("searchToDate_CapaPlanReAssignAuditPage")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(2000);
        sno++;
        driver.findElement(By.id("searchBtn_CapaPlanReAssignAuditPage")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on search button",sno,false);
        Thread.sleep(10000);
        methodToDoCAPAReAssign();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

    }

    private void methodToDoCAPAReAssign() throws Exception {
    	sno++;
        int count = 0;
        boolean isRecordSelected = false;
////        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String capaSequence = properties.getProperty("CAPA_NUMBER");
////        String canumber    = properties.getProperty("CA_NUMBER");
////        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
////        String chgCtrlNoWithPlantCode = changeCtrlDept + "-" + capaSequence;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "/0012";
////        String number = "-CA-01";
        String CAPANumber = properties.getProperty("CAPAId");
        sno++;
        isRecordSelected = selectRecdCAPAReAssign(CAPANumber, isRecordSelected, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record",sno,false);
        Thread.sleep(10000);
        if (isRecordSelected) {
            Thread.sleep(3000);
            sno++;                       
	    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
	    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
	    		Thread.sleep(5000);
	    		sno++;
	    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
	    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);

//            driver.findElement(By.id("reAssSelBtnInCapaRassignForm")).click();
//            Thread.sleep(1000);
//            driver.findElement(By.id("locationTreeForAssignUserSelect_2_switch")).click();
//            Thread.sleep(4000);
//            driver.findElement(By.id("locationTreeForAssignUserSelect_3_span")).click();
//            Thread.sleep(4000);
//            driver.findElement(By.xpath("//*[@id=\"assignSelUsersTableContainer\"]/div/table/tbody/tr[2]/td[2]")).click();
//            Thread.sleep(4000);
//            driver.findElement(By.id("selBtn_InAssignUserSelectDialog")).click();
//            Thread.sleep(4000);
//            driver.findElement(By.id("reasonInCapaRassignForm")).sendKeys(properties.getProperty("Task_Description"));
//            Thread.sleep(1000);
//
//            driver.findElement(By.id("submitBtnInCapaRassignForm")).click();
//            Thread.sleep(1000);
//            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
//            Thread.sleep(1000);
//            driver.findElement(By.id("subBtnInValidateESign")).click();
//            Thread.sleep(1000);
//            WebDriverWait wait = new WebDriverWait(driver, 70);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//            Thread.sleep(1000);
//            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
//            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdCAPAReAssign(String CAPANumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("recordsTable_CapaPlanReAssignAuditPage"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber)))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((CAPANumber == null) || ("".equalsIgnoreCase(CAPANumber))) {
                CAPANumber = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
                        if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[1]/td[3]")).getText();
                    if (CAPANumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"recordsTable_CapaPlanReAssignAuditPage\"]/div/table/tbody/tr[1]/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"capaRecodsTableInCapaRassignForm\"]/div/div[4]/div[1]/span[1]/span[5]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("capaRecodsTableInCapaRassignForm"));//Document Tree approve table
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
    
