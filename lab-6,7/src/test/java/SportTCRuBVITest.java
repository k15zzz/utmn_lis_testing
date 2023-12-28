import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class SportTCRuBVITest {
    @Test
    public void openBvi() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        $(By.cssSelector("a[title=\"Версия сайта для слабовидящих\"]")).click();
        $(By.cssSelector("div[class=\"font-family(oswald) font-size(64) @xs:font-size(96) @sm:font-size(144)" +
                " @md:font-size(192) @lg:font-size(224) @xl:font-size(256) line-height(.8em)" +
                " font-weight(700) color(white) text-align(center) text-transform(uppercase) bvi-hide\"]"))
                .shouldHave(hidden);

        $(By.cssSelector("a[class=\"bvi-link bvi-link-fixed-top bvi-no-styles bvi-show\"]")).click();
        $(By.cssSelector("a[data-bvi=\"close\"]")).click();

        Thread.sleep(3000);
    }

    @Test
    public void openCloseBvi() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        $(By.cssSelector("a[title=\"Версия сайта для слабовидящих\"]")).click();
        $(By.cssSelector("a[class=\"bvi-link bvi-link-fixed-top bvi-no-styles bvi-show\"]")).click();
        $(By.cssSelector("a[data-bvi=\"close\"]")).click();
        Thread.sleep(3000);
    }

    @Test
    public void bviThemeGreen() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        $(By.cssSelector("a[title=\"Версия сайта для слабовидящих\"]")).click();
        $(By.cssSelector("a[class=\"bvi-link bvi-link-fixed-top bvi-no-styles bvi-show\"]")).click();
        $(By.cssSelector("a[class=\"bvi-link bvi-theme-green\"]")).click();

        String expectedColor = "rgba(59, 39, 22, 1)";
        String actualColor = $(By.cssSelector("div[class=\"bvi-body\"]")).getCssValue("background-color");
        $(By.cssSelector("a[data-bvi=\"close\"]")).click();

        assertEquals(expectedColor, actualColor);
        Thread.sleep(3000);
    }

    @Test
    public void bviDelImage() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        $(By.cssSelector("a[title=\"Версия сайта для слабовидящих\"]")).click();
        $(By.cssSelector("a[class=\"bvi-link bvi-link-fixed-top bvi-no-styles bvi-show\"]")).click();
        $(By.cssSelector("a[class=\"bvi-link bvi-images-off\"]")).click();

        $(By.cssSelector("img[src=\"/site/assets/files/1/image_01.1600x700.jpg\"]"))
                .shouldHave(hidden);

        $(By.cssSelector("a[data-bvi=\"close\"]")).click();
        Thread.sleep(3000);
    }
}
