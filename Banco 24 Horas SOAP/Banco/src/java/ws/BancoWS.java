/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controller.ControllerBanco;
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
}
