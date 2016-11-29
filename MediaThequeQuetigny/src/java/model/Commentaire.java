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
@Table(name = "commentaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c")
    , @NamedQuery(name = "Commentaire.findByIdCommentaire", query = "SELECT c FROM Commentaire c WHERE c.idCommentaire = :idCommentaire")
    , @NamedQuery(name = "Commentaire.findByNote", query = "SELECT c FROM Commentaire c WHERE c.note = :note")
    , @NamedQuery(name = "Commentaire.findByTexte", query = "SELECT c FROM Commentaire c WHERE c.texte = :texte")
    , @NamedQuery(name = "Commentaire.findByDateCommentaire", query = "SELECT c FROM Commentaire c WHERE c.dateCommentaire = :dateCommentaire")
    , @NamedQuery(name = "Commentaire.findByAffiche", query = "SELECT c FROM Commentaire c WHERE c.affiche = :affiche")})
public class Commentaire implements Serializable, Comparable<Commentaire> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCommentaire")
    private Integer idCommentaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "note")
    private int note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "texte")
    private String texte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCommentaire")
    @Temporal(TemporalType.DATE)
    private Date dateCommentaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "affiche")
    private boolean affiche;
    @JoinColumn(name = "codeLivre", referencedColumnName = "codeLivre")
    @ManyToOne(optional = false)
    private Livre codeLivre;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Commentaire() {
    }

    public Commentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Commentaire(Integer idCommentaire, int note, String texte, Date dateCommentaire, boolean affiche) {
        this.idCommentaire = idCommentaire;
        this.note = note;
        this.texte = texte;
        this.dateCommentaire = dateCommentaire;
        this.affiche = affiche;
    }

    public Integer getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public boolean getAffiche() {
        return affiche;
    }

    public void setAffiche(boolean affiche) {
        this.affiche = affiche;
    }

    public Livre getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(Livre codeLivre) {
        this.codeLivre = codeLivre;
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
        hash += (idCommentaire != null ? idCommentaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.idCommentaire == null && other.idCommentaire != null) || (this.idCommentaire != null && !this.idCommentaire.equals(other.idCommentaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.afpa.mediatheque.model.Commentaire[ idCommentaire=" + idCommentaire + " ]";
    }

    @Override
    public int compareTo(Commentaire o) {
        return dateCommentaire.compareTo(o.dateCommentaire);
    }    
}