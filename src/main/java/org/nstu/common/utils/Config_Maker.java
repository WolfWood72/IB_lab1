package org.nstu.common.utils;

import org.apache.commons.cli.*;


public class Config_Maker {


    public Config_Maker() {
        this.options = new Options();
        this.cmdLinePosixParser = new PosixParser();
    }


    private Options options;
    private CommandLineParser cmdLinePosixParser;
    private CommandLine commandLine;


    void parse(String[] params)
    {
        try {
            this.commandLine = cmdLinePosixParser.parse(this.options, params);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    void SetRequiredParams(String ShortName, String LongName, String Description)
    {
        Option option = new Option(ShortName,LongName,true,Description);
        option.setArgs(1); // число аргументов в опции
        option.setOptionalArg(false);// являются ли аргументы необязательными для ввода, по умолчанию аргументы обязательны для ввода, так что эту строчку можно было опутить
        option.setArgName(LongName);//имя аргумета, именно так аргумент будет отображатся в справка по использованию командной строки.
        this.options.addOption(option);
    }
    void SetProperty(String ShortName, String LongName, String Description)
    {
        Option option = new Option(ShortName,LongName,true,Description);
        option.setArgs(0); // число аргументов в опции
        option.setArgName(LongName);//имя аргумета, именно так аргумент будет отображатся в справка по использованию командной строки.
        this.options.addOption(option);
    }


}
