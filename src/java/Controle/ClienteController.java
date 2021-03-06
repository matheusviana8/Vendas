/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ClienteDao;
import Modelo.Cliente;
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
public class ClienteController {

    private Cliente cliente;
    private List<Cliente> clientes;
    private ClienteDao dao;

    public ClienteController() {
        cliente = new Cliente();
        dao = new ClienteDao();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new ClienteDao().inserir(cliente);
    }
    
   
    public List<Cliente> listar() {
        return clientes = new ClienteDao().listar();
    }
    public List<Cliente> complete(String nome) {
		List<Cliente> queryResult = new ArrayList<Cliente>();

		if (clientes== null) {
			
			clientes = dao.listar();
		}
                
		for (Cliente cliente : clientes) {
			if (cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
				queryResult.add(cliente);
			}
		}

		return queryResult;
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
