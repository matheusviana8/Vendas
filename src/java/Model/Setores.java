/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHEUS
 */
@Entity
@Table(name = "setores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setores.findAll", query = "SELECT s FROM Setores s"),
    @NamedQuery(name = "Setores.findByIdSetor", query = "SELECT s FROM Setores s WHERE s.idSetor = :idSetor"),
    @NamedQuery(name = "Setores.findByDescricao", query = "SELECT s FROM Setores s WHERE s.descricao = :descricao")})
public class Setores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSetor")
    private Integer idSetor;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;

    public Setores() {
    }

    public Setores(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetor != null ? idSetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setores)) {
            return false;
        }
        Setores other = (Setores) object;
        if ((this.idSetor == null && other.idSetor != null) || (this.idSetor != null && !this.idSetor.equals(other.idSetor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Setores[ idSetor=" + idSetor + " ]";
    }
    
}
