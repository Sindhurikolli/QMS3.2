package com.pss.qms.ViewDeviations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.DeviationLoginDetails;
 
 

public class ViewDeviations extends DeviationLoginDetails {
 
	  @Test
	    public void ViewDeviation() throws InterruptedException {
	        Thread.sleep(2000);
	        driver.findElement(By.xpath(".//*[@id='deviation_tile_Id']/div/div/div/h2")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("deviationInDev")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.xpath("//*[@id=\"viewDeviationsMenu_Id\"]/a")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("selLocBtn_ViewDeviationsPage")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("treeContainer_2_switch")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("treeContainer_3_a")).click();
	        Thread.sleep(10000);
	        driver.findElement(By.id("selectBtnInLocSelect")).click();
	        Thread.sleep(10000);
	        Select Category = new Select(driver.findElement(By.id("severityInViewDevDet")));
	        Category.selectByIndex(2);
	        Thread.sleep(2000);
	        driver.findElement(By.id("searchBtn_ViewDeviationsPage")).click();
	        Thread.sleep(10000);
//	        JavascriptExecutor jse = (JavascriptExecutor) driver;
//	        WebElement element = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/div[4]/div[1]"));
//	        jse.executeScript("arguments[0].scrollIntoView(true);", element);
//	        Thread.sleep(1000);
	        methodToDoDeviationStatus();
	    }

	    private void methodToDoDeviationStatus() throws InterruptedException {
	        int count = 0;
	        boolean isRecordSelected = false;
//	        String DevDept = properties.getProperty("CHG_CNTRL_DEPT");
	        String DevSequence = properties.getProperty("DEVIATION_NO");
//	        String DepartmentCode = properties.getProperty("CHG_CONTROL_NO");
	        String DevNoWithPlantCode = DevSequence + "/";
	        Date date = new Date();
	        String sdf = new SimpleDateFormat("YY").format(date);
	        String DevId = "/0024";  
	        String DeviationNumber = DevNoWithPlantCode + sdf + DevId;
	        isRecordSelected = selectRecdCloseDeviation(DeviationNumber, isRecordSelected, count);
	        Thread.sleep(8000);
	        if (isRecordSelected) {
	            driver.findElement(By.xpath("/html/body/div[5]/div[1]/button/span[1]")).click();
	            Thread.sleep(5000);
	        } else {
	            System.out.println("Record is not Selected");
	        }
	    }

	    private boolean selectRecdCloseDeviation(String DeviationNumber, boolean isRecordSelected, int count) throws InterruptedException {
	        WebElement table = driver.findElement(By.id("viewDeviationsDetailsContainer"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        if (perPageNoOfRecordsPresent > 0 && count == 0) {
	            if ((totalNoOfRecords > 1) && ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber)))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((DeviationNumber == null) || ("".equalsIgnoreCase(DeviationNumber))) {
	                DeviationNumber = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
	                if (totalNoOfRecords > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr[ " + i + " ]/td[11]")).getText();//documentTypeName
	                        if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
	                            driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr[ " + i + " ]/td[24]/button")).click();
	                            isRecordSelected = true;
	                            break;
	                        }
	                    }
	                    if (isRecordSelected) {
	                        break;
	                    }
	                } else {
	                    String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr/td[11]")).getText();
	                    if (DeviationNumber.equalsIgnoreCase(DevNumberSequence)) {
	                        driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/table/tbody/tr/td[24]/button")).click();
	                        isRecordSelected = true;
	                        break;
	                    }
	                }
//	                noOfRecordsChecked += perPageNoOfRecordsPresent;
//	                if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
//	                    driver.findElement(By.xpath("//*[@id=\"viewDeviationsDetailsContainer\"]/div/div[4]/div[1]/span[1]/span[5]")).click();//next page in Document approve list
//	                    Thread.sleep(3000);
//	                    table = driver.findElement(By.id("viewDeviationsDetailsContainer"));//Document Tree approve table
//	                    tableBody = table.findElement(By.tagName("tbody"));
//	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	                }
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
	 

