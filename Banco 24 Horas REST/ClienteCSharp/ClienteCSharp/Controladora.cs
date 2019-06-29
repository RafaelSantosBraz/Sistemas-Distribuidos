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

        public string ConsultarCadastro(string CPF)
        {
            string resposta = GET("http://localhost:8080/Banco/webresources/Banco/consultarCadastro/" + CPF);
            if (resposta == null || resposta == "")
            {
                return "";
            }
            return JsonConvert.DeserializeObject<Cliente>(resposta).nome;
        }

        public bool AlterarCadastro(string CPF, string nome)
        {
            string resposta = POST("http://localhost:8080/Banco/webresources/Banco/alterarCadastro", JsonConvert.SerializeObject(new Cliente(CPF, nome)));
            if (resposta == null || resposta == "")
            {
                return false;
            }
            return Boolean.Parse(resposta);
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
            string resposta = GET("http://localhost:8080/Banco/webresources/Banco/realizarSaque/" + conta + "," + valor);
            if (resposta == null || resposta == "")
            {
                return false;
            }
            return Boolean.Parse(resposta);
        }

        public bool Depositar(int conta, double valor)
        {
            string resposta = GET("http://localhost:8080/Banco/webresources/Banco/realizarDeposito/" + conta + "," + valor);
            if (resposta == null || resposta == "")
            {
                return false;
            }
            return Boolean.Parse(resposta);
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

        public string GET(string url)
        {
            try
            {
                var requisicaoWeb = WebRequest.CreateHttp(url);
                requisicaoWeb.Method = "GET";
                using (var resposta = requisicaoWeb.GetResponse())
                {
                    using (var streamDados = resposta.GetResponseStream())
                    {
                        return new StreamReader(streamDados).ReadToEnd();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
        }

        public string POST(string url, string data)
        {
            try
            {
                var request = WebRequest.CreateHttp(url);
                request.Method = "POST";
                request.ContentType = "application/json";
                request.ContentLength = data.Length;
                using (var webStream = request.GetRequestStream())
                {
                    using (var requestWriter = new StreamWriter(webStream, Encoding.ASCII))
                    {
                        requestWriter.Write(data);
                    }
                }
                using (var webResponse = request.GetResponse())
                {
                    using (var webStream = webResponse.GetResponseStream() ?? Stream.Null)
                    {
                        return new StreamReader(webStream).ReadToEnd();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }
        }
    }
}
