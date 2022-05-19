package com.pss.qms.AuditManagementExternalScheduleWithAIForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.qms.ExtentTestNGPkg.Utility;
import com.pss.qms.login.AMLoginDetails;
import com.pss.qms.util.HeaderFooterPageEvent;
import com.pss.qms.util.Utilities;
@Listeners(com.pss.qms.Listners.TestListener.class)
public class ExternalObservationsAndResponse extends AMLoginDetails {

	@Test
	public void toExternalObservationsAndResponse() throws Exception {

//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ExternalObservationsAndResponse"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("ExternalSchedule", "PSS-QMS-004", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ExternalAuditRepresentative"));
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			Thread.sleep(1000);
			Select module = new Select(driver.findElement(By.id("qmsModule")));
			Thread.sleep(1000);
			module.selectByVisibleText(properties.getProperty("AMModule"));
			Thread.sleep(1000);
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/button[1]")).click();
			Thread.sleep(4000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(
					sno + "." + "Enter the username, password, Select Audit Management Module and click on login button"
							+ Utilities.prepareSSNumber(sno),
					font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
//				Thread.sleep(10000);
//				sno++;
//       driver.findElement(By.xpath("//*[@id=\"auditMgmt_tile_Id\"]/div/div/div/h2")).click();
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Audit Management Module",sno,false);
//       sno++;
//       driver.findElement(By.id("myActivitiesInAM")).click();
//       document = Utilities.getS1000creenShotAndAddInLogDoc(driver, document, "Click on MyActivities Tab",sno,false);  
//       sno++;
//       driver.findElement(By.xpath("//*[@id=\"amExternalAuditMainMenu\"]/a/b")).click();
//       document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on External Menu",sno,false); 
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.cssSelector("a[href='amExternalAuditObsAndResPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on External Obs And Response Menu", sno, false);
			methodToDoExternalObservationsAndResponse();
			Thread.sleep(3000);
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);

//		} catch (Exception e) {
//			e.printStackTrace();
		}
//	}

	private void methodToDoExternalObservationsAndResponse() throws Exception {

		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String ExternalId = properties.getProperty("EXTERNAL_AUDIT_NAME_WITH_AI");
		isRecordSelected = selectRecdExternalObservationsAndResponse(ExternalId, isRecordSelected, count);
		Thread.sleep(1000);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		if (isRecordSelected) {
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			sno++;
			Thread.sleep(4000);
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('inpectionDateInStartAuditForm').removeAttribute('readonly',0);");
			WebElement identifiedDate = driver.findElement(By.id("inpectionDateInStartAuditForm"));
			identifiedDate.clear();
			Date date = new Date();
			String todaysDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
			driver.findElement(By.id("inpectionDateInStartAuditForm")).sendKeys(todaysDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Start Date", sno, false);
			Thread.sleep(2000);
			sno++;
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('inpectionEndDateInStartAuditForm').removeAttribute('readonly',0);");
			WebElement identifiedDate1 = driver.findElement(By.id("inpectionEndDateInStartAuditForm"));
			identifiedDate1.clear();
			Date date1 = new Date();
			String todaysDate1 = new SimpleDateFormat("dd/MM/yyyy").format(date1);
			driver.findElement(By.id("inpectionEndDateInStartAuditForm")).sendKeys(todaysDate1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On End Date", sno, false);
			Thread.sleep(2000);
			sno++;
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('auditReportRecieivedDate').removeAttribute('readonly',0);");
			WebElement identifiedDate2 = driver.findElement(By.id("auditReportRecieivedDate"));
			identifiedDate2.clear();
			Date date2 = new Date();
			String todaysDate2 = new SimpleDateFormat("dd/MM/yyyy").format(date2);
			driver.findElement(By.id("auditReportRecieivedDate")).sendKeys(todaysDate2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On AuditReport Date", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("inpectionDateInStartAuditForm")).click();
			sno++;
			driver.findElement(By.xpath("//*[@id=\"addObservationsInExternalResp\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Observations", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("observationInExternalWin")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Observations", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("areaInExternalWin")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Area", sno, false);
			Thread.sleep(2000);
			sno++;
			Select Category = new Select(driver.findElement(By.id("categoryInExternalWin")));
			Category.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Categry", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("referenceInExternalWin")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("rootCauseInExternalWin")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter RootCause", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("responseInExternalWin")).sendKeys(properties.getProperty("AM_1500"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Response", sno, false);
			Thread.sleep(2000);
			sno++;
			Select Category1 = new Select(driver.findElement(By.id("taskTypeInExternalWin")));
			Category1.selectByIndex(2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select TaskType", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("addBtnInExternalWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor j = (JavascriptExecutor) driver;
			WebElement e = driver.findElement(By.cssSelector(
					"#actionItemsChkPointsTableInAuditeeResponseForm > div > table > tbody > tr > td:nth-child(11) > button > i"));
			j.executeScript("arguments[0].scrollIntoView(true);", e);
			driver.findElement(By.cssSelector(
					"#actionItemsChkPointsTableInAuditeeResponseForm > div > table > tbody > tr > td:nth-child(11) > button > i"))
					.click();
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
//           JavascriptExecutor j = (JavascriptExecutor) driver;
//           WebElement e = driver.findElement(By.id("actionItemEditDlgInStartAuditForm"));
//            j.executeScript("arguments[0].scrollIntoView(true);", e);

			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("actionItemEditDlgInStartAuditForm")));
			Thread.sleep(4000);
//           Select Dept = new Select(driver.findElement(By.id("selectBtnInDeptSelInAuditPlan")));
//           Dept.selectByIndex(1);
//           driver.findElement(By.linkText("Select"))
			driver.findElement(
					By.cssSelector("#actionItemEditDlgInStartAuditForm > div:nth-child(2) > div.col-sm-1 >button"))
					.click();
//           driver.findElement(By.xpath("//*[@id=\"selectBtnInDeptSelInAuditPlan\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("treeContainerInAllDepartmentsTree_2_switch")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("treeContainerInAllDepartmentsTree_3_span")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("selectBtnInAllDepartmentsSelect")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("actItemDescInActItmEditDlg")).sendKeys(properties.getProperty("AM_1500"));
			Thread.sleep(2000);
			Select ActionItemOwner1 = new Select(driver.findElement(By.id("userIdInAuditeeResponseInAM")));
			ActionItemOwner1.selectByVisibleText(properties.getProperty("AI_OWNER"));
			Thread.sleep(2000);
			SimpleDateFormat formattedDate1 = new SimpleDateFormat("d/M/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 30); // number of days to add
			String futureDate1 = (String) (formattedDate1.format(c.getTime()));
			driver.findElement(By.id("actItemTargetDateInActItmEditDlg")).sendKeys(futureDate1);
			driver.findElement(By.id("actItemDescInActItmEditDlg")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("addBtnInActItmEditDlg")).click();
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Next Button", sno, false);
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location",sno,false);
			boolean isRecordSelectedForUser = false;
//           String UserFirstName = properties.getProperty("AUDITEE_USER");
//         String UserFullName = UserFirstName;
//         int count1=0;
//         isRecordSelectedForUser=selectingTheUserReview(UserFullName,isRecordSelectedForUser,count1);
//         Thread.sleep(12000);
//         driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
//         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button",sno,false);  
//         Thread.sleep(2000);
//         sno++;
//           driver.findElement(By.id("commentsInAmStartAuditForm")).sendKeys(properties.getProperty("AM_1500"));
//           document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter The Comments",sno,false);
//           Thread.sleep(2000);
//           JavascriptExecutor jse1 = (JavascriptExecutor)driver;
//           jse1.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By
					.xpath("//*[@id=\"selectUsersForExternalAuditObservationsReview\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Reviewer Button", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			Thread.sleep(2000);
//           driver.findElement(By.id("locTreeInQmsProdReg_3_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(2000);
			boolean isRecordSelectedForUser1 = false;
			String UserFirstName1 = properties.getProperty("AUDIT_REVIEWER");
			String UserFullName1 = UserFirstName1;
			int count2 = 0;
			isRecordSelectedForUser = selectingTheAuditReview(UserFullName1, isRecordSelectedForUser1, count2);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"selectUsersForExternalAuditObservationsApproval\"]/div/div[3]/div[2]/span"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Add Approver Button", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInQmsProdReg_2_span")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select The Location", sno, false);
			Thread.sleep(2000);
			boolean isRecordSelectedForUser2 = false;
			String UserFirstName2 = properties.getProperty("AUDIT_APPROVER");
			String UserFullName2 = UserFirstName2;
			int count3 = 0;
			isRecordSelectedForUser = selectingTheApproverReview(UserFullName2, isRecordSelectedForUser2, count3);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Select Button", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("commentsInExternalWin")).sendKeys(properties.getProperty("AM_2000"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit Button", sno, false);
			Thread.sleep(2000);
//           WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
			if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
	         {
	             document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button",sno,false);
	         }
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On OK Button", sno, false);
			driver.findElement(By.className("modal-btn")).click();
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Username", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut", sno, true);

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

//   private boolean selectingTheUserReview(String UserFullName, boolean isRecordSelectedForUser, int count1) throws InterruptedException {
//  	 WebElement table = driver.findElement(By.id("usersTableContainer"));
//	        WebElement tableBody = table.findElement(By.tagName("tbody"));
//	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	        int totalNoOfRecords = 0;
//	        int noOfRecordsChecked = 0;
//	        if (perPageNoOfRecordsPresent > 0) {
//	            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//	            String[] parts = a.split(" of ");
//	            try {
//	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
//	        }
//	        if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
//	            if ((totalNoOfRecords > 1) && ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
//	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
//	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
//	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();//documentType
//
//	            }
//	            ++count1;
//	        }
//	        if (perPageNoOfRecordsPresent > 0) {
//	            while (noOfRecordsChecked < totalNoOfRecords) {
//	                if (totalNoOfRecords > 1) {
//	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
//	                        String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
//	                        if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
//	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
//	                            isRecordSelectedForUser = true;
//	                            break;
//	                        }
//	                    }
//	                    if (isRecordSelectedForUser) {
//	                        break;
//	                    }
//	                } else {
//	                    String UserNumberSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
//	                    if (UserFullName.equalsIgnoreCase(UserNumberSequence)) {
//	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).click();
//	                        isRecordSelectedForUser = true;
//	                        break;
//	                    }
//	                }
//	                noOfRecordsChecked += perPageNoOfRecordsPresent;
//	                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
//	                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
//	                    Thread.sleep(3000);
//	                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
//	                    tableBody = table.findElement(By.tagName("tbody"));
//	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//	                }
//	            }
//	        }
//	        return isRecordSelectedForUser;
//	    }
	private boolean selectRecdExternalObservationsAndResponse(String ExternalId, boolean isRecordSelected, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((ExternalId == null) || ("".equalsIgnoreCase(ExternalId)))) {
				ExternalId = driver
						.findElement(By.xpath(
								"//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((ExternalId == null) || ("".equalsIgnoreCase(ExternalId))) {
				ExternalId = driver
						.findElement(By
								.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]"))
						.getText();// documentType

			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String ExternalNumberSequence = driver.findElement(
								By.xpath(".//*[@id='auditsContainerInEnterAuditFindingsForm']/div/table/tbody/tr[ " + i
										+ "]/td[3]"))
								.getText();// documentTypeName
						if (ExternalId.equalsIgnoreCase(ExternalNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String ExternalNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (ExternalId.equalsIgnoreCase(ExternalNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"auditsContainerInEnterAuditFindingsForm\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#auditsContainerInEnterAuditFindingsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("auditsContainerInEnterAuditFindingsForm"));// Document Tree
																									// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private boolean selectingTheAuditReview(String UserFullName1, boolean isRecordSelectedForUser1, int count2)
			throws InterruptedException {

		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1)))) {
				UserFullName1 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName1 == null) || ("".equalsIgnoreCase(UserFullName1))) {
				UserFullName1 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName1.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser1 = true;
							break;
						}
					}
					if (isRecordSelectedForUser1) {
						break;
					}
				} else {
					String UserNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName1.equalsIgnoreCase(UserNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForUser1;
	}

	private boolean selectingTheApproverReview(String UserFullName2, boolean isRecordSelectedForUser2, int count3)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if ((totalNoOfRecords > 1) && ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2)))) {
				UserFullName2 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((UserFullName2 == null) || ("".equalsIgnoreCase(UserFullName2))) {
				UserFullName2 = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]")).getText();// documentType

			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String UserNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (UserFullName2.equalsIgnoreCase(UserNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelectedForUser2 = true;
							break;
						}
					}
					if (isRecordSelectedForUser2) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (UserFullName2.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForUser2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForUser2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForUser2;
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
