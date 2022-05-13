package com.pss.qms.QMSAdminRoleBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pss.qms.login.CCLoginDetails;

 

public class CCAdminRoleBase extends CCLoginDetails {
 
	
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"changeControl_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("adminInCC")).click();
        Thread.sleep(10000);
        createRolePageAccessMethod(driver);

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRolePageAccessMethod(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        Select rolesDropDownList = new Select(driver.findElement(By.id("rolesInCCRoleBaseAccessPage")));
        rolesDropDownList.selectByVisibleText(properties.getProperty("ADMIN_USERNAME"));
        Thread.sleep(5000);
        if ("1".equalsIgnoreCase(properties.getProperty("ROLE_ID_QMS_ROLE_PAGE_ACCESS"))) {
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForCCWorkFlow")).click();//My Activities Tab
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForCCMyActivities")).click();//My Activities Tab
            Thread.sleep(5000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("selectAllForCCReports"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForCCReports")).click();//Registration Tab
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForCCAuditTrails")).click();//Schedule Tab
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForAdmin")).click();//Schedule Tab
            Thread.sleep(5000);
        }
        driver.findElement(By.id("submitButtonInCcRoleBaseAccess")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(5000);
    }
}

	







