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






Here's a simple footnote,[^1] and here's a longer one.[^bignote]

[^1]: This is the first footnote.

[^bignote]: Here's one with multiple paragraphs and code.

    Indent paragraphs to include them in the footnote.

    `{ my code }`

    Add as many paragraphs as you like.




