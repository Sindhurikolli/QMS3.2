package com.pss.qms.CAPARoleBaseAccess;

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
import org.openqa.selenium.support.ui.Select;
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

public class QAHODRoleBaseAccess extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAHODRoleBaseAccess"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("QAHODRoleBaseAccess", "PSS-QMS-062",
					"Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME"));

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
        Thread.sleep(3000);
        driver.findElement(By.id("adminInCapa")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin Tab",sno,false);
        Thread.sleep(10000);
        createRoleMethod();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws Exception {
        Thread.sleep(1000);
        sno++;
         Select Roles = new Select(driver.findElement(By.id("rolesInCapaRoleBaseAccessPage")));
         Roles.selectByIndex(11);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the role from drop down list",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.id("capaReviewMenu")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on capa review",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.id("caApproveMenu")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on ca Approve",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.id("capaImplEffectiveCheckPostPerform")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on capa implementation effective check post perform",sno,false);
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.id("reqExtDueDateAppMenu")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on req Extend due date approval",sno,false);
         Thread.sleep(3000); 
         sno++;
         driver.findElement(By.id("effChkImplPostPerfomMenu")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on effective check implementation post perform",sno,false);
         Thread.sleep(3000); 
         sno++;
         driver.findElement(By.id("reqOverDueDateAppMenu")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check on req overdue date approval menu",sno,false);
         Thread.sleep(3000); 
         sno++;
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         WebElement element = driver.findElement(By.id("submitButtonInCapaRoleBaseAccess"));
         jse.executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(1000);
         driver.findElement(By.id("submitButtonInCapaRoleBaseAccess")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button",sno,false);
        Thread.sleep(3000); 
        sno++;
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("subBtnInValidateESign")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button",sno,false);
        Thread.sleep(1000);
        sno++;
        driver.findElement(By.className("modal-btn")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button",sno,false);
        Thread.sleep(1000);
        sno++;                       
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
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




	
	
	





