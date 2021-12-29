# Doutes

> 19-12-2021

J'ai des doutes sur l'utilisation d'une séparation User request et User response alors que j'ai createUserCommand et que jaime beaucoup cette objet

Solution pour trancher : Faire d'autre use case et voir ce qu'il se passe.

> 20-12-2021

J'ai envie de faire un système d'erreur qui me permet de gere beaucoup de cas

Pour aujourd'hui je pense surtout me focus sur les tests et le refacto en handler

> 28-12-2021

Je vais crée un document pour lister les erreurs via des codes.

je pense aussi a mettre a jour mes données, pour que ça soit raccord avec le sujet.

---

## Gestion des erreurs

Je me demande comment est ce que je peux intérrpeter les erreurs que quarkus catch a ma place.

### Erreurs

- Password policy
- email already existe
- email invalide
- age non conforme
- Transaction imposible fond inssufisant
- Transaction impossible les deux utilisateurs ne sont pas validé
- database issue (j'arrive pas a ecrire dedans)

## Todo

- Massuré que quand on crée un user on peut bien le GET
- Faire un readme
  - Parler de swagger
  - Parler de comment lancer le projet
  - Parler de la version de java ...
- Faire un petit détail des dépendances que j'ai
- Faire un schema de l'architecture que j'ai mise en place
- expliqué certain choix au travers d'une ADR

Ce n'est pas dans le role de l'entité Account d'avoir un event bus et de lancer des événements.

refacto de manière a n'avoir qu'un event bus
