package login;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import pom.core;
import testlink.api.java.client.TestLinkAPIResults;
import tool.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class creatBuild{
	protected static event event;
	private static String environment, retValue;
	protected static core test;	
	protected static testLink testLink;
	public static String TestProject = "GitHub Login", TestPlan = "Automation", build, 
			pass = TestLinkAPIResults.TEST_PASSED, fail = TestLinkAPIResults.TEST_FAILED, result;

	@Before
	public void setUp() throws Exception 
	{
		test = new core();
		test.driver();
		event = new event(test.getDriver());		
		build = testLink.createTestBuild(TestProject, TestPlan, event.getProperty("environment","env"));
		event.storeProperty("buildNum", "build", build);
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
		test.getDriver().quit();
	}	
	
	@Test
	public void creatTestBuildOnTestLink() throws Exception
	{
		System.out.println("Test build " + event.getProperty("buildNum","build") + " created!");
	}	
}





