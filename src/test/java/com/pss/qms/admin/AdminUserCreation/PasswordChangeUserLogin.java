package com.pss.qms.admin.AdminUserCreation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AdminLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Helper;
import com.pss.qms.util.Utilities;



public class PasswordChangeUserLogin extends AdminLoginDetails {
 
	@Test
    public void LoginPassChange() throws InterruptedException, DocumentException, IOException, Throwable {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "FirstTimeUserLogin"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("First Time Login", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("PassWordChangeUserID"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("UserOlDPASSWORD"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(1000);
//			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			driver.findElement(By.cssSelector("button[type='submit']")).click();
			Thread.sleep(3000);
			Helper.waitLoadRecords(driver, By.id("dashBoardBtnInAdmin"));
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(1000);
//			if (driver.findElement(By.cssSelector("a[class='modal-btn']")).isDisplayed()) {
//				System.out.println("Alert Displayed");
//				Actions action = new Actions(driver);
//				action.sendKeys(Keys.ENTER).perform();
//				System.out.println("Alert Acknowledged");
//			}
			driver.findElement(By.cssSelector("a[class='modal-btn']")).click();
			Thread.sleep(1000);
			methodToChangePassword();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//    }
	}
    private void methodToChangePassword() throws Exception {
        Thread.sleep(5000);
        driver.findElement(By.className("username")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[onclick='openUserPwdChngWindow()']")).click();
        Thread.sleep(1000);
         driver.findElement(By.id("oldPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UserOlDPASSWORD")); 
         Thread.sleep(1000);
         driver.findElement(By.id("newPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UsernewPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("confirmPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UsernewPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("oldESignPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UserOlDPASSWORD")); 
         Thread.sleep(1000);
         driver.findElement(By.id("newESignPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UsernewPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("confirmESignPassword_UserPwdChngWindow")).sendKeys(properties.getProperty("UsernewPassword")); 
         Thread.sleep(1000);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Entered all details",sno,false);
         sno++;
         driver.findElement(By.id("submitBtn_UserPwdChngWindow")).click();
         Thread.sleep(3000);
         driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
         
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
	










	





	
	

