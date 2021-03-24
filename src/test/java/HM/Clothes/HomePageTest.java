package HM.Clothes;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.BasePage;

public class HomePageTest extends BasePage{

	public WebDriver driver;
	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());
	public LandingPage lp;
	private LoginPage l;
	private Connection connection;
	private static Statement statement;
	private static ResultSet rs;
	
	@BeforeClass
    public void setUp() throws SQLException {
            String dbURL = "jdbc:mysql://localhost:3306/hmdatabase";
            String user = "xxxx";
            String password = "xxxx";
            connection = null;     

                log.info("Connecting to Database...");
                connection = DriverManager.getConnection(dbURL, user, password);
                if (connection != null) {
                log.info("Connected to the Database...");
                }
    }

    @AfterClass
    public void ConnectiontearDown() {
      if (connection != null) {
                try {
                    log.info("Closing Database Connection...");
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
      }

	@BeforeTest
	public void initialize() throws IOException {
		log.info("Driver is initializing");
		driver=initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void tearDown() {
		log.info("Driver is closing");
		driver.quit();
	}
	
	@Test
	public void costumerServiceButtonTest() {
		lp=new LandingPage(driver);
		log.info("Image height: "+ lp.getHmImage().getCssValue("height"));
		log.info("Image width: "+ lp.getHmImage().getCssValue("width"));
		log.info("Image background-size:" + lp.getHmImage().getCssValue("background-size"));
		
		SoftAssert softassert= new SoftAssert();
		log.info("SoftAssert method was started");
		softassert.assertEquals(lp.getHmImage().getCssValue("height"), "44px");
		log.info("SoftAssert method was executed");
		
		log.info("HardAssert method was started");
		Assert.assertEquals(lp.getHmImage().getCssValue("width"), "68px");
		log.info("HardAssert method passed");	
	}

	@Test
    public void getLoginValuesFromDataBase() {
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM hmdatabase.values";
            rs = statement.executeQuery(query);

            while(rs.next()){    
                String validUserName= rs.getString("valid username");
                String validPassword=rs.getString("valid password");
                String invalidUserName=rs.getString("invalid username");
                String invalidPassword=rs.getString("invalid password");  
                
                l = new LoginPage(driver);
            	lp = new LandingPage(driver);
                lp.getSigninButton().click();
                 
                //invalid username invalid password
                l.getUsername().sendKeys(invalidUserName);
                l.getPassword().sendKeys(invalidPassword);
                l.getSubmit().click();
                log.info("Invalid username invalid password error message is: " + l.getErrMsgUsername().getText());
                
                //invalid username valid password
                l.getUsername().clear();
                l.getPassword().clear();
                l.getUsername().sendKeys(invalidUserName);
                l.getPassword().sendKeys(validPassword);
                l.getSubmit().click();
                log.info("Invalid username valid password error message is: " + l.getErrMsgUsername().getText());
                
                //valid username invalid password
                l.getUsername().clear();
                l.getPassword().clear();
                l.getUsername().sendKeys(validUserName);
                l.getPassword().sendKeys(invalidPassword);
                l.getSubmit().click();
                log.info("Valid username invalid password error message is: " + l.getErrMsgPassword().getText());
                
                //valid username valid password
                l.getUsername().clear();
                l.getPassword().clear();
                l.getUsername().sendKeys(validUserName);
                l.getPassword().sendKeys(validPassword);
                l.getSubmit().click(); 
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
	}
}
	
	
	
	
	
	

