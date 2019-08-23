package Pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getCurrentMonth() {
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.US);
        return formatter.format(today);
    }

    public String getCurrentYear() {
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY").withLocale(Locale.US);
        return formatter.format(today);
    }

    public String getCurrentDate() {
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu").withLocale(Locale.US);
        return formatter.format(today);
    }

    public String getCurrentDay() {
        LocalDate today = LocalDate.now(ZoneId.of("America/Montreal"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd").withLocale(Locale.US);
        return formatter.format(today);
    }


    public void isElementPresentTrue(String element) {
        Boolean isElementPresent = driver.findElements(By.cssSelector(element)).size() != 0;
        if (isElementPresent) {
            System.out.println(element + "element is presented");
        } else {
            Assert.assertEquals(false, true);
        }
    }

    public BasePage scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) this.driver;
        jse.executeScript("scroll(0, 450);");
        return this;
    }

    public void clickOnElemenByJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickOnElemenByJSByXpath(String element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElements(By.xpath(element)));
    }


    public void waitOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitOfElementForCatch(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitInvisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitVisibilityOfElement(WebElement element) {
        (new WebDriverWait(driver, 120))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilElementDisappear(final WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 180);
        ExpectedCondition elementIsDisplayed = (ExpectedCondition<Boolean>) arg0 -> {
            try {
                element.isDisplayed();
                return false;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return true;
            }
        };
        wait.until(elementIsDisplayed);
    }

    public String getAssertUrl() {
        return driver.getCurrentUrl();
    }

    public void scrollDown1400() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 1400);");
        Thread.sleep(1000);
    }

    public void sideScroll(String sideScrollBar) {
        WebElement horizontalbar = driver.findElement(By.xpath(sideScrollBar));
        Actions action = new Actions(driver);

        Actions moveToElement = action.moveToElement(horizontalbar);
        for (int i = 0; i < 5; i++) {
            moveToElement.sendKeys(Keys.RIGHT).build().perform();
        }

    }
    public void scrollDownTheSeparateArea(String xpathElementToScroll) throws InterruptedException {
        WebElement webElement = driver.findElement(By.xpath(xpathElementToScroll));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += 200;", webElement);
        Thread.sleep(1000);
    }

    public int random() {
        Random rand = new Random();
        int n = rand.nextInt();
        return n;
    }

    public static String Data() {
        Date cal = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


    public void dragNdropElements(WebElement sourceElement, WebElement targetElement) {
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, targetElement).perform();
    }
}

