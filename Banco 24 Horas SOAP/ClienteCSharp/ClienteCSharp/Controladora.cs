using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteCSharp
{
    public class Controladora
    {

        private readonly ws.BancoWSClient banco;

        public static Controladora Instancia { get; } = new Controladora();

        private Controladora()
        {
            banco = new ws.BancoWSClient();
        }

        public string ConsultarCadastro(string CPF)
        {
            var cliente = banco.consultarCadastro(CPF);
            return cliente == null ? "" : cliente.nome;
        }

        public bool AlterarCadastro(string CPF, string nome) => banco.alterarCadastro(new ws.cliente(CPF, nome));

        public List<string> ConsultarExtrato(int conta) => banco.consultarExtrato(conta).ToList();

        public double ConsultarSaldo(int conta) => banco.consultarSaldo(conta);

        public bool Sacar(int conta, double valor) => banco.realizarSaque(conta, valor);

        public bool Depositar(int conta, double valor) => banco.realizarDeposito(conta, valor);

        public bool TransferirValor(int contaOrigem, int contaDestino, double valor) => banco.realizarTransferencia(contaOrigem, contaDestino, valor);

        public List<int> ConsultarNumerosContas(string CPF) => banco.consultarNumerosContasCliente(CPF).ToList();

        public bool CriarCliente(string CPF, string nome) => banco.criarCadastro(new ws.cliente(CPF, nome));

        public bool CriarConta(string CPF, double saldo) => banco.criarConta(CPF, saldo);
    }
}
