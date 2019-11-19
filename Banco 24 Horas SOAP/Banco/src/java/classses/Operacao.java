package classses;

/**
 *
 * @author Rafael Braz
 */
public class Operacao {

    private final int contaOrigem;
    private final double valor;
    private final String data;

    public Operacao(int contaOrigem, double valor, String data) {
        this.contaOrigem = contaOrigem;
        this.valor = valor;
        this.data = data;
    }

    public int getContaOrigem() {
        return contaOrigem;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

}
