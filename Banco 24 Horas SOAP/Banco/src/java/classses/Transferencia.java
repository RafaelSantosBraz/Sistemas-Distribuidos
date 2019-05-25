package classses;

/**
 *
 * @author Rafael Braz
 */
public class Transferencia extends Operacao {

    private final int contaDestino;

    public Transferencia(int contaOrigem, int contaDestino, double valor, String data) {
        super(contaOrigem, valor, data);
        this.contaDestino = contaDestino;
    }

    public int getContaDestino() {
        return contaDestino;
    }

    @Override
    public String toString() {
        return "Transferência: " + super.getContaOrigem() + " | " + contaDestino + " | " + super.getValor() + " | " + super.getData();
    }

}
