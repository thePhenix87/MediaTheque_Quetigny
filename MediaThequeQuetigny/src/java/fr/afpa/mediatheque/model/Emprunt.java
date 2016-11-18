/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.model;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tarik
 */
@Entity
@Table(name = "emprunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e")
    , @NamedQuery(name = "Emprunt.findByIdEmprunt", query = "SELECT e FROM Emprunt e WHERE e.idEmprunt = :idEmprunt")
    , @NamedQuery(name = "Emprunt.findByDateEmprunt", query = "SELECT e FROM Emprunt e WHERE e.dateEmprunt = :dateEmprunt")
    , @NamedQuery(name = "Emprunt.findByDateRetour", query = "SELECT e FROM Emprunt e WHERE e.dateRetour = :dateRetour")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmprunt")
    private Integer idEmprunt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateEmprunt")
    @Temporal(TemporalType.DATE)
    private Date dateEmprunt;
    @Column(name = "dateRetour")
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @JoinColumn(name = "idExemplaire", referencedColumnName = "idExemplaire")
    @ManyToOne(optional = false)
    private Exemplaire idExemplaire;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Emprunt() {
    }

    public Emprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Emprunt(Integer idEmprunt, Date dateEmprunt) {
        this.idEmprunt = idEmprunt;
        this.dateEmprunt = dateEmprunt;
    }

    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Exemplaire getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Exemplaire idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprunt != null ? idEmprunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.idEmprunt == null && other.idEmprunt != null) || (this.idEmprunt != null && !this.idEmprunt.equals(other.idEmprunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.afpa.mediatheque.model.Emprunt[ idEmprunt=" + idEmprunt + " ]";
    }
    
}
