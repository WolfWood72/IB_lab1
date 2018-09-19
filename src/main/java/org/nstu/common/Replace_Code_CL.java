package org.nstu.common;


import org.nstu.common.utils.Config_Maker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Replace_Code_CL {


    private static String ReadFileFromOption(Config_Maker config, String option) throws NoSuchFileException {
        try {
            String t = Files.lines(Paths.get(config.GetStringOption(option)),StandardCharsets.UTF_8).reduce("", String::concat);
            if(t.isEmpty() || t == null) throw new IllegalArgumentException(String.format("File %s is empty", config.GetStringOption(option)));
            return t;
        } catch ( NoSuchFileException nsfe)
        {
            System.out.println(nsfe.getMessage());
            nsfe.printStackTrace();
            throw nsfe;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String ReadFromFile(String path) throws NoSuchFileException {
        try {
            String t = Files.lines(Paths.get(path)).reduce("", String::concat);
            if(t.isEmpty() || t == null) throw new IllegalArgumentException(String.format("File %s is empty", path));
            return t;
        } catch ( NoSuchFileException nsfe)
        {
            System.out.println(nsfe.getMessage());
            nsfe.printStackTrace();
            throw nsfe;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static  void WriteStringToFile(String path, String text) throws IOException {
        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))
        {
            // запись всей строки
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

    }


    public static void  main(String[] args) throws IOException {

        List<String> code_list = Arrays.asList("ceaser","lozung","polibian","kardano","gamming");
        String directoryName = "data";
        File directory = new File(directoryName);
        if (! directory.exists())
            directory.mkdir();


        Config_Maker config = new Config_Maker();
        config.SetRequiredParam("code", "", "avaliable values: 'ceaser','lozung','polibian','kardano','gamming'");
        config.SetProperty("encode","encode","Option to encode message. Default filename='encode_%code_name.rxt'");
        config.SetProperty("decode","decode","Option to decode message. Default filename='encode_%code_name.rxt'");
        config.SetOptionalParam("input", "input_file", "Path to input encoded/decoded message");
        config.SetOptionalParam("output", "output_file", "Path to output encoded/decoded message");
        config.SetOptionalParam("key","key","key");
        config.SetOptionalParam("key_file","key_file","path to text file with key");
        config.SetOptionalParam("alph","alphabit","path to text file with alphabit");

        config.SetOptionalParam("grid","grid","path to file with grid for cardano");
        config.SetOptionalParam("gen_auto","generate_key_for_gammming","Option for gamming.");
        config.SetOptionalParam("key_format","key_format","Formats of key for gamming. Avaliable values: 'HEX','BIN','SYM'");


        config.parse(args);

        String code = config.GetStringOption("code");
        if(!code_list.contains(code))
            throw new IllegalArgumentException("invalid value of argument 'code'");
        boolean IsEncode = config.HasOption("encode");
        boolean IsDecode = config.HasOption("decode");
        if((IsEncode || IsDecode) == false)
            throw new IllegalArgumentException("must have decode or encode params");
        String InputFile = config.GetStringOption("input", String.format("data/%s/input_%s_%s.txt",code, code,IsEncode ? "encoded" : "decode"));
        String OutputFile = config.GetStringOption("output", String.format("data/%s/output_%s_%s.txt", code, code,IsEncode ? "encode" : "decode"));

        if(config.HasOption("key") && config.HasOption("key_file"))
            throw new IllegalArgumentException("Key must be specified either from a file or from the command line. Only one option");
        String key = null;
        key = config.HasOption("key_file") ? ReadFileFromOption(config, "key_file") : config.GetStringOption("key");
        String input_messege = ReadFromFile(InputFile);
        String alph = null;
        try {
            alph = ReadFileFromOption(config, "alph");
        }
        catch (Exception e)
        {

        }
        String output_message = null;
        try {
            switch (code) {
                case "ceaser":
                    if (key == null) throw new IllegalArgumentException("key must be not null");
                    Ceaser_Coder CC = new Ceaser_Coder(Integer.parseInt(key));
                    output_message = CC.MakeCode(IsEncode, input_messege);
                    break;
                case "lozung":
                    Lozung_Coder LC = config.HasOption("alph") ? new Lozung_Coder(key, alph) : new Lozung_Coder(key);
                    output_message = LC.MakeCode(IsEncode, input_messege);
                    break;
                case "polibian":
                    Polibian_Coder PC = new Polibian_Coder(Integer.parseInt(key));
                    PC.setAlph(config.HasOption("alph") ? alph : PC.getRUS_ALPH().toUpperCase());
                    output_message = PC.MakeCode(IsEncode, input_messege);
                    break;
                case "kardano":
                    if (!config.HasOption("grid")) throw new IllegalArgumentException("grid for kardano must be specified");
                    Kardano_Grid KG = IsEncode ? new Kardano_Grid(Integer.parseInt(key)) : new Kardano_Grid(ReadFileFromOption(config, "grid"));
                    if (IsEncode)
                        WriteStringToFile("kardano_grid.txt", KG.GridToString());
                    output_message = KG.MakeCode(IsEncode, input_messege);
                    break;
                case "gamming":


                    String key_format = config.GetStringOption("key_format", "SYM");
                    Gamming_Coder GC = new Gamming_Coder();
                    GC.setAlph(config.HasOption("alph") ? alph : GC.getRUS_ALPH().toUpperCase()+ " ");
                    if(config.HasOption("gen_auto") && !config.HasOption("key_file")) {
                        GC.setKey(GC.GenerateKey(config.GetIntegerOption("gen_auto", 10)));
                        key = GC.getKey();
                        WriteStringToFile("data/gamming_key.txt",GC.getFormatKey(key_format));
                    }
                    else
                        GC.setFormatKey(key, key_format);

                    output_message = GC.MakeCode(IsEncode, input_messege);
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        System.out.println(String.format("code:'%s'", code));
        System.out.println(String.format("key:'%s'", key));
        System.out.println(String.format((IsEncode ? "Encoded" : "Decoded") + " messege: '%s'", output_message) );
        WriteStringToFile(OutputFile, output_message);


    }
}
