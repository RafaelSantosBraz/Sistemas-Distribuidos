using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteCSharp.classes
{
    class Cliente
    {
        public string CPF { get; set; }
        public string nome { get; set; }

        public Cliente()
        {
            CPF = "";
            nome = "";
        }

        public Cliente(string CPF, string nome)
        {
            this.CPF = CPF;
            this.nome = nome;
        }
    }
}
