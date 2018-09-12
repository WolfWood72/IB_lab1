package org.nstu.common;


//https://ru.wikipedia.org/wiki/%D0%A8%D0%B8%D1%84%D1%80_%D0%A6%D0%B5%D0%B7%D0%B0%D1%80%D1%8F
public class Ceaser_Coder extends Coder {


    private Integer shift;


    public Ceaser_Coder(Integer shift)
    {
        if(shift < 0)
            throw new IllegalArgumentException("Shift in Ceaser Code must be greater than 0");
        this.shift = shift;
    }


    @Override
    public String Encode(String message) {

        StringBuilder code = new StringBuilder("");
        for(char i: message.toCharArray())
        {
            char symbol = i;
            if (Character.isLetter(symbol))
            {


                if(symbol >= 'a' &&  symbol <= 'z') {
                     symbol = (char)(symbol + (this.shift % this.getENG_MAX()));
                     if (symbol > 'z') symbol = (char)('a' + (symbol - 'z') - 1);

                }
                if(symbol >= 'а' &&  symbol <= 'я') {
                    symbol = (char)(symbol + (this.shift % this.getRUS_MAX()));
                    if (symbol > 'я') symbol = (char)('а' + (symbol - 'я') - 1);

                    if(symbol >= 'A' &&  symbol <= 'Z') {
                        symbol = (char)(symbol + (this.shift % this.getENG_MAX()));
                        if (symbol > 'Z') symbol = (char)('A' + (symbol - 'Z') - 1);

                    }
                    if(symbol >= 'А' &&  symbol <= 'Я') {
                        symbol = (char)(symbol + (this.shift % this.getRUS_MAX()));
                        if (symbol > 'Я') symbol = (char)('А' + (symbol - 'Я') - 1);

                    }
                }

            }
            code.append(symbol);
        }
        return  code.toString();
    }

    @Override
    public String Decode(String code) {

        StringBuilder message = new StringBuilder("");
        for(char i: code.toCharArray())
        {
            char symbol = i;
            if (Character.isLetter(symbol))
            {
                if(symbol >= 'a' &&  symbol <= 'z') {
                    symbol = (char)(symbol - (this.shift % this.getENG_MAX()));
                    if (symbol < 'a') symbol = (char)('z' - ('a' - symbol) + 1);

                }
                if(symbol >= 'а' &&  symbol <= 'я') {
                    symbol = (char)(symbol - (this.shift % this.getRUS_MAX()));
                    if (symbol < 'а') symbol = (char)('я' - ('а' - symbol) + 1);

                }
                if(symbol >= 'A' &&  symbol <= 'Z') {
                    symbol = (char)(symbol - (this.shift % this.getENG_MAX()));
                    if (symbol < 'A') symbol = (char)('Z' - ('A' - symbol) + 1);

                }
                if(symbol >= 'А' &&  symbol <= 'Я') {
                    symbol = (char)(symbol - (this.shift % this.getRUS_MAX()));
                    if (symbol < 'А') symbol = (char)('Я' - ('А' - symbol) + 1);

                }
            }
            message.append(symbol);
        }
        return  message.toString();
    }
}
