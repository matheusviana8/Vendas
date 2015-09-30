/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ClienteDao;
import Model.Cliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MATHEUS
 */
@ManagedBean
@RequestScoped
public class ClienteController {

    private Cliente cliente;

    public ClienteController() {
        cliente = new Cliente();
    }

    //Métodos dos botões 
    public void cadastrar() {
        new ClienteDao().inserir(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
