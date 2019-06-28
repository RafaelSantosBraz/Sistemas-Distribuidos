/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function consultarCadastro(cpf) {
    $.get("http://localhost:8080/Banco/webresources/Banco/consultarCadastro/" + cpf, function (data, status) {
        console.log(data);        
        if (data === null){
            $("#resultado").html("Erro!");
        }else{
            $("#resultado").html("CPF: " + cpf + "<br>Nome: " + data.nome);
        }
    });
}
