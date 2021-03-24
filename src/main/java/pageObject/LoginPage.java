package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By username = By.id("modal-txt-signin-email"); 
	private By password = By.id("modal-txt-signin-password");
	private By submit = By.cssSelector("button[class*='btn-login']");
	private By errMsgUsername= By.cssSelector("span[id*='format-error'] ");
	private By errMsgPassword= By.cssSelector("span[id*='unknown-error-type-error']");
	private By becomeAmember= By.xpath("//button[@data-remodal-trigger='join']");
	
	
	public WebElement getUsername() {
		return driver.findElement(username);
	}
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	public WebElement getSubmit() {
		return driver.findElement(submit);
	}
	public WebElement getErrMsgUsername() {
		return driver.findElement(errMsgUsername);
	}
	public WebElement getErrMsgPassword() {
		return driver.findElement(errMsgPassword);
	}
	public WebElement getBecomeAMember() {
		return driver.findElement(becomeAmember);
	}
}
