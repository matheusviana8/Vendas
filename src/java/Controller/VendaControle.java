/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.VendaDao;
import Model.Cliente;
import Model.Venda;
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
public class VendaControle {

    private Venda venda;
    private List<Venda> vendas;

    public VendaControle() {
        venda = new Venda();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new VendaDao().inserir(venda);
    }
    
    public List<Venda> listar() {
        return vendas = new VendaDao().listar();
    }
    
    public DefaultStreamedContent emitir() throws JRException{
        
        return new VendaDao().emitir();
    }
    

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    
    
}
