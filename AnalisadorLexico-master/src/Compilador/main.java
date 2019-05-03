
package Compilador;

import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Compilador comp = new Compilador();
        
        comp.analisar();
        comp.imprimir();
    }
}
