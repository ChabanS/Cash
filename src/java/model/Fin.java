/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sergey
 */
@Entity
@Table(name = "fin", catalog = "finance", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fin.findAll", query = "SELECT f FROM Fin f"),
    @NamedQuery(name = "Fin.findById", query = "SELECT f FROM Fin f WHERE f.id = :id"),
    @NamedQuery(name = "Fin.findByData", query = "SELECT f FROM Fin f WHERE f.data = :data"),
    @NamedQuery(name = "Fin.findByFinrez", query = "SELECT f FROM Fin f WHERE f.finrez = :finrez")})
public class Fin implements Serializable {

    @Basic(optional = false)
    @Column(name = "idUser")
    private int idUser;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "finrez", nullable = false)
    private int finrez;
    @JoinColumn(name = "account", referencedColumnName = "idaccount", nullable = false)
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "name", referencedColumnName = "idoperation", nullable = false)
    @ManyToOne(optional = false)
    private Operation operation;

    public Fin() {
    }

    public Fin(Integer id) {
        this.id = id;
    }

    public Fin(Integer id, Date data, int finrez) {
        this.id = id;
        this.data = data;
        this.finrez = finrez;
    }

    public Fin(Date data, int finrez, Account account, Operation operation, int idUser) {
        this.data = data;
        this.finrez = finrez;
        this.account = account;
        this.operation = operation;
        this.idUser = idUser;
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

    public int getFinrez() {
        return finrez;
    }

    public void setFinrez(int finrez) {
        this.finrez = finrez;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation name) {
        this.operation = name;
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
        if (!(object instanceof Fin)) {
            return false;
        }
        Fin other = (Fin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Fin[ id=" + id + " ]";
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
