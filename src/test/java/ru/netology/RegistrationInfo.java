package ru.netology;

import lombok.Value;

@Value
public class RegistrationInfo {
    String city;
    String name;
    String phone;
    String date;
}