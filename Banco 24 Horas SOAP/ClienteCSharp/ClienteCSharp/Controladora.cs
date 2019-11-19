using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

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
            try
            {
                ws.cliente cliente = banco.consultarCadastro(CPF);
                return cliente == null ? "" : cliente.nome;
            }
            catch(Exception e)
            {
                return "";
            }
        }

        public bool AlterarCadastro(string CPF, string nome)
        {
            try
            {
                return banco.alterarCadastro(new ws.cliente(CPF, nome));
            }
            catch
            {
                return false;
            }
        }

        public List<string> ConsultarExtrato(int conta)
        {
            try
            {
                return banco.consultarExtrato(conta).ToList();
            }
            catch
            {
                return new List<string>();
            }
        }

        public double ConsultarSaldo(int conta)
        {
            try
            {
                return banco.consultarSaldo(conta);
            }
            catch
            {
                return -1;
            }
        }

        public bool Sacar(int conta, double valor)
        {
            try
            {
                return banco.realizarSaque(conta, valor);
            }
            catch
            {
                return false;
            }
        }

        public bool Depositar(int conta, double valor)
        {
            try
            {
                return banco.realizarDeposito(conta, valor);
            }
            catch
            {
                return false;
            }
        }

        public bool TransferirValor(int contaOrigem, int contaDestino, double valor)
        {
            try
            {
                return banco.realizarTransferencia(contaOrigem, contaDestino, valor);
            }
            catch
            {
                return false;
            }
        }

        public List<int> ConsultarNumerosContas(string CPF)
        {
            try
            {
                return banco.consultarNumerosContasCliente(CPF).ToList();
            }
            catch
            {
                return new List<int>();
            }
        }

        public bool CriarCliente(string CPF, string nome)
        {
            try
            {
                return banco.criarCadastro(new ws.cliente(CPF, nome));
            }
            catch
            {
                return false;
            }
        }

        public bool CriarConta(string CPF, double saldo)
        {
            try
            {
                return banco.criarConta(CPF, saldo);
            }
            catch
            {
                return false;
            }
        }
    }
}
