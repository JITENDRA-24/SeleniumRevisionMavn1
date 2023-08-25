package Module2Contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PBContactDetailsM {

	@FindBy(xpath="//div[text()='Contact details']") private WebElement contactDetails;
	@FindBy(xpath="(//input[@type='text'])[1]") private WebElement mobNumber;
	@FindBy(xpath="(//input[@type='text'])[2]") private WebElement email;
	@FindBy(xpath="//div[@title='Logout']") private WebElement logout;
	@FindBy(xpath="//div[@class='sc-gGBfsJ eBYNEi']/a") private WebElement home;
	
	public PBContactDetailsM (WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnContactDetails() {
		contactDetails.click();
	}
	
	public String actualmobNumber() {
	String	actualMobNumber = mobNumber.getAttribute("value");
	return actualMobNumber;
	}
	
	public String actualemail() {
	String actualEmail = email.getAttribute("value");
	return actualEmail;
	}
	public void clickOnHomePage() {
	home.click();	
	}
	public void clickonLogout() {
		logout.click();
	}
}
