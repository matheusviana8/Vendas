/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHEUS
 */
@Entity
@Table(name = "detalhe_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalhePedido.findAll", query = "SELECT d FROM DetalhePedido d")})
public class DetalhePedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalhePedidoPK detalhePedidoPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlrUnitario")
    private Double vlrUnitario;
    @JoinColumn(name = "idPedido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "idProduto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public DetalhePedido() {
    }

    public DetalhePedido(DetalhePedidoPK detalhePedidoPK) {
        this.detalhePedidoPK = detalhePedidoPK;
    }

    public DetalhePedido(int idPedido, int idProduto) {
        this.detalhePedidoPK = new DetalhePedidoPK(idPedido, idProduto);
    }

    public DetalhePedidoPK getDetalhePedidoPK() {
        return detalhePedidoPK;
    }

    public void setDetalhePedidoPK(DetalhePedidoPK detalhePedidoPK) {
        this.detalhePedidoPK = detalhePedidoPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalhePedidoPK != null ? detalhePedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalhePedido)) {
            return false;
        }
        DetalhePedido other = (DetalhePedido) object;
        if ((this.detalhePedidoPK == null && other.detalhePedidoPK != null) || (this.detalhePedidoPK != null && !this.detalhePedidoPK.equals(other.detalhePedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DetalhePedido[ detalhePedidoPK=" + detalhePedidoPK + " ]";
    }
    
}
