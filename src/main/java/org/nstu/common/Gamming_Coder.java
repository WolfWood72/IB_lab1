package org.nstu.common;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.util.Random;

public class Gamming_Coder  extends Coder{

    public Gamming_Coder() {

    }
    public Gamming_Coder(String key) {
        this.setKey(key);
    }
    public Gamming_Coder(String key , String key_format) {
        switch (key_format){
            case "HEX":
                this.SetKeyHex(key);
                break;
            case "BIN":
                this.SetKeyBin(key);
                break;
            case "SYM":
                this.SetKeySymbol(key);
                break;
            default:
                this.setKey(key);
                break;
        }
    }
    public Gamming_Coder(String alph, String key, String key_format) {
        this.setAlph(alph);
        ///this.setKey(key);
        switch (key_format){
            case "HEX":
                this.SetKeyHex(key);
                break;
            case "BIN":
                this.SetKeyBin(key);
                break;
            case "SYM":
                this.SetKeySymbol(key);
                break;
            default:
                this.setKey(key);
                break;
        }

    }


    public String getAlph() {
        return Alph;
    }

    public void setAlph(String alph) {
        Alph = alph;
    }

    private String Alph;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    private int HexToInt(String HEX)
    {
        return (int) Long.parseLong(HEX, 16);
    }
    private String IntToHex(int val)
    {
        return Integer.toHexString(val);
    }
    private int BinToInt(String BIN)
    {
        return (int) Long.parseLong(BIN, 2);
    }
    private String IntToBin(int val)
    {
        return Integer.toBinaryString(val);
    }


    public void SetKeyHex(String KeyHex)
    {
        StringBuilder sym_key = new StringBuilder();
        for(String c: KeyHex.split(" "))
        {
            sym_key.append((char)HexToInt(c));
        }
        this.setKey(sym_key.toString());
    }
    public void SetKeyBin(String KeyBin)
    {
        StringBuilder sym_key = new StringBuilder();
        for(String c: KeyBin.split(" "))
        {
            sym_key.append((char)BinToInt(c));
        }
        this.setKey(sym_key.toString());
    }
    public void SetKeySymbol(String KeySymbol)
    {
        this.setKey(KeySymbol);
    }


    public String GetKeyHex()
    {
        if(this.key == null)
            throw new IllegalArgumentException("key must be not null");
        StringBuilder Hex_key = new StringBuilder();
        for(char c: this.key.toCharArray())
        {
            Hex_key.append(IntToHex(c) + " ");
        }
        return Hex_key.toString();
    }
    public String GetKeyBin()
    {
        if(this.key == null)
            throw new IllegalArgumentException("key must be not null");
        StringBuilder Bin_key = new StringBuilder();
        for(char c: this.key.toCharArray())
        {
            Bin_key.append(IntToBin(c) + " ");
        }
        return Bin_key.toString();
    }
    public String GetKeySymbol()
    {
        return this.getKey();
    }


    public String getFormatKey(String format) {
        String k;
        switch (format){
            case "HEX":
                k = this.GetKeyHex();
                break;
            case "BIN":
                k = this.GetKeyBin();
                break;
            case "SYM":
                k = this.GetKeySymbol();
                break;
            default:
                k = this.GetKeySymbol();
                break;
        }

        return k;
    }

    public void setFormatKey(String k,String format) {

        switch (format){
            case "HEX":
                this.SetKeyHex(k);
                break;
            case "BIN":
                this.SetKeyBin(k);
                break;
            case "SYM":
                this.SetKeySymbol(k);
                break;
            default:
                this.setKey(k);
                break;
        }

    }





    public String GenerateKey(Integer N)
    {
        StringBuffer randStr = new StringBuffer();
        Random rand = new Random();
        for(int i=0; i < N; i++){
            randStr.append(this.Alph.charAt(rand.nextInt(this.Alph.length())));
        }
        return randStr.toString();
    }

    public String Encode(String message){

      StringBuilder result = new StringBuilder();
        Integer N = Alph.length();
        int keyword_index = 0;
        for(char symbol: message.toCharArray())
        {
            int c = (Alph.indexOf(symbol) + Alph.indexOf(key.charAt(keyword_index)))%N;

            result.append(Alph.charAt(c));

            keyword_index++;

            if ((keyword_index + 1) == key.length())
                keyword_index = 0;
        }
        return result.toString();
    }
    public String Decode(String  code){

        StringBuilder result = new StringBuilder();
        Integer N = Alph.length();
        int keyword_index = 0;

        for (char symbol : code.toCharArray())
        {
            int p = (Alph.indexOf(symbol) + N - Alph.indexOf(key.charAt(keyword_index)))%N;


            result.append( Alph.charAt(p));

            keyword_index++;

            if ((keyword_index + 1) == key.length())
                keyword_index = 0;
        }

        return result.toString();


    }

}
