package com.encapsulateideas.excelToVcard.processing;
/**
 * Created by Exhilaration on 12-01-2018.
 */
public class Vcard {

    public static String singleContactVcard(String name, String number){
        String str="BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "FN:"+name+"\n"+
                "N:"+name+";;;\n" +
                "TEL;TYPE=CELL:"+number+"\n"+
                "END:VCARD"+"\n";
        return str;
    }
}
