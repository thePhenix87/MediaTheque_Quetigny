/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tarik
 */
@Entity
@Table(name = "exemplaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exemplaire.findAll", query = "SELECT e FROM Exemplaire e")
    , @NamedQuery(name = "Exemplaire.findByIdExemplaire", query = "SELECT e FROM Exemplaire e WHERE e.idExemplaire = :idExemplaire")
    , @NamedQuery(name = "Exemplaire.findByStatut", query = "SELECT e FROM Exemplaire e WHERE e.statut = :statut")})
public class Exemplaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExemplaire")
    private Integer idExemplaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statut")
    private boolean statut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExemplaire")
    private List<Emprunt> empruntList;
    @JoinColumn(name = "codeLivre", referencedColumnName = "codeLivre")
    @ManyToOne(optional = false)
    private Livre codeLivre;

    public Exemplaire() {
    }

    public Exemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Exemplaire(Integer idExemplaire, boolean statut) {
        this.idExemplaire = idExemplaire;
        this.statut = statut;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @XmlTransient
    public List<Emprunt> getEmpruntList() {
        return empruntList;
    }

    public void setEmpruntList(List<Emprunt> empruntList) {
        this.empruntList = empruntList;
    }

    public Livre getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(Livre codeLivre) {
        this.codeLivre = codeLivre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExemplaire != null ? idExemplaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exemplaire)) {
            return false;
        }
        Exemplaire other = (Exemplaire) object;
        if ((this.idExemplaire == null && other.idExemplaire != null) || (this.idExemplaire != null && !this.idExemplaire.equals(other.idExemplaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.afpa.mediatheque.model.Exemplaire[ idExemplaire=" + idExemplaire + " ]";
    }
    
}
