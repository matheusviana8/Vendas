/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ClienteDao;
import Modelo.Cliente;
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
public class ClienteController {

    private Cliente cliente;
    private List<Cliente> clientes;

    public ClienteController() {
        cliente = new Cliente();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new ClienteDao().inserir(cliente);
    }
    
    public List<Cliente> listar() {
        return clientes = new ClienteDao().listar();
    }
    
    public DefaultStreamedContent emitir() throws JRException{
        
        return new ClienteDao().emitir();
    }
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    
}
