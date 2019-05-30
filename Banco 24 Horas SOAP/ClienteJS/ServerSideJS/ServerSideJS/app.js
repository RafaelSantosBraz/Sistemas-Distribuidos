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
    argsRequisicao = {
        CPF: req.body.CPF       
    };
    consultarCadastro(argsRequisicao, res);
})

app.post('/alterar_cadastro', urlencodedParser, function (req, res) {
    argsRequisicao = {
        cliente: {
            CPF: req.body.CPF,
            nome: req.body.nome
        }
    };
    console.log(argsRequisicao);
    alterarCadastro(argsRequisicao, res);
})

app.post('/realizar_saque', urlencodedParser, function (req, res) {
    argsRequisicao = {
        conta: req.body.conta,
        valor: req.body.valor
    };
    realizarSaque(argsRequisicao, res);
})

app.post('/realizar_deposito', urlencodedParser, function (req, res) {
    argsRequisicao = {
        conta: req.body.conta,
        valor: req.body.valor
    };
    realizarDeposito(argsRequisicao, res);
})

app.post('/consultar_saldo', urlencodedParser, function (req, res) {
    argsRequisicao = {
        conta: req.body.conta
    };
    consultarSaldo(argsRequisicao, res);
})

app.post('/realizar_transferencia', urlencodedParser, function (req, res) {
    argsRequisicao = {
        contaOrigem: req.body.contaOrigem,
        contaDestino: req.body.contaDestino,
        valor: req.body.valor
    };
    realizarTransferencia(argsRequisicao, res);
})

app.post('/consultar_Extrato', urlencodedParser, function (req, res) {
    argsRequisicao = {
        conta: req.body.conta
    };
    consultarExtrato(argsRequisicao, res);
})

app.post('/criar_cadastro', urlencodedParser, function (req, res) {
    argsRequisicao = {
        cliente: {
            CPF: req.body.CPF,
            nome: req.body.nome
        }
    };
    console.log(argsRequisicao);
    criarCadastro(argsRequisicao, res);
})

app.post('/criar_conta', urlencodedParser, function (req, res) {
    argsRequisicao = {
        CPF: req.body.CPF,
        saldo: req.body.saldo
    };
    console.log(argsRequisicao);
    criarConta(argsRequisicao, res);
})

app.post('/consultar_numeros_contas_cliente', urlencodedParser, function (req, res) {
    argsRequisicao = {
        CPF: req.body.CPF
    };
    console.log(argsRequisicao);
    consultarNumerosContasCliente(argsRequisicao, res);
})

// Métodos Get para requisições de páginas no navegador
app.get('/', urlencodedParser, function (req, res) {
    res.end(htmlCabecalho + htmlFim);
})

