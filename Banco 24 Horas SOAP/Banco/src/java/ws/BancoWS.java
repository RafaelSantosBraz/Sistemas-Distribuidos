package ws;

import classses.*;
import controller.*;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Rafael Braz
 */
@WebService(serviceName = "BancoWS")
@Stateless()
public class BancoWS {

    private final ControllerBanco controller;

    public BancoWS() {
        controller = new ControllerBanco("localhost", "3306", "banco", "mysql", "mysql");
    }

    @WebMethod(operationName = "consultarCadastro")
    public Cliente consultarCadastro(@WebParam(name = "CPF") String CPF) {
        return controller.consultarCadastro(CPF);
    }

    @WebMethod(operationName = "alterarCadastro")
    public boolean alterarCadastro(@WebParam(name = "cliente") Cliente cliente) {
        return controller.alterarCadastro(cliente);
    }

    @WebMethod(operationName = "realizarSaque")
    public boolean realizarSaque(@WebParam(name = "conta") int conta, @WebParam(name = "valor") double valor) {
        return controller.realizarSaque(conta, valor);
    }

    @WebMethod(operationName = "realizarDeposito")
    public boolean realizarDeposito(@WebParam(name = "conta") int conta, @WebParam(name = "valor") double valor) {
        return controller.realizarDeposito(conta, valor);
    }

    @WebMethod(operationName = "consultarSaldo")
    public double consultarSaldo(@WebParam(name = "conta") int conta) {
        return controller.consultarSaldo(conta);
    }

    @WebMethod(operationName = "realizarTransferencia")
    public boolean realizarTransferencia(@WebParam(name = "contaOrigem") int contaOrigem, @WebParam(name = "contaDestino") int contaDestino, @WebParam(name = "valor") double valor) {
        return controller.realizarTransferencia(contaOrigem, contaDestino, valor);
    }

    @WebMethod(operationName = "consultarExtrato")
    public ArrayList<String> consultarExtrato(@WebParam(name = "conta") int conta) {
        return controller.consultarExtrato(conta);
    }

    @WebMethod(operationName = "criarCadastro")
    public boolean criarCadastro(@WebParam(name = "cliente") Cliente cliente) {
        return controller.criarCadastro(cliente);
    }

    @WebMethod(operationName = "criarConta")
    public boolean criarConta(@WebParam(name = "CPF") String CPF, @WebParam(name = "saldo") double saldo) {
        return controller.criarConta(CPF, saldo);
    }

    @WebMethod(operationName = "consultarNumerosContasCliente")
    public ArrayList<Integer> consultarNumerosContasCliente(@WebParam(name = "CPF") String CPF) {
        return controller.consultarNumerosContasCliente(CPF);
    }
}
