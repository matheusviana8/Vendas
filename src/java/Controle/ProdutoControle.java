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
import javax.faces.bean.ViewScoped;

/**
 *
 * @author MATHEUS
 */
@ManagedBean
@ViewScoped
public class ProdutoControle {
    private Produto produto;
    private List<Produto> produtos;
    ProdutoDao dao = new ProdutoDao();
    
    public ProdutoControle(){
        produto = new Produto();
       
    }
    
     public void novo(){
        produto = new Produto();
       
    }
    
    public void salvar(){
        System.out.println("SALVAR");
        dao.salvar(produto);
    }
    
     public void alterar(){
         System.out.println("ALTERAR: "+produto.getId());
        dao.alterar(produto);
    }
     
     public void deletar(){
         System.out.println("DELETAR: "+produto.getId());
        dao.deletar(produto);
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
    
    public boolean isEditando() {
		return this.produto.getId() != null;
	}
    
}
