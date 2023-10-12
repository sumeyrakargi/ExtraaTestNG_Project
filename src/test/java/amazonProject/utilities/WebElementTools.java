package amazonProject.utilities;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementTools {
    private WebDriver _driver;
    private int timeOut = 10;
    public void setTimeOut(int seconds) {
        timeOut = seconds;
    }
    public WebElementTools(WebDriver Driver) {

        _driver = Driver;
    }

    public WebElementTools() {

        _driver = Driver.getDriver();
    }

    private WebDriverWait waits() {
        return new WebDriverWait(_driver, Duration.ofSeconds(timeOut));
    }

    public void hardWait(int miliSec) {
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /* methods */

    public void openUrl(String url) {
        _driver.get(url);
        waitForPageToLoad(10);
    }
    public void click(WebElement element) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(element)).click();
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    public void click(By by) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(by)).click();
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }



    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public void jsClick(WebElement element) {
        ((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) _driver).executeScript("arguments[0].click();", element);
    }

    public void jsScrollInto(WebElement element) {
        ((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void jsChangeBackgroundColor(WebElement element, String color) {
        ((JavascriptExecutor) _driver).executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
    }

    public void jsFlash(WebElement element) {
        class MyRunnable implements Runnable {
            public void run() {
                String bgColor = element.getCssValue("backgroundcolor");
                for (int i = 0; i < 5; i++) {
                    jsChangeBackgroundColor( element,"rgb(0,200,0");
                    hardWait(500);
                    jsChangeBackgroundColor(element, bgColor);
                    hardWait(500);
                }
            }
        }

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    public void jsSetAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) _driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public String jsGetAttribute(WebElement element, String attributeName) {
        JavascriptExecutor jse = (JavascriptExecutor) _driver;
        return ((String)jse.executeScript("return arguments[0].getAttribute('" + attributeName + "');", element));
    }



    public void executeJScommand(WebElement element, String command) {
        ((JavascriptExecutor) _driver).executeScript(command, element);
    }

    public void jsAlert(String message) {
        ((JavascriptExecutor) _driver).executeScript("alert('" + message + "')");
    }


    public void sendKeys(WebElement element, CharSequence... keysToSend) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keysToSend);
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    public void sendKeys(By by, CharSequence... keysToSend) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(by)).sendKeys(keysToSend);
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }


    public void clear(WebElement element) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(element)).clear();
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }
    public void clear(By by) {
        int attempts = 0;
        boolean isDone = false;
        while (!isDone && attempts < 3) {
            try {
                waits().until(ExpectedConditions.elementToBeClickable(by)).clear();
                isDone = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }


    public String getAlertText() {
        return waits().until(ExpectedConditions.alertIsPresent()).getText();
        //return _driver.switchTo().alert().getText();
    }

    public WebElement verifyElement(WebElement element) {
        int attempts = 0;
        boolean isDone = false;
        WebElement result = null;
        while (!isDone && attempts < 3) {
            try {
                result = waits().until(ExpectedConditions.visibilityOf(element));
                result.isEnabled();
                isDone = true;
            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
                result = null;
            } catch (NoSuchElementException e) {
                return null;
            }
        }
         return result;
    }

    public WebElement verifyElement(By by) {
        int attempts = 0;
        boolean isDone = false;
        WebElement result = null;
        while (!isDone && attempts < 3) {
            try {
                result = waits().until(ExpectedConditions.visibilityOfElementLocated(by));
                result.isEnabled();
                isDone = true;
            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
                result = null;
            } catch (NoSuchElementException e) {
                return null;
            }
        }
        return result;
    }

    public boolean isVisible(WebElement element) {
        return waits().until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }


    public void switchToWindow(String targetTitle) {
        String origin = _driver.getWindowHandle();
        for (String handle : _driver.getWindowHandles()) {
            _driver.switchTo().window(handle);
            if (_driver.getTitle().equals(targetTitle)) {
                return;
            }
        }
        _driver.switchTo().window(origin);
    }

    public Boolean isPageLoaded(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }
}
