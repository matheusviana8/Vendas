/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PedidoDao;
import Modelo.Cliente;
import Modelo.ItemPedido;
import Modelo.Pedido;
import Modelo.Produto;
import Util.HibernateUtil;
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
    private List<Pedido> pedidos;

    public PedidoControle() {
        System.out.println("PEDIDO CONTROLE");
        pedido = new Pedido();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new PedidoDao().inserir(pedido);
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

    public void inicializar() {
        ItemPedido item = new ItemPedido();
        item.setIdPedido(pedido);
        item.setIdProduto(new Produto());
        this.pedido.setItemPedidoList(new ArrayList<ItemPedido>());
        this.pedido.getItemPedidoList().add(item);
    }

    
}
