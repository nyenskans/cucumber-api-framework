package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

    public class CommonMethods extends PageInitializer {
        public static WebDriver driver;

        public static void openBrowserAndLaunchApplication(){
            ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
            //cross browser testing
            switch (ConfigReader.getPropertyValues("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser name");
            }

            driver.manage().window().maximize();
            driver.get(ConfigReader.getPropertyValues("url"));
            driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
            //this method is used to initialize all the objects of the pages at the very beginning
            initializePageObjects();
        }

        public static void closeBrowser(){
            driver.quit();
        }

        public static void sendText(WebElement element, String textToSend){
            element.clear();
            element.sendKeys(textToSend);
        }
        //it will return 20 sec wait
        public static WebDriverWait getWait(){
            WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
            return wait;
        }

        //it will wait till the time element becomes clickable
        public static void waitForClickability(WebElement element){
            getWait().until(ExpectedConditions.elementToBeClickable(element));
        }

        //to perform click operation

        public static void click(WebElement element){
            waitForClickability(element);
            element.click();
        }

        //select class for dropdown
        public static void selectDropdown(WebElement element, String text){
            Select s = new Select(element);
            s.selectByVisibleText(text);
        }

        //js click
        public static JavascriptExecutor getJSExecutor(){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js;
        }

        /**
         * to perform click via javascript executor
         * @param element
         */
        public static void jsClick(WebElement element){
            getJSExecutor().executeScript("arguments[0].click();", element);
        }
            // To take a screenshot: Cucumber accepts an array of bytes

        /**
         * This method takes a screenshot and saves it to a specific location
         * @param fileName
         * @return
         */
        // we need screenshot at the end of execution --> at the last stage so we need to add it to our hooks -->
        // before closing the browser
       public static byte[] takeScreenshot(String fileName){
           TakesScreenshot ts = (TakesScreenshot) driver;
           byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
           File sourceFile = ts.getScreenshotAs(OutputType.FILE);
           // how to name the screenshot:
           // name of scenario: Valid Admin Login --> if we execute it multiple times, we will only get one screenshot
           // (files with same name get overridden);
           // so we have to make a functionality to make a different screenshot name each time:
           try {
               FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH  + fileName +
                                                      getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
               // we are calling .getTimeStamp() to add date/time to the name of the screenshot file to make it unique
               // and adding .png to provide file extension
           } catch (IOException e) {
               e.printStackTrace();
           }
       return picBytes;
       }

       // to be able to get a unique name for the screenshot, we are writing another method to get a timestamp and
       // calling it in the method above

        /**
         * This method will get a timestamp and format it according to our preference
         * @param pattern
         * @return
         */
        public static String getTimeStamp(String pattern){ // pattern by which we want to save the date
            Date date = new Date();
            // to format the date according to our own choice:
            SimpleDateFormat sdf = new SimpleDateFormat();
            return sdf.format(date);
        }


    }