# Trade Me

## [Lien github](<(https://github.com/SwannHERRERA/CC1-AL-4)>)

Le CC2 est dans le repo du cc1 que je vais renommé une fois corrigé.

le cc2 est dans un nouveau dossier (cc2). L'arrivé de quarkus ma plutôt amnené à recrée un projet et a migré mes class dedans plutôt que de rajouté quarkus dans le projet du CC1.

## Choix

J'ai donc choisi Quarkus principalement par curiosité.

Certain choix technique sont détailler dans le dossier Architectual Decision

- [IOC container et expostion](Architectural-Decision/Exposition_and_configuration.md)
- [user exceptions](Architectural-Decision/UserExceptions.md)

J'ai décidé de faire un package shared et un package use case pour avoir à la fois un découpage par structure de fichier mais quand même d'avoir une base commune.

## Gestion des erreurs

Ici je passe uniquement par quarkus c'est a dir eque je déclanche des excetpions (java ou custom). Et je crée des Handlers dans mon dossier expostion qui renvoie une réponse associé.

### Erreur client

Il se peut que l'on fasse une erreur dans le formatage de la query, ou de la command dans sa requette HTTP ou autre, ce type d'erreur est directement géré par Quarkus grace a un system de validator sur les "Request"

## Package exposition

Il y a pluisieurs package exposition un dans chaque use_case un dans shared et un dans java qui consert l'exposition et ses "port" vers l'exterieurs.

Dans ce package j'ai a la fois les controllers web mais aussi le container IOC, une route qui est utilisé par le scheduler

## Use case

J'ai 3 use case pricipaux :

- AddMoney (rajout d'argent sur son compte de la part d'un utilisateur)
- register (inscription)
- transaction (process lié a la finance)

## CQS

Depuis le CC1 j'utilise un wrapper pour mes commands.
par contre j'utilisais un userService. qui aujourd'hui est découpé en commandHandler et QueryHandler.

## Money

Comme dans le CC1 je garde cette idée de value object nottament pour ce qui est de l'argent dans l'application.

### Transaction

Par contre j'ai crée un objets transaction qui est un évenement.

## UserBuilder

L'entité User commançais a devenir un peu lourde j'ai donc décidé de la découpé en plusieurs plus petite:

- account
- subscribtion
- professional Abilities

Pour des raisons de simplcité et j'ai crée un builder pour le user avec l'intention d'être fluent dans la sythaxe.
qui permet de crée un utilisateur sans savoir connaitre le découpage interne

## Test

L'application est partiellement couverte par des test unitaires nottament sur la partie domaine.

J'ai essayé de faire des tests d'intégrations avec quarkus, car je trouve la sythaxe intéréssante. Je ne suis pas aller jusq'au bout. C'est surement quelque chose que je vais essayé de continué par la suite.

## Swagger UI

Quarkus me permet de généré une interface swagger qui est accèssible en mode developpement sur [swagger: http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

Elle a en plus l'interêt d'exposé tout les DTO

## Lancement de l'application

> Etre dans le folder cc2

```sh
./mvnw compile quarkus:dev
```

Cette comande lance a la fois l'application en mode developpement mais aussi les test et l'interface swagger.

personnelement j'utlise mon IDE tout de même pour lancé les tests.
