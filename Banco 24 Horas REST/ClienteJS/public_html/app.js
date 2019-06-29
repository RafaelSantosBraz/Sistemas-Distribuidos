/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.postJSON = function (url, data, callback) {
    return jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};

$.putJSON = function (url, data, callback) {
    return jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'PUT',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};

function consultarCadastro() {
    var cpf = $('#cpf').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/consultarCadastro/" + cpf, function (data, status) {
        console.log(data);
        if (data === null) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("CPF: " + cpf + "<br>Nome: " + data.nome);
        }
    });
}

function alterarCadastro() {
    var cliente = {
        CPF: $("#cpf").val(),
        nome: $("#nome").val()
    };
    console.log(cliente);
    $.postJSON("http://localhost:8080/Banco/webresources/Banco/alterarCadastro", cliente, function (data, status) {
        console.log(data);
        if (data === null || data === false) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Cadastro Alterado!");
        }
    });
}

function realizarSaque() {
    var conta = $('#conta').val();
    var valor = $('#valor').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/realizarSaque/" + conta + "," + valor, function (data, status) {
        console.log(data);
        if (data === null || data === false) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Saque Realizado!");
        }
    });
}

function realizarDeposito() {
    var conta = $('#conta').val();
    var valor = $('#valor').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/realizarDeposito/" + conta + "," + valor, function (data, status) {
        console.log(data);
        if (data === null || data === false) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Depósito Realizado!");
        }
    });
}

function consultarSaldo() {
    var conta = $('#conta').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/consultarSaldo/" + conta, function (data, status) {
        console.log(data);
        if (data === null) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Saldo: R$ " + data);
        }
    });
}

function realizarTransferencia() {
    var contaOrigem = $('#contaOrigem').val();
    var contaDestino = $('#contaDestino').val();
    var valor = $('#valor').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/realizarTransferencia/" + contaOrigem + "," + contaDestino + "," + valor, function (data, status) {
        console.log(data);
        if (data === null) {
            $("#resultado").html("Erro!");
        } else if (data === false) {
            $("#resultado").html("Transferência Negada!");
        } else {
            $("#resultado").html("Transferência Realizada!");
        }
    });
}

function consultarExtrato() {
    var conta = $('#conta').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/consultarExtrato/" + conta, function (data, status) {
        console.log(data);
        if (data === null) {
            $("#resultado").html("Erro!");
        } else if (data.length === 0) {
            $("#resultado").html("Nenhuma Movimentação!");
        } else {
            $("#resultado").html("");
            for (var i = 0; i < data.length; i++) {
                //console.log(data[i]);
                $("#resultado").append(data[i] + "<br>");
            }
        }
    });
}

function criarCadastro() {
    var cliente = {
        CPF: $("#cpf").val(),
        nome: $("#nome").val()
    };
    console.log(cliente);
    $.putJSON("http://localhost:8080/Banco/webresources/Banco/criarCadastro", cliente, function (data, status) {
        console.log(data);
        if (data === null || data === false) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Cadastro Criado!");
        }
    });
}

function criarConta() {
    var conta = {
        CPF: $("#cpf").val(),
        saldo: $("#saldo").val()
    };
    console.log(conta);
    $.putJSON("http://localhost:8080/Banco/webresources/Banco/criarConta", conta, function (data, status) {
        console.log(data);
        if (data === null || data === false) {
            $("#resultado").html("Erro!");
        } else {
            $("#resultado").html("Conta Criada!");
        }
    });
}

function consultarNumerosContasCliente() {
    var cpf = $('#cpf').val();
    $.get("http://localhost:8080/Banco/webresources/Banco/consultarNumerosContasCliente/" + cpf, function (data, status) {
        console.log(data);
        if (data === null) {
            $("#resultado").html("Erro!");
        } else if (data.length === 0) {
            $("#resultado").html("Nenhuma Conta!");
        } else {
            $("#resultado").html("");
            for (var i = 0; i < data.length; i++) {
                //console.log(data[i]);
                $("#resultado").append(data[i] + "<br>");
            }
        }
    });
}