package com.pss.qms.CAPAAdminTab;

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
import com.pss.qms.login.AdminLoginDetails;
 
import com.pss.qms.login.CAorPALoginDetails;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;

public class AssignRole extends CAorPALoginDetails {

	@Test
    public void toReviewCAPA() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document();
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "AssignRole"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("AssignRole", "PSS-QMS-049",
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
        Thread.sleep(6000);
        driver.findElement(By.id("adminInCapa")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin Tab",sno,false);
        Thread.sleep(10000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]/a")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Assign role menu",sno,false);
        Thread.sleep(8000);
        sno++;
        driver.findElement(By.id("selectBtnInAssignRole")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select button",sno,false);
        Thread.sleep(3000);
        sno++;
         driver.findElement(By.id("locationTreeForSingleUserSelect_2_switch")).click();
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("locationTreeForSingleUserSelect_8_span")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the location",sno,false);
        Thread.sleep(3000);
         toAssignRole();
         document.close();
 		writer.close();
 		Desktop desktop = Desktop.getDesktop();
 		File file = new File(output);
 		//desktop.open(file);
// 	} catch (Exception e) {
// 		e.printStackTrace();
// 	}
    }

    private void toAssignRole() throws Exception {
        Thread.sleep(4000);  
        sno++;
        int count = 0;
        String AssignRoleRecord = properties.getProperty("ASSIGN_ROLE_USER_FIRST_NAME");
        boolean isRecordSelectedForAssignRole = false;
        isRecordSelectedForAssignRole = selectRecordToAssignRole(AssignRoleRecord, isRecordSelectedForAssignRole, count);
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record",sno,false);
        Thread.sleep(10000);
        if (isRecordSelectedForAssignRole) {
        	sno++;
        driver.findElement(By.id("selBtn_InSingleUserSelectDialog")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[13]")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select the roel from non-selected list",sno,false);
        Thread.sleep(3000); 
        sno++;
        driver.findElement(By.id("subBtnInAssignRole")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on select button",sno,false);
        Thread.sleep(3000);
        sno++;
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",sno,false);
        Thread.sleep(5000);
        sno++;
        driver.findElement(By.id("subBtnInValidateESign")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button",sno,false);
        Thread.sleep(5000);
        sno++;
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
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
    }
        private boolean selectRecordToAssignRole(String AssignRoleRecord, boolean isRecordSelectedForAssignRole, int count) throws InterruptedException {
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
            if (perPageNoOfRecordsPresent > 0 && count == 0) {
                if ((totalNoOfRecords > 1) && ((AssignRoleRecord == null) || ("".equalsIgnoreCase(AssignRoleRecord)))) {
                	AssignRoleRecord = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();//documentType
                } else if ((AssignRoleRecord == null) || ("".equalsIgnoreCase(AssignRoleRecord))) {
                	AssignRoleRecord = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();//documentType
                }
                ++count;
            }
            if (perPageNoOfRecordsPresent > 0) {
                while (noOfRecordsChecked < totalNoOfRecords) {
                    if (totalNoOfRecords > 1) {
                        for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                            String newDoctReqNameInApproval = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
                            if (AssignRoleRecord.equalsIgnoreCase(newDoctReqNameInApproval)) {
                                driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
                                isRecordSelectedForAssignRole = true;
                                break;
                            }
                        }
                        if (isRecordSelectedForAssignRole) {
                            break;
                        }
                    } else {
                        String newDoctReqNameInApproval = driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();
                        if (AssignRoleRecord.equalsIgnoreCase(newDoctReqNameInApproval)) {
                            driver.findElement(By.xpath("//*[@id=\"singleSelUsersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).click();
                            isRecordSelectedForAssignRole = true;
                            break;
                        }
                    }
                    noOfRecordsChecked += perPageNoOfRecordsPresent;
                    if ((!isRecordSelectedForAssignRole) && (noOfRecordsChecked < totalNoOfRecords)) {
                        driver.findElement(By.className("jtable-page-number-next")).click();//next page in Document approve list
                        Thread.sleep(3000);
                        table = driver.findElement(By.id("dmsNewDocReviewTable"));//Document Tree approve table
                        tableBody = table.findElement(By.tagName("tbody"));
                        perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                    }
                }
            }
            return isRecordSelectedForAssignRole;
        }
        @AfterMethod
    	public void testIT(ITestResult result)

    	{
    		if (ITestResult.FAILURE == result.getStatus()) {
    			Utility.captureScreenshot(driver, result.getName());
    		}

    	}
}
       
