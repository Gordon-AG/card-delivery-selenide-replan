# Card Delivery Replan — Selenide Tests (ДЗ 2.3. Patterns)
Автотесты формы заказа доставки карты с проверкой перепланирования встречи.
## Технологии
 - Selenide 6.19.1
 - JUnit Jupiter 5.10.2
 - Lombok 1.18.30
 - Faker 1.0.2 (ru locale)
## Сценарий
1. Заполнение формы через DataGenerator (без хардкода).
2. Проверка успешного планирования.
3. Повторная отправка с новой датой → replan-notification.
4. Нажатие «Перепланировать» → успех с новой датой.
## CI
[![Java CI with Gradle](https://github.com/Gordon-AG/card-delivery-selenide-replan/actions/workflows/gradle.yml/badge.svg)](https://github.com/Gordon-AG/card-delivery-selenide-replan/actions/workflows/gradle.yml)
