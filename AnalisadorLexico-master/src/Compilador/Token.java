
package Compilador;

public class Token {
    private String classe, representacao;
    private Integer indice, linha, coluna, valor;

    public Token(String classe, String representacao, Integer valor, Integer indice, Integer linha, Integer coluna) {
        this.classe = classe;
        this.representacao = representacao;
        this.valor = valor;
        this.indice = indice;
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "[ " + classe + ", " + representacao + ", " + valor + ", " + indice + ", " + linha + ", " + coluna + " ]";
    }

}
