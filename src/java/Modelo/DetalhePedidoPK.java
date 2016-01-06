/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MATHEUS
 */
@Embeddable
public class DetalhePedidoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPedido")
    private int idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduto")
    private int idProduto;

    public DetalhePedidoPK() {
    }

    public DetalhePedidoPK(int idPedido, int idProduto) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPedido;
        hash += (int) idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalhePedidoPK)) {
            return false;
        }
        DetalhePedidoPK other = (DetalhePedidoPK) object;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DetalhePedidoPK[ idPedido=" + idPedido + ", idProduto=" + idProduto + " ]";
    }
    
}
