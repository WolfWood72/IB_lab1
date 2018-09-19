package org.nstu.common;


import java.util.Arrays;
import java.util.Random;

public class Kardano_Grid extends Coder {


    private Integer[][] Grid;
    private Integer Size;

    public Kardano_Grid(Integer Size) {
        this.Size = Size;
        this.Grid = GenerateGrid(this.Size);
    }
    public Kardano_Grid(String grid) {
        SetGridFromString(grid);
    }


    public Integer[][] GenerateGrid(Integer Size)
    {
        if(Size%2 != 0)
            throw new IllegalArgumentException("Width and height of grid must be even");
        Random rand = new Random();
        Integer[][] Grid = new Integer[Size][Size];
        for(int i = 0; i < Size;i++)
            Arrays.fill(Grid[i],0);
        for(int i = 0; i < Size;i+=2) {

            for (int j = 0; j < Size; j += 2)
                Grid[i + rand.nextInt(1)][j + rand.nextInt(1)] = 1;
        }
        return Grid;
    }

    //Rotate Matrix to 90 degree toward Left(counter clockwise)
    private Integer[][] rotateMatrixBy90DegreeCounterClockwise(Integer[][] matrix) {

        Integer totalRowsOfRotatedMatrix = matrix[0].length; //Total columns of Original Matrix
        Integer totalColsOfRotatedMatrix = matrix.length; //Total rows of Original Matrix
        Integer[][] rotatedMatrix = new Integer[totalRowsOfRotatedMatrix][totalColsOfRotatedMatrix];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rotatedMatrix[(totalRowsOfRotatedMatrix-1)-j][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    public String GridToString()
    {
        if(Grid.equals(null))
            throw new IllegalArgumentException("Grid is not initialyzed");
        StringBuilder SB = new StringBuilder();
        for(int i =0; i < this.Size; i++)
            for(int j = 0; j < this.Size; j++)
                SB.append(Grid[i][j]);
        return  SB.toString();
    }
    public void SetGridFromString(String StrGrid)
    {

        if(Math.sqrt(StrGrid.length()) % 1 != 0 )
            throw new IllegalArgumentException("Input Grid have invalid size.  It must be square of some number");
        Integer Size = (int)Math.sqrt(StrGrid.length());
        this.Grid = new Integer[Size][Size];
        int ind = 0;
        for(int i =0; i < Size; i++)
            for(int j = 0; j < Size; j++)
            {
                this.Grid[i][j] = Character.getNumericValue(StrGrid.charAt(ind++));
            }
        this.Size = Size;
    }


    @Override
    public String Encode(String message)
    {
        StringBuilder mes = new StringBuilder("");

        for( Character[] c: Encode_Kardano(message))
            for( Character k: c)
            mes.append(k);
        return  mes.toString();
    }

    @Override
    public String Decode(String code)
    {
        Character[][] code_matrix = new Character[this.Size][this.Size];
        int code_ind = 0;
        for(int i = 0; i < this.Size;i++)
        {
            for(int j = 0; j < this.Size;j++)
                code_matrix[i][j] = code.charAt(code_ind++);
        }
        return this.Decode_Kardano(code_matrix);
    }

    private Character[][] Encode_Kardano(String message)
    {
        Character[][] Encoded_messege = new Character[this.Size][this.Size];
        Integer curr_ind_mess = 0;


        Integer[][] t_Grid = this.Grid;
        for(int r = 0; r < 4;r++)
        {

            for(int i = 0; i < this.Size;i++)
                for(int j = 0; j < this.Size; j++)
                    if(t_Grid[i][j] == 1)
                        try {
                            Encoded_messege[i][j] = message.charAt(curr_ind_mess++);
                        }
                        catch (Exception e)
                        {
                            Encoded_messege[i][j] = '-';
                        }

            t_Grid = rotateMatrixBy90DegreeCounterClockwise(t_Grid);

        }
        return  Encoded_messege;
    }


    private String Decode_Kardano(Character[][] code)
    {
        StringBuilder decoded_messege = new StringBuilder("");
        Integer[][] t_Grid = this.Grid;
        for(int r = 0; r < 4;r++)
        {

            for(int i = 0; i < this.Size;i++)
                for(int j = 0; j < this.Size; j++)
                    if(t_Grid[i][j] == 1 && code[i][j] != '-')
                        decoded_messege.append(code[i][j]);
            t_Grid = rotateMatrixBy90DegreeCounterClockwise(t_Grid);

        }
        return decoded_messege.toString();
    }

}
