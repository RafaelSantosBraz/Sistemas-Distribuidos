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
    public partial class ConsultarExtrato : Form
    {
        public ConsultarExtrato()
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
            listBox1.Items.Clear();
            foreach (string i in Controladora.Instancia.ConsultarExtrato(int.Parse(textBox2.Text)))
            {
                listBox1.Items.Add(i + " ,");
            }
        }

        private void ConsultarExtrato_Shown(object sender, EventArgs e)
        {
            textBox2.Focus();
        }

        private void TextBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void ConsultarExtrato_FormClosing(object sender, FormClosingEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
