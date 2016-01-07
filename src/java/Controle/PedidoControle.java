/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PedidoDao;
import Dao.ProdutoDao;
import Modelo.Cliente;
import Modelo.DetalhePedido;
import Modelo.Pedido;
import Modelo.Produto;
import Util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author MATHEUS
 */
@ManagedBean
@RequestScoped
public class PedidoControle {

    private Pedido pedido;
    private Produto produtoLinhaEditavel;
    private List<Pedido> pedidos;

    public PedidoControle() {
        System.out.println("PEDIDO CONTROLE");
        pedido = new Pedido();
        this.pedido.adicionarItemVazio();
//        pedido.setCliente(new Cliente());
    }
    
    //Métodos dos botões 
    public void cadastrar() {

        System.out.println("CADASTRANDO PEDIDO: produto /tamanho "
                + this.pedido.getDetalhePedidoList().isEmpty()
                + this.pedido.getDetalhePedidoList().size());
        new PedidoDao().inserir(pedido);
    }

    public void carregarProdutoLinhaEditavel() {
        
        
        if(existeItemComProduto(produtoLinhaEditavel)){
            System.out.println("NÃO ADICIONA");
            FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
        }else{
            System.out.println("ADIC NOVO");
            FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
            DetalhePedido item = this.pedido.getDetalhePedidoList().get(0);
            item.setIdPedido(this.pedido);
            item.setIdProduto(produtoLinhaEditavel);
            pedido.getDetalhePedidoList().add(item);
            
            this.pedido.adicionarItemVazio();
            produtoLinhaEditavel = null;
        }
       
    }

    public List<Pedido> listar() {
        return pedidos = new PedidoDao().listar();
    }
    
    private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (DetalhePedido item : this.pedido.getDetalhePedidoList()) {
			if (produto.equals(item.getIdProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

    public DefaultStreamedContent emitir() throws JRException {

        return new PedidoDao().emitir();
    }

    public Pedido getPedido() {
        return pedido;
    }

//    public void inicializar() {
//        ItemPedido item = new ItemPedido();
//        item.setIdPedido(pedido);
//        item.setIdProduto(new Produto());
//        this.pedido.setItemPedidoList(new ArrayList<ItemPedido>());
//        this.pedido.getItemPedidoList().add(item);
//    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    private boolean existeItem() {
        if (this.pedido.getDetalhePedidoList().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}
