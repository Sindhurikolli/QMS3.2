package com.pss.qms.CAPAWorkFlow;

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

public class CAPAWorkFlow extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CAPAWorkFlow"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("CAPAWorkFlow", "PSS-QMS-063",
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
        driver.findElement(By.id("workFlowInCapa")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on capa workflow Tab",sno,false);
        Thread.sleep(10000);
        methodToDoWorkFlowDeviation();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
    }
    
    private void methodToDoWorkFlowDeviation() throws Exception {
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("regWorkFlowSelect")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("treeContainer_3_switch")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("treeContainer_7_span")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("selectBtnInLocSelect")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("workFlowUserBrowse1Button")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on  depthod browse button",sno,false);
        Thread.sleep(3000);
        driver.findElement(By.id("locTreeInQmsCapaWfReg_3_switch")).click();
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("locTreeInQmsCapaWfReg_7_span")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
        Thread.sleep(3000);
        boolean isRecordSelectedForUser = false;
//      String InvFirstName =properties.getProperty("First_Name1");
      String UserFirsrtName =properties.getProperty("UserName1");
      String UserFullName = UserFirsrtName;
      int count4=0;
      sno++;
      isRecordSelectedForUser = selectingTheUser(UserFullName,isRecordSelectedForUser, count4);
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record",sno,false);
      Thread.sleep(3000);
//      driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
//      Thread.sleep(3000);
      sno++;
      driver.findElement(By.id("selectBrowse1Button")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
      Thread.sleep(3000);
      sno++;
       driver.findElement(By.id("workFlowUserBrowse2Button")).click();
       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on QMSCoordinator browse button",sno,false);
      Thread.sleep(3000); 
      driver.findElement(By.id("locTreeInQmsCapaWfReg_3_switch")).click();
      Thread.sleep(3000);
      sno++;
      driver.findElement(By.id("locTreeInQmsCapaWfReg_7_span")).click();
      document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
      Thread.sleep(3000);   
      boolean isRecordSelectedForUser1 = false;
//    String InvFirstName =properties.getProperty("First_Name1");
		String UserFirsrtName1 = properties.getProperty("UserName2");
		String UserFullName1 = UserFirsrtName1;
		int count5 = 0;
		sno++;
		isRecordSelectedForUser1 = selectingTheUser(UserFullName1, isRecordSelectedForUser1, count4);
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select the user",sno,false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("selectBrowse1Button")).click();
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
		Thread.sleep(3000);
		 JavascriptExecutor scl = ((JavascriptExecutor) driver);
         scl.executeScript("window.scrollBy(0,1000)");
         Thread.sleep(5000);
         sno++;
         driver.findElement(By.id("workFlowUserBrowse3Button")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on qahod browse",sno,false);
         Thread.sleep(3000);
         driver.findElement(By.id("locTreeInQmsCapaWfReg_3_switch")).click();
         Thread.sleep(3000);
         sno++;
         driver.findElement(By.id("locTreeInQmsCapaWfReg_7_span")).click();
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
         Thread.sleep(3000);   
         boolean isRecordSelectedForUser2 = false;
//       String InvFirstName =properties.getProperty("First_Name1");
   		String UserFirsrtName2 = properties.getProperty("FIRST_NAME3");
   		String UserFullName2= UserFirsrtName2;
   		int count6 = 0;
   		sno++;
   		isRecordSelectedForUser2 = selectingTheUser(UserFullName2, isRecordSelectedForUser2, count6);
   	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the user",sno,false);
   		Thread.sleep(3000);
   		sno++;
   		driver.findElement(By.id("selectBrowse1Button")).click();
   	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
   		Thread.sleep(3000);
   		sno++;
   	 driver.findElement(By.id("workFlowUserBrowse4Button")).click();
   	 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on browse button",sno,false);
     Thread.sleep(3000);
     driver.findElement(By.id("locTreeInQmsCapaWfReg_3_switch")).click();
     Thread.sleep(3000);
     sno++;
     driver.findElement(By.id("locTreeInQmsCapaWfReg_7_ico")).click();
     document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
     Thread.sleep(3000);   
     boolean isRecordSelectedForUser3 = false;
//   String InvFirstName =properties.getProperty("First_Name1");
		String UserFirsrtName3 = properties.getProperty("SRM_ROLE_NAME");
		String UserFullName3= UserFirsrtName3;
		int count7 = 0;
		sno++;
		isRecordSelectedForUser2 = selectingTheUser(UserFullName3, isRecordSelectedForUser3, count7);
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the user",sno,false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("selectBrowse1Button")).click();
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
		Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("regWfSubBtnId"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
        sno++;
        driver.findElement(By.id("regWfSubBtnId")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button",sno,false);
		Thread.sleep(3000);
		sno++;
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",sno,false);
            Thread.sleep(5000);
            sno++;
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button",sno,false);
            Thread.sleep(5000);
            sno++;
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
            Thread.sleep(5000);
            driver.findElement(By.className("modal-btn")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button",sno,false);
            Thread.sleep(5000);
            sno++;                       
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/a/span")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno,false);
    		Thread.sleep(5000);
    		sno++;
    		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul[2]/li[7]/ul/li[3]/a")).click();
    		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno,true);
    }

private boolean selectingTheUser(String userFullName, boolean isRecordSelectedForUser, int count4) throws InterruptedException {
	  WebElement table = driver.findElement(By.id("usersTableContainer"));
      WebElement tableBody = table.findElement(By.tagName("tbody"));
      int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
      int totalNoOfRecords = 0;
      int noOfRecordsChecked = 0;
      if (perPageNoOfRecordsPresent > 0) {
          String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
          String[] parts = a.split(" of ");
          try {
              totalNoOfRecords = Integer.parseInt(parts[1].trim());
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      }
      //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
      if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
          if (((userFullName == null) || ("".equalsIgnoreCase(userFullName)))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();//documentType
          } else if ((userFullName == null) || ("".equalsIgnoreCase(userFullName))) {
        	  userFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();//documentType

          }
          ++count4;
      }
      if (perPageNoOfRecordsPresent > 0) {
          //while (noOfRecordsChecked < totalNoOfRecords) {
              if (perPageNoOfRecordsPresent > 1) {
                  for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                      String InvFirstName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                      System.out.println("InvReviewFullName: "+InvFirstName);
                      if (userFullName.equalsIgnoreCase(InvFirstName)) {
                          driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                          isRecordSelectedForUser = true;
                          break;
                      }
                  }
                 
              } else {
                  String InvReviewFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
                  if (userFullName.equalsIgnoreCase(InvReviewFullName)) {
                      driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
                      isRecordSelectedForUser = true;
                      
                  }
              }
              noOfRecordsChecked += perPageNoOfRecordsPresent;
              if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
                  driver.findElement(By.cssSelector("#devReviewTableContailner > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                  Thread.sleep(3000);
                  table = driver.findElement(By.id("devReviewTableContailner"));//Document Tree approve table
                  tableBody = table.findElement(By.tagName("tbody"));
                  perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
              }
          }
      
      return isRecordSelectedForUser;
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
	






	

