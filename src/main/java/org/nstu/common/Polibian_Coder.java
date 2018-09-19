package org.nstu.common;


public class Polibian_Coder extends Coder{

    private Integer SquareSize;
    private char[][] PolibianMatrix;

    public String getAlph() {
        return Alph;
    }

    public void setAlph(String alph) {
        if(alph.length()> SquareSize*SquareSize)
            throw new IllegalArgumentException(String.format("This Alphabit greater then PolibianMatrix with number of elements = {}", SquareSize));

        Alph = alph;
        if(!this.Alph.contains(" "))
            this.Alph+=" ";
    }

    private String Alph="абвгдеёжзийклмнопрстуфхцчшщьыъэюя";

    private char[][] MakeMatrix(String Alph, Integer SquareSize)
    {
        char[][] PolibianMatrix = new char[SquareSize][SquareSize];

        String true_alph = Alph;
        for(int i = 0; i < (SquareSize*SquareSize - Alph.length()); i++)
            true_alph+= "-";
        for(int i = 0; i < SquareSize; i++)
        {
            PolibianMatrix[i] = true_alph.substring(i*SquareSize,Math.min(true_alph.length(),(i+1)*SquareSize)).toCharArray();
        }
        return PolibianMatrix;
    }

    public Polibian_Coder(String Alph, Integer SquareSize) {
        if( SquareSize >= 10)
            throw new IllegalArgumentException("SquareSize must be less then 10");
        if(Alph.length() > SquareSize*SquareSize)
            throw new IllegalArgumentException(String.format("This Alphabit greater then PolibianMatrix with number of elements = {}", SquareSize));

        this.Alph = Alph;
        this.SquareSize = SquareSize;
        if(!this.Alph.contains(" "))
            this.Alph+=" ";

        this.PolibianMatrix = MakeMatrix(this.Alph, SquareSize);
    }
    public Polibian_Coder( Integer SquareSize) {
        if( SquareSize >= 10)
            throw new IllegalArgumentException("SquareSize must be less then 10");
        if(Alph.length() > SquareSize*SquareSize)
            throw new IllegalArgumentException(String.format("This Alphabit greater then PolibianMatrix with size = %s", SquareSize));
        if(!this.Alph.contains(" "))
            this.Alph+=" ";
        this.SquareSize = SquareSize;
        this.PolibianMatrix = MakeMatrix(Alph, SquareSize);
    }





    @Override
    public String Encode(String message) {


        StringBuilder code = new StringBuilder();
        for (char c : message.toCharArray()
             ) {

            if(this.Alph.indexOf(c) == -1) {
                throw new IllegalArgumentException(String.format("Alphabit does not exist character '%s' from message", c));

            }
                for (Integer i = 0; i < this.PolibianMatrix.length; i++)
                    for (Integer j = 0; j < this.PolibianMatrix.length; j++)
                        if (Character.toLowerCase(this.PolibianMatrix[i][j]) ==  Character.toLowerCase(c))
                            code.append(i.toString() + j.toString() );


        }
        return code.toString();
    }

    @Override
    public String Decode(String code) {
        StringBuilder message = new StringBuilder();
        for (int c = 0; c < code.length();c+=2)
        {
            int i = Character.getNumericValue(code.charAt(c)), j = Character.getNumericValue(code.charAt(c+1));
            message.append(this.PolibianMatrix[i][j]);

        }
        return message.toString();
    }
}

