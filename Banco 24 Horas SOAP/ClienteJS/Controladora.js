// controladora do Cliente SOAP

var soap = require('soap');
var url = 'http://localhost:8080/BancoWS/BancoWS?WSDL';
//var args = {CPF: '47192962899'};

function consultarCadastro(argsRequisicao) {
    soap.createClient(url, function (err, client) {
        client.consultarCadastro(argsRequisicao, function (err, result) {
            return result === null ? "" : result.nome;
        });
    });
}
