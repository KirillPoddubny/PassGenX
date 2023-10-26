package org.example.passgen;

import java.security.SecureRandom;
import java.lang.StringBuilder;

public class PasswordGenerator {

    public int lenght;

    public PasswordGenerator (int lenght) {
        this.lenght = lenght;
    }

    public String Generate () {
        String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        if (password.length() < 50) {

            for (int i = 0; i < this.lenght; i++) {
                int randomIndex = random.nextInt(symbols.length());
                password.append(symbols.charAt(randomIndex));
            }

            return password.toString();
        }
        else {
            return "пароль должен быть не больше 50 символов";
        }
    }
}
