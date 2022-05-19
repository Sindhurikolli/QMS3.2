package com.pss.qms.AuditManagementAdmin;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
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
import com.pss.qms.login.AMLoginDetails;
 
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class AssignRoleInAuditManagement extends AMLoginDetails {
    
	 @Test
	    public void methodtoAssignRole() throws InterruptedException, IOException, DocumentException, Exception {
//			try {

				document = new Document(PageSize.A4, 36, 36, 20, 20);
		  	Font font = new Font(Font.FontFamily.TIMES_ROMAN);
				output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AssignRoleInAuditManagement"
						+ (new Random().nextInt()) + ".pdf";
				fos = new FileOutputStream(output);

				writer = PdfWriter.getInstance(document, fos);
				
	            
				writer.open();
				HeaderFooterPageEvent event = new HeaderFooterPageEvent("AssignRoleInAuditManagement", "PSS-QMS-001",
						"Pass");
				writer.setPageEvent(event);
				document.open();
	      driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME"));
	      driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
	      input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	     driver.findElement(By.xpath(".//*[@id='loginform']/div[5]/button[1]")).click();
	     im = Image.getInstance(input);
				im.scaleToFit((PageSize.A4.getWidth()-(PageSize.A4.getWidth()/8)), (PageSize.A4.getHeight()-(PageSize.A4.getHeight()/8)));
				document.add(new Paragraph(sno+"."+"Enter the username, password and click on login button"+ Utilities.prepareSSNumber(sno), font));
				document.add(im);
				document.add(new Paragraph("                                     "));
				document.add(new Paragraph("                                     "));
				sno++;
	          driver.findElement(By.cssSelector("#auditMgmt_tile_Id > div > div > div > h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"adminInAM\"]/div/a/div/div/div/h2")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin Tab",sno,false);  
	         sno++;
	         driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
	         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Assign Role Menu",sno,false);  
	        todoAssignRoleInAuditManagement();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	        }

	    private void todoAssignRoleInAuditManagement() throws Exception {  	
	        sno++;
	        driver.findElement(By.id("selectBtnInAssignAMRoles")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
	        sno++;
	        driver.findElement(By.id("locationTreeForSingleUserSelect_2_switch")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("locationTreeForSingleUserSelect_11_span")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
	        boolean isRecordSelectedForUser = false;
            String UserFirstName = properties.getProperty("AUDIT_COORDINATOR");
          String UserFullName = UserFirstName;
          int count1=0;
          isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
          sno++;
          driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
          document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
          sno++;
	     driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[8]")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Role",sno,false); 
//	        sno++;
//	        driver.findElement(By.id("descriptionInCreateRoleInAM")).sendKeys(properties.getProperty("ROLE_DESC_IN_AM"));
//	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Role Name",sno,false); 
	        sno++;
	        driver.findElement(By.id("subBtnInAssignAMRoles")).click();
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
	        driver.findElement(By.className("username")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false);
	        sno++;
	        driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
	        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut",sno,false);
	       
	    }

		private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
			 WebElement table = driver.findElement(By.id("singleSelUsersTableContainer"));
	         WebElement tableBody = table.findElement(By.tagName("tbody"));
	         int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	         int totalNoOfRecords = 0;
	         int noOfRecordsChecked = 0;
	         if (perPageNoOfRecordsPresent > 0) {
	             String a = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	             String[] parts = a.split(" of ");
	             try {
	                 totalNoOfRecords = Integer.parseInt(parts[1].trim());
	             } catch (Exception e) {
	                 System.out.println(e.getMessage());
	             }
	         }
	         if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
	             if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
	            	 UserFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	             } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
	            	 UserFullName = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	             }
	             ++count1;
	         }
	         if (perPageNoOfRecordsPresent > 0) {
	             while (noOfRecordsChecked < totalNoOfRecords) {
	                 if (totalNoOfRecords > 1) {
	                     for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                         String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                         if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
	                             driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                             isRecordSelectedForUser = true;
	                             break;
	                         }
	                     }
	                     if (isRecordSelectedForUser) {
	                         break;
	                     }
	                 } else {
	                     String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
	                     if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
	                         driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
	                         isRecordSelectedForUser = true;
	                         break;
	                     }
	                 }
	                 noOfRecordsChecked += perPageNoOfRecordsPresent;
	                 if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
	                     driver.findElement(By.cssSelector("#singleSelUsersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                     Thread.sleep(3000);
	                     table = driver.findElement(By.id("singleSelUsersTableContainer"));//Document Tree approve table
	                     tableBody = table.findElement(By.tagName("tbody"));
	                     perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                 }
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


