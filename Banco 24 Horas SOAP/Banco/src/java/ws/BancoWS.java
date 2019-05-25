package ws;

import classses.*;
import controller.*;
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

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "consultarCadastro")
    public Cliente consultarCadastro(@WebParam(name = "CPF") String CPF) {
        return controller.consultarCadastro(CPF);
    }

    @WebMethod(operationName = "alterarCadastro")
    public boolean alterarCadastro(@WebParam(name = "cliente") Cliente cliente) {
        return controller.alterarCadastro(cliente);
    }
}
