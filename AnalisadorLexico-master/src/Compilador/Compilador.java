package Compilador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Compilador {

    private String fonte;
    private HashMap<Integer, Token> tabelaDeSimbolos;
    private List<Token> tokensLidos;

    public Compilador() throws IOException {
        this.tabelaDeSimbolos = new HashMap<>();
        this.tokensLidos = new ArrayList<>();
        this.fonte = Arquivo.ler("fonte.txt");
        this.excluir_comentario();
    }

    public void analisar() {
        this.fonte = fonte.replaceAll(" +"," ");
        String[] linhas = this.fonte.split("\\\\n");
        Token t = null;
        int pos = 1;
        for (int j = 0; j < linhas.length; j++) {
            int coluna = 0;
            String[] colunas = linhas[j].split(" ");
            for (int i = 0; i < colunas.length; i++) {
                if (this.identificador(colunas[i])) {
                    t = new Token("ident", null, -1, j + 1, j, coluna);
                    this.tokensLidos.add(t);
                    if (!this.tabelaDeSimbolos.containsValue(t)) {
                        tabelaDeSimbolos.put(pos++, t);
                    }
                } else if (this.atribuicao(colunas[i])) {
                    t = new Token("atrib", colunas[i], null, j + 1, j, coluna);
                    this.tokensLidos.add(t);
                    if (!this.tabelaDeSimbolos.containsValue(t)) {
                        tabelaDeSimbolos.put(pos++, t);
                    }
                } else if (this.operador(colunas[i])) {
                    t = new Token("oplog", colunas[i], null, j + 1, j, coluna);
                    this.tokensLidos.add(t);
                    if (!this.tabelaDeSimbolos.containsValue(t)) {
                        tabelaDeSimbolos.put(pos++, t);
                    }
                } else if (this.comentario(colunas[i])) {
                } else if (this.constante_logica(colunas[i])) {
                    t = new Token("const", null, Integer.parseInt(colunas[i]), i + 1, i, coluna);
                    this.tokensLidos.add(t);
                    if (!this.tabelaDeSimbolos.containsValue(t)) {
                        tabelaDeSimbolos.put(pos++, t);
                    }
                } else if (this.espaco(colunas[i])) {
                    coluna++;
                } else {
                    System.out.println("Erro na linha " + j + ", coluna " + coluna + ". Caracter inválido: " + colunas[i]);
                }
                coluna += colunas[i].length() + 1;
            }
            this.imprimir();
            this.tokensLidos = new ArrayList<>();
        }
    }

    public void imprimir() {
        this.tokensLidos.forEach((token) -> {
            System.out.print(token);
        });
        System.out.println();
    }

    public void excluir_comentario() {
        this.fonte = fonte.replaceAll(" \\{.*?\\} ", " ");
    }

    public boolean espaco(String str) {
        return str.equals(" ");
    }

    public boolean identificador(String str) {
        return str.matches("[A-Z][0-9]?");
    }

    public boolean constante_logica(String str) {
        return str.equals("0") || str.equals("1");
    }

    public boolean atribuicao(String str) {
        return str.equals("=");
    }

    public boolean operador(String str) {
        return str.equals("+") || str.equals(".") || str.equals("~");
    }

    public boolean comentario(String str) {
        return str.matches("\\{(.)*\\}");
    }
}
