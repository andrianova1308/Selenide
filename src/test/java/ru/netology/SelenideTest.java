package ru.netology;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    public String data(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String del = Keys.chord(Keys.CONTROL + "a") + Keys.DELETE;

    @Test
    void CardDeliveryTest() {

        Configuration.holdBrowserOpen = true;
        String dataDevlivery = data(4);

        open("http://localhost:9999/");
        $x("//input[@placeholder='Город']").setValue("Рязань");
        $("[data-test-id=date] input").sendKeys(del);
        $x("//input[@placeholder='Дата встречи']").setValue(dataDevlivery);
        $("[data-test-id=name] input").setValue("Самсикова Анастасия");
        $("[data-test-id=phone] input").setValue("+79605653616");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(byClassName("notification__title")).should(ownText("Успешно!"), Duration.ofSeconds(15));
        $(byClassName("notification__content")).should(ownText("Встреча успешно забронирована на " + dataDevlivery), Duration.ofSeconds(15));


    }
}
