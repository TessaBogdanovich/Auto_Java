import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Feature
public class FindElements extends TestBase{

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"E-Katalog Logo\"]/android.widget.Image")
    MobileElement titleEKat;


    @FindBy(xpath = "//*[@id=\"mp_slider\"]/div/div[1]/div[1]/div[2]/a/span")
    MobileElement phoneBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/div[3]/div[2]/button")
    MobileElement filterBtn;

    @FindBy(xpath = "//*[@id=\"form_match\"]/div[4]")
    MobileElement proizvBtn;

//    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"ПРОИЗВОДИТЕЛИ\"]//")
//    MobileElement proizvBtn;


    @FindBy(xpath = "//*[@id=\"li_br116\"]/label")
    MobileElement appleChoise;

    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"ПОДОБРАТЬ23\uEA3C\"]")
    MobileElement choiseShow;

    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"по популярности\"]")
    MobileElement popularBtn;

    @AndroidFindBy(xpath = "//android.widget.ListView/android.view.View[2]/android.view.View")
    MobileElement cheapToExpenc;



    @Description("Тест приложения E-Katalog")
    @Story("E-Katalog тест")
    @Test
    public void sampleTest() {





        changeDriverContextToNative(driver);
        titleEKat.isDisplayed();

        changeDriverContextToWebView(driver);
        phoneBtn.isDisplayed();
        phoneBtn.click();
        filterBtn.isDisplayed();
        filterBtn.click();

        proizvBtn.isDisplayed();
        proizvBtn.click();

        appleChoise.isDisplayed();
        appleChoise.click();

        changeDriverContextToNative(driver);
        choiseShow.isDisplayed();
        choiseShow.click();
        popularBtn.isDisplayed();
        popularBtn.click();
        cheapToExpenc.isDisplayed();
        cheapToExpenc.click();






    }

    void changeDriverContextToWebView(AppiumDriver<?> driver){
        Set<String> contextHandles = driver.getContextHandles();
        for (String name: contextHandles){
            if (name.contains("WEBVIEW"))
                driver.context(name);
        }
    }

    void changeDriverContextToNative(AppiumDriver<?> driver){
        Set<String> contextHandles = driver.getContextHandles();
        for (String name: contextHandles){
            if (name.contains("NATIVE_APP"))
                driver.context(name);
        }
    }

    @Step("Явное ожидание")
    private void wait2Sec(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private enum Direction{
        RIGHT,
        LEFT
    }

    @Step("Swipe {direction}")
    private void swipe(Direction direction){
        int edge = 5;
        int press = 300;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(dims.width/2,dims.height/2);
        PointOption<?> pointOptionEnd;
        switch (direction) {
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - edge, dims.height/2);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edge, dims.height/2);
                break;
            default:
                throw new IllegalArgumentException("Свайп не поддерживается! ");

        }

        new TouchAction<>(driver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(press)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();
    }

    @Step("Проверка поворота Landscape")
    private boolean rotateToLandscape(){
        try{
            driver.rotate(ScreenOrientation.LANDSCAPE);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
