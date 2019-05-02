package com.timesheet.Automation;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
		System.out.println("username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("passwoord: ");
		String password = scanner.nextLine();
		startAutomation(username,password);
	}
	
	public static boolean startAutomation(String username,String password) {
		 WebDriver driver = getWebDriverInstance();
		 driver.get("https://"+username+":"+password+"@"+"epm.mphr11.morpho.com/pwa/timesheet.aspx");
		 WebElement element = null;
		 
		 //element = driver.findElement(By.xpath("//*[contains(@id,'_1_0_2')]"));
		 //element = fluentWait(By.xpath("//*[@id=\"ctl00_m_g_7c1c643f_008b_4510_a057_5abc576d5ca2_TimesheetPartJSGridControl_1_0_2\"]"), driver);
		 element = fluentWait(By.xpath("//div[3]/div/table[2]/tbody/tr[2]/td[3]"),driver);
		 element.click();
		 driver.findElement(By.id("ctl00_m_g_7c1c643f_008b_4510_a057_5abc576d5ca2_TimesheetPartJSGridControl_focusElement")).sendKeys("1");
         element.sendKeys(Keys.RIGHT);
         element.sendKeys("1d");

         element.sendKeys(Keys.RIGHT);
         element.sendKeys("1d");
         element.sendKeys(Keys.RIGHT);
         element.sendKeys("1d");
         element.sendKeys(Keys.RIGHT);
         element.sendKeys("1d");
         driver.findElement(By.cssSelector("#Ribbon\\.ContextualTabs\\.TiedMode\\.Home\\.Sheet\\.Save-Medium > .ms-cui-ctl-mediumlabel")).click();
		 return true;
	}
	
    public static WebDriver getWebDriverInstance() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        System.out.println(System.getProperty("webdriver.ie.driver"));
        return new ChromeDriver();
    }
    
    public static WebElement fluentWait(final By locator, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
        	public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return  foo;
    };

}
