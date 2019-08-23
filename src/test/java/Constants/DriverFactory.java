package Constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver(String browserType) {
        try {
            DesiredCapabilities capabilities;
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver = new RemoteWebDriver(new URL("http://localhost:9515"), capabilities);

            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());


        } catch (MalformedURLException e) {
            System.err.println(e);
        }
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        return this.driver;
    }

}
