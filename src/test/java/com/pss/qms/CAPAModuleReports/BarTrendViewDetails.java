
package com.pss.qms.CAPAModuleReports;

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
import org.openqa.selenium.support.ui.Select;
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
public class BarTrendViewDetails extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "BarTrendViewDetails"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("BarTrendViewDetails", "PSS-QMS-020",
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
        driver.findElement(By.id("reportsInCapa")).click();
        Thread.sleep(16000);
        driver.findElement(By.cssSelector("#capaTrendsListMenuId > a")).click();
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("#capaTrendsListMenuId > ul > li:nth-child(2) > a")).click();
          Thread.sleep(4000);
          driver.findElement(By.id("selLocBtn_CapaBarTrendSearch")).click();
          Thread.sleep(4000);
          driver.findElement(By.id("treeContainer_2_switch")).click();
          Thread.sleep(4000);
          driver.findElement(By.id("treeContainer_5_span")).click();
          Thread.sleep(4000);
          driver.findElement(By.id("selectBtnInLocSelect")).click();
          Thread.sleep(4000);
//          Select rolesDropDownList = new Select(By.id("problemType_CapaPieTrendSearch"));
//          rolesDropDownList.selectByIndex(1);
//          Thread.sleep(4000);
//          driver.findElement(By.id("capaTrendFromDate")).sendKeys(properties.getProperty("DATE_BETWEEN"));
//          Thread.sleep(4000);
//          driver.findElement(By.id("capaTrendToDate")).sendKeys(properties.getProperty("TO_DATE"));
//          Thread.sleep(4000);
//            Select rolesDropDownList1 = new Select(driver.findElement(By.id("timePeriodInCapaTrends")));
//          rolesDropDownList1.selectByIndex(1);
//          Thread.sleep(4000);          
          driver.findElement(By.id("capaNumnerInCapaTrends")).sendKeys(properties.getProperty("CAPA_REPORT_NO"));
          Thread.sleep(4000);          
          driver.findElement(By.id("searchBtnInCapaTrends")).click();
          Thread.sleep(4000);
          driver.findElement(By.id("viewBtnInCapaCompleted")).click();
          Thread.sleep(24000);
          driver.findElement(By.xpath("/html/body/div[4]/div[1]/button/span[1]")).click();
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
		if(ITestResult.FAILURE==result.getStatus())
		{
			Utility.captureScreenshot(driver, result.getName());
		}
		
	}
}
