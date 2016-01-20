/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MATHEUS
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "prazoEntrega")
    @Temporal(TemporalType.DATE)
    private Date prazoEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlrDesconto")
    private BigDecimal vlrDesconto = BigDecimal.ZERO;
    @Column(name = "vlrTotal")
    private BigDecimal vlrTotal = BigDecimal.ZERO;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "idPedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalhePedido> detalhePedidoList = new ArrayList<>();

    public Pedido() {
    }

    public void adicionarItemVazio() {

        Produto produto = new Produto();

        DetalhePedido item = new DetalhePedido();
        item.setIdProduto(produto);
        item.setIdPedido(this);

        this.getDetalhePedidoList().add(0, item);

    }

    public void removerItemVazio() {
        this.getDetalhePedidoList().remove(0);
    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        total = total.add(this.getVlrDesconto());

        for (DetalhePedido item : this.getDetalhePedidoList()) {
            if (item.getIdProduto() != null && item.getIdProduto().getId() != null) {
                total = total.add(item.getValorTotal());
            }
        }

        this.setVlrTotal(total);
    }
    
    public BigDecimal getValorSubtotal() {
		return this.getVlrTotal().subtract(this.getVlrDesconto());
	}

    public Pedido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Date prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public BigDecimal getVlrDesconto() {
        return vlrDesconto;
    }

    public void setVlrDesconto(BigDecimal vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    public BigDecimal getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(BigDecimal vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<DetalhePedido> getDetalhePedidoList() {
        return detalhePedidoList;
    }

    public void setDetalhePedidoList(List<DetalhePedido> detalhePedidoList) {
        this.detalhePedidoList = detalhePedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Pedido[ id=" + id + " ]";
    }

}
