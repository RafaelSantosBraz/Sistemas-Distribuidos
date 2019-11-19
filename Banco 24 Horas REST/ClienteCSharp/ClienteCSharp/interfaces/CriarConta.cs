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
    public partial class CriarConta : Form
    {
        public CriarConta()
        {
            InitializeComponent();
        }

        private void CriarConta_Load(object sender, EventArgs e)
        {

        }

        private void Button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form form = new TelaPrincipal();
            form.Show();
        }

        private void TextBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void Button7_Click(object sender, EventArgs e)
        {            
            if (Controladora.Instancia.CriarConta(textBox3.Text, double.Parse(textBox2.Text)))
            {
                MessageBox.Show("Cadastrado com Sucesso", "Operação de Cadastro", MessageBoxButtons.OK);
            }
            else
            {
                MessageBox.Show("Erro no Cadasto", "Operação de Cadastro", MessageBoxButtons.OK);
            }
        }

        private void CriarConta_Shown(object sender, EventArgs e)
        {
            textBox3.Focus();
        }

        private void CriarConta_FormClosing(object sender, FormClosingEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
