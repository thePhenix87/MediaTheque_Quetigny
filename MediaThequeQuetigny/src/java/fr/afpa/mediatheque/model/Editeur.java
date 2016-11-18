/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.model;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tarik
 */
@Entity
@Table(name = "editeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editeur.findAll", query = "SELECT e FROM Editeur e")
    , @NamedQuery(name = "Editeur.findByIdEditeur", query = "SELECT e FROM Editeur e WHERE e.idEditeur = :idEditeur")
    , @NamedQuery(name = "Editeur.findByNom", query = "SELECT e FROM Editeur e WHERE e.nom = :nom")})
public class Editeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEditeur")
    private Integer idEditeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEditeur")
    private List<Livre> livreList;

    public Editeur() {
    }

    public Editeur(Integer idEditeur) {
        this.idEditeur = idEditeur;
    }

    public Editeur(Integer idEditeur, String nom) {
        this.idEditeur = idEditeur;
        this.nom = nom;
    }

    public Integer getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(Integer idEditeur) {
        this.idEditeur = idEditeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEditeur != null ? idEditeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editeur)) {
            return false;
        }
        Editeur other = (Editeur) object;
        if ((this.idEditeur == null && other.idEditeur != null) || (this.idEditeur != null && !this.idEditeur.equals(other.idEditeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.afpa.mediatheque.model.Editeur[ idEditeur=" + idEditeur + " ]";
    }
    
}
