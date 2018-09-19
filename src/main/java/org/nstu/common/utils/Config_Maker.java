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


    public void parse(String[] params)
    {
        try {
            this.commandLine = cmdLinePosixParser.parse(this.options, params);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void SetOptionalParam(String ShortName, String LongName, String Description)
    {
        Option option = new Option(ShortName,LongName,true,Description);
        option.setArgs(1); // число аргументов в опции
        option.setOptionalArg(false);// являются ли аргументы необязательными для ввода, по умолчанию аргументы обязательны для ввода, так что эту строчку можно было опутить
        option.setArgName(LongName);//имя аргумета, именно так аргумент будет отображатся в справка по использованию командной строки.
        this.options.addOption(option);
    }

    public void SetRequiredParam(String ShortName, String LongName, String Description)
    {
        Option option = new Option(ShortName,LongName,true,Description);
        option.setArgs(1); // число аргументов в опции
        option.setOptionalArg(true);// являются ли аргументы необязательными для ввода, по умолчанию аргументы обязательны для ввода, так что эту строчку можно было опутить
        option.setArgName(LongName);//имя аргумета, именно так аргумент будет отображатся в справка по использованию командной строки.
        this.options.addOption(option);
    }
    public void SetProperty(String ShortName, String LongName, String Description)
    {
        Option option = new Option(ShortName,LongName,true,Description);
        option.setArgs(0); // число аргументов в опции
        option.setArgName(LongName);//имя аргумета, именно так аргумент будет отображатся в справка по использованию командной строки.
        this.options.addOption(option);
    }

    public boolean HasOption(String Option)
    {
        return this.commandLine.hasOption(Option);
    }
    public String GetStringOption(String Option, String DefaultValue)
    {
        return commandLine.getOptionValue(Option,DefaultValue);// если такая опция есть, то получаем переданные ей аргументы
    }
    public String GetStringOption(String Option)
    {
        return commandLine.getOptionValue(Option);// если такая опция есть, то получаем переданные ей аргументы
    }
    public Integer GetIntegerOption(String Option, Integer DefaultValue)
    {
        return Integer.parseInt(commandLine.getOptionValue(Option, DefaultValue.toString()));// если такая опция есть, то получаем переданные ей аргументы

    }









}
