# Rapport Labo SMTP DAI

**Auteur:** Guilherme Pinto da Cunha da Mata

## Table des matières

1. [Introduction](#1-introduction)
2. [Description](#2-description)
3. [Configuration du serveur SMTP](#3-configuration-du-serveur-smtp)
4. [Configuration et exécution du client SMTP](#5-configuration-et-exécution-du-client-smtp)
   1. [Configuration messagesList.txt](#51-configuration-messageslisttxt)
   2. [Configuration victimsList.txt](#52-configuration-victimslisttxt)
   3. [Configuration Main.java](#53-configuration-mainjava)
5. [Implémentation](#6-implémentation)
   1. [Main.java](#61-mainjava)
   2. [ClientSMTP.java](#62-clientsmtpjava)
   3. [Message.java](#63-messagejava)
   4. [Email.java](#64-emailjava)
   5. [ConfigManager.java](#65-configmanagerjava)
   6. [GroupEmail.java](#66-groupemailjava)
6. [Conclusion](#7-conclusion)
7. [Références](#8-références)
   - [SMTP RFC](https://datatracker.ietf.org/doc/html/rfc5321)
   - [MailDev - Serveur SMTP de test](https://github.com/maildev/maildev)
   - [Guide de démarrage rapide](#configuration-et-exécution-du-client-smtp)

## 1. Introduction

Ce rapport présente le travail réalisé dans le cadre du laboratoire n°4 du cours de DAI à l'HEIG-VD. L'objectif 
principal était de mettre en pratique les concepts du protocole SMTP en développant un client SMTP en Java. Ce client 
devait être capable d'envoyer des emails de manière automatisée en respectant certaines contraintes définies dans les 
spécifications du laboratoire.

## 2. Description

Ce répertoire GitHub contient le laboratoire 4 du cours de DAI à l'Heig-vd. Ce laboratoire a comme objectif de réaliser 
un client SMTP qui soit capable d'envoyer des emails. L'utilisateur doit fournir comme configuration un fichier texte 
contenant une liste d'adresses email, un fichier texte contenant les messages à envoyer (sujet et corps).

## 3. Configuration du serveur SMTP 

Pour les tests, nous recommandons d'utiliser le serveur SMTP de test MailDev. Pour le démarrer, utilisez 
la commande Docker suivante :

```bash
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```
Cela fournira une interface web sur `localhost:1080` et un serveur SMTP sur `localhost:1025`.

Si vous n'avez jamais utilisé le serveur SMTP de maildev vous pouvez tout d'abords lancer la commande suivante:

```bash
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```

Pour plus de détails ou cas de problème avec maildev veuillez vous référez au lien suivant 
[MailDev - Serveur SMTP de test](https://github.com/maildev/maildev).


## 5. Configuration et exécution du client SMTP

Suivez ces étapes simples pour configurer et exécuter le client SMTP :

1. Clonez ce dépôt GitHub.
2. Modifiez les fichiers de configuration `messagesList.txt` et `victimsList.txt` selon vos besoins.
3. Modifiez le fichier Main pour le nombre de groupe souhaité, ce qui définira le nombre d'emails envoyé. 
4. Lancez le serveur SMTP maildev.
5. Exécutez le client SMTP en utilisant la classe `Main.java`.
6. Pour voir les mails reçus, ouvrez votre navigateur puis tapez votre adresse IP suivi de ":" et du port utilisé. 
Si vous avez conservé la configuration de base tapez "localhost:1080"

### 5.1 Configuration messagesList.txt

Dans ce fichier chaque message doit contenir un champ sujet qui commence par "Subject:". La séparation entre les 
différents messages doit se faire avec deux tirets "--".

### 5.2 Configuration victimsList.txt

Il suffit d'introduire un mail par ligne.

### 5.3 Configuration Main.java

Dans la classe Main il y a des attributs qui peuvent être initialisés selon les souhaits de l'utilisateur. 
La variable nbGroup détermine le nombre de groupes formés. Pour chaque groupe de personnes un email sera envoyé.

## 6. Implémentation

![UML](C:\Users\guisp\IntelliJ\Lab4SMTP\UML.png "UML").

### 6.1 Main.java

Le fichier `Main.java` contient la classe principale qui orchestre le processus d'envoi d'emails. Il charge la 
configuration à partir des fichiers, crée des instances de messages et d'adresses email, puis utilise 
la classe `ClientSMTP` pour envoyer les emails.

### 6.2 ClientSMTP.java

La classe `ClientSMTP.java` représente le client SMTP lui-même. Elle utilise la classe `Socket` pour établir une 
connexion avec le serveur SMTP, puis envoie les commandes SMTP nécessaires pour transférer les emails.

### 6.3 Message.java

La classe `Message.java` modélise un message email avec un sujet et un corps.

### 6.4 Email.java

La classe `Email.java` représente un email complet, comprenant l'expéditeur, les destinataires, le sujet et le corps. 
Elle utilise la classe `GroupEmail` pour gérer les groupes de destinataires.

### 6.5 ConfigManager.java

La classe `ConfigManager.java` gère la lecture des fichiers de configuration, notamment les adresses email et les 
messages. Elle assure également la validation des données.

### 6.6 GroupEmail.java

La classe `GroupEmail.java` représente un groupe d'emails avec un expéditeur et plusieurs destinataires. Elle utilise 
une expression régulière pour valider les adresses email.

## 7. Conclusion

Ce client SMTP offre une solution robuste pour l'envoi automatisé d'emails en respectant les exigences du laboratoire.
L'utilisation du serveur SMTP de test facilite les essais sans envoyer de vrais emails. L'implémentation respecte les 
principes de la programmation orientée objet et maintient la séparation des préoccupations.

## 8. Références

- [SMTP RFC](https://datatracker.ietf.org/doc/html/rfc5321)
- [MailDev - Serveur SMTP de test](https://github.com/maildev/maildev)

N'oubliez pas de consulter le [guide de démarrage rapide](#configuration-et-exécution-du-client-smtp) pour une utilisation facile du client SMTP.

