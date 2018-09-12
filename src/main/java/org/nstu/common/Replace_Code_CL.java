package org.nstu.common;


public class Replace_Code_CL {


    public static void  main(String[] args)
    {
        Ceaser_Coder Ceaser = new Ceaser_Coder(3);

        String s = "яяя ааааяяяа zzzzzz asdas aaaaaaaaaaa asd 2312 ./k fgh";
        System.out.println(s);
        String a = Ceaser.Encode(s);
        System.out.println(a);
        System.out.println(Ceaser.Decode(a));


        Lozung_Coder Lozung = new Lozung_Coder("ДИДЮЛЯ");
        System.out.println(Lozung.Encode(s));
        String ss = Lozung.Encode(s);
        System.out.println(Lozung.Decode(ss));

        String Alph="абвгдеёжзийклмнопрстуфхцчшщьыъэюя";
        Integer square = 6;
        Polibian_Coder  PC = new Polibian_Coder(Alph,square);


        Kardano_Grid KG = new Kardano_Grid(4);
        Character[][] tt =  KG.Encode("АБРАМОВ+ДЯДИНА");
        System.out.println(KG.Decode(tt));



    }
}
