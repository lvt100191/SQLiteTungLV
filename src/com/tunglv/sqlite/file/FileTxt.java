/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TUNGLV
 */
public class FileTxt {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("mail.txt"));
        FileOutputStream outputStream = new FileOutputStream("MailExtract.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if (line.contains("@")) {
                    Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line);
                    while (m.find()) {
                        String mail = m.group();
                        char end = mail.charAt(mail.length()-1);
                        if (end == '.') {
                            String mailstr = mail.substring(0, mail.length() - 2);
                            bufferedWriter.write(mailstr + ";"+"\n");
                        }else{
                            bufferedWriter.write(mail+ ";"+"\n");
                        }
                    }
                }
                line = br.readLine();
            }
            String everything = sb.toString();
        } finally {
            br.close();
            bufferedWriter.close();
        }
    }

}
