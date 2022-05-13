package com.pss.qms.AuditManagementRejection;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AMLoginDetails;
 
import org.openqa.selenium.By;
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
 * @author likhitha
 */
public class InternalAuditObservations extends AMLoginDetails {
    
    @Test
    public void AuditPlan() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(15000);
        driver.findElement(By.id("myActivitiesInAM")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"amInternalAuditMainMenu\"]/a")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//*[@id=\"amStartAuditPageTag\"]/a")).click();
        Thread.sleep(15000);
        methodToDoAuditPlan();
    }

    private void methodToDoAuditPlan() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
//        String changeCtrlDept = properties.getProperty("CHG_CNTRL_DEPT1");
////        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("DEPARTMENT_CODE_QMS1");
//        String chgCtrlNoWithPlantCode =changeCtrlDept + "-" + DepartmentCode;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
        String chgCtrlId = "Audit-021";
//        String chgControlNumber = chgCtrlNoWithPlantCode + sdf + chgCtrlId;
        isRecordSelected = selectRecdQAReviewChgControl(chgCtrlId, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("inpectionDateInStartAuditForm")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[5]/td[4]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("inpectionEndDateInStartAuditForm")).click();
            Thread.sleep(3000);
             driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[5]/td[1]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[1]/td[9]/button/i")).click();
            Thread.sleep(3000);
            Select ComplianceStatus = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
            ComplianceStatus.selectByIndex(2);
            Thread.sleep(3000);
            Select Category = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
            Category.selectByIndex(1);
            Thread.sleep(3000);

            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.id("addBtnInPerformChkList")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[2]/td[9]/button/i")).click();
            Thread.sleep(3000);
            Select ComplianceStatus1 = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
            ComplianceStatus1.selectByIndex(2);
            Thread.sleep(3000);
            Select Category2 = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
            Category2.selectByIndex(1);
            Thread.sleep(3000);
            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.id("addBtnInPerformChkList")).click();
            Thread.sleep(3000);
//            driver.findElement(By.xpath("//*[@id=\"chkPointsJtableInStartAuditForm\"]/div/table/tbody/tr[3]/td[9]/button")).click();
//            Thread.sleep(3000);
//            Select ComplianceStatus3 = new Select(driver.findElement(By.id("auditStatusInPerformChkList")));
//            ComplianceStatus3.selectByIndex(2);
//            Thread.sleep(3000);
//            Select Category1 = new Select(driver.findElement(By.id("severtiyInPerformChkList")));
//            Category1.selectByIndex(1);
//            Thread.sleep(3000);
//            driver.findElement(By.id("observationInPerformChkList")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
//            Thread.sleep(3000);
//            driver.findElement(By.id("addBtnInPerformChkList")).click();
//            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("evalAreasInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.id("evalDocsInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.id("persMetDuringAuditInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
            Thread.sleep(3000);

            driver.findElement(By.id("infoFromPrevAuditInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            Select ComplianceStatus4 = new Select(driver.findElement(By.id("auditRatingInAmStartAuditForm")));
            ComplianceStatus4.selectByIndex(1);
            Thread.sleep(3000);
            driver.findElement(By.id("refDocInAmStartAuditForm")).sendKeys(properties.getProperty("Document-1"));
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditees\"]/div/div[3]/div[2]/span/span[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_5_span")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[6]/td[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("commentsInAmStartAuditForm")).sendKeys(properties.getProperty("AUDIT_COMMENTS"));
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditObservationsReview\"]/div/div[3]/div[2]/span")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(3000);

            driver.findElement(By.id("locTreeInQmsProdReg_5_span")).click();
            Thread.sleep(5000);
//            driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span:nth-child(4)")).click();
//            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            Thread.sleep(3000);

            driver.findElement(By.xpath("//*[@id=\"selectUsersForAuditObservationsApproval\"]/div/div[3]/div[2]/span/span[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_2_switch")).click();
            Thread.sleep(3000);

            driver.findElement(By.id("locTreeInQmsProdReg_5_span")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[2]/td[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(3000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 70);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
            Thread.sleep(3000);
            driver.findElement(By.className("modal-btn")).click();
            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
            Assert.assertTrue(false);
        }
    }

    private boolean selectRecdQAReviewChgControl(String chgControlNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath(".//*[@id='auditsContainerInEnterAuditFindingsForm']/div/table/tbody/tr[ " + i + "]/td[3]")).getText();//documentTypeName
                        if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).getText();
                    if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.cssSelector("#auditsContainerInEnterAuditFindingsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));//Document Tree approve table
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
