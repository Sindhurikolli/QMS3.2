/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.qms.PlannedSchdRejectFlow;

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
public class PlannedScheduleAuditManagement extends AMLoginDetails {
    
     @Test
    public void methodtoPlanScheduleAM() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
	  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "PlannedScheduleAM"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			
            
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("PlannedSchedule", "PSS-QMS-001",
					"Pass");
			writer.setPageEvent(event);
			document.open();
      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("UserName10"));
      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
     im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
			document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
          driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
         sno++;
         driver.findElement(By.id("myActivitiesInAM")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
        todoPlanScheduleAM();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        }

    private void todoPlanScheduleAM() throws Exception {
    	sno++;
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next Button",sno,false);  
        sno++;
        driver.findElement(By.id("planNameInAmPlannedAuditForm")).sendKeys(properties.getProperty("PLAN_NAME_PLANNED_SCHEDULE"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Plan Name",sno,false); 
        sno++;
        driver.findElement(By.xpath("//*[@id=\"plannedAuditsInAmPlannedAuditForm\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Plan Button",sno,false); 
        sno++;
        driver.findElement(By.id("selectBtnInDeptSelInAuditPlan")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        driver.findElement(By.id("treeContainer_2_switch")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("treeContainer_3_ico")).click();
        sno++;
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
        sno++;
        Select Frequency = new Select(driver.findElement(By.id("frequencyInAmPlannedAuditForm")));
        Frequency.selectByIndex(1);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Frequency",sno,false);
        sno++;
        Select Month = new Select(driver.findElement(By.id("monthInAmPlannedAuditForm")));
        Month.selectByIndex(1);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Month",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInAmPlanForm")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"checkListsContainerInAmPlannedAuditForm\"]/div/div[3]/div[2]/span/span[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Checklist",sno,false);
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"approvedCheckListsInAmPlannedAuditForm\"]/div/table/tbody/tr[12]/td[2]"));
//        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//        Thread.sleep(1000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"approvedCheckListsInAmPlannedAuditForm\"]/div/table/tbody/tr/td[2]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The CheckList",sno,false);
        sno++;
        driver.findElement(By.id("addBtnInAddCheckList")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        sno++;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
        sno++;
        driver.findElement(By.id("subBtnInValidateESign")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
        sno++;
        driver.findElement(By.className("modal-btn")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Ok Button",sno,false);
        sno++;
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[9]/a/span/b")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
        sno++;
        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
       
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


