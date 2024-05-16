# Retro-conception
 
**Binome 1 : [Mounib Malik]**
**Binome 2 : [Barrat Adrien]**
 
Complétez ce document pour décrire votre projet, les difficultés rencontrées, les design patterns mis en oeuvre, les améliorations possibles, et en quoi la POO vous a été utile.
 
> **Faites le avec sérieux, ce document ainsi que votre code seront évalués.**
Si vous considérez que votre code est quasi-parfait, je vais chercher les erreurs et en trouver, et cela vous pénalisera.
Si vous êtes critique envers vous-même et que vous expliquez correctement vos difficultés, vous serez "à moitié" pardonné.
 
Ce n'est pas grave de ne pas avoir été au bout de ce que vous vouliez faire, comportez vous comme un ingénieur qui doit rendre des comptes à son client, et qui doit expliquer pourquoi il n'a pas pu tout faire.
Pour rappel le client n'est pas un developpeur (sinon il ne vous aurait pas payé une fortune pour le faire à sa place), vous devez lui expliquer de manière simple et claire les problèmes rencontrés, en vous justifiant 
>Imaginez que vous avez codé Mortal Kombat 
Ne dites pas "je n'ai pas eu le temps de tout faire", mais plutôt "j'ai préféré me concentrer sur la création des spectaculaires "Finish Him" des personnages car c'est un élément essentiel du gameplay, cependant la difficulté dynamique en fonction de la maîtrise du joueur n'a pas pu être implémentée à temps, compte tenu que les critères de maîtrises sont difficilement modélisables, toutefois nous pourrions envisager d'implémenter que plus le combat dure longtemps, plus les coups portés sont puissants, ce qui est rapide à implémenter et lors d'une mise à jour, nous pourrions remplacer ce système par quelque chose de plus élaboré"
 
Aussi, en entreprise, vous serez confronté à des programmes très mal codés, et vous allez galérer à les comprendre, vous risquez d'essayer de les corriger et tomber dans les mêmes ecueils que les développeurs précédents.
Pour cela, il est courrant de tenir un jour un Document d'Architecture Technique (DAT) qui explique comment fonctionne le programme, et comment le reprendre ainsi qu'un document de réversibilité qui explique comment reprendre le code de quelqu'un d'autre.
(C'est obligatoire pour les marchés publics de prévoir une réversibilité, c'est à dire que le client peut vous dégager et une autre entreprise doit pouvoir reprendre votre code sans difficulté)
Dans ces documents, il ne s'agit pas de cacher la poussière sous le tapis, il faut être honnête et proposer une vision d'ensemble de votre code, et expliquer pourquoi vous avez fait des choix, et pourquoi vous n'avez pas fait d'autres choix, il est souvent question de compromis (on fait un truc pas ouf pour gagner du temps, mais la qualité du code en pâtit, etc.)
> Vous pouvez dire : "Pour la gestion des collisions, nous utilisons une librairie tierce, toutefois celle-ci ne gère que les collisions entre des rectangles, au fur et à mesure des itérations, des ennemis de grande taille et de forme complexe sont apparus, toutefois, nous avons conservé une hitbox rectangulaire, en résulte que le joueur peut être touché alors que visuellement, il n'est pas en contact avec l'ennemi ; nous avions également envisagé de créer plusieurs hitbox de tailles différentes sur un même ennemi afin de mieux coller à la forme de celui-ci, toutefois, les performances du jeu ont étés trop dégradées"
 
 
---
# Partie "Client" (pas trop technique) :
 
## Objectif du projet
 
[Décrivez ici l'objectif initial du projet, ne cherchez pas à le minorer si vous n'avez pas tout fini, décrivez ce que vous avez voulu faire]
Le projet reprends le jeu time bomb. 
Dans ce jeu, les participants font partie de deux équipes secretes aux objectifs différents. L'équipe de Moriarty cherche à trouver une carte "explosion" alors que celle de Sherlock doit couper les câbles permettant de désamorcer la bombe.
Allant de 4 à 6 participants le jeu ce deroule par système de manche. Dans un manche il y a autant de tour que de joueurs present dans la partie.
A chaque manche on remélange les cartes et les redistribuent.
 
## Résultat
 
[Avez vous atteint votre objectif ?]
 
Oui mais des améliorations son encore possible quelles soient graphiques ou dans le back.
 
### Améliorations possibles
 
[Décrivez ici les améliorations que vous auriez pu apporter si vous aviez eu plus de temps]
- Rendre plus beau visuelement et plus estéthique.
- Peut-être faire plusieurs interface fenêtre pour les utilisateurs 
- mettre toute l'architecture sous serveur et rendre le projet accessible sur plusieurs appareils en même temps
 
---
# Partie "Développeur" (plus technique) :
 
 
### Implémentations remarquables
 
