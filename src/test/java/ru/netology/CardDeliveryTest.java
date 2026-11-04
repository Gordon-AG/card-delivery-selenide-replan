package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldPlanAndReplanMeeting() {
        RegistrationInfo info = DataGenerator.generateInfo(4);
        String newDate = DataGenerator.generateDate(7);

        // Шаг 1: первое планирование
        $("[data-test-id='city'] input").setValue(info.getCity());

        // Очистка предзаполненной даты
        SelenideElement dateField = $("[data-test-id='date'] input");
        dateField.doubleClick();
        dateField.sendKeys(Keys.DELETE);
        dateField.setValue(info.getDate());

        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();

        // Проверка первого успеха (проверяем полное сообщение с датой)
        $("[data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title")
                .shouldHave(exactText("Успешно!"));
        // дополнена проверка
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + info.getDate()));

        // Шаг 2: перепланирование — меняем только дату
        dateField.doubleClick();
        dateField.sendKeys(Keys.DELETE);
        dateField.setValue(newDate);
        $$("button").find(exactText("Запланировать")).click();

        // Появляется replan-notification
        $("[data-test-id='replan-notification']")
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__title")
                .shouldHave(exactText("Необходимо подтверждение"));

        // Нажимаем «Перепланировать»
        $("[data-test-id='replan-notification'] button")
                .shouldHave(exactText("Перепланировать"))
                .click();

        // success-notification дополнен новым полным сообщением с новой датой
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + newDate), Duration.ofSeconds(15));
    }
}