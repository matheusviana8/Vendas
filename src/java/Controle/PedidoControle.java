/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PedidoDao;
import Modelo.Cliente;
import Modelo.DetalhePedido;
import Modelo.Pedido;
import Modelo.Produto;
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
        produtoLinhaEditavel = new Produto();
//        pedido.setCliente(new Cliente());
    }

    //Métodos dos botões 
    public void cadastrar() {
        System.out.println("CADASTRANDO PEDIDO: "+pedido.getCliente());
        new PedidoDao().inserir(pedido);
    }
    
    public void carregarProdutoLinhaEditavel(){
        
    }

    public List<Pedido> listar() {
        return pedidos = new PedidoDao().listar();
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

    
    
}
