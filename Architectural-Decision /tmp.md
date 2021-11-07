Il faut que je parle du choix du répos
Il faut que je parle du fait d'avoir crée un Engine de validation et de ne pas throw dedans
Le choix de mettre le throw dans le repo plutot pour l'email exist déjà
Le choix des exceptions plutot que des notifications
J'ai fais des test avec le nombre de call sur des dummys pour tester l'event bus, j'ai utiliser mockito pour ça
Je pars du principe qu'un Money ne peut pas avoir de valeur négative, c'est potentiellement faut, mais dans ce cas le code sera facile a modifier
Le choix de l'exception custom sur la création d'argent
Parler de primitive obession pour money