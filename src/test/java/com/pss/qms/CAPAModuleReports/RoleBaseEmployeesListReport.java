
package com.pss.qms.CAPAModuleReports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
 
 
import com.pss.qms.login.CAorPALoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

/**
 *
 * @author Mounika
 */
public class RoleBaseEmployeesListReport extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleBaseEmployeesListReport"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleBaseEmployeesListReport", "PSS-QMS-066",
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
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Reports Tab",sno,false);
		Thread.sleep(16000);
		sno++;
		driver.findElement(By.xpath("//*[@id=\"capaRoleBaseEmployeesListReportMenuId\"]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on capa role base employee list report",sno,false);
		Thread.sleep(4000);
		methodtocapaRoleBaseEmployeesList();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
  }
	
	private void methodtocapaRoleBaseEmployeesList() throws Exception {
		sno++;
	 driver.findElement(By.id("locSelBtnInCapaRoleBaseEmpListReport")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button",sno,false);
		Thread.sleep(4000);
		driver.findElement(By.id("treeContainer_2_switch")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("treeContainer_5_span")).click();
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("selectBtnInLocSelect")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
		Thread.sleep(8000);
		sno++;
		driver.findElement(By.id("viewReportBtnInCapaRoleBaseEmpListReport")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View Report button",sno,false);
		Thread.sleep(4000);

		// It will return the parent window name as a String
		String mainWindow = driver.getWindowHandle();
		System.out.println("mainWindow :" + mainWindow);
		Thread.sleep(2000);

		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set = driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows is not equal to child window. If not equal,
			// we will close.
			if (!mainWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
				driver.close();

				// This is to switch to the main window
				driver.switchTo().window(mainWindow);

				driver.findElement(By.id("myActivitiesInCapa")).click();
				Thread.sleep(5000);
				
				sno++;                       
        		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
        		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
        		Thread.sleep(5000);
        		sno++;
        		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
        		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
				
				

			}

		}

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
