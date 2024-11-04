
# Mercato App

Guide d'installation pour cette application Spring Boot avec MySQL, veuillez suivre les étapes pour configurer l'environnement, la base de données, et exécuter l'application.




## Prérequis

<b> Java JDK 17 : </b> Installez Java 17 pour garantir la compatibilité avec Spring Boot 3.3.x.

<b>Maven :</b> Maven est nécessaire pour gérer les dépendances et construire le projet.

<b>MySQL :</b> Installez MySQL pour gérer la base de données de votre application.

<b>IDE (environnement de développement intégré) :</b> Utilisez un IDE comme IntelliJ IDEA, Eclipse ou Visual Studio Code pour écrire et exécuter le code.
## Étapes pour configurer et lancer l'application

1. <b>Cloner le projet avec la commande :</b>

```bash
git clone
```

2. <b>Créer la base de données MySQL</b>
Ouvrez MySQL depuis la ligne de commande ou votre interface MySQL préférée (comme MySQL Workbench). <br/> 
Connectez-vous à votre serveur MySQL et créez une base de données pour l'application (par exemple mercato_db).

3. <b>Configurer le fichier `application.properties`</b>
Dans le projet, ouvrez le fichier `src/main/resources/application.properties` et configurez les paramètres de connexion MySQL :<br/> 
- `spring.datasource.url` : Remplacez `localhost:3306 par` l'hôte et le port de votre serveur MySQL (par défaut, localhost:3306).<br/> 
- `spring.datasource.username` et `spring.datasource.password` : Renseignez le nom d'utilisateur et le mot de passe que vous avez définis pour MySQL.

4. <b>Exécuter l'application</b>
Ouvrez un terminal à la racine du projet et exécutez la commande suivante pour lancer l'application :

```bash
mvn spring-boot:run
```


Si tout est correctement configuré, vous verrez Spring Boot démarrer et une sortie confirmant que le serveur est opérationnel sur http://localhost:8080
