using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    [Route("api/produto")]
    [ApiController]
    public class ProdutosController : ControllerBase
    {
        private static List<Produtos> listaProdutos = new List<Produtos>();

        [AcceptVerbs("POST")]
        //[Route("CadastrarProduto")]
        public string CadastrarProduto(Produtos p)
        {
            listaProdutos.Add(p);

            return "Produto cadastrado com sucesso!";
        }

        [AcceptVerbs("PUT")]
        //[Route("AlterarProduto")]
        public string AlterarProduto(Produtos p)
        {
            listaProdutos.Where(n => n.Codigo == p.Codigo)
                         .Select(s =>
                         {
                             s.Codigo = p.Codigo;
                             s.Nome = p.Nome;
                             s.Estoque = p.Estoque;
                             s.Preco = p.Preco;

                             return s;

                         }).ToList();



            return "Produto alterado com sucesso!";
        }


        [AcceptVerbs("DELETE")]
        [Route("ExcluirProduto/{codigo}")]
        public string ExcluirProduto(int codigo)
        {

            if (listaProdutos.Count == 0)
            {
                return "Lista vazia";
            }
            else
            {
                Produtos p = listaProdutos.Where(n => n.Codigo == codigo)
                                                    .Select(n => n)
                                                    .First();
                if (p == null)
                {
                    return "Registro não encontrado!";
                }
                else
                {
                    listaProdutos.Remove(p);

                    return "Registro excluido com sucesso!";
                }
            }
        }

        [AcceptVerbs("GET")]
        [Route("ConsultarProdutosPorCodigo/{codigo}")]
        public Produtos ConsultarProdutosPorCodigo(int codigo)
        {

            Produtos p = listaProdutos.Where(n => n.Codigo == codigo)
                                                .Select(n => n)
                                                .FirstOrDefault();

            return p;
        }

        [AcceptVerbs("GET")]
        [Route("ConsultarProdutosPorNome/{nome}")]
        public Produtos ConsultarProdutosPorNome(String nome)
        {

            Produtos p = listaProdutos.Where(n => n.Nome == nome)
                                                .Select(n => n)
                                                .FirstOrDefault();

            return p;
        }



        [AcceptVerbs("GET")]
        [Route("ConsultarProdutos")]
        public List<Produtos> ConsultaProdutos()
        {
            return listaProdutos;
        }
    }

}