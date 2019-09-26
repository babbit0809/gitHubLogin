package tool;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import login.LoginByCorrectID;
import pom.core;
import java.util.*;
import javax.imageio.ImageIO;
import static org.testng.Assert.assertEquals;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;



public class event
{
	private static RemoteWebDriver driver;
	StringWriter sw = null;
	PrintWriter pw = null;
	static InputStream inputStream;
	static Properties configFile;
	private XSSFWorkbook workbook;
	private String downloadPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\";

	public event(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public void findXpath(String path, int retry) throws InterruptedException 
	{
		int i = 0, cnt = 0;
		while(i == 0 && cnt < retry) {
			try {
				driver.findElement(By.xpath(path));
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
	}
	
	public void displayXpath(String path, int retry) throws InterruptedException 
	{
		boolean i = false;
		int cnt = 0;
		while(i == false && cnt < retry) {
			try {
				i = driver.findElement(By.xpath(path)).isDisplayed();
			}catch(Exception e) {
				System.out.println(e);				
			}
			Thread.sleep(1500);
			cnt ++;
		}
	}
	
	public boolean findText(String path, String text, int retry) throws InterruptedException 
	{
		int i = 0, cnt = 0;
		String txt = "";
		while(i == 0 && cnt < retry) {
			try {
				txt = driver.findElement(By.xpath(path)).getText();
				if(text.contains(txt)) {
					i = 1;
				}
				Thread.sleep(1500);
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
		if(i == retry) {
			return false;
		}
		return true;		
	}
	
	public void clickByXpath(String path, int retry) throws InterruptedException 
	{
		int i = 0, cnt = 0;
		while(i == 0 && cnt < retry) {
			try {
				driver.findElement(By.xpath(path));
				Thread.sleep(3000);
				driver.findElement(By.xpath(path)).click();
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
	}
	
	public void clickByID(String id, int retry) throws InterruptedException 
	{
		int i = 0, cnt = 0;

		while(i == 0 && cnt < retry) {
			try {
				driver.findElement(By.id(id));
				Thread.sleep(2000);
				driver.findElement(By.id(id)).click();
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
	}
	
	public void type(String location, String content, int retry) throws InterruptedException 
	{
		int i = 0, cnt = 0;
		StringSelection stringSelection = new StringSelection(content);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		WebElement element = driver.findElement(By.xpath(location)); 
		while(i == 0 && cnt < retry) {
			try {
				element.sendKeys(Keys.CONTROL + "v");
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
	}
	
	public String storeText(String path, int retry) throws InterruptedException 
	{
		String txt = null;
		int i = 0, cnt = 0;
		while(i == 0 && cnt < retry) {
			try {
				txt = driver.findElement(By.xpath(path)).getText();
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
		return txt;
	}
	
	public String storeValue(String path, int retry) throws InterruptedException 
	{
		String txt = null;
		int i = 0, cnt = 0;
		while(i == 0 && cnt < retry) {
			try {
				txt = driver.findElement(By.xpath(path)).getAttribute("value");
				i = 1;
			}catch(Exception e) {
				System.out.println(e);
				Thread.sleep(1500);
				cnt ++;
			}
		}
		return txt;
	}	

	public String storeProperty(String file, String key, String value)
	 {				  
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream("resources\\" + file + ".properties");
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	 }
	
	
	 public static String getProperty(String file, String key)
	 {				  
		Properties prop = new Properties();
		InputStream input = null;

			try {
				input = new FileInputStream("resources\\" + file + ".properties");
				prop.load(input);

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return prop.getProperty(key);
	 }
	 

	 
	 public void switchToWindow(String window) throws Exception{
		 String winHandleBefore = null;
		 Thread.sleep(3000);
		 switch (window)
		 {		 	
		 	case "after":
		 		winHandleBefore = driver.getWindowHandle();
		 		storeProperty("WindowID","windowID",winHandleBefore);
		 		for(String winHandle : driver.getWindowHandles()){
		 			driver.switchTo().window(winHandle);
		 			Thread.sleep(2000);
		 			driver.manage().window().maximize();
		 		}
		 		break;
		 	case "before":
		 		driver.close();
		 		driver.switchTo().window(getProperty("WindowID","windowID"));
		 		break;
		 }
	 }
	 
	 public String getAttributes(String location, String attributes){
		 WebElement element = driver.findElement(By.xpath(location));
		 String value = element.getAttribute("id");
		 return value;
	 }
	 
	 public void switchToiFrame(String location, String iframe){
		 WebElement element = driver.findElement(By.xpath(location));
		 switch (iframe)
		 {		 	
		 	case "after":
		 		driver.switchTo().frame(element);
		 		break;
		 	case "before":
		 		driver.switchTo().defaultContent();
		 		break;
		 }
	 }
	 
	 public void Login(String user, String pwd) 
	 {
			try {
//				clickByXpath(getProperty("element","loginEntry"), 20);
				driver.findElement(By.xpath(getProperty("element","loginEntry"))).click();
				clickByXpath(getProperty("element","signName"), 20);
				driver.findElement(By.xpath(getProperty("element","signName"))).sendKeys(user);
				clickByXpath(getProperty("element","signPass"), 20);
				driver.findElement(By.xpath(getProperty("element","signPass"))).sendKeys(pwd);			
				driver.findElement(By.xpath(getProperty("element","signSubmit"))).click();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	 
	 public void drag(WebDriver driver, String source, String target) throws InterruptedException, AWTException 
	 {
		 WebElement elementSource = driver.findElement(By.xpath(source)); 
		 WebElement elementTarget = driver.findElement(By.id(target));
		 Point from = elementSource.getLocation();
		 Point to = elementTarget.getLocation();
		 Robot robot = new Robot(); 
		 robot.mouseMove(from.getX(),from.getY()+120);
		 Thread.sleep(2000);
		 robot.mousePress(InputEvent.BUTTON1_MASK);
		 robot.mouseMove(to.getX(),to.getY()+120);
		 Thread.sleep(3000);
		 robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	 
	 public void dragByOffset(WebDriver driver, String path, int x, int y) throws InterruptedException, AWTException 
	 {
		 Actions dragger = new Actions(driver);
		 WebElement element = driver.findElement(By.xpath(path)); 
		 dragger.dragAndDropBy(element, x, y);
		 Thread.sleep(3000);
	}
	 
	 public void moveToElement(WebDriver driver, String path) throws InterruptedException, AWTException 
	 {
		 Actions dragger = new Actions(driver);
		 WebElement element = driver.findElement(By.xpath(path)); 
		 dragger.moveToElement(element);
		 Thread.sleep(3000);
	}
	 
	 public void keyString(String str) throws InterruptedException, AWTException 
	 {
		 Robot robot = new Robot();
		 Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		 Transferable tText = new StringSelection(str);
		 clip.setContents(tText, null);
		 keyPressWithCtrl(robot, KeyEvent.VK_V);
	}

	 public void keyPressWithCtrl(Robot robot, int key) throws InterruptedException, AWTException 
	 {
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(key);
		 robot.keyRelease(key);
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 Thread.sleep(3000);
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
	}
	 
	 public void pageDownToEnd() throws InterruptedException, AWTException 
	 {
		 Robot robot = new Robot();
		 robot.keyPress(KeyEvent.VK_SPACE);
		 Thread.sleep(3000);
		 robot.keyRelease(KeyEvent.VK_SPACE);
	}
	 
		public boolean checkProjectFileExist(String name, int retry) throws InterruptedException{
			String filename = downloadPath + name + ".xls";
			System.out.println(filename);
			boolean status = false;
			int i = 0, cnt = 0;
			while(i == 0 && cnt < retry){
				try {
					File file = new File(filename);
					
					if(file.exists()){
						status = true;
						i = 1;
					}else{
						System.out.println("File Not Found");
						status = false;
						Thread.sleep(1500);
						cnt ++;
					}					
				}catch(Exception e){
					System.out.println("File Not Found");
					e.printStackTrace();
					Thread.sleep(1500);
					cnt ++;
				}
			}			
			return status;
		}
	 
		public boolean checkFileExist(String name, int retry) throws InterruptedException{
			String filename = downloadPath + name + ".xlsx";
			System.out.println(filename);
			boolean status = false;
			int i = 0, cnt = 0;
			while(i == 0 && cnt < retry){
				try {
					File file = new File(filename);
					
					if(file.exists()){
						status = true;
						i = 1;
					}else{
						System.out.println("File Not Found");
						status = false;
						Thread.sleep(1500);
						cnt ++;
					}					
				}catch(Exception e){
					System.out.println("File Not Found");
					e.printStackTrace();
					Thread.sleep(1500);
					cnt ++;
				}
			}			
			return status;
		}
		
		
		public void checkProjectFileDelete(String name){
			try{
				String filename = downloadPath + name + ".xls";
				System.out.println(filename);
				File file = new File(filename);
				if(file.exists()){
					file.delete();
				}
			}catch(Exception e){
				System.out.println("File delete fail");
				e.printStackTrace();
			}
		}
		
		public void checkCSVDelete(String name){
			try{
				String filename = downloadPath + name + ".xlsx";
				File file = new File(filename);
				if(file.exists()){
					file.delete();
				}
			}catch(Exception e){
				System.out.println("File delete fail");
				e.printStackTrace();
			}
		}
		
		public String[] checkProjectFile(String name) throws Exception{
			int check = 0;
			String filename = downloadPath + name + ".xls";
			System.out.println(filename);
			String[] data = null;
			while(check == 0) {
				try {
					InputStream ExcelFileToRead = new FileInputStream(filename);
					HSSFWorkbook  wb = new HSSFWorkbook(ExcelFileToRead);
			        HSSFWorkbook test = new HSSFWorkbook(); 
			        HSSFSheet sheet = wb.getSheetAt(0);
			        HSSFRow row; 
			        HSSFCell cell;

			        Iterator rows = sheet.rowIterator();
			        data = new String[20];
		            row=(HSSFRow) rows.next();
			        Iterator cells = row.cellIterator();
			            int order = 0;
			            while (cells.hasNext())
			            {
			                cell=(HSSFCell) cells.next();

			                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			                {
			                    	data[order] = cell.getStringCellValue();			                    	 
			                }
			                else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			                {
			                		Double numericValue = new Double(cell.getNumericCellValue());
			                		data[order] = Integer.toString(numericValue.intValue());
			                }
			                else
			                {
			                    //doing nothing
			                }
			                order ++;			                
			            }   
			            check = 1;
				}catch(Exception e){
					Thread.sleep(1500);
					e.printStackTrace();					
				}
			}	        
	        return data;
		}
		
		public String[] checkCSV(String name) throws Exception{
			int check = 0, count = 1;
			String filename = downloadPath + name + ".xlsx";
			String[] data = null;
			while(check == 0) {
				try {
					InputStream ExcelFileToRead = new FileInputStream(filename);
					XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
			        XSSFWorkbook test = new XSSFWorkbook(); 
			        XSSFSheet sheet = wb.getSheetAt(0);
			        XSSFRow row; 
			        XSSFCell cell;

			        Iterator rows = sheet.rowIterator();
			        data = new String[10];
			        
			        while (rows.hasNext())
			        {
			            row=(XSSFRow) rows.next();
			            Iterator cells = row.cellIterator();
			            int order = 1;
			            while (cells.hasNext())
			            {
			                cell=(XSSFCell) cells.next();

			                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
			                {
			                	if(count > 19 && count < 23) {
			                    	data[order] = cell.getStringCellValue();
			                    	order++;
			                	}
			                    
			                }
			                else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
			                {
			                	if(count == 19 || count == 23) {
			                		Double numericValue = new Double(cell.getNumericCellValue());
			                		String converToText = Integer.toString(numericValue.intValue());
			                		if(count == 19) {
			                			data[0] = converToText;
			                		}else if(count == 23) {
			                			data[4] = converToText;
			                		}
			                		
			                	}
			                }
			                else
			                {
			                    //doing nothing
			                }
			                count ++;
			            }   
			        }
					check = 1;
				}catch(Exception e){
					Thread.sleep(1500);
					e.printStackTrace();
				}
			}
						
	        
	        return data;
		}
		
		public String[][] checkXls(String name, int parseRow) throws Exception{
			int check = 0, count = 0, order;
			parseRow = parseRow + 1;
			String filename = downloadPath + name + ".xls";
			String[][] data = new String[10][10];
			while(check == 0) {
				try {
					InputStream ExcelFileToRead = new FileInputStream(filename);
					HSSFWorkbook  wb = new HSSFWorkbook(ExcelFileToRead);
			        HSSFWorkbook test = new HSSFWorkbook(); 
			        HSSFSheet sheet = wb.getSheetAt(0);
			        HSSFRow row; 
			        HSSFCell cell;

			        Iterator rows = sheet.rowIterator();
			        
			        while (rows.hasNext() && count < parseRow)
			        {	
			        	order = 1;
			            row=(HSSFRow) rows.next();
			            Iterator cells = row.cellIterator();
			            while (cells.hasNext())
			            {
			                cell=(HSSFCell) cells.next();

			                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			                {		
			                    	data[count][order] = cell.getStringCellValue();
			                }
			                else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			                {

			                		Double numericValue = new Double(cell.getNumericCellValue());
			                		String converToText = Integer.toString(numericValue.intValue());
			                		data[count][order] = converToText;
			                }
			                order ++;
			            }
			            count ++;
			        }
					check = 1;
				}catch(Exception e){
					Thread.sleep(1500);
					e.printStackTrace();
				}
			}
						
	        
	        return data;
		}
		
		public String[][] checkXlsx(String name, int parseRow) throws Exception{
			int check = 0, count = 0, order;
			parseRow = parseRow + 1;
			String filename = downloadPath + name + ".xlsx";
			String[][] data = new String[10][10];
			while(check == 0) {
				try {
					InputStream ExcelFileToRead = new FileInputStream(filename);
					XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
					XSSFWorkbook test = new XSSFWorkbook(); 
					XSSFSheet sheet = wb.getSheetAt(0);
					XSSFRow row; 
					XSSFCell cell;

			        Iterator rows = sheet.rowIterator();
			        
			        while (rows.hasNext() && count < parseRow)
			        {	
			        	order = 1;
			            row=(XSSFRow) rows.next();
			            Iterator cells = row.cellIterator();
			            while (cells.hasNext())
			            {
			                cell=(XSSFCell) cells.next();

			                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
			                {		
			                    	data[count][order] = cell.getStringCellValue();
			                }
			                else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
			                {

			                		Double numericValue = new Double(cell.getNumericCellValue());
			                		String converToText = Integer.toString(numericValue.intValue());
			                		data[count][order] = converToText;
			                }
			                System.out.println("[" + count + "]"+ "[" + order + "]" + data[count][order]);
			                order ++;
			            }
			            count ++;
			        }
					check = 1;
				}catch(Exception e){
					Thread.sleep(1500);
					e.printStackTrace();
				}
			}	        
	        return data;
		}
		
		public String recordException(Exception e){
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String retValue = sw.toString();
			return retValue;
		}
		
		public void uploadFilePath(String file) throws InterruptedException, AWTException {
			File filePath = new File("resources\\import\\" + file + ".xlsx");
			String absolutePath = filePath.getAbsolutePath();
			keyString(absolutePath);
		}
		
		public void screenShot(String build, String caseNum, String status, String message) throws IOException {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
			Date date = new Date();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String path = ".\\screenshots\\" + build + "\\" + status + "\\" + caseNum + "_" + dateFormat.format(date) + ".png";
			FileUtils.copyFile(scrFile, new File(path));
			
			File file = new File(path);
			Image image = ImageIO.read(file);
			Color color = new Color(255, 0, 0);
			int height = image.getHeight(null);
			int width = image.getWidth(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = bufferedImage.createGraphics();
			graphics.drawImage(image, 0, 0, width, height, null);
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
			graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			if (image != null) {
				graphics.setFont(new Font("Roboto", Font.PLAIN, 14));
				graphics.setColor(color);
				graphics.drawString(message, 30, 30);
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				ImageIO.write(bufferedImage, "png", fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}
		
		public void checkProjectPatentListExist() throws Exception {
			int check = 0;
			while(check == 0) {
				try {
					Thread.sleep(15000);
					WebElement patentListFolder = driver.findElement(By.xpath(getProperty("element","overviewFolder")));
					deleteProjectPatentList();
				}catch(Exception e) {
					Thread.sleep(1500);
					check = 1;
				}			
			}			
		}
		
		public void deleteProjectPatentList() throws Exception {
			try {
				clickByXpath(event.getProperty("element","overviewFolder"), 20);
				Thread.sleep(6000);
				clickByXpath(event.getProperty("element","overviewDeleteFolder"), 20);
				Thread.sleep(3000);
				clickByXpath(event.getProperty("element","overviewIncludeSub"), 20);
				clickByXpath(event.getProperty("element","overviewDeleteConfirm"), 20);
				clickByXpath(event.getProperty("element","viewStatus"), 20);
				Thread.sleep(8000);
				switchToWindow("after");
				projectViewStatus();
				switchToWindow("before");
				Thread.sleep(5000);
				driver.navigate().refresh();
			}catch(Exception e) {
				Thread.sleep(1500);
			}			
		}
		
		public int projectViewStatus() throws Exception {
			int check = 0, count = 0;
			String status = "";
			Thread.sleep(3000);
			while(check == 0 && count <= 40) {
				try {
					driver.findElement(By.xpath(event.getProperty("element","projectStatusComplete")));
					check = 1;
				}catch(Exception e) {
					Thread.sleep(1500);
					count ++;
				}
			}
			if(check == 1) {
				status = storeText(event.getProperty("element","projectStatusComplete"), 20);
				assertEquals(status, "Completed");
			}
			return check;						
		}
		
		public String getTableLastRow(String tablePath, int column) throws Exception {
			int check = 0, count = 1;
			String row = "";
			while(check == 0) {
				try {
					Thread.sleep(3000);
					driver.findElement(By.xpath(tablePath + "//tr[" + count + "]/td[" + column + "]"));
					row = driver.findElement(By.xpath(tablePath + "//tr[" + count + "]/td[" + column + "]")).getText();
					count ++;
				}catch(Exception e) {
					Thread.sleep(1500);
					check = 1;
				}
			}
			return row;						
		}
		
		public String getTableLastRow(int column) throws Exception {
			int check = 0, count = 1;
			String row = "";
			while(check == 0) {
				try {
					Thread.sleep(3000);
					driver.findElement(By.xpath("//tr[" + count + "]/td[" + column + "]"));
					row = driver.findElement(By.xpath("//tr[" + count + "]/td[" + column + "]")).getText();
					count ++;
				}catch(Exception e) {
					Thread.sleep(1500);
					check = 1;
				}
			}
			return row;						
		}
		
		public boolean checkMultiDimensionalArray(String[][] multiArray, String row, String value) throws Exception {
			int count = 1;
			boolean result = false;
			while(count <=  Integer.parseInt(row)) {
				List<String> list = Arrays.asList(Arrays.asList(multiArray).get(count));	
				System.out.println(list);
			    if(list.contains(value)){
			    	result = true;
			    	count = Integer.parseInt(row) + 1;			    	
			    }else {
			    	result = false;
			    }
			    count ++;
			}
			return result;
		}
		
		public void hideChartLegnet() {
			int check = 0, count = 2;
			while(check == 0) {
				try {
					Thread.sleep(2000);
					driver.findElement(By.xpath(event.getProperty("element","srChartLegend") + "[" + count + "]")).click();
					count ++;
				}catch(Exception e) {
					check =1;
				}
			}
		}
		
		public void collapseSetting(String setting) throws InterruptedException {
			String option = "";
			switch (setting) {
				case "none": option = "accountCountUnitPatent";
					break;
				case "application": option = "accountCountUnitApplNo";
					break;
				case "family": option = "accountCountUnitFamily";
					break;
			}
			
			clickByXpath(getProperty("element","expandUserMenu"), 20);
			clickByXpath(getProperty("element","userMenuAccount"), 20);
			clickByXpath(getProperty("element","accountSettings"), 20);
			clickByXpath(getProperty("element",option), 20);
			driver.findElement(By.xpath(getProperty("element","accountSettingSave"))).click();
		}
}
