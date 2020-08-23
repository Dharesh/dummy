package genric;

import java.util.concurrent.TimeUnit;

import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
//@BM(OA) and @AM(CA) acts like pre and post for all the test scripts
//@Before Suite this is used to specify suite level condition which is apply to all test scripts
public class Genric_Test  {
	File_Manager fm=new File_Manager();
	public WebDriver driver ;
	public static ExtentSparkReporter report;
	public static ExtentReports extent;
	public static ExtentTest test;   //test contain test case name

//@Before Suite used which is conditions is apply for all the test scripts to generate extent reports 
//precondition for generate reports	
	@BeforeSuite 
	public void setup() 
	{
	report = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/index.html");
	report.config().setDocumentTitle("redshark Version 1.12");
	report.config().setReportName("Regression Test Cases");
    extent=new ExtentReports();
	extent.attachReporter(report);
	}
	
//@aftersuite : used to p
    @AfterSuite
    public void tearDown() {
    	extent.setSystemInfo("Windows", "10");
    	test.assignAuthor("Dharesh Kiran M");
    	test.assignCategory("web Automation on Chrome");
    	test.assignDevice(" HP Laptop");
   	 extent.flush();
    }

 // @beforemethod used to specify the preconditions  for all the tests
	@Parameters({"browser"})
     @BeforeMethod
     public void openApp(@Optional("chrome") String browser)
     {
		if(browser.equals("chrome"))
		{
	WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.get(fm.getQatUrl());
		}else if(browser.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
			  driver.get(fm.getQatUrl());
		}
		driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(fm.getimplicetlyWait(), TimeUnit.SECONDS);
	  }
	
//@afterMethod: this is used to post condition for all tests
//closing the browser and implemented the Listeners
     @AfterMethod
     public void closeApp(ITestResult res){
    	 System.out.println(res.getStatus());
    	 String testName = res.getName();
    	 if (ITestResult.FAILURE==res.getStatus())
    	 {
    		test.fail(MediaEntityBuilder.
    				createScreenCaptureFromPath(new Screenshots().capture(driver, testName)).build());
    	 }
    	 
     driver.quit();
     }
   



}

