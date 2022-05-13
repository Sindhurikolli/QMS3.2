package com.pss.qms.QMSAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.qms.login.AdminLoginDetails;

public class CreateRole extends AdminLoginDetails {
  
	@Test
    public void toDoRolePage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"deviation_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("adminInDev")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        Thread.sleep(5000);
        createRoleMethod();

    }
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }

    private void createRoleMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("roleNameInCreateRoleInDev")).sendKeys(properties.getProperty("Role_Name"));
        Thread.sleep(3000);
        driver.findElement(By.id("descriptionInCreateRoleInDev")).sendKeys(properties.getProperty("Description"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnIdInCreateDevRole")).click();
        Thread.sleep(3000); 
        driver.findElement(By.id("eSignPwdInDevWnd")).sendKeys(properties.getProperty("Password"));
        Thread.sleep(3000);
        driver.findElement(By.id("subBtnInValESignInDev")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("modal-btn")).click();
        Thread.sleep(1000);
    }
}




