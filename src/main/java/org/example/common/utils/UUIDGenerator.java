package org.example.common.utils;
import java.security.SecureRandom;

import static org.example.common.utils.DataBuffer.isUuidExist;
import static org.example.common.utils.DataBuffer.uuidPool;

public class UUIDGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 7;
    private static final SecureRandom random = new SecureRandom();

    private static String createUUID() {
        StringBuilder uuidBuilder = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            uuidBuilder.append(CHARS.charAt(randomIndex));
        }
        return uuidBuilder.toString();
    }

    public static String generateUUID(){
        String uuid = createUUID();
        while(isUuidExist(uuid)){
            uuid = createUUID();
        }
        uuidPool.add(uuid);
        return uuid;
    }

}
