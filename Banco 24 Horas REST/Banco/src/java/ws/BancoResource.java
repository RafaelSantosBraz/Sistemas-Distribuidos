/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import classses.Cliente;
import com.google.gson.Gson;
import controller.ControllerBanco;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import transfrede.ContaAux;

/**
 * REST Web Service
 *
 * @author Rafael Braz
 */
@Path("Banco")
public class BancoResource {

    private final ControllerBanco controller;
    private final Gson conversorJson;

    public BancoResource() {
        controller = new ControllerBanco("localhost", "3306", "banco", "mysql", "mysql");
        conversorJson = new Gson();
    }

    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of ws.BancoResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BancoResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    // métodos REST
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarCadastro/{CPF}")
    public String consultarCadastro(@PathParam("CPF") String CPF) {
        return conversorJson.toJson(controller.consultarCadastro(CPF));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("alterarCadastro")
    public String alterarCadastro(String cliente) {
        return conversorJson.toJson(controller.alterarCadastro(conversorJson.fromJson(cliente, Cliente.class)));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("realizarSaque/{conta},{valor}")
    public String realizarSaque(@PathParam("conta") int conta, @PathParam("valor") double valor) {
        return conversorJson.toJson(controller.realizarSaque(conta, valor));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("realizarDeposito/{conta},{valor}")
    public String realizarDeposito(@PathParam("conta") int conta, @PathParam("valor") double valor) {
        return conversorJson.toJson(controller.realizarDeposito(conta, valor));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarSaldo/{conta}")
    public String consultarSaldo(@PathParam("conta") int conta) {
        return conversorJson.toJson(controller.consultarSaldo(conta));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("realizarTransferencia/{contaOrigem},{contaDestino},{valor}")
    public String realizarTransferencia(@PathParam("contaOrigem") int contaOrigem, @PathParam("contaDestino") int contaDestino, @PathParam("valor") double valor) {
        return conversorJson.toJson(controller.realizarTransferencia(contaOrigem, contaDestino, valor));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarExtrato/{conta}")
    public String consultarExtrato(@PathParam("conta") int conta) {
        return conversorJson.toJson(controller.consultarExtrato(conta));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("criarCadastro")
    public String criarCadastro(String cliente) {
        return conversorJson.toJson(controller.criarCadastro(conversorJson.fromJson(cliente, Cliente.class)));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("criarConta")
    public String criarConta(String contaAux) {
        ContaAux conta = conversorJson.fromJson(contaAux, ContaAux.class);
        return conversorJson.toJson(controller.criarConta(conta.getCPF(), conta.getSaldo()));
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarNumerosContasCliente/{CPF}")
    public String consultarNumerosContasCliente(@PathParam("CPF") String CPF) {
        return conversorJson.toJson(controller.consultarNumerosContasCliente(CPF));
    }
}
