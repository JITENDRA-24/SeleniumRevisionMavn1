package Module1_login_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import Module1_Login.PBHomePage;
import Module1_Login.PBLoginPage;
import Module1_Login.PBMobNumPage;
import Module1_Login.PBMyAccPage;
import Module1_Login.PBProfilePage;
import Module1_Login.PBPwdPage;
import Module2Contact.PBContactDetailsM;

public class PBContactsDetailsVerification extends BaseClass {
	PBLoginPage login;
	PBMobNumPage mobNum;
	PBPwdPage pwd;
	PBHomePage home;
	PBMyAccPage myAcc;
	PBProfilePage profile;
	PBContactDetailsM  contactDetails;
	SoftAssert soft;
	int TCID;
	
	static ExtentTest test;
	static ExtentHtmlReporter reporter;


	@BeforeTest
	public void extentreportBeforeTest ( ) {

	reporter = new ExtentHtmlReporter ("test—output/ExtentReport/Extent.html");
	ExtentReports extent = new ExtentReports ( ) ;
	extent.attachReporter(reporter);

	//System.out.println("Before Test");
	Reporter.log("Before Test", true);
	}	
	
	@BeforeClass
	public void openBrowser1() throws EncryptedDocumentException, IOException
	{
		Reporter.log("Before Class Start", true);
		initializeBrowser();
		 login=new PBLoginPage(driver);
		 mobNum=new PBMobNumPage(driver);
		 pwd=new PBPwdPage(driver);
		 home=new PBHomePage(driver);
		 myAcc=new PBMyAccPage(driver);
		 profile=new PBProfilePage(driver);
		 contactDetails = new PBContactDetailsM(driver);
		 soft = new SoftAssert();
	}
	
	@BeforeMethod
	public void loginToApp1() throws InterruptedException, EncryptedDocumentException, IOException
	{
		Reporter.log("Before Method Start", true);
		login.clickPBLoginPageSignIN();
		mobNum.inpPBMobNumPageMobNum(UtilityClass.getPFData("mobNum"));
		mobNum.clickPBMobNumPageSignInWithPwd();
		pwd.inpPBPwdPagePWD(UtilityClass.getPFData("pwd"));
		pwd.clickPBPwdPageSignIn();
		Thread.sleep(2000);
		home.openDDOptionPBHomePageMyAcc();
		Thread.sleep(3000);
		myAcc.clickPBMyAccPageMyProfile();
		profile.switchToSwitchChildWindow();
	}
	
	@Test (priority=1)
	public void verifyMobNumber() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		Reporter.log("Test1 Execution", true);
		TCID=1001;
		contactDetails.clickOnContactDetails();
		String actMobNumber = contactDetails.actualmobNumber();
	   String expMobNumber = UtilityClass.getTestData(1, 0);
	   soft.assertEquals(actMobNumber, expMobNumber, "Failed: both names are diff- ");
	   
	   Thread.sleep(3000);
	}
	@Test (priority=2)
	public void verifyEmail() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		Reporter.log("Test2 Execution", true);
		TCID=1002;
		contactDetails.clickOnContactDetails();
		String actEmail = contactDetails.actualemail();
	   String exEmail = UtilityClass.getTestData(2, 0);
	   soft.assertEquals(actEmail, exEmail, "Failed: both names are diff- ");
	   
	   Thread.sleep(1000);
	}
	
	@AfterMethod
	public void name(ITestResult s1) throws IOException, InterruptedException 
	{
		Reporter.log("After Method running", true);
		if(s1.getStatus()==ITestResult.FAILURE)
		{
			UtilityClass.captureSS(driver, TCID);
		}	
		Thread.sleep(2000);
		contactDetails.clickonLogout();
		Thread.sleep(1000);
		contactDetails.clickOnHomePage();
	}

	@AfterClass
	public void closeBrowser()
	{
		Reporter.log("closing Browser", true);
		driver.quit();
	}
	
	@AfterTest
	public void clean() {
		Reporter.log("After test Execute", true);
		System.gc();
	}
	
	
	
}
