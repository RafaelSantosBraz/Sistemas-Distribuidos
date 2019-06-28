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
