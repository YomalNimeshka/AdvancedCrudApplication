package com.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashingFunction {
    //creating a byte value from the string
    public static byte[] createHash(String password) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    //converting the byte value to a hex string to be saved in the database
    public static String convertByteToString(byte[] byteArray){


        BigInteger number = new BigInteger(1, byteArray);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() <32){
            hexString.insert(0,'0');
        }
        return hexString.toString();
    }
}
