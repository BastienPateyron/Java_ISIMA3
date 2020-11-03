
# Fonctionnement de base
arg[1]: Nom de classe java en entrée
arg[2]: Nom de classe C++ à générer (même nom que classe java par défaut)
arg[3]: optnion --stdout pour afficher sur sortie standard

types primitifs (int, float ...)
et String (faire un mapping des fonctions Java vers C++)

# Génération d'un makefile avec 3 règles
1. make-java   compile le java
2. run-java    lance la conversion Java vers CPP
3. make-cpp    compile le C++ généré