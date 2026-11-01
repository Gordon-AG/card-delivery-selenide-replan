package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Только админцентры РФ, которые валидируются SUT
    private static final String[] VALID_CITIES = {
            "Москва", "Санкт-Петербург", "Майкоп", "Уфа", "Краснодар",
            "Екатеринбург", "Казань", "Красноярск", "Нижний Новгород",
            "Новосибирск", "Пермь", "Ростов-на-Дону", "Самара",
            "Воронеж", "Ярославль"
    };

    // Приватный конструктор — утилитный класс нельзя инстанцировать
    private DataGenerator() {
    }

    public static RegistrationInfo generateInfo(int plusDays) {
        String city = VALID_CITIES[new Random().nextInt(VALID_CITIES.length)];
        String name = faker.name().fullName();
        String phone = "+7" + faker.number().digits(10);
        String date = LocalDate.now().plusDays(plusDays).format(DATE_FORMATTER);
        return new RegistrationInfo(city, name, phone, date);
    }

    // Удобный метод для генерации только новой даты (для перепланирования)
    public static String generateDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DATE_FORMATTER);
    }
}