package org.nstu.common;




import java.util.HashMap;
import java.util.Map;

//https://questhint.ru/lozungovyj-shifr/
public class Lozung_Coder extends Coder {


    public Lozung_Coder()
    {

    }


    public Lozung_Coder(String key)
    {
        this.setKey(key);
        MakeLozungAlph(this.getKey());
        FillDicts(this.getRUS_ALPH().toLowerCase().toCharArray(), this.Lozung_ALPH.toLowerCase().toCharArray());
        FillDicts(this.getRUS_ALPH().toUpperCase().toCharArray(), this.Lozung_ALPH.toUpperCase().toCharArray());
    }

    public Lozung_Coder(String key, String alph)
    {
        this.setKey(key);
        this.setAlph(alph);
        MakeLozungAlph(this.getKey());
        FillDicts(alph.toLowerCase().toCharArray(), this.Lozung_ALPH.toLowerCase().toCharArray());
        FillDicts(alph.toUpperCase().toCharArray(), this.Lozung_ALPH.toUpperCase().toCharArray());
    }



    private  String Lozung_ALPH;


    public String getAlph() {
        return alph;
    }

    public void setAlph(String alph) {
        this.alph = alph;
    }

    private String  alph;
    private Map<Character,Character> encode_dict = new HashMap<Character,Character>();
    private Map<Character,Character> decode_dict = new HashMap<Character,Character>();

    private void MakeLozungAlph(String Lozung)
    {
        String DeduplicatedLozung = DropDuplicate(Lozung);
        Integer n = DeduplicatedLozung.length();
        this.Lozung_ALPH = DropDuplicate(DeduplicatedLozung + this.getRUS_ALPH().substring(0, this.getRUS_ALPH().length()-n));
    }

    private  void FillDicts(char[] Original_Alph, char[] Lozunged_Alph)
    {
        if(Original_Alph.length != Lozunged_Alph.length)
            throw new  IllegalArgumentException("Error with make encode\\decode dict! Original alph nd alph with lozung must be a same size");
        for(int i = 0;i < Original_Alph.length; i++)
        {
            encode_dict.put(Original_Alph[i], Lozunged_Alph[i] );
            decode_dict.put(Lozunged_Alph[i], Original_Alph[i]);
        }



    }

    private String DropDuplicate(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<str.length();i++){
            String si = str.substring(i, i+1);
            if (sb.indexOf(si)==-1){
                sb.append (si);
            }
        }
        return sb.toString();
    }

    @Override
    public String Encode(String message) {

        StringBuilder code = new StringBuilder("");
        for(char i: message.toCharArray())
            code.append(Character.isLetter(i)? encode_dict.get(i) : i);

        return code.toString();
    }

    @Override
    public String Decode(String code) {


        StringBuilder message = new StringBuilder("");
        for(char i: code.toCharArray()) {
            message.append(Character.isLetter(i)? decode_dict.get(i) : i);
        }

        return message.toString();

    }


}
