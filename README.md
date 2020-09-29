# Exemple de base
## Code
Exemple.java
```
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
```
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
```
int [] t1 = new int[10];
t1.length();
```
Initialisés avec la valeur par défaut du type


## Collection (Taille variable)
```
...
col.size();
```



