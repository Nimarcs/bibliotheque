# Bibliothèque - Application Java Spring Boot

## Description

Ceci est le TP1 du cours de patron de conception
Ce projet est une application Java Spring Boot qui utilise Hibernate comme ORM (Object-Relational Mapping) et Maven comme outil de gestion de dépendances. Il est conçu pour gérer les fonctionnalités d'une bibliothèque, en se connectant à une base de données MySQL.

## Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html) (ou une version compatible avec votre configuration Spring Boot)
- [Apache Maven 3.6+](https://maven.apache.org/download.cgi)
- [MySQL Server 8.0+](https://dev.mysql.com/downloads/mysql/)
(Xampp permet d'heberger localement le serveur)

## Installation

### Étape 1 : Cloner le projet

Clonez ce dépôt en local :

```bash
git clone <url-du-repo>
cd bibliotheque
```

## Configurer la base de données

Connectez-vous à votre serveur MySQL et créez une base de données appelée bibliotheque
```sql
CREATE DATABASE bibliotheque;
```

Vérifiez les informations de connexion dans le fichier application.properties situé dans src/main/resources/

## Lancement

```bash
mvn clean install
mvn spring-boot:run
```

## Auteur

Alexis Lopes Vaz - Marcus Richier
Projet Bibliothèque - Patron de Conception 

