package com.pss.qms.Registration;

import com.pss.qms.ExtentTestNGPkg.Utility;

import com.pss.qms.login.RegistrationLoginDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author likhitha
 */
public class ModifyUserGroup extends RegistrationLoginDetails {
    
   
    @Test
    public void ModifyUserGroup() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"registration_tile_Id\"]/div/div/div/h2")).click();
        Thread.sleep(10000);
//        driver.findElement(By.id("reportsInMC")).click();
//        Thread.sleep(10000);
          JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@id=\"regModifyMenusListId\"]/a"));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"regModifyMenusListId\"]/a")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"regModifyMenusListId\"]/ul/li[2]/a")).click();
        Thread.sleep(10000);
        methodToDoModifyUserGroup();
    }

    private void methodToDoModifyUserGroup() throws InterruptedException {
        int count = 0;
        boolean isRecordSelected = false;
        String GroupName = properties.getProperty("GROUP_NAME");
//        String changeCtrlSequence = properties.getProperty("CHG_CONTROL_NO");
//        String DepartmentCode = properties.getProperty("CAPA_NUMBER");
        String chgCtrlNoWithPlantCode = GroupName;
//        Date date = new Date();
//        String sdf = new SimpleDateFormat("YY").format(date);
//        String chgCtrlId = "-0013";
        String chgControlNumber = chgCtrlNoWithPlantCode;
        isRecordSelected = selectRecdModifyUserGroup(chgControlNumber, isRecordSelected, count);
        Thread.sleep(10000);
        if (isRecordSelected) {
            driver.findElement(By.xpath("//*[@id=\"userDetailsInModifyUserGroup\"]/div/table/tbody/tr/td[5]/button")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"DeleteDialogButton\"]/span")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("usersSelectInSelectionInUserGrp")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_3_switch")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("locTreeInQmsProdReg_7_ico")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[8]/td[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("usersSelBtnRepProb")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("submitInModifyUserGrpWdw")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(1000);
            driver.findElement(By.id("subBtnInValidateESign")).click();
            Thread.sleep(1000);
//            WebDriverWait wait = new WebDriverWait(driver, 70);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//            Thread.sleep(1000);
            driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
            Thread.sleep(4000);
        } else {
            System.out.println("Record is not Selected");
        }
    }

    private boolean selectRecdModifyUserGroup(String chgControlNumber, boolean isRecordSelected, int count) throws InterruptedException {
        WebElement table = driver.findElement(By.id("modifyUserGroupsTableContainer"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
        int totalNoOfRecords = 0;
        int noOfRecordsChecked = 0;
        if (perPageNoOfRecordsPresent > 0) {
            String a = driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
            String[] parts = a.split(" of ");
            try {
                totalNoOfRecords = Integer.parseInt(parts[1].trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (perPageNoOfRecordsPresent > 0 && count == 0) {
            if ((totalNoOfRecords > 1) && ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber)))) {
                chgControlNumber = driver.findElement(By.xpath(".//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
            } else if ((chgControlNumber == null) || ("".equalsIgnoreCase(chgControlNumber))) {
                chgControlNumber = driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

            }
            ++count;
        }
        if (perPageNoOfRecordsPresent > 0) {
            while (noOfRecordsChecked < totalNoOfRecords) {
                if (totalNoOfRecords > 1) {
                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
                        String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]")).getText();//documentTypeName
                        if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                            driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]/button")).click();
                            isRecordSelected = true;
                            break;
                        }
                    }
                    if (isRecordSelected) {
                        break;
                    }
                } else {
                    String capaNumberSequence = driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
                    if (chgControlNumber.equalsIgnoreCase(capaNumberSequence)) {
                        driver.findElement(By.xpath("//*[@id=\"modifyUserGroupsTableContainer\"]/div/table/tbody/tr/td[6]/button")).click();
                        isRecordSelected = true;
                        break;
                    }
                }
                noOfRecordsChecked += perPageNoOfRecordsPresent;
                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
                    driver.findElement(By.xpath(".//*[@id='modifyUserGroupsTableContainer']/div/div[4]/div[1]/span[1]/span[4]")).click();//next page in Document approve list
                    Thread.sleep(3000);
                    table = driver.findElement(By.id("modifyUserGroupsTableContainer"));//Document Tree approve table
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



