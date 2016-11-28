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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tarik
 */
@Entity
@Table(name = "nouvelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nouvelle.findAll", query = "SELECT n FROM Nouvelle n")
    , @NamedQuery(name = "Nouvelle.findByIdNouvelle", query = "SELECT n FROM Nouvelle n WHERE n.idNouvelle = :idNouvelle")
    , @NamedQuery(name = "Nouvelle.findByTexte", query = "SELECT n FROM Nouvelle n WHERE n.texte = :texte")
    , @NamedQuery(name = "Nouvelle.findByDateNouvelle", query = "SELECT n FROM Nouvelle n WHERE n.dateNouvelle = :dateNouvelle")})
public class Nouvelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNouvelle")
    private Integer idNouvelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "texte")
    private String texte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateNouvelle")
    @Temporal(TemporalType.DATE)
    private Date dateNouvelle;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne(optional = false)
    private Categorie idCategorie;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Nouvelle() {
    }

    public Nouvelle(Integer idNouvelle) {
        this.idNouvelle = idNouvelle;
    }

    public Nouvelle(Integer idNouvelle, String texte, Date dateNouvelle) {
        this.idNouvelle = idNouvelle;
        this.texte = texte;
        this.dateNouvelle = dateNouvelle;
    }

    public Integer getIdNouvelle() {
        return idNouvelle;
    }

    public void setIdNouvelle(Integer idNouvelle) {
        this.idNouvelle = idNouvelle;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDateNouvelle() {
        return dateNouvelle;
    }

    public void setDateNouvelle(Date dateNouvelle) {
        this.dateNouvelle = dateNouvelle;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
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
        hash += (idNouvelle != null ? idNouvelle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nouvelle)) {
            return false;
        }
        Nouvelle other = (Nouvelle) object;
        if ((this.idNouvelle == null && other.idNouvelle != null) || (this.idNouvelle != null && !this.idNouvelle.equals(other.idNouvelle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(idNouvelle);
    }
    
    //Méthodes ajoutées 
    public String renvoyerDescriptionCourte(){
        return this.getDateNouvelle().toString();
    }
}
