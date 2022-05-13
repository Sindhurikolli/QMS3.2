
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

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mounika
 */
public class RoleAudittrails extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleAudittrails"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleAudittrails", "PSS-QMS-076",
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
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audittrails Tab",sno,false);
        Thread.sleep(16000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Role Audittrails menu",sno,false);
        Thread.sleep(4000);
//        driver.findElement(By.id("rolesNameInCapaAt")).sendKeys(properties.getProperty("ROLE_NAME"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("fromDateInCapaRoles")).sendKeys(properties.getProperty("DATE_BETWEEN"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("toDateInCapaRoles")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("searchRolesButtonId")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on search button",sno,false);
        Thread.sleep(4000);
        sno++;
//        driver.findElement(By.cssSelector("div.ui-dialog:nth-child(45) > div:nth-child(3) > div:nth-child(1) > button:nth-child(1)")).click();
//        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"capaRolesTableContainer\"]/div/table/tbody/tr/td[8]/button")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record",sno,false);
        Thread.sleep(4000);
        sno++;
        driver.findElement(By.id("closeInButtonIdInCapaRolesAudit")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on close button",sno,false);
        Thread.sleep(4000);
        sno++;                       
  		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
  		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
  		Thread.sleep(5000);
  		sno++;
  		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
  		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}

