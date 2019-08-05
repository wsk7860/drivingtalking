package com.drivingtalking.util;


import java.util.Random;
import java.util.UUID;

public class UUIDGenerator {

    public static final String PREFIX = "DT_";

    public static final String[] CHARS =  new String[]{
            "a","b" ,"c","d","e","f","g","h","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "A","B" ,"C","D","E","F","G","H","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
            "1","2","3","4","5","6","7","8","9"
    };

    public static String generateShortUUID(){
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-","");
        for(int i=0;i<16;i++){
            int randomIndex = new Random().nextInt(CHARS.length-1);
            builder.append(CHARS[randomIndex]);
        }
        return  builder.toString();
    }

    public static  String generateUUID(){
        return PREFIX + System.currentTimeMillis() + generateShortUUID();
    }
}
