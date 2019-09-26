package tool;

import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import login.LoginByCorrectID;
import java.util.*;
import java.util.Properties;
import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import tool.event;


public class verify
{
	private static RemoteWebDriver driver;
	protected static event event;
	static InputStream inputStream;
	static Properties configFile;

	public verify(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public static void multiDataByPath(RemoteWebDriver driver, String[] path)
	{
		String[] data = path;
		event = new event(driver);
		for(String element:data) {
			try {
				Thread.sleep(1000);
				event.findXpath(event.getProperty("element", element), 20);
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
	}
	
	public static String[] saveFilterValue(RemoteWebDriver driver, String path)
	{
		event = new event(driver);
		int check = 0, i = 1;
		String[] data = null;
		 data = new String[11];
		while(check == 0) {
			try {
				data[i] = driver.findElement(By.xpath(path + "//tr[" + i + "]/td[2]")).getText();
				i ++;
			} catch (Exception e) {
				System.out.println(e);
				check = 1;
			}
		}
		return data;			
	}
	
	public static void multiAssertion(String[][] arr,int length)
	{
		for(int i=0;i<length-1;i++) {
			for(int count=0;count<arr[i].length;count++) {
				try {
					assertEquals(arr[i][count],arr[i+1][count]);
					System.out.println(arr[i][count] + " -- " + arr[i+1][count]);
				} catch (Exception e) {
					System.out.println(e);
				}
			}			
		}
	}

}
