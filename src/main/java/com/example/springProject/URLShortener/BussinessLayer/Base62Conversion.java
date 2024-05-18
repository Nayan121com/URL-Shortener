package com.example.springProject.URLShortener.BussinessLayer;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Base62Conversion {
    private String base62;
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public Base62Conversion (){
        this.base62 = "";
    }

    public void convertToBase62(@NotNull UUID uuid){

        byte[] bytes = new byte[16];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());

        BigInteger uuidByte = new BigInteger(1, bytes);
        StringBuilder base62Value = new StringBuilder();
        while(uuidByte.compareTo(BigInteger.ZERO) > 0){
            BigInteger[] divisionResult = uuidByte.divideAndRemainder(BigInteger.valueOf(62));
            base62Value.insert(0,BASE62_CHARS.charAt(divisionResult[1].intValue()));
            uuidByte = divisionResult[0];
        }
        this.base62 = base62Value.toString();
    }

    public String getBase62Value(){
        return this.base62;
    }
}
