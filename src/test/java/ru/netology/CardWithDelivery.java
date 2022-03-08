package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardWithDelivery {


    @BeforeAll
    static void setUp() {
        Faker faker = new Faker(new Locale("ru"));
            WebDriverManager.chromedriver().setup();
    }


    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldCardApplication() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        String planningDate = generateDate(4);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val(GeneratorOfData.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id='name'] input").val(GeneratorOfData.getName());
        $("[data-test-id='phone'] input").val(GeneratorOfData.getPhone());
        $("[data-test-id='agreement']>span").click();
        $("[role='button'] span [class='button__text']").click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));


        $("[data-test-id='date'] input").setValue(GeneratorOfData.getDataRandom());
        $(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button.button").click();
        $(withText("Успешно")).shouldBe(visible);


    }


}