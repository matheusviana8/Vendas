/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.DetalhePedidoDao;
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
import javax.faces.bean.ViewScoped;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author MATHEUS
 */
@ManagedBean
@ViewScoped
public class PedidoControle {

    private Pedido pedido;
    private Produto produtoLinhaEditavel;
    private List<Pedido> pedidos;

    public PedidoControle() {
        System.out.println("PEDIDO CONTROLE");
        pedido = new Pedido();
//        pedido.setCliente(new Cliente());
        pedido.adicionarItemVazio();
    }

    //Métodos dos botões 
    public void cadastrar() {

        System.out.println("CADASTRANDO PEDIDO: produto /tamanho "
                + this.pedido.getDetalhePedidoList().isEmpty()
                + this.pedido.getDetalhePedidoList().size());
        this.pedido.removerItemVazio();
        new PedidoDao().inserir(pedido);

    }

    public void carregarProdutoLinhaEditavel() {

        DetalhePedido item = this.pedido.getDetalhePedidoList().get(0);
        if (this.produtoLinhaEditavel != null) {
            if (existeItemComProduto(produtoLinhaEditavel)) {
                System.out.println("NÃO ADICIONA");
                FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
            } else {
                System.out.println("ADIC NOVO");
                item.setIdPedido(pedido);
                item.setIdProduto(produtoLinhaEditavel);
                item.setVlrUnitario(produtoLinhaEditavel.getVlrvenda());

                pedido.adicionarItemVazio();
                produtoLinhaEditavel = null;
                
                this.pedido.recalcularValorTotal();
                
                FacesUtil.addInfoMessage("adicionado.");
            }
        }
    }
    
    public void atualizarQuantidade(DetalhePedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getDetalhePedidoList().remove(linha);
			}
		}
		
		this.pedido.recalcularValorTotal();
	}

    public List<Pedido> listar() {
        pedidos = new PedidoDao().listar();
        return pedidos;
    }
    
    public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
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
        System.out.println("emitir controle: id= "+pedido.getId());
        return new PedidoDao().emitir(pedido);
    }

    public Pedido getPedido() {
        return pedido;
    }

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

    public void setPedido(Pedido pedido) {
        System.out.println("SET PEDIDO");
        this.pedido = pedido;
    }
    
    

}
