package com.pss.qms.IncidentManagementWithCustomerCftRACAPAInvRejFlow;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
 
import com.pss.qms.login.IncidentManagementLoginDetails;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ram
 */
public class InvPerformInc extends IncidentManagementLoginDetails {
    
 @Test
    public void InvestigationPerformIncident() throws InterruptedException, IOException, DocumentException, Exception {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InvPerformDev"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("InvPerformDev", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("INVEST_USER"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Select module= new Select(driver.findElement(By.id("qmsModule")));
			module.selectByIndex(5);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(10000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
	 
//	   driver.findElement(By.xpath(".//*[@id='investigation_tile_Id']/div/div/div/h2")).click();
//	   document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Investigation Module",sno,false);
//        Thread.sleep(10000);
        sno++;
        driver.findElement(By.cssSelector("a[href='performFullInvPage.do']")).click();
        document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Perform Menu",sno,false);
        Thread.sleep(10000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/div[4]/div[1]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(10000);
        methodToDoInvestigationIncident();
        document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
        
        
    }

    private void methodToDoInvestigationIncident() throws Exception {
        int count = 0;
        boolean isRecordSelected = false;
        WebDriverWait wait1 = new WebDriverWait(driver, 240);

        wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-busy-message"))));
        String IncidentNumber = properties.getProperty("IncidentNo");
//        String changeCtrlDept= properties.getProperty("CHG_CNTRL_DEPT"); 
//        String DevSequence = properties.getProperty("Incident_NO");
////        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
//        String DevNoWithPlantCode = DevSequence + "/";
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String DevId = "/0068";
//        String IncidentNumber = DevNoWithPlantCode + sdf + DevId;
//        String IncidentNumberToTrim = LoginDetails.finalIncidentNumber;
//        String IncidentNumber = IncidentNumberToTrim.trim(); 
//        System.out.println("Incident Number is coming........:"+IncidentNumber);
       
        isRecordSelected = selectRecdInvestigationIncident(IncidentNumber, isRecordSelected, count);
        if (isRecordSelected) {
        	
        	sno++;
        	Thread.sleep(50000);
        	 wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("invDetailsInPerformInvActionsInInvPlan")));
            driver.findElement(By.id("invDetailsInPerformInvActionsInInvPlan")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Investigation Details",sno,false);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("summaryInPerformInvActionsInInvPlan"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.id("summaryInPerformInvActionsInInvPlan")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Summary & Conclusion",sno,false);
            Select DeptHOD = new Select(driver.findElement(By.id("stageName1")));
            //DeptHOD.selectByIndex(1);
            DeptHOD.selectByVisibleText(properties.getProperty("Inv_DepartmentHod"));
            Thread.sleep(3000);
//            JavascriptExecutor scl1 = ((JavascriptExecutor) driver);//*[@id="rcaAnalysisInDevReview"]/div/div[3]/div[2]/span/span[2]
//            scl1.executeScript("window.scrollBy(0,-250)");
            JavascriptExecutor jse90 = (JavascriptExecutor) driver;
            WebElement element90 = driver.findElement(By.id("ui-id-391"));
            jse90.executeScript("arguments[0].scrollIntoView(true);", element90);
            jse90.executeScript("arguments[0].click();", element90);
//            driver.findElement(By.id("ui-id-367]")).click();
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisInDevReview\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("rootCauseInrootCauseDlg")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter RootCause",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"rcaAnalysisSources\"]/div/table/tbody/tr[3]/td[1]/input")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Sources",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addBtnInRootCauseWndw")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
            driver.findElement(By.xpath("//*[@id=\"ui-id-392\"]")).click();
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("impactAssessmentDescription")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Impact Assessment",sno,false);
            driver.findElement(By.xpath("//*[@id=\"ui-id-393\"]")).click();
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("isNeedCapaInInvReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On CAPA Required RadioButton",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"capaDetailsInFullInvPage\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("deptSelBtn_IncPostAppActAddCapaWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("capaTreeContainer_2_switch")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("capaTreeContainer_3_span")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("selectBtnInCapaLocSelect")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addCorrectiveActCktInInvCapaAddWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Corrective Actions Check Box",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"caDetialsJTableInInvCapaPerformDialog\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("caDesciptionInCADetailsJtable1")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CA Comments",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addPreventiveActCktInInvCapaAddWin")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Preventive Actions Check Box",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"paDetialsJTableInInvCapaPerformDialog\"]/div/div[3]/div[2]/span/span[2]")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add New Record Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("paDesciptionInCADetailsJtable1")).sendKeys(properties.getProperty("Description_Of_Incident_2000"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter CAPA Comments",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("addBtnInInvCapaWinInPerform")).click();
            Thread.sleep(2000);
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button",sno,false);
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            WebElement element2 = driver.findElement(By.id("submitBtnInfullInvReview"));
            jse2.executeScript("arguments[0].click();", element2);
            sno++;
         
          //  driver.findElement(By.id("submitBtnInfullInvReview")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password",sno,false);
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button",sno,false);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
            sno++;
            Thread.sleep(3000);
            driver.findElement(By.className("username")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName",sno,false); 
            sno++;
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
            document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Logout",sno,true);      
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdInvestigationIncident(String IncidentNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("investigationTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
        	
        	WebElement elementshowing = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/div[4]/div[2]/span"));
            JavascriptExecutor jsshow = (JavascriptExecutor)driver;
            jsshow.executeScript("arguments[0].scrollIntoView(true);", elementshowing);
        	
        String a = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
        String[] parts = a.split(" of ");
        try {
        totalNoOfRecords = Integer.parseInt(parts[1].trim());
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
        if ((totalNoOfRecords > 1) && ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber)))) {
        IncidentNumber = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[1]/td[5]")).getText();//documentType
        } else if ((IncidentNumber == null) || ("".equalsIgnoreCase(IncidentNumber))) {
        IncidentNumber = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[5]")).getText();//documentType
        }
        ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
        while (noOfRecordsChecked < totalNoOfRecords) {
        if (totalNoOfRecords > 1) {
        for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
        	
        	WebElement elementsele = driver.findElement(By.xpath(".//*[@id='investigationTableContainer']/div/table/tbody/tr[ " + i + "]/td[5]"));
            JavascriptExecutor jssel = (JavascriptExecutor)driver;
            jssel.executeScript("arguments[0].scrollIntoView();", elementsele);
            Thread.sleep(2000);
          //*[@id="investigationTableContainer"]/div/table/tbody/tr[2]/td[5]
//      String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
        String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]")).getText();//documentTypeName
        if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[28]/button")).click();
                            isRecordSelected = true;
                            break;
                        }//*[@id="investigationTableContainer"]/div/table/tbody/tr[2]/td[28]/button
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String IncNumberSequence = driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[5]")).getText();
                    if (IncidentNumber.equalsIgnoreCase(IncNumberSequence)) {
//                        driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[23]/button")).click();
                        driver.findElement(By.xpath("//*[@id=\"investigationTableContainer\"]/div/table/tbody/tr/td[28]/button")).click();
                      //*[@id="investigationTableContainer"]/div/table/tbody/tr[2]/td[25]/button
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                	
                	WebElement elementnext = driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
                    JavascriptExecutor jsnext = (JavascriptExecutor)driver;
                    jsnext.executeScript("arguments[0].scrollIntoView(true);", elementnext);
                	
                    driver.findElement(By.cssSelector("#investigationTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    table = driver.findElement(By.id("investigationTableContainer"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
    }
    @AfterMethod
    public void testIT(ITestResult result)    {
    	if(ITestResult.FAILURE==result.getStatus())
    	{
    		Utility.captureScreenshot(driver, result.getName());
    	}
    	
    }
    } 

   
    







