# Outlaw
Lancement : `./gradlew quarkusdev` <br/>
Utilise le port 8100

## Rôle du service
Simuler des appels à myGreffe :
 - /health
 - /convergence-greffe-web/rest/kbis/recupererPdf : retourne un pdf correspondant à un Kbis
 
## Build natif
###Generation de l'executable:
`./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:{mandrel-flavor}`
###Generation de l'image docker:
`docker build -f src/main/docker/Dockerfile.native -t mask/outlaw .`
###Lancement de l'image docker:
`docker run -i --rm -p 8100:8100 mask/outlaw`