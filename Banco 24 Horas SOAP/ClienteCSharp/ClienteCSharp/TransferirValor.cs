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
    public partial class TransferirValor : Form
    {
        public TransferirValor()
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
            if (Controladora.Instancia.TransferirValor(int.Parse(textBox1.Text), int.Parse(textBox3.Text), double.Parse(textBox2.Text)))
            {
                MessageBox.Show("Transferência realizada com Sucesso", "Operação de Tranferência", MessageBoxButtons.OK);
            }
            else
            {
                MessageBox.Show("Erro na Transferência", "Operação de Tranferência", MessageBoxButtons.OK);
            }
        }

        private void TransferirValor_Shown(object sender, EventArgs e)
        {
            textBox1.Focus();
        }
    }
}
