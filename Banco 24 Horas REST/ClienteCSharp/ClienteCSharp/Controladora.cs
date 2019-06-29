using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteCSharp.classes;
using Newtonsoft.Json;

namespace ClienteCSharp
{
    public class Controladora
    {

        public static Controladora Instancia { get; } = new Controladora();

        private Controladora()
        {
        }

        public string ConsultarCadastro(string CPF)
        {
            try
            {
                var requisicaoWeb = WebRequest.CreateHttp("http://localhost:8080/Banco/webresources/Banco/consultarCadastro/" + CPF);
                requisicaoWeb.Method = "GET";
                using (var resposta = requisicaoWeb.GetResponse())
                {
                    using (var streamDados = resposta.GetResponseStream())
                    {
                        string objResponse = new StreamReader(streamDados).ReadToEnd();
                        if (objResponse == null || objResponse == "")
                        {
                            return "";
                        }
                        return JsonConvert.DeserializeObject<Cliente>(objResponse).nome;
                    }
                }
            }
            catch
            {
                return "";
            }
        }

        public bool AlterarCadastro(string CPF, string nome)
        {
            try
            {
                string cliente = JsonConvert.SerializeObject(new Cliente(CPF, nome));
                var request = WebRequest.CreateHttp("http://localhost:8080/Banco/webresources/Banco/alterarCadastro");
                request.Method = "POST";
                request.ContentType = "application/json";
                request.ContentLength = cliente.Length;
                using (var webStream = request.GetRequestStream())
                {
                    using (var requestWriter = new StreamWriter(webStream, Encoding.ASCII))
                    {
                        requestWriter.Write(cliente);
                    }
                }
                using (var webResponse = request.GetResponse())
                {
                    using (var webStream = webResponse.GetResponseStream() ?? Stream.Null)
                    {
                        string objResponse = new StreamReader(webStream).ReadToEnd();                        
                        if (objResponse == null || objResponse == "")
                        {
                            return false;
                        }
                        return Boolean.Parse(objResponse);
                    }
                }
            }
            catch
            {
                return false;
            }
        }

        public List<string> ConsultarExtrato(int conta)
        {
            return null;
        }

        public double ConsultarSaldo(int conta)
        {
            return -1;
        }

        public bool Sacar(int conta, double valor)
        {
            return true;
        }

        public bool Depositar(int conta, double valor)
        {
            return true;
        }

        public bool TransferirValor(int contaOrigem, int contaDestino, double valor)
        {
            return true;
        }

        public List<int> ConsultarNumerosContas(string CPF)
        {
            return null;
        }

        public bool CriarCliente(string CPF, string nome)
        {
            return true;
        }

        public bool CriarConta(string CPF, double saldo)
        {
            return true;
        }
    }
}
