package ru.netology;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class GeneratorOfData {
    public static Faker faker = new Faker(new Locale("ru"));

    public static String getCity() {
        String[] city = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала"};
        return city[new Random().nextInt(2)];
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getDataRandom() {
        Random random = new Random();
        int randomDay = 3 + random.nextInt(362);
        return LocalDate.now().plusDays(randomDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static String getName() {
        return faker.name().fullName();
    }

    public static String getPhone() {
        return faker.phoneNumber().phoneNumber();

    }


}
