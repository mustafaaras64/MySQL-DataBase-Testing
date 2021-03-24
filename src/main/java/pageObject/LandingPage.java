package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	private By navBar = By.cssSelector("nav[class='menu']");
	private By signinButton = By.cssSelector("a[data-remodal-trigger='signin']");
	private By hmImage = By.cssSelector("a[class='menu__hm']");

	public WebElement getSigninButton() {
		return driver.findElement(navBar).findElement(signinButton);
	}
	public WebElement getHmImage() {
		return driver.findElement(hmImage);
	}
}