[Si pendant votre implémentation, vous trouvez que vous pouvez être particulièrment fiers d'une partie de votre code, décrivez là ici ; par exemple si vous avez généré une carte de manière procédurale, ou à l'aide d'un fichier]
L'initialisation de la partie.
Notre architecture est faite de sorte à etre facilement modulable, ainsi l'amélioration et l'implémentation de DLC est accessible.
Explication : On à une classe abstraite de carte qui débouche sur différent type de cartes. De même l'implémentation de roles ce fait de la même manière.

Le code de distribution de carte permet de s'adapté, peut importe le nombre de carte.

### Faiblesses du code
 
[C'est ici que vous me dites ce que vous savez que vous avez mal fait, expliquez pourquoi vous avez fait ce choix (manque de temps, manque de compétence, trop pénible à faire, etc.)]
 
Au départ le code était fait pour de la console le fait de devoir le convertir et l'adapter au java.fx nous à fait perdre du temps.
Au départ on faisait des fonctions beaucoup trop longues ce qui nous perdaient dans le code.
 
### Difficultés rencontrées
 
#### 1. [Génération dynamique des ... pour ...]
du nombres d'utilisateurs et des cartes.
 
[Expliquez ici la difficulté rencontrée et comment vous l'avez contournée]
Problème avec l'interface graphique. Pour régler cela on à appris comment marchait la téchnologie.
le fait de devoir assigner les roles en faisait attention à ce que ca soit bien aléatoire.
A savoir que pour une partie de 4 personnes on prends 5 roles (3scherlocks et 2 moriaty) et on distribue aléatoire les cartes en ayant un carte rôles non-assignée à la fin.
Ce qui veut dire qu'on peut avoir un 1 moriaty contre 3 sherlock ou  2 moriaty contre 2 scherlock. Pour la contourné nous avons decidé de definir directement le nombre de rôles. 
On aura alors :
4 joueurs : 1 moriaty 3 scherlock
5 joueurs : 2 moriaty 3 scherlock
6 joueurs : 3 moriaty 3 scherlock
 
#### 2. [Gestion des collisions]
 
[Exemple : Nous n'avons pas réussi à gérer les collisions, PARCE QUE, mes objets n'héritaient pas d'une classe commune, car nos objets héirtaient de ... et nos personnages de ...]
On en a pas rencontré.
 
 
### *Design Patterns* mis en oeuvre
 
#### 1. [Factory]
[Décrivez ici brièvement le design pattern utilisé et pourquoi]
On a utilisé un desgin patern factory, car il était beaucoup plus facile à gérer.
On aurait bien aimé utiliser un singleton pour la partie mias nous avons rencontré des difficulté pour son utilisation.
[Ajouter éventuellement des exemples de code pour montrer l'élégence de votre solution, pour cela vous pouvez écrire en Markdown votre code ainsi :
 
<pre>
```java 
private void handleCardAction(Carte carte, Button button, Joueur joueurChoisi) {
        carte.estRetourner();
        button.setGraphic(carte.getImageView());
        if (carte instanceof Desamorceur){
            partie.removeOneDesamorceur();
        }
        changeNBDesamorceurs();
        if (carte instanceof Bomb){
            showAlert("Fin du jeu", "Une bombe a explosé... L'équipe Moriarty gagne!");
            return;
        }
        if(partie.verifDesamorceur()){
            showAlert("Fin du jeu", "Tout les désamorseurs ont été trouvé ");
            return;
        }
        partie.isMancheFinit();
 
 
        changePlayer(partie.getJoueurActuel(),joueurChoisi);
 
    }
 
```
 </pre>
---
# Partie pédagogique
 
 
### En quoi la POO vous a été utile
 
[Par exemple, expliquez que vous auriez éprouvé des difficultés à gérer les collisions si vous n'aviez pas utilisé la POO, ou que vous avez pu facilement ajouter des fonctionnalités à votre jeu grâce à la POO
Minimum 10 lignes (personnalisé en fonction de votre projet)]
 
 
La POO nous à été utile pour clarifier le code et le rendre plus efficace par exemle l'utilisation d'une class abstract pour gérer les différents type de carte permettait ainsi de garder les meme fonctions réalisant des actions différentes en fonction de si elle sont Bombe, désarmorceur ou null. Par exemple pour cette partie la on peut penser visuellement. Lorsque on souhaite afficher les cartes on doit avoir différent affichage pour que les utilisateur puissent voir visuel quelle cartes correspond à quoi. Cela implique donc une meme bases mais différentes données en fonction du type de la carte. 
De plus cela permettait de facilement ajouter de nouvelles extensions à chaque cartes, puisque des que l'ont souhaite faire une modification, elle était faisable sans perturber l'ensemble du programme et donc cela le rend plus flexible.
La POO nous à donc permis de garder un code claire et facilement manipulable pour le modifier. Cela permet donc d'amélioration durable dans le temps et nous pouvons donc réfléchire à l'implémentation de nouvelles feature.
 
 
### Conclusion
 
[Décrivez ici si vous avez compris un concept particulier que vous n'aviez pas compris en cours, inversement si vous pensiez qu'il était possible de faire qqchose mais que cela ne s'est pas passé comme prévu]
 
Oui le concept de singletton à été un des principaux problème puisque il a été difficile à comprendre et mal compris et
malheureusement, sa mise en place n'a pas été possible par manque de temps du à l'apprentissage de javaFX (manque cruel de documentation à jours et claire).

