using ClienteSdayle.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteSdayle
{
    public partial class Form1 : Form
    {
        List<Produtos> listaprodutos;
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string url = "https://localhost:44327/api/produto/ConsultarProdutosPorNome/" + textBox1.Text;
            var requisicaoWeb = WebRequest.CreateHttp(url);
            requisicaoWeb.Method = "GET";
            
            using (var resposta = requisicaoWeb.GetResponse())
            {
                var streamDados = resposta.GetResponseStream();
                StreamReader reader = new StreamReader(streamDados);
                object objResponse = reader.ReadToEnd();
                Console.WriteLine(objResponse.ToString());
                MessageBox.Show(objResponse.ToString());
                Console.ReadLine();
                streamDados.Close();
                resposta.Close();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Produtos p = new Produtos();
            p.Codigo = Convert.ToInt32(textBox2.Text);
            p.Nome = textBox3.Text;
            p.Estoque =Convert.ToInt32(textBox4.Text);
            p.Preco = Convert.ToDouble(textBox5.Text);

            string DATA = JsonConvert.SerializeObject(p);

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("https://localhost:44327/api/produto");
            request.Method = "POST";
            request.ContentType = "application/json";
            request.ContentLength = DATA.Length;
            using (Stream webStream = request.GetRequestStream())
            using (StreamWriter requestWriter = new StreamWriter(webStream, System.Text.Encoding.ASCII))
            {
                requestWriter.Write(DATA);
            }

            try
            {
                WebResponse webResponse = request.GetResponse();
                using (Stream webStream = webResponse.GetResponseStream() ?? Stream.Null)
                using (StreamReader responseReader = new StreamReader(webStream))
                {
                    string response = responseReader.ReadToEnd();
                    MessageBox.Show(response);
                }
            }
            catch (Exception erro)
            {
                MessageBox.Show(erro.Message);
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Produtos p = new Produtos();
            p.Codigo = Convert.ToInt32(textBox2.Text);
            p.Nome = textBox3.Text;
            p.Estoque = Convert.ToInt32(textBox4.Text);
            p.Preco = Convert.ToDouble(textBox5.Text);

            string DATA = JsonConvert.SerializeObject(p);

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("https://localhost:44327/api/produto");
            request.Method = "PUT";
            request.ContentType = "application/json";
            request.ContentLength = DATA.Length;
            using (Stream webStream = request.GetRequestStream())
            using (StreamWriter requestWriter = new StreamWriter(webStream, System.Text.Encoding.ASCII))
            {
                requestWriter.Write(DATA);
            }

            try
            {
                WebResponse webResponse = request.GetResponse();
                using (Stream webStream = webResponse.GetResponseStream() ?? Stream.Null)
                using (StreamReader responseReader = new StreamReader(webStream))
                {
                    string response = responseReader.ReadToEnd();
                    MessageBox.Show(response);
                }
            }
            catch (Exception erro)
            {
                MessageBox.Show(erro.Message);
            }

        }

        private void button4_Click(object sender, EventArgs e)
        {
            string url = "https://localhost:44327/api/produto/ConsultarProdutos/";
            var requisicaoWeb = WebRequest.CreateHttp(url);
            requisicaoWeb.Method = "GET";

                         
            using (var resposta = requisicaoWeb.GetResponse())
            {
                var streamDados = resposta.GetResponseStream();
                StreamReader reader = new StreamReader(streamDados);
                object objResponse = reader.ReadToEnd();
                listaprodutos = JsonConvert.DeserializeObject<List<Produtos>>(objResponse.ToString());
                Console.WriteLine(objResponse.ToString());
                Console.ReadLine();
                streamDados.Close();
                resposta.Close();
            }
            dataGridView1.DataSource = listaprodutos;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            string url = "https://localhost:44327/api/produto/ExcluirProduto/" + textBox6.Text;
            var requisicaoWeb = WebRequest.CreateHttp(url);
            requisicaoWeb.Method = "DELETE";

            using (var resposta = requisicaoWeb.GetResponse())
            {
                var streamDados = resposta.GetResponseStream();
                StreamReader reader = new StreamReader(streamDados);
                object objResponse = reader.ReadToEnd();
                Console.WriteLine(objResponse.ToString());
                MessageBox.Show(objResponse.ToString());
                Console.ReadLine();
                streamDados.Close();
                resposta.Close();
            }

        }
    }
}
