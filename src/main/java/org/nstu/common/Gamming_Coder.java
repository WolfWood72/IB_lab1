package org.nstu.common;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.util.Random;

public class Gamming_Coder  extends Coder{
    public Gamming_Coder() {
    }

    public void givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
    }

    public String Encode(String message){

    }
    public String Dencode(String code){
        KeyGenerator kg = new KeyGenerator();
        SecretKey a = kg.generateKey();
        a.toString()
    }

}
