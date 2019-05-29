// controladora do Cliente SOAP

var soap = require('soap');
var url = 'http://localhost:8080/BancoWS/BancoWS?WSDL';

function consultar(argsRequisicao) {
   soap.createClient(url, function (err, client) {
       client.consultarCadastro(argsRequisicao, function (err, result) {
            manipularRetorno(result);
        });
    });
}

function manipularRetorno(result){
    console.log(result.return.nome);
}

var args = {CPF: "47192962899"};

consultar(args);
