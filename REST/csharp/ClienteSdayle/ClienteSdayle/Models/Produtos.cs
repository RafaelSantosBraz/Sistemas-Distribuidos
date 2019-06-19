using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteSdayle.Models
{
    public class Produtos
    {
        private int codigo;
        private String nome;
        private int estoque;
        private double preco;

        public int Codigo { get => codigo; set => codigo = value; }
        public string Nome { get => nome; set => nome = value; }
        public int Estoque { get => estoque; set => estoque = value; }
        public double Preco { get => preco; set => preco = value; }
    }
}
