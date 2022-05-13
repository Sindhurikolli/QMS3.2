package com.pss.qms.ExtentTestNGPkg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
 
public class Utility 
{
 
	public static void captureScreenshot(WebDriver driver, String screenshotname) 
	{
		try
		{
			TakesScreenshot screenshot =(TakesScreenshot)driver;
			
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			 String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());  
			
			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotname+timeStamp+".png"));
			
			System.out.println("Screenshot Taken");
			
		}catch(Exception excep)
		{
			System.out.println("Throwing exception while taking screenshot" +excep.getMessage());
		}
 
	}
}