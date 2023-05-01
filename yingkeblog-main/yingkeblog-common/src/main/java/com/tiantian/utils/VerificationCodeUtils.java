package com.tiantian.utils;

import java.util.Random;

public class VerificationCodeUtils {

    private VerificationCodeUtils() {
    }

    public static String getVerification4Code() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public static String getVerification6Code() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            Random r = new Random();
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }
}
