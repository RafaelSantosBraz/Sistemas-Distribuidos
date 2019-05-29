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
    public partial class SacarDepositar : Form
    {
        public SacarDepositar()
        {
            InitializeComponent();
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form form = new TelaPrincipal();
            form.Show();
        }

        private void Button7_Click(object sender, EventArgs e)
        {            
            if (Controladora.Instancia.Sacar(int.Parse(textBox1.Text), double.Parse(textBox3.Text)))
            {
                MessageBox.Show("Saque realizado com Sucesso", "Operação de Saque", MessageBoxButtons.OK);
            }
            else
            {
                MessageBox.Show("Erro no Saque", "Operação de Saque", MessageBoxButtons.OK);
            }
        }

        private void SacarDepositar_Shown(object sender, EventArgs e)
        {
            textBox1.Focus();
        }
    }
}
