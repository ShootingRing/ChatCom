package org.example.common.utils;
import java.security.SecureRandom;

public class UUIDGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 7;
    private static final SecureRandom random = new SecureRandom();

    public static String generateUUID() {
        StringBuilder uuidBuilder = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            uuidBuilder.append(CHARS.charAt(randomIndex));
        }
        return uuidBuilder.toString();
    }

}
