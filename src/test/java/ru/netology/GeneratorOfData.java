package ru.netology;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class GeneratorOfData {
    public static Faker faker = new Faker(new Locale("ru"));

    public static String getCity() {
        return faker.address().cityName();
    }


    public static String getDataRandom() {
        Random random = new Random();
        int minDay = (int) LocalDate.now().plusDays(3).toEpochDay();
        int maxDay = (int) LocalDate.now().plusDays(3).plusMonths(5).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        String randomDate = LocalDate.ofEpochDay(randomDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return randomDate;
    }

    public static String generateDataDefault() {
        String setDateDefault = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return setDateDefault;
    }

    public static String getName(){
        return faker.name().fullName();
    }

    public static String getPhone(){
        return faker.phoneNumber().phoneNumber();

    }


}
