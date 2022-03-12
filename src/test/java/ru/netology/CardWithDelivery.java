package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.GeneratorOfData.generateDate;


public class CardWithDelivery {

    @Test
    void shouldCardApplication() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        String planningDate = generateDate(4);
        String randomDate = GeneratorOfData.getDataRandom();
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val(GeneratorOfData.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id='name'] input").val(GeneratorOfData.getName());
        $("[data-test-id='phone'] input").val(GeneratorOfData.getPhone());
        $("[data-test-id='agreement']>span").click();
        $("[role='button'] span [class='button__text']").click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(randomDate);
        $(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button.button").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + randomDate));


    }


}