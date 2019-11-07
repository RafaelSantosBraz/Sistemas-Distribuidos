/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import classes.Livro;
import java.util.ArrayList;
import java.util.Arrays;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author cc47192962899
 */
@WebService(serviceName = "WSAndroid")
@Stateless()
public class WSAndroid {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "soma")
    public double soma(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        return num1 + num2;
    }

    @WebMethod(operationName = "retornaLivro")
    public Livro retornaLivro() {
        return new Livro("Sistemas Distribuidos", 2018, "UENP");
    }

    @WebMethod(operationName = "retornaLista")
    public ArrayList<Livro> retornaLista() {
        return new ArrayList<>(
                Arrays.asList(
                        new Livro("Sistemas Distribuidos", 2018, "UENP"),
                        new Livro("Gerencia de Projetos", 2019, "UENP-CLM")
                )
        );
    }
}
