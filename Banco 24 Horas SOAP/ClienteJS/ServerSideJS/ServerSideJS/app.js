var express = require('express');
var app = express();
var bodyParser = require('body-parser');

// Create application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false })


// Métodos do servidor JS (Node.JS) para o Cliente JS (HTML + JavaScript)
app.post('/consultar_cadastro', urlencodedParser, function (req, res) {
    console.log(req);
    // Prepare output in JSON format    
    response = {
        CPF: req.body.CPF,
        nome: "Rafael"
    }; 
    var htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>consultar</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
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

// Métodos para requisições SOAP com o Banco

