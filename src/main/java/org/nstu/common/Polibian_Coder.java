package org.nstu.common;

import java.nio.charset.CharsetEncoder;

public class Polibian_Coder extends Coder{

    private Integer SquareSize;
    private char[][] PolibianMatrix;
    private String Alph="абвгдеёжзийклмнопрстуфхцчшщьыъэюя";

    private char[][] MakeMatrix(String Alph, Integer SquareSize)
    {
        char[][] PolibianMatrix = new char[SquareSize][SquareSize];
        for(int i = 0; i < SquareSize; i++)
        {
            PolibianMatrix[i] = Alph.substring(i*SquareSize,Math.min(Alph.length(),(i+1)*SquareSize)).toCharArray();
        }
        return PolibianMatrix;
    }

    public Polibian_Coder(String Alph, Integer SquareSize) {
        if( SquareSize >= 10)
            throw new IllegalArgumentException("SquareSize must be less then 10");
        if(Alph.length() > SquareSize*SquareSize)
            throw new IllegalArgumentException(String.format("This Alphabit greater then PolibianMatrix with size = {}", SquareSize));

        this.Alph = Alph;
        this.SquareSize = SquareSize;
        this.PolibianMatrix = MakeMatrix(Alph, SquareSize);
    }

    @Override
    public String Encode(String message) {


        StringBuilder code = new StringBuilder();
        for (char c : message.toCharArray()
             ) {
            if(this.Alph.indexOf(c) == -1) {
                throw new IllegalArgumentException(String.format("Alpabit does not exist character '{}' from messege", c));

            }
                //TODO: Сделать проверку на вхождение символа в алфавит
                for (int i = 0; i < this.PolibianMatrix.length; i++)
                    for (int j = 0; j < this.PolibianMatrix.length; j++)
                        if (this.PolibianMatrix[i][j] == c)
                            code.append(i + " ");


        }
        return code.toString();
    }

    @Override
    public String Decode(String code) {
        StringBuilder message;
        for (String c :code.split(" "))
        {
            int i = Character.getNumericValue(c.charAt(0)), j = Character.getNumericValue(c.charAt(1));

        }
        return  null;
    }
}

