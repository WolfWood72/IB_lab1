package org.nstu.common;

public abstract class Coder {

    public Coder(){

    }

    public int getENG_MAX() {
        return ENG_MAX;
    }

    public int getRUS_MAX() {
        return RUS_MAX;
    }

    public String getENG_ALPH() {
        return ENG_ALPH;
    }

    public String getRUS_ALPH() {
        return RUS_ALPH;
    }

    private    int  ENG_MAX = 26;
    private    int  RUS_MAX = 32;
    private    String ENG_ALPH = "abcdefghijklmnopqrstuvwxyz";
    private    String RUS_ALPH = "абвгдеёжзийклмнопрстуфхчшщъыьэюя";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;


    //https://ru.wikipedia.org/wiki/%D0%A8%D0%B8%D1%84%D1%80_%D0%A6%D0%B5%D0%B7%D0%B0%D1%80%D1%8F
    public String Encode(String message)
    {
        return null;
    }

    public String Decode(String message)
    {

        return null;
    }




}
