package tool;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import login.LoginByCorrectID;
import java.util.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class testLink extends LoginByCorrectID
{
	private static WebDriver driver;
	
	public testLink(WebDriver driver) {
		this.driver = driver;
	}
	 
	 public static String createTestBuild(String TestProject, String TestPlan, String env) throws TestLinkAPIException{
		 DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		 Date date = new Date();
		 String buildID = env + "-" + dateFormat.format(date);
		 return buildID;
		 }
}
