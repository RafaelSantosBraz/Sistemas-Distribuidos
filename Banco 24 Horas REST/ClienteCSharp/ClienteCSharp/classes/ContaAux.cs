using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteCSharp.classes
{
    class ContaAux
    {
        public string CPF { get; set; }
        public double saldo { get; set; }

        public ContaAux(string cPF, double saldo)
        {
            CPF = cPF;
            this.saldo = saldo;
        }

        public ContaAux()
        {
            CPF = "";
            saldo = 0.0;
        }
    }
}
