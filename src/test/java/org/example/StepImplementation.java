package org.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation extends BaseTest {

    Logger logger = LogManager.getLogger(StepImplementation.class);


    @Step("<second> saniye kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }


    @Step("<xpath> xpath'li elemente tıkla")
    public void clickElementByXpath(String xpath) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        element.click();
        //System.out.println(element + "elemente tıklandı");
        logger.info(element + " xpath'li elemente tiklandi");
    }

    @Step("<id> li elemente tıkla")
    public void clickElementByid(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        element.click();
        //System.out.println(element + "elemente tıklandı");
        logger.info(element + " id'li elemente tiklandi");
    }


    @Step("<xpath> li elementi bul ve <key> değerini yaz")
    public void sendkeyElementByXpath(String xpath, String key) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        element.sendKeys(key);
        //System.out.println(element + "elemente tıklandı");
        logger.info(element + " xpath'li elemente " + key + " degeri yazildi");
    }

    @Step("<id> id li elementi bul ve <key> değerini yaz")
    public void sendkeyElementById(String id, String key) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        element.sendKeys(key);
        //System.out.println(element + "elemente tıklandı");
        logger.info(element + " id'li elemente " + key + " degeri yazildi");
    }

    @Step("<id> id li elementin gorunurlugunu kontrol et")
    public void isElementVisibleById(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        element.isDisplayed();
        //System.out.println("Anasayfadaki " + element + " elementi gorunur");
        logger.info("Anasayfadaki " + element + " elementi gorunur");

    }

    @Step("<id> li element gorunuyor ise tıkla")
    public void clickElementByidIfDisplayed(String id) {
        if (appiumDriver.findElement(By.id(id)).isDisplayed()) {
            appiumDriver.findElement(By.id(id)).click();
        }
    }

    @Step("Kabul ediyorum butonuna bas")
    public void clickAcceptbutton() throws InterruptedException {
        if (appiumDriver.findElement(By.id("android:id/button1")).isDisplayed()) {
            appiumDriver.findElement(By.id("android:id/button1")).click();
            waitForsecond(3);
        } else {
            System.out.println("Pop-up gelmedi");
        }
    }

    @Step("<id> li element <text> değerini içeriyor mu kontrol et")
    public void assertElementById(String id, String text) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        //System.out.println("Alinan text degeri == " + element.getText());
        Assert.assertTrue("Element bulunamadi", element.getText().equals(text));
        logger.info(id + " id'li element " + text + " degerini iceriyor");
    }

    @Step("<xpath> li element <text> değerini içeriyor mu kontrol et")
    public void assertElementByXpath(String xpath, String text) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        //System.out.println("Alinan text degeri ===== " + element.getText());
        Assert.assertTrue("Element bulunamadi", element.getText().equals(text));
        logger.info(xpath + " xpath'li element " + text + " degerini iceriyor");
    }

    @Step("Swipe Et")
    public void swipeScreen() {
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefonun boyutu ====== " + dims);
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);

        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }


}
