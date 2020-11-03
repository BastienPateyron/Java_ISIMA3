# Exemple de base
## Code
Exemple.java
``` java
import java.io.*;

public class Exemple {
   public static int main(String[] args) {
      System.out.println(args[0]);
      return 0;
   }
}
```
## Compiler
```
javac [-classpath classpath] Exemple.java
```

## Exécution
```
java [-cp classpath] Exemple [arguments ...]
```

# Questions 
## Pourquoi le main doit-il retourner void ?
``` java 
public static int main(String[] args) {
```

Réponse: Parce que rien ne nous dit qu'à la fin du main notre programme soit complètement terminé (Threading)

## Combien de JVM lancées ?
-> Chaque commande 'java' créee sa propre instance de JVM pour lancer son programme.

## Jar (Java ARchive)
ZIP contenant
   - Définition de classes java
   - Métadonnées
   - Fichiers

### Exécution
```
java -jar monCode.jar
```

## Tableaux (taille fixe)
``` java
int [] t1 = new int[10];
t1.length();
```
Initialisés avec la valeur par défaut du type


## Collection (Taille variable)
### Implémentations
   * Linked list: liste chainée
   * ArrayList:   tableau redimensionnable
   * Vector
   * Stack

``` java
col.size();
```

Set: Liste sans doublons

SortedSet: Liste sans doublons triée


### Itérateurs
```java
public void printElements ( Collection c, PrintStream sortie ){
   Iterator it = c. iterator ();
   while (it. hasNext ()) {
      sortie.println (it.next());
   }
}
```

## Maps
   * HashMap
   * HashTable
   * LinkedHashMap

# Concepts
## Polymorphisme
### Héritage
```java
Vehicule a = new Voiture();   // Valide
Voiture  b = new Vehicule();  // Invalide
```

### Surcharge
   - Même nom
   - **Arguments différents**
   - Code similaire

### Redéfinition
   - Même nom
   - **Arguments similaires**
   - Code différent

### Super
Appel de la classe mère
```java
super.methodeMere(); // Appel valide
super.super.methodeGrandMere(); // Interdit !
```

### Constructeurs
Pas d'héritage de constructeur

## Fuites mémoires
### Static
Espace alloué dès le premier appel de la classe et jusqu'à la fin du programme
Faire attention à des objets trops gros

### equals / hashCode
A surcharger quand on travailler avec des maps par exemple
Conteneur associatif

## Statique
Une méthode statique ne peut accéder qu'à des variables déclarées **statiques**

### Initialisation d'une var statique
```java
// Il faut initialiser une variable statique
static int[] tab;

// Initialisation statique
static {
   for (int i = 0; i < 1000; i++) tab[i] = 1;
}
```

## Final
Une classe fille ne peut pas **surcharger** la méthode de la classe mère. Mais on peut redéfinir je pense.

## Encapsulation
Mécanisme objet de sécurisation des données.
On limite la visibilité des attributs et méthodes.

## Portée
   * public
   * protected -> Limité aux classes filles (directes)
   * package (défaut) accessible partout dans le package
   * private   -> Limité à la classe

## Abstraction
### Méthodes abstraites
```java
abstract public void dessiner();
```

### Classes abstraites
Non instanciable
Abstraite dès qu'on a une méthode abstraite

### Interface
Classe abstraite "pure" avec uniquement des méthodes abstraites.

```java
public class C extends A implements Interface_B {
   void methodeDeA() {} // redefinition
   void methodeDeB() {} // redefinition
}
```

## Exceptions
Tout est Throwable
Toute exception hérite de la classe **Exception**

### Throw

#### Déclarer les exceptions retournées
``` java
void readFromFile ( String path )
   throws IOException , ParseException {
      ...
}
```

#### Lancer une exception
```java
throw new SecurityException("Accès au fichier interdit");
```


### Catch
```java
try {
   readFromFile("nomFichier");
} catch (Exception e) {
   System.out.println("Exception lors de la lecture " + e);
}

```
----
----


# Tests

Le plus dur, c'est d'écrire du code **testable**.
Il vaut mieux faire **plein de méthodes** pour segmenter mon code.

## Types de test
Test Unitaire: test qu'une seule classe
| Test | Définition |
| --- | ------|
| Unitaire | Test qu'une seule classe
| Fonctionnel | Plusieurs classes en même temps ~ blocs de code
| Intégration | Check si le programme fonctionne avec un autre produit
| Performance | Test la rapidité
| Charge | Test avec un certain nombre d'utilisateurs
| Smoke | Jusqu'à un problème apparaisse (sur la BDD ou le compute par ex)
| Client-Simulé | Simule les intéractions utilisateur

## TDD
1. Ecrire les signatures
2. Ecrire les tests
3. Ecrire le code
4. Valider les tests
5. S'arrêter dès que ça marche

## Intégration continue
Lance les tests à chaque modification sur le dépôt
Renvoie un Feedback (mail, sms, allume une lampe...)

## Classe de Mock
Classe qui simule un comportement externe (BDD, Réseau, Fichier) de manière contrôlée. Très utile pour du test unitaire.

## Couverture de code
On vise 50 à 70 %
On essaie de tester uniquement les choses utiles et risquées

En Java:
* EMMA
* Jacoco


## JUnit
### Ecriture du test
```java
assertTrue(valeur)
assertEquals(valeur1,valeur2)
assertNull(valeur)
```

### Compilation des tests
```java
javac -cp . :junit -4. XX.jar CalculatorTest . java
```

### Exécution des tests
```java
java -cp .:junit -4. XX.jar org. junit . runner . JUnitCore CalculatorTest
```


# Entrées / Sorties 

## Types de flux
| InputStream | Flux d'entrée primitif |
|-------------|------------------------|
| OutputStream | Flux de sortie primitif |
| DataInputStream | Manipulation de types primitifs |
| ObjectInputStream | Manipulation d'objets (sérialisés) |
| FileInputStream | Manipulation de flux binaire |
| FileReader | Manipulation de fichier texte |
| GZIPInput/OutputStream | Manipulation de fichiers compressés (ZIP) |

# HTTP
HttpURLConnection: Slide 36
...

# String Builder: J'ai pas compris l'intérêt


# Introspection
La capacité pour un objet de regarder la strucutre de sa classe (et éventuellement la modifier)

## Méthodes
getMethods(): Toutes les méthodes publiques
getDeclaredMethods(): Toutes les méthodes (publiques, privées, protectected …)

## Invoque
Permet d'invoquer une méthode
```java
maMethode.invoke(objetCible, mesArguments[]); // objetCible = Null si statique
```

