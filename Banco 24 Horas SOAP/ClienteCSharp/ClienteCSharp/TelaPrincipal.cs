using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteCSharp
{
    public partial class TelaPrincipal : Form
    {
        public TelaPrincipal()
        {
            InitializeComponent();
        }

        private void Button1_Click(object sender, EventArgs e) //Consultar Cadastro
        {
            this.Hide();
            Form form = new ConsultarAlterarCadastro();
            form.Show();      
        }

        private void Button7_Click(object sender, EventArgs e) //Alterar Cadastro
        {
            this.Hide();
            Form form = new ConsultarAlterarCadastro();
            form.Show();
        }

        private void Button6_Click(object sender, EventArgs e) //Realizar Saque
        {
            this.Hide();
            Form form = new SacarDepositar();
            form.Show();
        }

        private void Button5_Click(object sender, EventArgs e) //Realizar Depósito
        {
            this.Hide();
            Form form = new SacarDepositar();
            form.Show();
        }

        private void Button4_Click(object sender, EventArgs e) //Consultar Saldo
        {
            this.Hide();
            Form form = new ConsultarSaldo();
            form.Show();
        }

        private void Button3_Click(object sender, EventArgs e) //Transferir Valor
        {
            this.Hide();
            Form form = new TransferirValor();
            form.Show();
        }

        private void Button10_Click(object sender, EventArgs e) //Consultar Extrato
        {
            this.Hide();
            Form form = new ConsultarExtrato();
            form.Show();
        }

        private void Button9_Click(object sender, EventArgs e) //Criar Cliente
        {
            this.Hide();
            Form form = new CriarCliente();
            form.Show();
        }

        private void Button8_Click(object sender, EventArgs e) //Criar Conta
        {
            this.Hide();
            Form form = new CriarConta();
            form.Show();
        }

        private void TelaPrincipal_Load(object sender, EventArgs e)
        {

        }

        private void TelaPrincipal_FormClosing(object sender, FormClosingEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
