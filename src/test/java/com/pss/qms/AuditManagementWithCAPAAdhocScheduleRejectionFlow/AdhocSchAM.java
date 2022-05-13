/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.AuditManagementWithCAPAAdhocScheduleRejectionFlow;

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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

/**
 *
 * @author likhitha
 */
public class AdhocSchAM extends AMLoginDetails {

	@Test
	public void methodtoPlanScheduleAM() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlannedScheduleAM"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlannedSchedule", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByVisibleText(properties.getProperty("AMModule"));
			Thread.sleep(1000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[6]/button[1]")).click();
			Thread.sleep(4000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(
					sno + "." + "Enter the username, password, Select Audit Management Module and click on login button"
							+ Utilities.prepareSSNumber(sno),
					font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
//			Thread.sleep(10000);
//			sno++;
//          driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amAdhocAuditPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Adhoc internal audit schedule preparation", sno, false);
			todoPlanScheduleAM();
			Thread.sleep(5000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private void todoPlanScheduleAM() throws Exception {

		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("planNameInAmAdhocAuditForm"))
				.sendKeys(properties.getProperty("ADHOC_NAME_IN_ADHOC_SHCEDULE_REJECT_FLOW"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Plan Name", sno, false);
		Thread.sleep(2000);
//        sno++;
//        driver.findElement(By.xpath("//*[@id=\"plannedAuditsInAmPlannedAuditForm\"]/div/div[3]/div[2]/span/span[2]")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Plan Button",sno,false); 
		sno++;
		driver.findElement(By.id("locSelBtn_AmAdhocAuditForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("treeContainer_3_span")).click();
		sno++;
		driver.findElement(By.id("selectBtnInLocSelect")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
		Thread.sleep(2000);
//        sno++;
//        Select Frequency = new Select(driver.findElement(By.id("frequencyInAmPlannedAuditForm")));
//        Frequency.selectByIndex(1);
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Frequency",sno,false);
		sno++;
		Select Month = new Select(driver.findElement(By.id("monthInAmAdhocAuditForm")));
		Month.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Month", sno, false);
//        sno++;
//        driver.findElement(By.id("addBtnInAmPlanForm")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(
				By.xpath("//*[@id=\"checkListsContainerInAmAdhocAuditForm\"]/div/div[3]/div[2]/span/span[2]")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Checklist", sno, false);
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"approvedCheckListsInAmPlannedAuditForm\"]/div/table/tbody/tr[12]/td[2]"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(1000);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"approvedCheckListsInAmAdhocAuditForm\"]/div/table/tbody/tr[1]/td[2]"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The CheckList", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("addBtnInAddCheckList")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button", sno, false);
		Thread.sleep(2000);
		sno++;
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		jse.executeScript("arguments[0].scrollIntoView(true);", element1);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(2000);
		sno++;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
		Thread.sleep(5000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Ok Button", sno, false);
		driver.findElement(By.className("modal-btn")).click();
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/a/span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[3]/li[4]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);
		
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
