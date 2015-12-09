/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PedidoDao;
import Modelo.Cliente;
import Modelo.Pedido;
import Util.HibernateUtil;
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
        pedido = new Pedido();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new PedidoDao().inserir(pedido);
    }
    
    public List<Pedido> listar() {
        return pedidos = new PedidoDao().listar();
    }
    
    public DefaultStreamedContent emitir() throws JRException{
        
        return new PedidoDao().emitir();
    }
    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    
}
