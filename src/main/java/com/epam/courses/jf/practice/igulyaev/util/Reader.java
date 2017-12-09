package com.epam.courses.jf.practice.igulyaev.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {
    private BufferedReader reader;
    public Reader(InputStream stream){
        reader = new BufferedReader(new InputStreamReader(stream));
    }
    public String readLine(){
        String str = null;
        try{
            str = reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }
}
