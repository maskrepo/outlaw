# Outlaw
Lancement : ./gradlew quarkusdev <br>
(port 8100)

## Rôle du service
Simuler des appels à myGreffe :
 - /convergence-greffe-web/rest/kbis/recupererPdf : retourne un pdf correspondant à un Kbis
 - /convergence-greffe-web/rest/impression/demandeOK : demande d'impression qui se passera BIEN, retourne un message OK sur le topic MYGREFFE_REPONSE_IMPRESSION <br>
Exemple d'appel : http://localhost:8100/convergence-greffe-web/rest/impression/demandeOK?urlfichieraimprimer="TATATATATATA"&IDSortieDocument=sortiedocbidon&nbExemplaires=2&nomBacEntree=marianneRV&rectoVerso=true&nomImprimante=Ricoh_accueil
  - /convergence-greffe-web/rest/impression/demandeKO : demande d'impression qui se passera MAL, retourne un message KO sur le topic MYGREFFE_REPONSE_IMPRESSION<br>
Exemple d'appel : http://localhost:8100/convergence-greffe-web/rest/impression/demandeKO?urlfichieraimprimer="TATATATATATA"&IDSortieDocument=sortiedocbidon&nbExemplaires=2&nomBacEntree=marianneRV&rectoVerso=true&nomImprimante=Ricoh_accueil 