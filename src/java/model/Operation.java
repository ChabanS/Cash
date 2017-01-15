/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sergey
 */
@Entity
@Table(name = "operation", catalog = "finance", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o"),
    @NamedQuery(name = "Operation.findByIdoperation", query = "SELECT o FROM Operation o WHERE o.idoperation = :idoperation"),
    @NamedQuery(name = "Operation.findByOperationName", query = "SELECT o FROM Operation o WHERE o.operationName = :operationName"),
    @NamedQuery(name = "Operation.findByBalans", query = "SELECT o FROM Operation o WHERE o.balans = :balans")})
public class Operation implements Serializable {

    @Basic(optional = false)
    @Column(name = "idUser")
    private int idUser;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idoperation", nullable = false)
    private Integer idoperation;
    @Basic(optional = false)
    @Column(name = "operationName", nullable = false, length = 45)
    private String operationName;
    @Basic(optional = false)
    @Column(name = "balans", nullable = false)
    private int balans;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operation")
    private Collection<Fin> finCollection;

    public Operation() {
    }

    public Operation(Integer idoperation) {
        this.idoperation = idoperation;
    }

    public Operation(Integer idoperation, String operationName, int balans) {
        this.idoperation = idoperation;
        this.operationName = operationName;
        this.balans = balans;
    }
    
    public Operation(String operationName, int balans) {
        this.operationName = operationName;
        this.balans = balans;
    }
    
    public Operation(String operationName, int balans, int idUser) {
        this.operationName = operationName;
        this.balans = balans;
        this.idUser = idUser;
    }

       public Integer getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(Integer idoperation) {
        this.idoperation = idoperation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public int getBalans() {
        return balans;
    }

    public void setBalans(int balans) {
        this.balans = balans;
    }

    @XmlTransient
    public Collection<Fin> getFinCollection() {
        return finCollection;
    }

    public void setFinCollection(Collection<Fin> finCollection) {
        this.finCollection = finCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperation != null ? idoperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.idoperation == null && other.idoperation != null) || (this.idoperation != null && !this.idoperation.equals(other.idoperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Operation[ idoperation=" + idoperation + " ]";
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
