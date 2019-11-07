using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Cliente
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Button1_Click(object sender, EventArgs e)
        {
            ws.WSAndroidClient cliente = new ws.WSAndroidClient();
            ws.livro livro = cliente.retornaLivro();
            MessageBox.Show(livro.nome);
        }
    }
}
