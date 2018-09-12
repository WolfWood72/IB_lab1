package org.nstu.common;

import java.util.Arrays;
import java.util.Random;

public class Kardano_Grid {


    private Integer[][] Grid;
    private Integer Size;

    public Kardano_Grid(Integer Size) {
        this.Size = Size;
        this.Grid = GenerateGrid(this.Size);
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


    public Character[][] Encode(String message)
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


    public String Decode(Character[][] code)
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
