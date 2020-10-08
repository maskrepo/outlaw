# Outlaw
Lancement : `./gradlew quarkusdev` <br/>
Utilise le port 8100

## Rôle du service
Simuler des appels à myGreffe :
 - /health
 - /convergence-greffe-web/rest/kbis/recupererPdf : retourne un pdf correspondant à un Kbis
 - /convergence-greffe-web/rest/impression/demandeOK : demande d'impression qui se passera BIEN, retourne un message OK sur le topic MYGREFFE_REPONSE_IMPRESSION <br>
Exemple d'appel : http://localhost:8100/convergence-greffe-web/rest/impression/demandeOK?urlfichieraimprimer="TATATATATATA"&IDSortieDocument=sortiedocbidon&nbExemplaires=2&nomBacEntree=marianneRV&rectoVerso=true&nomImprimante=Ricoh_accueil
  - /convergence-greffe-web/rest/impression/demandeKO : demande d'impression qui se passera MAL, retourne un message KO sur le topic MYGREFFE_REPONSE_IMPRESSION<br>
Exemple d'appel : http://localhost:8100/convergence-greffe-web/rest/impression/demandeKO?urlfichieraimprimer="TATATATATATA"&IDSortieDocument=sortiedocbidon&nbExemplaires=2&nomBacEntree=marianneRV&rectoVerso=true&nomImprimante=Ricoh_accueil 
 
## Build natif
###Generation de l'executable:
`./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:{mandrel-flavor}`
###Generation de l'image docker:
`docker build -f src/main/docker/Dockerfile.native -t mask/outlaw .`
###Lancement de l'image docker:
`docker run -i --rm -p 8100:8100 mask/outlaw`