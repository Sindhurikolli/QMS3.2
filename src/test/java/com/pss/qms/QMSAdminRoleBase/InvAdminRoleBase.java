package com.pss.qms.QMSAdminRoleBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


import com.pss.qms.login.InvestigationLoginDetails;

public class InvAdminRoleBase extends InvestigationLoginDetails{
  
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"investigation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
        driver.findElement(By.id("invAdminIdInQms")).click();
        Thread.sleep(10000);
        createRolePageAccessMethod(driver);

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRolePageAccessMethod(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        Select rolesDropDownList = new Select(driver.findElement(By.id("rolesInInvRoleBaseAccessPage")));
        rolesDropDownList.selectByVisibleText(properties.getProperty("ADMIN_USERNAME"));
        Thread.sleep(5000);
        if ("1".equalsIgnoreCase(properties.getProperty("ROLE_ID_QMS_ROLE_PAGE_ACCESS"))) {
            Thread.sleep(5000);
            driver.findElement(By.id("fullInvMainMenu")).click();//My Activities Tab
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForAudit")).click();//My Activities Tab
            Thread.sleep(5000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.id("selectAllForAdmin"));
            jse.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            driver.findElement(By.id("selectAllForAdmin")).click();//Registration Tab
            Thread.sleep(5000);
        driver.findElement(By.id("submitButtonInInvRoleBaseAccess")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(5000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(5000);
    }
    }
}
	






