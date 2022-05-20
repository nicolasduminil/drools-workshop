# Drools workshop

Ce projet est dédié à illustrer le workshop organisé autour de la gestion des règles métier (BRM: *Business Rules Management*) dans Omega et notamment dans GDI.

L'utilisation d'un moteur d'inférence (BRE: *Business Rules Engine*), dans des applications d'entreprise comme Omega, est une très bonne prattique.

## Avantages

En tant que développeurs Java, nous aurions le reflèxe d'implémenter en Java les règles métier. Est-ce une mauvaise pratique ? En soi, sans doute non. Mais le bénéfice réel d'un moteur d'inférence n'est pas évident lorsqu'il s'agit d'un nombre limité de règles métier, mais plutot lorsqu'on doit en gérer plusieurs dizaines, voir plusieurs centaines. Lorsqu'on a besoin d'implémenter des nouvelles règles, de modifier des règles existantes, de remplacer des paramètres ou de changer la structure de notre système de règles d'une manière innattendue, un moteur d'inférence s'avère beaucoup plus pratique qu'une implémentation Java car:

* Il permet de gérer et rédiger indépendament chaque règle metier.
* Il permet de centraliser dans un endroit unique l'ensemble de règles métier.
* Il permet de diviser les règles complexes dans des règles plus simples et de les enchainer.
* Il remplace des séquence de code Java avec des structures if ... then ... else extremement imbriquées et présentant une compléxité cyclomatique élevée.
* Il permet l'éxécution atomique des règles.
* Il permet d'impliquer les business analystes directememnt dans le processus de rédaction et test des règles métier.

## Quand faut il adopter un moteur d'inférences ?

* Lorsque le système est composé d'un grand nombre de règle métier.
* Lorsque ces règles sont complèxes et dificillement définissables, y compris par des experts métier.
* Lorsque les spécifications sont plutot volatiles et des règles à la vollée apparaissent souvent, nécessitant des mises à jour de l'ensemble du système.

## Quand ne pas utiliser des moteurs d'inférences

Lorsqu'on a un petit nombre de règles à gérer

Lorsque le domaine métier est de telle nature qu'il ne change jamais ou très rarement

## Déploiement et tests

Pour déployer et tester ce projet, exécutez le script build.sh se trouvant à la racine du répertoire, une fois le repository GIT cloné. Un certain nombre de tests unitaires et d'intégration, avec ou sans Arquillian, est disponibles.

Le projet est un projet maven avec des sous-projets, comme suit:

* gdi: le POM parent
* gdi-cdi: démontre l'utilisation de Drools avec CDI (*Common Dependencies Injection*)
* gdi-ci: démontre l'utilisation de Drools dans un context CI (*Continuous Integration*)
* gdi-cp: démontre l'utilisation de Drools dans un contexte basé sur le classpath
* gdi-kjar: démontre le packaging et le déploiememnt d'un KJAR (*KIE Java Archive*)
* gdi-model: défini le domaine métier utilisé dans le cadre du workshop
* gdi-common: bibliothèque commune utilisée par l'ensemble des sous-projets
* gdi-war: démontre le packaging et le déploiement des règles métier sous forme de WAR
* gdi-war-dt: démontre le packaging des règles métier sous forme de tables de décisions (Excel ou CSV) déployées comme des WARs
* gdi-kie-server: POM parent du KIE Server
* gdi-kie-server-war: démontre le packaging et le déploiement du KIE Server
* gdi-kie-server-tests: tests unitaires et d'intégration du KIE Serrver
