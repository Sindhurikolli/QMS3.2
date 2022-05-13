package com.pss.qms.ChangeControlReports;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.CCLoginDetails;
 

public class ChangeControlFinalReport extends CCLoginDetails {
 
	@Test
    public void ChangeControlReports() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='changeControl_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("reportsInCC")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"ccFinalReportMenuId\"]/a")).click();
        Thread.sleep(10000);
//    driver.findElement(By.id("changeControlNoInCcReportsPostClosure")).sendKeys(properties.getProperty("CHANGE_CONTROL_NUMBER"));
//    Thread.sleep(3000);
//        driver.findElement(By.id("fromDateInCcReportsPostClosure")).sendKeys(properties.getProperty("FROM_DATE"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("toDateInCcReportsPostClosure")).sendKeys(properties.getProperty("TO_DATE"));
//        Thread.sleep(3000);
//        Select CategoryOfChange = new Select(driver.findElement(By.id("cateOfChangeInCcCompleteReport")));
//        CategoryOfChange.selectByVisibleText(properties.getProperty("Category_Of_Change"));
//        Thread.sleep(4000);
//        Select changetype = new Select(driver.findElement(By.id("changeTypeInCcReportsPostClosure")));
//        changetype.selectByVisibleText(properties.getProperty("Change_Type"));
//        Thread.sleep(4000);
//        driver.findElement(By.id("locSelBtn_CcReportsPostClosure")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.id("treeContainer_2_switch")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("treeContainer_3_span")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.id("selectBtnInLocSelect")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("documentChkInCcReportsPostClosure")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.id("searchBtnInCcReportsPostClosure")).click();
//        Thread.sleep(8000);
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(5000);
        methodToDoChangeControlReports();
    }

    private void methodToDoChangeControlReports() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS");
//        String chgCtrlNoWithPlantCode =  DepartmentCode;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = properties.getProperty("CHANGE_CONTROL_REPORT_NO");
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdChangeControlDoctReports(chgCtrlId, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
        	Thread.sleep(3000);
        	JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("viewBtnInCcInCCFinalReport"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
            Thread.sleep(8000);
//            driver.findElement(By.id("commentsInHodCcReview")).sendKeys(properties.getProperty("COMMENTS"));
//            Thread.sleep(1000);
              String applicationWindowOrPDF = driver.getWindowHandle();
                System.out.println(" applicationWindow: " + applicationWindowOrPDF);
                Thread.sleep(3000);
                driver.findElement(By.id("viewBtnInCcInCCFinalReport")).click();
                Thread.sleep(12000);
                applicationWindowOrPDF = driver.getWindowHandle();
                Thread.sleep(2000);
                System.out.println("pdfWindow: " + applicationWindowOrPDF);
                Thread.sleep(12000);
                String indepPDFWindow = driver.getWindowHandle();
                System.out.println("indepPDFWindow: " + indepPDFWindow);
                
                Set <String> pdfWindow = driver.getWindowHandles();
                //Iterator iterator = pdfWindow.iterator();
               for( String pdfWindows : pdfWindow)  {
                      System.out.println(" pdfWindows: " +pdfWindows);
                        if(!applicationWindowOrPDF.equalsIgnoreCase(pdfWindows))   {
                            driver.switchTo().window(pdfWindows);
                            Thread.sleep(2000);
                            //driver.manage().timeouts().implicitlyWait(0, MILLISECONDS);
                            driver.close();
                            Thread.sleep(3000);
                            driver.switchTo().window(applicationWindowOrPDF);
                            Thread.sleep(6000);
                            driver.findElement(By.id("myActivitiesInCC")).click();
                    	    Thread.sleep(5000); 
                        }
                    }
//                
        
        }
    }
    

    private boolean selectRecdChangeControlDoctReports(String chgCtrlId, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("ccDetailsInCCFinalReportsTable"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId)))) {
            	chgCtrlId = driver.findElement(By.xpath(".//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgCtrlId == null) || ("".equalsIgnoreCase(chgCtrlId))) {
            	chgCtrlId = driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String CCNumberSequence = driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();
                        Thread.sleep(5000);
                        if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
                            Thread.sleep(5000);
                            driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            Thread.sleep(5000);
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String CCNumberSequence = driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr/td[3]")).getText();
                    if (chgCtrlId.equalsIgnoreCase(CCNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath("//*[@id=\"ccDetailsInCCFinalReportsTable\"]/div/div[4]/div[1]/span[1]/span[6]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("ccDetailsInCCFinalReportsTable"));//Document Tree approve table
                    tableBody = table.findElement(By.tagName("tbody"));
                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
                }
            }
        }
        return isRecordSelected;
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




	
	

