/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ProdutoDao;
import Modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MATHEUS
 */
@ManagedBean
@RequestScoped
public class ProdutoControle {
    private Produto produto;
    private List<Produto> produtos;
    ProdutoDao dao = new ProdutoDao();
    
    public ProdutoControle(){
        produto = new Produto();
       
    }
    
    public void salvar(){
        dao.salvar(produto);
    }
     public List<Produto> listar() {
        return dao.listar();
    }
     public List<Produto> complete(String nome) {
        List<Produto> queryResult = new ArrayList<Produto>();

        if (produtos == null) {

            produtos = dao.listar();
        }

        for (Produto produto : produtos) {
            if (produto.getDescricao().toLowerCase().contains(nome.toLowerCase())) {
                queryResult.add(produto);
            }
        }

        return queryResult;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    
}
