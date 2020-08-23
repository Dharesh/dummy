package regressionScripts;
import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genric.Excel;
import genric.Genric_Test;
import pom.Enter_Time_Track;
import pom.Login_page;
import pom.Report_Bug_Vendor;

//Inheritance : All Test Clases Classes are extending  Genric_test
//Test classes includes test scripts
public class Regression extends Genric_Test {

@Test
public void validLoginLogout() {
	
//	test=extent.createTest("validLoginLogout");
//	test.log(Status.INFO, "validLoginLogout test script started executing");
	
	String username = Excel.readData("regression", 1, 0);
	String password = Excel.readData("regression", 1, 1 );
	String hp_Title = Excel.readData("regression", 1, 3);
	
	Login_page lp=new Login_page(driver); 
	lp.enterUn(username);
//	test.pass("Entered Valid username Successfuly");
	lp.enterPs(password);
//	test.pass("Entered Valid password Successfuly");
	lp.clickLogin();
//	test.pass("logged into ActiTime Successfuly");
	Enter_Time_Track hp=new Enter_Time_Track(driver) ;
	hp.verifyTitle(hp_Title, 10);
//	test.pass("Verified Title of ActiTime Successfuly");
	hp.clickonLgout();
//	test.pass("Logout From ActiTime Successfuly");
}

@Test
public void invalidLogin() {
//	extent.createTest("invalidLogin");
//	test.log(Status.INFO, "invalidLogin test script started executing");
	
	String username = Excel.readData("regression", 2, 0);
	String password = Excel.readData("regression", 2, 1);
	String lp_title = Excel.readData("regression", 2, 2);
	
	Login_page lp=new Login_page(driver); 
	lp.enterUn(username);
//	test.pass("Entered Valid username Successfuly");
	lp.enterPs(password);
//	test.pass("Entered Valid password Successfuly");
	lp.clickLogin();
//	test.pass("logged into ActiTime Successfuly");
	lp.verifyErrorMsg();
//	test.pass("Verified Error message Successfuly");
	lp.verifyTitle(lp_title, 10);
//	test.pass("Verified Title of ActiTime Successfuly");
}

@Test
public void sendBugReport() {
//	extent.createTest("sendBugReport");
//	test.log(Status.INFO, "sendBugReport test script started executing");
	
	String username = Excel.readData("regression", 4, 0);
	String password = Excel.readData("regression", 4, 1);
	String bugSummary = Excel.readData("regression", 4, 5);
	
	String fn = Excel.readData("regression", 4, 6);
	String ln = Excel.readData("regression", 4, 7);
	String email = Excel.readData("regression", 4, 8);
	String company = Excel.readData("regression", 4, 9);
	Login_page lp=new Login_page(driver); 
	lp.enterUn(username);
//	test.pass("Entered Valid username Successfuly");
	lp.enterPs(password);
//	test.pass("Entered Valid password Successfuly");
	lp.clickLogin();
//	test.pass("logged into ActiTime Successfuly");
	Enter_Time_Track hp=new Enter_Time_Track(driver) ;
	hp.clickOnHelp();
//	test.pass("Clicked on HelpButton Successfuly");
	hp.clickOnReportBug();
//	test.pass("Clicked on Report Bug link Successfuly");
    hp.verifyChildtab(2, 5);
	Set<String> ch = driver.getWindowHandles();
	for (String tab : ch) {
		driver.switchTo().window(tab);// write genric method for this in base page
	}
	Report_Bug_Vendor rp=new Report_Bug_Vendor (driver);
	rp.enterBugDescription(bugSummary);
//	test.pass("Entered Bug Description Successfuly");
	rp.enterFirstName(fn);
//	test.pass("Entered First Name Successfuly");
	rp.enterLasttName(ln);
//	test.pass("Entered Last Name Successfuly");
	rp.enterEmail(email );
//	test.pass("Entered Email Address Successfuly");
	rp.enterCompanyName(company);
//	test.pass("Entered Company Name Successfuly");
	rp.clickOnSendBugReport();
//	test.pass("Clicked on Send Bug Report Button Successfuly");
	
}

}