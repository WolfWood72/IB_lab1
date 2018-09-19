package org.nstu.common;

public  class Coder {

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

    public String Encode(String message) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    public String Decode(String message) {
        throw new UnsupportedOperationException("Method not implemented");
        }

    public String MakeCode(Boolean IsEncode,String messege)
    {
        return IsEncode ? this.Encode(messege) : this.Decode(messege);
    }




}
