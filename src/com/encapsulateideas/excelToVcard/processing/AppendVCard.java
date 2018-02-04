package com.encapsulateideas.excelToVcard.processing;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Exhilaration on 12-01-2018.
 */
public class AppendVCard {
    String f = null;
    FileWriter fop = null;
    public AppendVCard(String name){
    	this.f=name;
        try {
			this.fop= new FileWriter(this.f,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    public void appendFile(String name, String number){
    	String singleContactVcard = Vcard.singleContactVcard(name, number);
    	try {
			fop.write(singleContactVcard);
			fop.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
