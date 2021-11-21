# Objectif du projet

Conception & développement d’un module logiciel de gestion des inscriptions  d’une solution e-commerce, 
comportant une fonction d’ajout d’un membre.

## Descriptif

- Respect des patterns logiciels et des concepts objets :
  - Pattern value Objectif
  - Pattern Entity
  - Pattern Repository
  - Pattern Strategy
  - Injection de dependance
- Favoriser une approche modulaire du code en gardant un maximum d'options le plus longtemps possible
- Favoriser le polymorphisme
- Respecter l'intention métier dans le code
- Utiliser des stubs / dummy
- Justifier certains critères de qualité si besoin

# Sujet

![sujet](assets/sujet.png)

- il faut cd swannherrera
- faire le logger tout ça
- les choix techniques réalisé
  - Passer par plusieurs event Bus
  - utiliser un système de command
  - primitive obcession
  - le système d'account
- il manque un système de gestion des dependence
- phylosophie YAGNI
- mon account est différencié de la persistance
- je me suis beaucoup inspiré pour la logger factory


Test command
`mvn clean jacoco:prepare-agent install jacoco:report`