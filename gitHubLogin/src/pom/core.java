package pom;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import tool.*;

public class core {
	public static RemoteWebDriver driver;
	public static String output;
	
	public void driver() throws MalformedURLException
	{		

		DesiredCapabilities capabilities = null;
		switch(event.getProperty("environment", "browser"))
		{
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", ".//driver//chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", ".//driver//IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			break;
		}

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
	}
	
	public RemoteWebDriver getDriver() {
		return driver;
	}
	
	public static String url(String environment) throws InterruptedException, MalformedURLException
	{
		switch (environment) 
        {
            case "Stage": output= "https://github.com/";
            	break;
            case "Production": output= "https://github.com/";
        		break;
            case "QA": output= "https://github.com/";
        		break;
        }
		return output;
	}
}
