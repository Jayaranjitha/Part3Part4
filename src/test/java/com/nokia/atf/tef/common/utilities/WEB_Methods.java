package com.nokia.atf.tef.common.utilities;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.locators.WaitForWebElements;

//import org.monte.media.Format;
//import org.monte.media.FormatKeys.MediaType;
//import org.monte.media.math.Rational;
//import org.monte.screenrecorder.ScreenRecorder;
//import static org.monte.media.FormatKeys.EncodingKey;
//import static org.monte.media.FormatKeys.FrameRateKey;
//import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
//import static org.monte.media.FormatKeys.MIME_AVI;
//import static org.monte.media.FormatKeys.MediaTypeKey;
//import static org.monte.media.FormatKeys.MimeTypeKey;
//import static org.monte.media.VideoFormatKeys.CompressorNameKey;
//import static org.monte.media.VideoFormatKeys.DepthKey;
//import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
//import static org.monte.media.VideoFormatKeys.QualityKey;

public class WEB_Methods {

	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static final Logger logger = LoggerFactory.getLogger(WEB_Methods.class);
	
	public static WebDriver driver = null;
	public static String timeStamp1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());;
	public static String strScenarioName = "";
	public static String strFeatureName = "";
	static Actions action;
	WebElement element;
	static WebDriverWait wait;
	String strPropertyVal = "";
	String Path = "";
	String Value = "";
	String ActulValue1 = "";
	static String CountryLang = "";
	static String fPath = "";
	static String fPath1 = "";
	static String ShfPath = "";
	ArrayList<String> ListValuesInp = new ArrayList<String>();
	ArrayList<String> ListValuesOut = new ArrayList<String>();
	public ArrayList<String> CompResult = new ArrayList<String>();
	int stepNo = 1;
	public static int scrnsh = 1;
	//private static ScreenRecorder screenRecorder;
	public static String fileVideoPath;

	public static WebDriver getDriver() {
		return driver;

	}

	@SuppressWarnings("deprecation")
	public static WebDriver initialization(WebDriver driver, String browserName, String Langvalue) throws IOException {

		CountryLang = Langvalue;
		if (browserName.equalsIgnoreCase("Chrome")) {
			fPath = System.getProperty("user.dir") + "\\Drivers";
			System.out.println("fPath is" + fPath);
			String fPath1 = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", fPath + "/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=" + CountryLang);
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", fPath + "/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			String IEPath = System.getProperty("user.dir");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			// capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			// capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.ie.driver", IEPath + "\\Drivers\\IEDriverServer.exe");
//			DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
//			ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://10.59.140.210/portal/dsp");
//			driver = new InternetExplorerDriver(ieCaps);

			driver = new InternetExplorerDriver(capabilities);
		}
		return driver;
	}

	public static Properties WEB_rProperty(String PropertiesFile) {

		fPath = System.getProperty("user.dir") + "\\src\\test\\resources\\UI-Locators";
		File file = new File(fPath + File.separator + PropertiesFile + ".properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();

		// load Properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String WEB_getPropertyValue(String strPropertykey) {
		Properties lProperties = null;
		String strPropertyVal = null;
		if (CountryLang.equalsIgnoreCase("en"))
			lProperties = WEB_rProperty("CSCWEB");
		else if (CountryLang.equalsIgnoreCase("es"))
			lProperties = WEB_rProperty("CSCWEB_ES");
		else if (CountryLang.equalsIgnoreCase("pt"))
			lProperties = WEB_rProperty("CSCWEB_pt-br");

		try {
		
			strPropertyVal = lProperties.getProperty(strPropertykey);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return strPropertyVal;
	}

	@SuppressWarnings("deprecation")
	public static WebDriver AWSinitialization(WebDriver driver,String url, String browserName, String Langvalue)
			throws IOException {
		try {
			// String url=WfLoginLogout.smpURL;
			// System.out.println(url);
			CountryLang = Langvalue;
			if (browserName.equalsIgnoreCase("Chrome")) {
				fPath = System.getProperty("user.dir") + "\\Drivers";
				System.out.println("fPath is" + fPath);
				String fPath1 = System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", fPath + "/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--lang=" + CountryLang);
				
				DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome()  ;    
				handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
				handlSSLErr.setCapability(ChromeOptions.CAPABILITY, options);
				//driver = new ChromeDriver(options);
				driver = new ChromeDriver(handlSSLErr);
 
				driver.get(url);

			} else if (browserName.equalsIgnoreCase("FF")) {
				fPath = System.getProperty("user.dir") + "\\Drivers";
				System.setProperty("webdriver.gecko.driver", fPath + "/geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--lang=" + CountryLang);
				driver = new FirefoxDriver(options);
				driver.get(url);
			} else if (browserName.equalsIgnoreCase("IE")) {
				String IEPath = System.getProperty("user.dir");
				System.setProperty("webdriver.ie.driver", IEPath + "\\Drivers\\IEDriverServer.exe");
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
						url);
				driver = new InternetExplorerDriver(ieCaps);
				
//				InternetExplorerOptions options = new InternetExplorerOptions();
//				options.addArguments("--lang=" + CountryLang);
//				driver = new InternetExplorerDriver(options);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	
	public static void switchWindow(String parentGuid) throws Exception{
		Set<String> allGUID = driver.getWindowHandles();

		for(String guid : allGUID){
			
			if(! guid.equals(parentGuid)){
				// switch to the guid
				driver.switchTo().window(guid);
				// break the loop
				break;
			}
		}
		
	
	}
	public void WEB_setPropertyValue(String strPropertykey, String Propertyval) {
		String fPath = System.getProperty("user.dir");
		File file = new File(fPath + File.separator + "CSCWEB.properties");
		Properties props = new Properties();
		try {
			// first load old one:
			FileInputStream configStream = new FileInputStream(file);
			props.load(configStream);
			configStream.close();

			// modifies existing or adds new property
			props.setProperty(strPropertykey, Propertyval);

			// save modified property file
			FileOutputStream output = new FileOutputStream(file);
			props.store(output, "Refer SubID for current testdata");
			output.close();

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public boolean WEB_isDisplayed(WebDriver driver, String attributeType, String attribute) {
		WebElement element = null;
		try {
			element = WEB_findElement(attributeType, attribute);
			if (element.isDisplayed())
				;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static WebElement WEB_findElement(String attributeType, String attribute)
			throws IOException, InterruptedException {

		// get property key value from properties file WebDriver driver,
		String strPropertyVal = "";

		if (attributeType.contains("STRING") == false) {
			strPropertyVal = WEB_getPropertyValue(attribute);
		}

		WebElement element = null;

		// if the locator value is not specified in the properties file
		if (strPropertyVal != null) {
			try {
				wait = new WebDriverWait(driver, 90);
				switch (attributeType.toUpperCase()) {
				case "ID":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strPropertyVal.trim())));
					driver.findElement(By.id(strPropertyVal.trim()));
					break;
				case "ID STRING":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(attribute.trim())));
					break;
				case "NAME":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(strPropertyVal.trim())));
					break;
				case "TAG NAME":
					element = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(strPropertyVal.trim())));
					break;
				case "CLASS NAME":
					element = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.className(strPropertyVal.trim())));
					break;
				case "CSS":
					element = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.cssSelector(strPropertyVal.trim())));
					break;
				case "PARTIAL LINKTEXT":
					element = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(strPropertyVal.trim())));
					break;
				case "LINKTEXT":
					element = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strPropertyVal.trim())));
					break;
				case "STRING":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attribute)));
					break;
				case "CSS STRING":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attribute)));
					break;
				case "XPATH":
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strPropertyVal)));
					break;
				default:
					element = driver.findElement(By.xpath(strPropertyVal.trim()));
					// setAttribute(driver,element,"AttributeName",attribute);
				}

			} catch (NoSuchElementException e) {
				logger.info("Unable to find element with " + attribute);
				Report_getscreenShot("Unable to find element with " + attribute);
			}
		} else {
			logger.info("WEB_findElement Locator: '" + attribute + "' is not found in properties file");

		}
		// highLighterMethod(driver,element);

		return element;
	}

	@SuppressWarnings("unchecked")
	public static List<WebElement> WEB_findElements(String attributeType, String attribute)
			throws IOException, InterruptedException {

		// get property key value from properties file WebDriver driver,
		String strPropertyVal = " ";
		System.out.println(attribute);
		if (attributeType.contains("STRING") == false) {
			strPropertyVal = WEB_getPropertyValue(attribute);
			System.out.println(strPropertyVal);
		}

		List<WebElement> allElements = null;

		// if the locator value is not specified in the properties file
		if (strPropertyVal != null) {
			try {
				wait = new WebDriverWait(driver, 90);
				switch (attributeType.toUpperCase()) {
				case "ID":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.id(strPropertyVal.trim())));
					driver.findElements(By.id(strPropertyVal.trim()));
					break;
				case "NAME":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.name(strPropertyVal.trim())));
					break;
				case "TAG NAME":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(strPropertyVal.trim())));
					break;
				case "CLASS NAME":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.className(strPropertyVal.trim())));
					break;
				case "CSS":
					allElements = (List<WebElement>) wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.cssSelector(strPropertyVal.trim())));
					break;
				case "PARTIAL LINKTEXT":
					allElements = (List<WebElement>) wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(strPropertyVal.trim())));
					break;
				case "LINKTEXT":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strPropertyVal.trim())));
					break;
				case "STRING":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attribute)));
					break;
				case "CSS STRING":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(attribute)));
					System.out.println("TRUE");
					break;
				case "XPATH":
					allElements = (List<WebElement>) wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strPropertyVal)));
					break;
				default:
					allElements = (List<WebElement>) driver.findElement(By.xpath(strPropertyVal.trim()));
					// setAttribute(driver,element,"AttributeName",attribute);
				}

			} catch (NoSuchElementException e) {
				logger.info("Unable to find element with " + attribute);
				Report_getscreenShot("Unable to find element with " + attribute);
			}
		} else {
			logger.info("WEB_findElement Locator: '" + attribute + "' is not found in properties file");

		}

		return allElements;
	}

	public static void WEB_click(WebElement element) {
		wait = new WebDriverWait(driver, 150);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (element != null) {
			WebDriver driver = getDriver();
			try {
				element.click();

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			logger.info("WEB_click-Unable to find the element");
		}
	}

	public static void scroll_down(WebElement element) {
		wait = new WebDriverWait(driver, 150);
		// element=wait.until(ExpectedConditions.elementToBeClickable(element));
		if (element != null) {
			WebDriver driver = getDriver();
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", element);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logger.info("scroll_down the page");
		}
	}

	public static void WEB_action_sendKeys(WebElement rowToUpdate, String data) {
		action = new Actions(driver);
		action.sendKeys(rowToUpdate, data).build().perform();
	}

	public void WEB_action_doubleClick(WebElement rowToUpdate) {
		action = new Actions(driver);
		action.doubleClick(rowToUpdate).build().perform();
	}

	public void WEB_action_clear(WebElement rowToUpdate) {
		action = new Actions(driver);
		action.click(rowToUpdate).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
				.build().perform();
	}

	public void WEB_scrolldown_eventfiring(String cssValue) {
		try {
			if (WEB_findElement("CSS STRING", cssValue).isDisplayed()) {
				EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
				System.out.println("CSS is visible" + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
				eventFiringWebDriver.executeScript("document.querySelector('" + cssValue + "').scrollTop=2000");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WEB_scrollUp_eventfiring(String cssValue) {
		try {
			if (WEB_findElement("CSS STRING", cssValue).isDisplayed()) {
				EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
				logger.info("CSS is visible" + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
				eventFiringWebDriver.executeScript("document.querySelector('" + cssValue + "').scrollTop=0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WEB_scrolldown_eventfire(String cssValue) throws IOException, InterruptedException {
		try {
			String[] val = WEB_findElement("STRING", "//div[contains(@aria-label,'Row 1') and @row-index='0']")
					.getAttribute("aria-label").split(" ");
			int refno = Integer.parseInt(val[3].replaceAll(",", ""));
			int ref = refno - 1;
			for (int i = 0; i <= ref; i++) {
				EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
				logger.info("CSS is visible" + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
				eventFiringWebDriver.executeScript("document.querySelector('" + cssValue + "').scrollTop=3000");
				List<WebElement> ele = driver.findElements(
						By.xpath("//div[contains(@aria-label,'Row " + refno + "') and @row-index='" + ref + "'] "));
				if (ele.size() > 0) {
					System.out.println("is displayed");
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void WEB_scrolldownUptoEle(String ele) throws IOException, InterruptedException {
		try {
			for (int i = 0; i <= 100; i++) {
				int j = 200;
				List<WebElement> elem = driver.findElements(By.xpath("//*[text()='" + ele + "']"));
				if (driver.getPageSource().contains(ele)) {
					System.out.println(ele + " Text is present");
				}
				if (elem.size() > 0) {
					System.out.println(ele + " is displayed");
					break;
				}
				WEB_scrolldown_eventfirings("div.ag-body-viewport.ag-layout-normal", Integer.toString(j));
				// j = j + 100;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void WEB_scrolldown_eventfirings(String cssValue, String valueToScroll) {
		if (driver.findElement(By.cssSelector(cssValue)).isDisplayed()) {
			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
			logger.info("CSS is visible " + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
			eventFiringWebDriver
					.executeScript("document.querySelector('" + cssValue + "').scrollTop=" + valueToScroll + "");
		} else
			logger.info("CSS is NOT visible " + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
	}
	public static void WEB_scrollup_eventfirings(String cssValue, String valueToScroll) {
		if (driver.findElement(By.cssSelector(cssValue)).isDisplayed()) {
			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
			logger.info("CSS is visible " + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
			eventFiringWebDriver
					.executeScript("scroll(0, -3000);");
		} else
			logger.info("CSS is NOT visible " + driver.findElement(By.cssSelector(cssValue)).isDisplayed());
	}

	public void WEB_scrolldown_eventfirings() {
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		logger.info("CSS is visible"
				+ driver.findElement(By.cssSelector("div.ag-body-viewport.ag-layout-normal")).isDisplayed());
		eventFiringWebDriver
				.executeScript("document.querySelector('div.ag-body-viewport.ag-layout-normal').scrollTop=4000");
	}

	public void WEB_clickJS(WebElement element) {
		wait = new WebDriverWait(driver, 150);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (element != null) {
			WebDriver driver = getDriver();
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			logger.info("WEB_click-Unable to find the element");
		}
	}
	public void WEB_ActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 150);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (element != null) {
			WebDriver driver = getDriver();
			try {
				 Actions builder = new Actions(driver);

				 org.openqa.selenium.interactions.Action myAction = builder.click(element)
				       .release()
				       .build();

				    myAction.perform();

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			logger.info("WEB_click-Unable to find the element");
		}
	}

	public boolean WEB_isDisplayed(String attributeType, String attribute) {
		WebElement element = null;
		try {
			element = WEB_findElement(attributeType, attribute);
			if (element.isDisplayed())
				;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void WEB_dropDown(String attributeType, String attribute, String data) {
		wait = new WebDriverWait(driver, 150);

		try {

			Select select = new Select(WEB_findElement(attributeType, attribute));
			select.selectByVisibleText(data);
			System.out.println("Data" + data);
		} catch (Exception e) {
			System.out.println("Exceptioncame");
		}

	}

	public static void WEB_SendKeys(WebElement element, String Value) {
		wait = new WebDriverWait(driver, 150);
		element = wait.until(ExpectedConditions.visibilityOf(element));
		if (element != null) {

			try {
				element.sendKeys(Value);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			logger.info(element + " is not visible in the webpage");
		}
	}

	public void WEB_clickWarning(WebDriver driver, String Warning, String button)
			throws IOException, InterruptedException {
		wait = new WebDriverWait(driver, 150);

		strPropertyVal = "//*[contains(text(),'" + Warning
				+ "')]/../following-sibling::div[contains(@class,'buttonpane')]//*[text()='" + button + "']";
		element = WEB_findElement("STRING", strPropertyVal);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (element != null) {
			// WebDriver driver = getDriver();
			try {
				element.click();

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			logger.info("WEB_click-Unable to find the element");
		}
	}

	public static void ScreenShotPath() throws IOException, InterruptedException {
		ShfPath = System.getProperty("user.dir") + "\\Output\\ScreenShots\\"+strFeatureName+ "_" + timeStamp1 +"\\" + strScenarioName + "_" + timeStamp1 + "";
	}

	public static void Report_getscreenShot(String val) throws IOException, InterruptedException {
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(ShfPath + "/" + val +".jpg");
			//File dest = new File(ShfPath + "/" + val + "_" + timeStamp + ".jpg");
			FileUtils.copyFile(scr, dest);
			scrnsh++;
		} catch (Exception e) {
			logger.info(e.toString());
		}

		// Reporter.getCurrentStep().addScreenCaptureFromPath(fPath+"/"+Testdata+"_"+val+"_"+timeStamp+".jpg");
	}

	public static void Report_screenshot(String val) throws IOException, InterruptedException {

		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			ShfPath = System.getProperty("user.dir") + "\\Output\\ScreenShots";
			// FileUtils.cleanDirectory(ShfPath);
			ImageIO.write(image, "png", new File(ShfPath + "/" + val + "_" + timeStamp + ".jpg"));
		} catch (Exception e) {
			logger.info(e.toString());
		}
	}

	public void WEB_waitWithSleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void uploadFile(String file) throws Exception {

		try {
			Thread.sleep(2000);
			StringSelection pathToSelect = new StringSelection(file);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pathToSelect, null);

			System.out.println("----"+file);
			Robot robot = new Robot();

			 robot.delay(250);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void startRecording() throws Exception {
//
//		File file = new File(System.getProperty("user.dir") + File.separator + "downloadFiles");
//		FileUtils.cleanDirectory(file);
//		FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + File.separator + "FailedFiles"));
//		fileVideoPath = new File(System.getProperty("user.dir") + File.separator + "downloadFiles").toString();
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
//
//		System.out.println("Path of videorecorder" + file);
//
//		Rectangle captureSize = new Rectangle(0, 0, width, height);
//
//		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
//				.getDefaultConfiguration();
//
//		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
//				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
//				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
//						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
//						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
//				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
//				null, file, "MyVideo");
//		this.screenRecorder.start();
//
//	}

	public static void Quit() throws Exception {
		driver.quit();
	}

//	public void stopRecording() throws Exception {
//		this.screenRecorder.stop();
//	}

	public String addDateToCurrentday(int value) throws Exception {
		String day = "";
		LocalDate today = LocalDate.now();
		if (value == 0)
			day = today.toString();
		if (value > 0) {
			LocalDate plus = today.plusDays(value);
			day = plus.toString();
		}
		if (value < 0) {
			value = Math.abs(value);
			LocalDate minus = today.minusDays(value);
			day = minus.toString();
		}
		return day;
	}

	public static String currentDateinFormat() throws Exception {

		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		// String date = simpleDateFormat.format(new Date());
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return simpleDateFormat.format(dt);
	}

	public void refresh() {

		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static String currenttime() throws Exception {
//		SimpleDateFormat simpleDateFormatArrivals = new SimpleDateFormat("HH:mm", Locale.JAPANESE);
//	return simpleDateFormatArrivals;
		Date date = new Date();
		date.setHours(date.getHours() + 24);
		System.out.println(date);
		SimpleDateFormat simpDate;
		simpDate = new SimpleDateFormat("kk:mm", Locale.JAPANESE);
		return simpDate.format(date);
	}

	public static void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	public String getCssproperty(WebElement element, String property) {
		 
		String value = "";
 
		switch (property) {
		case "color":{
			value = element.getCssValue("color");
			String color_hex[];  
			 color_hex = value.replace("rgba(", "").split(",");       
			 String actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()), Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));  
			 value=actual_hex;
			break;}
		case "size":
			value = element.getCssValue("font-size");
			break;
		case "alignment":
			value = element.getCssValue("text-align");
			break;
		case "font name":
			value = element.getCssValue("font-family");
			break;
		case "font style":
			value = element.getCssValue("font-weight");
			if(value.equals("bold") || value.equals("700"));{
			value="bold";}
			break;
			case "background color":{
			value = element.getCssValue("background-color");
			String color_hex[];  
			 color_hex = value.replace("rgba(", "").split(",");       
			 String actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()), Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));  
			 value=actual_hex;
			break;}
		default:
			break;
		}
		logger.info(property+" ----> "+value);
			return value;
	}
	
	
	public static void scrolldown() throws Exception{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,1000)");

		 js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
   }
	
	 public static void ScrollHorizontally(String element) throws Exception {
	  
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    
        js.executeScript("arguments[0].scrollIntoView();", WEB_Methods.WEB_findElement("XPATH", element));
	    }

	 
	 public static void scrollLeft(String element) throws Exception{
		 
		 Thread.sleep(1000);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", element);
	 }
	 
		public static void main(String[] args) throws Exception {
		
			System.out.println(WEB_getPropertyValue("LWM2M_Impact"));
			
		}
		
}