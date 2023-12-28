import com.codeborne.selenide.*;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SportTCRuTest {
    @Test
    public void searchInstitution() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href='/institutions/']")).click();
        var title = "МАУ ДО ДЮЦ \"Пламя\" города Тюмени";
        $("input[name='search']").setValue(title);
        $("a[href=\"/institutions/plamya/\"]").shouldHave(text(title));
        Thread.sleep(3000);
    }

    @Test
    public void initMapSearchInstitution() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href='/institutions/']")).click();
        $(By.cssSelector("#map ymaps")).shouldHave(visible);
        Thread.sleep(3000);
    }

    @Test
    public void searchObject() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href='/objects/']")).click();
        var title = "Волейбольная площадка";
        $("input[name='search']").setValue(title);
        $("a[href=\"/objects/20504/\"]").shouldHave(text(title));
        Thread.sleep(3000);
    }

    @Test
    public void initMapObject() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href='/objects/']")).click();
        $("a[href=\"/objects/20504/\"]").click();
        SelenideElement mapElement = $("div[id=\"map\"]");
        actions().moveToElement(mapElement).scrollByAmount(0, 250).perform();
        $("div[data-address=\"20504\"]").click();
        Thread.sleep(3000);
    }

    @Test
    public void openCloseNavigation() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href=\"#navigation\"]")).click();
        $("button[class=\"offcanvas__close color(white) bvi-no-styles js-icon\"]").click();
        Thread.sleep(3000);
    }

    @Test
    public void openNews() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href=\"#navigation\"]")).click();
        $(By.cssSelector("a[href=\"/posts\"]")).click();
        $(By.cssSelector("a[href=\"/posts/26965/\"]")).click();
        $(By.cssSelector("div[js-lightbox]")).click();
        $("img[src=\"/site/assets/files/26965/rky5qmkqyzg.jpg\"]").shouldHave(visible);
        Thread.sleep(3000);
    }

    @Test
    public void openVideos() throws InterruptedException {
        open("https://sport.tyumen-city.ru");
        $(By.cssSelector("a[href=\"#navigation\"]")).click();
        $(By.cssSelector("a[href=\"/videos\"]")).click();
        Thread.sleep(3000);
    }

    @Test
    public void openSite() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        assertEquals("Наш спорт!", title());
        Thread.sleep(3000);
    }

    @Test
    public void menuIsVisible() throws InterruptedException {
        open("https://sport.tyumen-city.ru/");
        $("[data-name='logo']").shouldBe(visible);
        Thread.sleep(3000);
    }

    @Test
    public void downloadedDocument() throws InterruptedException, IOException {
        open("https://sport.tyumen-city.ru/department/documents/");

        $(By.cssSelector("div[js-accordion-toggle]")).click();

        File downloadedFile = $(By.cssSelector("a[href*='/site/assets/files/11977/doc-20220803133013.pdf']")).download();

        if (downloadedFile.exists() && Files.size(Paths.get(downloadedFile.getPath())) > 0) {
            System.out.println("Файл успешно скачан: " + downloadedFile.getAbsolutePath());
        } else {
            System.out.println("Файл не был скачан или он пустой");
        }
        Thread.sleep(3000);
    }

    @Test
    public void checkedAddressInMap() throws InterruptedException {
        open("https://sport.tyumen-city.ru/institutions/avangard/");
        $("div[data-address=\"10080\"]").click();
        Thread.sleep(1000);
        $("div[data-address=\"10082\"]").click();
        Thread.sleep(1000);
        $("div[data-address=\"10084\"]").click();
        Thread.sleep(3000);
    }

    @Test
    public void openSocialMap() throws InterruptedException {
        Selenide.open("https://sport.tyumen-city.ru/institutions/avangard/");

        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        $(By.cssSelector("a[href='https://vk.com/avangard_72']")).click();

        for (String windowHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

        if ("https://vk.com/avangard_72".equals(currentUrl)) {
            System.out.println("Ссылка открылась в новом окне и URL соответствует ожидаемому");
        } else {
            System.out.println("URL нового окна не соответствует ожидаемому");
        }

        WebDriverRunner.getWebDriver().close();
        switchTo().window(originalWindow);
        Thread.sleep(3000);
    }

    @Test
    public void openRukovodstvo() throws InterruptedException {
        open("https://sport.tyumen-city.ru/institutions/avangard/");
        $$(By.cssSelector("a[class=\"js-link-card\"]")).get(2).click();
        $(By.cssSelector("a[href=\"/institutions/avangard/rukovodstvo-pedagogicheskii-nauchno-pedagogicheskii-sostav/14008/\"]")).click();
        $(By.cssSelector("div[data-title]"))
                .shouldHave(text("Феоктистова Татьяна Викторовна"));
        Thread.sleep(3000);
    }

    @Test
    public void sliderObjects() throws InterruptedException {
        open("https://sport.tyumen-city.ru/institutions/avangard/");

        $(".js-slider").scrollIntoView(true);
        Thread.sleep(1000);
        $("[js-slider-item='next']").click();
        Thread.sleep(1000);
        $("[js-slider-item='previous']").click();
        Thread.sleep(3000);
    }

    @Test
    public void openCloseNavigationInstitution() throws InterruptedException {
        open("https://sport.tyumen-city.ru/institutions/avangard/");
        $(By.cssSelector("a[href=\"#institution-burger\"]")).click();
        $("button[class=\"offcanvas__close color(grey-500) bvi-no-styles js-icon\"]").click();
        Thread.sleep(3000);
    }

    @Test
    public void downloadLesson() throws InterruptedException {
        Selenide.open("https://sport.tyumen-city.ru/lessons/");

        var file = $$("a[href*='/site/assets/files/']").get(1).download();

        try {
            if (file.exists() && Files.size(Paths.get(file.getPath())) > 0) {
                System.out.println("Файл успешно скачан: " + file.getAbsolutePath());
            } else {
                System.out.println("Файл не был скачан или он пустой: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);
    }
}
