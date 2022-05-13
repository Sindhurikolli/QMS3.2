package com.pss.qms.RiskAssessmentModule;
import com.pss.qms.ExtentTestNGPkg.Utility;
 
import com.pss.qms.login.RALoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class RiskAssessmentInitiation extends RALoginDetails {

    @Test
    public void methodtoinitiatedocumentRA() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='riskAsmt_tile_Id']/div/div/div/h2")).click();
        Thread.sleep(10000);
        touploaddocumentRA();
    }

    private void touploaddocumentRA() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id("newRiskAssmntAction")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("raShortDescriptionInRaInit")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
        Thread.sleep(2000);
        driver.findElement(By.id("documentChkInRaInit")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("addDocumentsInRaInit")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("locTreeForAddDocuments_2_span")).click();
        Thread.sleep(2000);
//      driver.findElement(By.id("locTreeForAddDocuments_3_span")).click();
//      Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='documentsWidowDetialsTableContainer']/div/table/tbody/tr/td[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("addBtnInDocumentAdd")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='propRisksJtableInRAInit']/div/div[3]/div[2]/span/span[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("identifyRiskInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
        Thread.sleep(2000);
        driver.findElement(By.id("failureCauseInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
        Thread.sleep(2000);
        driver.findElement(By.id("riskControlsInPropRiskDlg")).sendKeys(properties.getProperty("RA_SHORT_DESCRIPTION"));
        Thread.sleep(2000);
        driver.findElement(By.id("refDocNameInPropRiskDlg")).sendKeys(properties.getProperty("Reference_Document"));
        Thread.sleep(2000);
        driver.findElement(By.id("addBtnInPropRisksAdd")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='assmntTeamJtableInRAInit']/div/div[3]/div[2]/span/span[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("locTreeInQmsWfReg_2_switch")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("locTreeInQmsWfReg_3_span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='usersTableContainer']/div/table/tbody/tr[2]/td[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("selectBrowseButton")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='TotalContent']/div[3]/ul/li[3]/a")).click();
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(1000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);

    }
    @AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
