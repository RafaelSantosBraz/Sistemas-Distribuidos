// Variáveis do Node.js e SOAP
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var soap = require('soap');
var url = 'http://localhost:8080/BancoWS/BancoWS?WSDL';

// Create application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false })


// Métodos do servidor JS (Node.JS) para o Cliente JS (HTML + JavaScript)
app.post('/consultar_cadastro', urlencodedParser, function (req, res) {
    //console.log(req);    
    response = {
        CPF: req.body.CPF,
        nome: "Rafael"
    };     
    consultarCadastro({ CPF: "47192962899" }, res);
})

// Inicialização do servidor JS
var server = app.listen(7000, function () {
    var host = "localhost";
    var port = 7000;
    console.log("Example app listening at http://%s:%s", host, port);
})

// Variáveis HTML 
var htmlCabecalho = '<!DOCTYPE html> <html> <head> <title>Cliente JS</title> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> </head> <body>';
var htmlFim = '</body></html >';
var htmlCorpo = '';

// Métodos para requisições SOAP com o Banco
function consultarCadastro(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.consultarCadastro(argsRequisicao, function (err, result) {
            console.log(result);
            htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>consultar</button> </form>';
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}
