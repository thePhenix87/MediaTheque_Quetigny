/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Tarik
 * Created: 23 nov. 2016
 */

-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.16-log - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour mediatheque
CREATE DATABASE IF NOT EXISTS `mediatheque` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mediatheque`;


-- Export de la structure de table mediatheque. auteur
CREATE TABLE IF NOT EXISTS `auteur` (
  `idAuteur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  PRIMARY KEY (`idAuteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.auteur : ~0 rows (environ)
DELETE FROM `auteur`;
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
/*!40000 ALTER TABLE `auteur` ENABLE KEYS */;


-- Export de la structure de table mediatheque. categorie
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(50) NOT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.categorie : ~0 rows (environ)
DELETE FROM `categorie`;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` (`idCategorie`, `intitule`, `type`) VALUES
	(1, 'Culture General', 'doc'),
	(2, 'sex', 'doc');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;


-- Export de la structure de table mediatheque. commentaire
CREATE TABLE IF NOT EXISTS `commentaire` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `note` int(11) NOT NULL,
  `texte` varchar(25) NOT NULL,
  `dateCommentaire` date NOT NULL,
  `affiche` tinyint(1) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `codeLivre` int(11) NOT NULL,
  PRIMARY KEY (`idCommentaire`),
  KEY `dateCommentaire` (`dateCommentaire`),
  KEY `FK_commentaire_idUtilisateur` (`idUtilisateur`),
  KEY `FK_commentaire_codeLivre` (`codeLivre`),
  CONSTRAINT `FK_commentaire_codeLivre` FOREIGN KEY (`codeLivre`) REFERENCES `livre` (`codeLivre`),
  CONSTRAINT `FK_commentaire_idUtilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.commentaire : ~0 rows (environ)
DELETE FROM `commentaire`;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;


-- Export de la structure de table mediatheque. editeur
CREATE TABLE IF NOT EXISTS `editeur` (
  `idEditeur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`idEditeur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.editeur : ~0 rows (environ)
DELETE FROM `editeur`;
/*!40000 ALTER TABLE `editeur` DISABLE KEYS */;
/*!40000 ALTER TABLE `editeur` ENABLE KEYS */;


-- Export de la structure de table mediatheque. emprunt
CREATE TABLE IF NOT EXISTS `emprunt` (
  `idEmprunt` int(11) NOT NULL AUTO_INCREMENT,
  `dateEmprunt` date NOT NULL,
  `dateRetour` date DEFAULT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `idExemplaire` int(11) NOT NULL,
  PRIMARY KEY (`idEmprunt`),
  KEY `dateEmprunt` (`dateEmprunt`,`dateRetour`),
  KEY `FK_emprunt_idUtilisateur` (`idUtilisateur`),
  KEY `FK_emprunt_idExemplaire` (`idExemplaire`),
  CONSTRAINT `FK_emprunt_idExemplaire` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`),
  CONSTRAINT `FK_emprunt_idUtilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.emprunt : ~0 rows (environ)
DELETE FROM `emprunt`;
/*!40000 ALTER TABLE `emprunt` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprunt` ENABLE KEYS */;


-- Export de la structure de table mediatheque. exemplaire
CREATE TABLE IF NOT EXISTS `exemplaire` (
  `idExemplaire` int(11) NOT NULL AUTO_INCREMENT,
  `statut` tinyint(1) NOT NULL,
  `codeLivre` int(11) NOT NULL,
  PRIMARY KEY (`idExemplaire`),
  KEY `FK_exemplaire_codeLivre` (`codeLivre`),
  CONSTRAINT `FK_exemplaire_codeLivre` FOREIGN KEY (`codeLivre`) REFERENCES `livre` (`codeLivre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.exemplaire : ~0 rows (environ)
DELETE FROM `exemplaire`;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;


-- Export de la structure de table mediatheque. livre
CREATE TABLE IF NOT EXISTS `livre` (
  `codeLivre` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `anneeEdition` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(25) DEFAULT NULL,
  `statut` varchar(25) NOT NULL,
  `idAuteur` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  `idEditeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`codeLivre`),
  KEY `FK_livre_idAuteur` (`idAuteur`),
  KEY `FK_livre_idCategorie` (`idCategorie`),
  KEY `FK_livre_idEditeur` (`idEditeur`),
  CONSTRAINT `FK_livre_idAuteur` FOREIGN KEY (`idAuteur`) REFERENCES `auteur` (`idAuteur`),
  CONSTRAINT `FK_livre_idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`),
  CONSTRAINT `FK_livre_idEditeur` FOREIGN KEY (`idEditeur`) REFERENCES `editeur` (`idEditeur`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.livre : ~0 rows (environ)
DELETE FROM `livre`;
/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
INSERT INTO `livre` (`codeLivre`, `titre`, `anneeEdition`, `description`, `image`, `statut`, `idAuteur`, `idCategorie`, `idEditeur`) VALUES
	(1, 'harry', '2016-11-18', 'wxvwxv', NULL, 'xvwxv', NULL, NULL, NULL);
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;


-- Export de la structure de table mediatheque. nouvelle
CREATE TABLE IF NOT EXISTS `nouvelle` (
  `idNouvelle` int(11) NOT NULL AUTO_INCREMENT,
  `texte` varchar(255) NOT NULL,
  `dateNouvelle` date NOT NULL,
  `idCategorie` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idNouvelle`),
  KEY `dateNouvelle` (`dateNouvelle`),
  KEY `FK_nouvelle_idCategorie` (`idCategorie`),
  KEY `FK_nouvelle_idUtilisateur` (`idUtilisateur`),
  CONSTRAINT `FK_nouvelle_idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`),
  CONSTRAINT `FK_nouvelle_idUtilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.nouvelle : ~0 rows (environ)
DELETE FROM `nouvelle`;
/*!40000 ALTER TABLE `nouvelle` DISABLE KEYS */;
/*!40000 ALTER TABLE `nouvelle` ENABLE KEYS */;


-- Export de la structure de table mediatheque. utilisateur
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sessionFormation` tinyint(1) NOT NULL,
  `type` varchar(25) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `dateNaissance` date NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `cp` varchar(10) NOT NULL,
  `login` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table mediatheque.utilisateur : ~0 rows (environ)
DELETE FROM `utilisateur`;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
