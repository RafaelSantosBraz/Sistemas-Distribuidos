package classses;

/**
 *
 * @author Rafael Braz
 */
public class Movimentacao extends Operacao {

    private final int tipo;

    public Movimentacao(int conta, int tipo, double valor, String data) {
        super(conta, valor, data);
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

}
