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
    public partial class ConsultarAlterarCadastro : Form
    {
        public ConsultarAlterarCadastro()
        {
            InitializeComponent();
        }

        private void Button1_Click(object sender, EventArgs e)
        {
            textBox3.Text = Controladora.Instancia.ConsultarCadastro(textBox1.Text);
            textBox2.Clear();
            foreach (int i in Controladora.Instancia.ConsultarNumerosContas(textBox1.Text))
            {
                textBox2.Text += i.ToString() + " ,";
            }
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form form = new TelaPrincipal();
            form.Show();
        }

        private void Label1_Click(object sender, EventArgs e)
        {

        }

        private void Label3_Click(object sender, EventArgs e)
        {

        }

        private void ConsultarAlterarCadastro_Load(object sender, EventArgs e)
        {
        }

        private void ConsultarAlterarCadastro_Shown(object sender, EventArgs e)
        {
            textBox1.Focus();
        }

        private void text(object sender, EventArgs e)
        {

        }

        private void Button7_Click(object sender, EventArgs e)
        {
            if (Controladora.Instancia.AlterarCadastro(textBox1.Text, textBox3.Text))
            {
                MessageBox.Show("Alterado com Sucesso", "Operação de Alteração de Cadastro", MessageBoxButtons.OK);
            }
            else
            {
                MessageBox.Show("Erro na Alteração", "Operação de Alteração de Cadastro", MessageBoxButtons.OK);
            }
        }

        private void ConsultarAlterarCadastro_FormClosing(object sender, FormClosingEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