app.get('/consultar_cadastro', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>consultar</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

app.get('/alterar_cadastro', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/alterar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>alterar</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

app.get('/realizar_saque', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_saque" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>realizar saque</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

app.get('/realizar_deposito', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_deposito" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>realizar depósito</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

app.get('/consultar_saldo', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_saldo" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>consultar saldo</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

app.get('/realizar_transferencia', urlencodedParser, function (req, res) {
    htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_saldo" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>consultar saldo</button> </form>';
    res.end(htmlCabecalho + htmlCorpo + htmlFim);
})

// Inicialização do servidor JS
var server = app.listen(7000, function () {
    var host = "localhost";
    var port = 7000;
    console.log("Example app listening at http://%s:%s", host, port);
})

// Variáveis HTML 
var htmlCabecalho = '<!DOCTYPE html> <html> <head> <title>Cliente JS</title> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> </head> <body>' + '<div> Navegação:<br> <a href="http://localhost:7000/consultar_cadastro">Consultar Cadastro</a><br> <a href="http://localhost:7000/alterar_cadastro">Alterar Cadastro</a><br> <a href="http://localhost:7000/realizar_saque">Realizar Saque</a><br> <a href="http://localhost:7000/realizar_deposito">Realizar Depósito</a><br> <a href="http://localhost:7000/consultar_saldo">Consultar Saldo</a><br> <a href="http://localhost:7000/realizar_transferencia">realizar Transferência</a><br> <a href="http://localhost:7000/consultar_extrato">Consultar Extrato</a><br> <a href="http://localhost:7000/criar_cadastro">Criar Cadastro</a><br> <a href="http://localhost:7000/criar_conta">Criar Conta</a><br> <a href="http://localhost:7000/consultar_numeros_contas_cliente">Consultar Números das Contas do Cliente</a><br><br> </div>';
var htmlFim = '';
var htmlCorpo = '';

function mudarStatus(respStatus) {
    htmlFim = '</body><script>alert("' + respStatus + '");</script></html>';
}

// Métodos para requisições SOAP com o Banco
function consultarCadastro(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.consultarCadastro(argsRequisicao, function (err, result) {            
            console.log(result);
            if (result === null) {
                mudarStatus("Falha!");                
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>consultar</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF" value = "' + result.return.CPF + '"> <br> Nome: <input type = "text" name = "nome" value = "' + result.return.nome + '"> <button>consultar</button> </form>';
            }            
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function alterarCadastro(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.alterarCadastro(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/alterar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>alterar</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/alterar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF" value = "' + argsRequisicao.cliente.CPF + '"> <br> Nome: <input type = "text" name = "nome" value = "' + argsRequisicao.cliente.nome + '"> <button>alterar</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function realizarSaque(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.realizarSaque(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_saque" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>realizar saque</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_saque" method="POST"> Conta: <input type = "text" name = "conta" value = "' + argsRequisicao.conta + '"> <br> Valor: <input type = "text" name = "valor" value = "' + argsRequisicao.valor + '"> <button>realizar saque</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function realizarDeposito(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.realizarDeposito(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_deposito" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>realizar depósito</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_deposito" method="POST"> Conta: <input type = "text" name = "conta" value = "' + argsRequisicao.conta + '"> <br> Valor: <input type = "text" name = "valor" value = "' + argsRequisicao.valor + '"> <button>realizar depósito</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function consultarSaldo(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.consultarSaldo(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_saldo" method="POST"> Conta: <input type = "text" name = "conta"> <br> Valor: <input type = "text" name = "valor"> <button>consultar saldo</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_saldo" method="POST"> Conta: <input type = "text" name = "conta" value = "' + argsRequisicao.conta + '"> <br> Valor: <input type = "text" name = "valor" value = "' + result.return + '"> <button>consultar saldo</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function realizarTransferencia(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.realizarTransferencia(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_transferencia" method="POST"> Conta de Origem: <input type = "text" name = "contaOrigem"> <br> Conta de Destino: <input type = "text" name = "contaDestino"> <br>Valor: <input type = "text" name = "valor"> <br><button>realizar transferência</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/realizar_transferencia" method="POST"> Conta de Origem: <input type = "text" name = "contaOrigem" value = "' + argsRequisicao.contaOrigem + '"> <br> Conta de Destino: <input type = "text" name = "contaDestino" value = "' + argsRequisicao.contaDestino + '"> <br>Valor: <input type = "text" name = "valor" value = "' + argsRequisicao.valor + '"> <br><button>realizar transferência</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function consultarExtrato(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.consultarExtrato(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_extrato" method="POST"> Conta: <input type = "text" name = "conta"> <br> <div> extrato </div> <button>consultar extrato</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_extrato" method="POST"> Conta: <input type = "text" name = "conta"> <br> <div> ' + result.return.join("<br>") + ' </div> <button>consultar extrato</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function criarCadastro(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.criarCadastro(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/criar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Nome: <input type = "text" name = "nome"> <button>criar cadastro</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/criar_cadastro" method="POST"> CPF: <input type = "text" name = "CPF" value = "' + argsRequisicao.cliente.CPF + '"> <br> Nome: <input type = "text" name = "nome" value = "' + argsRequisicao.cliente.nome + '"> <button>criar cadastro</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function criarConta(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.criarConta(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null || result === false) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/criar_conta" method="POST"> CPF: <input type = "text" name = "CPF"> <br> Saldo: <input type = "text" name = "saldo"> <br><button>criar conta</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/criar_conta" method="POST"> CPF: <input type = "text" name = "CPF" value = "' + argsRequisicao.CPF + '"> <br> Saldo: <input type = "text" name = "saldo" value = "' + argsRequisicao.saldo + '"> <br><button>criar conta</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}

function consultarNumerosContasCliente(argsRequisicao, response) {
    soap.createClient(url, function (err, client) {
        client.consultarNumerosContasCliente(argsRequisicao, function (err, result) {
            console.log(result);
            if (result === null) {
                mudarStatus("Falha!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_numeros_contas_cliente" method="POST"> CPF: <input type = "text" name = "CPF"> <br> <div> contas </div> <button>consultar contas</button> </form>';
            } else {
                mudarStatus("Sucesso!");
                htmlCorpo = '<form id="formulario" action="http://localhost:7000/consultar_numeros_contas_cliente" method="POST"> CPF: <input type = "text" name = "CPF"> <br> <div> ' + result.return.join(", ") + ' </div> <button>consultar contas</button> </form>';
            }
            response.end(htmlCabecalho + htmlCorpo + htmlFim);
        });
    });
}