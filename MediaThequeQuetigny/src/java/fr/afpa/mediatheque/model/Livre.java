/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.mediatheque.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tarik
 */
@Entity
@Table(name = "livre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l")
    , @NamedQuery(name = "Livre.findByCodeLivre", query = "SELECT l FROM Livre l WHERE l.codeLivre = :codeLivre")
    , @NamedQuery(name = "Livre.findByTitre", query = "SELECT l FROM Livre l WHERE l.titre = :titre")
    , @NamedQuery(name = "Livre.findByAnneeEdition", query = "SELECT l FROM Livre l WHERE l.anneeEdition = :anneeEdition")
    , @NamedQuery(name = "Livre.findByDescription", query = "SELECT l FROM Livre l WHERE l.description = :description")
    , @NamedQuery(name = "Livre.findByImage", query = "SELECT l FROM Livre l WHERE l.image = :image")
    , @NamedQuery(name = "Livre.findByStatut", query = "SELECT l FROM Livre l WHERE l.statut = :statut")})
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codeLivre")
    private Integer codeLivre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anneeEdition")
    @Temporal(TemporalType.DATE)
    private Date anneeEdition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 25)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "statut")
    private String statut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeLivre")
    private List<Exemplaire> exemplaireList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeLivre")
    private List<Commentaire> commentaireList;
    @JoinColumn(name = "idAuteur", referencedColumnName = "idAuteur")
    @ManyToOne(optional = false)
    private Auteur idAuteur;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne(optional = false)
    private Categorie idCategorie;
    @JoinColumn(name = "idEditeur", referencedColumnName = "idEditeur")
    @ManyToOne(optional = false)
    private Editeur idEditeur;

    public Livre() {
    }

    public Livre(Integer codeLivre) {
        this.codeLivre = codeLivre;
    }

    public Livre(Integer codeLivre, String titre, Date anneeEdition, String description, String statut) {
        this.codeLivre = codeLivre;
        this.titre = titre;
        this.anneeEdition = anneeEdition;
        this.description = description;
        this.statut = statut;
    }

    public Integer getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(Integer codeLivre) {
        this.codeLivre = codeLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getAnneeEdition() {
        return anneeEdition;
    }

    public void setAnneeEdition(Date anneeEdition) {
        this.anneeEdition = anneeEdition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @XmlTransient
    public List<Exemplaire> getExemplaireList() {
        return exemplaireList;
    }

    public void setExemplaireList(List<Exemplaire> exemplaireList) {
        this.exemplaireList = exemplaireList;
    }

    @XmlTransient
    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }

    public Auteur getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Auteur idAuteur) {
        this.idAuteur = idAuteur;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Editeur getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(Editeur idEditeur) {
        this.idEditeur = idEditeur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeLivre != null ? codeLivre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.codeLivre == null && other.codeLivre != null) || (this.codeLivre != null && !this.codeLivre.equals(other.codeLivre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.afpa.mediatheque.model.Livre[ codeLivre=" + codeLivre + " ]";
    }
    
}
