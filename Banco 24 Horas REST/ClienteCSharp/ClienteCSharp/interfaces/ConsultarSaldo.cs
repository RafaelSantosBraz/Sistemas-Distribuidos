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
    public partial class ConsultarSaldo : Form
    {
        public ConsultarSaldo()
        {
            InitializeComponent();
        }

        private void Label3_Click(object sender, EventArgs e)
        {

        }

        private void Button7_Click(object sender, EventArgs e)
        {
            textBox2.Text = Controladora.Instancia.ConsultarSaldo(int.Parse(textBox3.Text)).ToString();
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form form = new TelaPrincipal();
            form.Show();
        }

        private void ConsultarSaldo_Shown(object sender, EventArgs e)
        {
            textBox3.Focus();
        }

        private void ConsultarSaldo_FormClosing(object sender, FormClosingEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
