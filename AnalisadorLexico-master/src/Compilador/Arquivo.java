package Compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Arquivo {
    public static String ler(String url) throws FileNotFoundException, IOException{
        String resultado = "";
        BufferedReader br = new BufferedReader(new FileReader(url));
        while(br.ready()){
           resultado+=br.readLine()+"\\n";
        }
        br.close();
        return resultado;
    }
}
