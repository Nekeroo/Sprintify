# Sprintify - Backend

Sprintify - Backend est l'API appelée par l'application mobile et / ou web. 
Celle-ci permet de créer, modifier et supprimer les objets nécessaires.

Cette API est utilisée sur l'application frontend : 

https://github.com/Nekeroo/SprintifyAppMobile

## Dernière version stable (LTS)

Version actuelle : v1.9.0

https://github.com/Nekeroo/Sprintify/tree/v1.9.0

## Stack technique

* Java 21
* SpringBoot 3.5.4
  * Spring Web
  * Spring Data JPA (MySQL)
  * Spring Security
* MySQL (8+)
* Flyway
* Maven 
* Swagger / Springdoc OpenAPI
* JUnit 5 / Mockito pour les tests

## Prérequis

* Java JDK 21
* Maven 3.8.6 (*recommandée*) ou + 
  * (*Un maven wrapper est disponible dans le projet*)
* mysql 8.0.33 (*recommandée*) ou +

## Installation


### Cloner le projet 

```bash
git clone https://github.com/Nekeroo/Sprintify.git
```

### Installation des dépendances

```bash
cd Sprintify
mvn clean install -DskipTests
```

## Utilisation de Docker 

### Docker Compose

Pour faciliter le lancement de l'application sans forcément créer de base de données locale, vous pouvez utiliser Docker Compose.

### Packager l'application

Pour se faire, vous aurez besoin de maven et d'exécuter la commande suivante :

```bash
mvn clean package -DskipTests
```

Une fois celle-ci terminé, vous pouvez lancer l'application avec la commande suivante :

```bash
docker-compose up --build
```

*Assurez-vous d'avoir docker de lancer sur votre machine. Pour se faire, vous pouvez exécutez ceci : 

```bash
docker info
```

## Utilisation manuelle

### Créer votre base de données locale 

Pour faire tourner l'application en local, vous aurez besoin d'une base de donnée MySQL.

Si cela n'est pas fait, installé MySQL sur votre machine.

Pour ce faire :

* Si vous êtes sous Windows : 

```bash
choco install mysql
```

* Si vous êtes sous Linux : 

```bash
sudo apt-get install mysql-server
```

Une fois que vous avez installé MySQL, vous devez vous connecter :

```bash
mysql -u root -p
```

(*Si vous ne connaissez plus votre mot de passe, vous pouvez le réinitialiser via cette manipulation : https://cloud.ibm.com/docs/database-tools?topic=database-tools-dbt-change-mysql-password&locale=fr*)

Etant donné qu'on utilise flyway, il faut simplement créer la base de données. Flyway se charge de créer les tables et des données de base.

```bash
CREATE DATABASE sprintify;
```

### Fichier environnement

Vous devez créer un fichier '.env' à la racine du projet avec les variables suivantes :

```text
DATABASE_URL=url_de_votre_base
DATABASE_USER=nom_user
DATABASE_PASSWORD=mot_de_passe
JWT_SECRET_KEY=clé_secrète
```


Le fichier application.properties fait appel à ces variables afin de se connecter à la base de données ainsi que la clé secrète pour le JWT.


### Lancer l'application

Afin de lancer l'application, vous devez simplement taper la commande suivante :

```bash
./mvnw spring-boot:run
```


## Documentation

Vous pouvez consulter la documentation de l'API (Routes, Objets, etc.) en vous rendant sur la page suivante : https://sprintify.mathieugr.fr/swagger-ui/index.html

## Tests

Dans le dossier src/test, vous retrouverez les différents tests implémentés. Ces tests se lancent avec une base de données en mémoire remise à l'état initiale dans chaque test.

Ceci permet de vérifier l'intégrité des données et des routes.

## Déploiement

L'application est déployée sur Coolify, solution de déploiement rapide, hébergée sur un VPS. 
Ce déploiement est automatique grâce à aux Github Actions.