package login;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;


import pom.core;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIResults;
import tool.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginByCorrectID{
	protected static event event;
	private static String environment, retValue;
	protected static core test;	
	protected static testLink testLink;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	public static String TestProject = "GitHub Login", TestPlan = "Automation", build, 
			pass = TestLinkAPIResults.TEST_PASSED, fail = TestLinkAPIResults.TEST_FAILED, result;

	@Before
	public void setUp() throws Exception 
	{
		test = new core();
		test.driver();
		event = new event(test.getDriver());		
		build = event.getProperty("buildNum","build");		
		String url = test.url(event.getProperty("environment","env"));	
		
		try
		{
			test.getDriver().navigate().to(url);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		event = new event(test.getDriver());
	}
	
	@After
	public void tearDown() throws Exception 
	{
		event.clickByXpath(event.getProperty("element","extendProfileMenu"), 20);
		event.clickByXpath(event.getProperty("element","signOutProfileMenu"), 20);
		Thread.sleep(2000);
		test.getDriver().quit();
	}	
	
	@Test
	public void gitHubLogin() throws Exception
	{
		String caseID = "GL-1";	
		try {
			event.clickByXpath(event.getProperty("element","signIn"), 20);
			event.findXpath(event.getProperty("element","userName"), 20);
			test.getDriver().findElement(By.xpath(event.getProperty("element","userName"))).sendKeys(event.getProperty("userInfo","account"));
			test.getDriver().findElement(By.xpath(event.getProperty("element","password"))).sendKeys(event.getProperty("userInfo","password"));
			test.getDriver().findElement(By.xpath(event.getProperty("element","signInSubmit"))).click();
			event.findXpath(event.getProperty("element","extendProfileMenu"), 20);
			test.getDriver().findElement(By.xpath(event.getProperty("element","extendProfileMenu")));
			result = pass;
			retValue = "pass";
		}catch(Exception e) {
			System.out.println(e);
			retValue = event.recordException(e);
			result = fail;
		}finally{			
			event.screenShot(build, caseID + event.getProperty("testCase", caseID), result, retValue);
			if(result == fail) {
				Assert.fail("Failed test case id: " + caseID + "\n" + retValue);
			}
		}		
	}	
}
