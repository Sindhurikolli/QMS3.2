package com.pss.qms.CAPAModule;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
  
 
import com.pss.qms.login.CAorPALoginDetails;
// 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CAPANewPlan extends CAorPALoginDetails {

	@Test
	public void toCreateCAPANewPlan() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPANewPlan" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPANewPlan", "PSS-QMS-001", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("qmsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText("CAPA");
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(
				new Paragraph(sno + "." + "Enter the username, password, Select CAPA Module and click on login button"
						+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(8000);
//        driver.findElement(By.cssSelector("#capa_tile_Id > div > div > div > h2")).click();
//        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CAPA module",sno,false);
//        sno++;
//         WebElement element = driver.findElement(By.id("closeBtnInNotify"));
//    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        driver.findElement(By.id("closeBtnInNotificationNotify")).click();
		driver.findElement(By.cssSelector("a[href='createCapaPlanPage.do']")).click();
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("newCapaPlanInCapa")));
//        driver.findElement(By.id("myActivitiesInCapa")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Capa Plan", sno, false);
//        Thread.sleep(20000);
//        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"capaCreatePlanMenuId\"]/a"));
//    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
		// driver.findElement(By.xpath("//*[@id=\"capaCreatePlanMenuId\"]/a")).click();
		sno++;
		driver.findElement(By.id("newCapaPlanInCapa")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Plan New CAPA radio button", sno,
				false);
		Thread.sleep(7000);
		toCAPAPlan();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toCAPAPlan() throws Exception {

		sno++;
		Select Source = new Select(driver.findElement(By.id("capaSourceInCreateCapaPlan")));
		Source.selectByIndex(2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Source", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("referenceNumberInCreateCapaPlan"))
				.sendKeys(properties.getProperty("Reference_Number"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the reference number", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("descriptionInCreateCapaPlan")).sendKeys(properties.getProperty("CAPA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Enter the  source description in create capa plan", sno, false);
		Thread.sleep(2000);
		sno++;
		Select TaskType = new Select(driver.findElement(By.id("taskTypeInAddCaDetailsForSingleCapa")));
		String Tasktype = properties.getProperty("Task_Type");
		TaskType.selectByVisibleText(Tasktype);
		driver.findElement(By.id("taskDescInCapaInitiate")).sendKeys(properties.getProperty("CAPA_2000"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the  capa description in capa plan",
				sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.cssSelector(
				"#createCapaPlanWindowMainDiv > div:nth-child(5) > div:nth-child(2) > div.form-group.col-sm-4 > div.radio_false_disable > label"))
				.click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on supporing dosuments Yes radio button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("capaAddSuppDocs")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add button", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("uploadSuppDoc_1")).sendKeys(properties.getProperty("Document-1"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload the document", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("submitIdInCreateCapaPlan")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(2000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")))
				.sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the  E-Signature Password", sno,
				false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
		if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
				.equalsIgnoreCase("Submitted successfully.")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		}
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
