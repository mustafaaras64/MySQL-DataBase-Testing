package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		
	prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
	prop.load(fis);
	String browserName = prop.getProperty("browser");
	
	if(browserName.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "----chromedriver.exe path here----");					
		driver = new ChromeDriver();			
	}
	else if(browserName.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", "----geckodriver.exe path here----");					
		driver = new FirefoxDriver();				
	}
	else if(browserName.equals("EDGE")){
		System.setProperty("webdriver.edge.driver", "----msedgedriver.exe path here----");					
		driver = new EdgeDriver();	
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {						
		TakesScreenshot ts = (TakesScreenshot) driver;					
		File source= ts.getScreenshotAs(OutputType.FILE);					
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";					
		FileUtils.copyFile(source,new File(destinationFile));					
		return destinationFile;					
	
	}
}
	
	
	
	
	
	

