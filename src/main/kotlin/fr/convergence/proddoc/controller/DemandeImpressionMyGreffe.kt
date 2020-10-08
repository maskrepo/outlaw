package fr.convergence.proddoc.controller

import fr.convergence.proddoc.model.lib.obj.MaskMessage
import fr.convergence.proddoc.model.metier.DemandeImpression
import fr.convergence.proddoc.model.metier.RetourImpressionMyGreffe
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response

@ApplicationScoped
@Path("/convergence-greffe-web/rest/impression/")
class DemandeImpressionMyGreffe {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(DemandeImpressionMyGreffe::class.java)
    }

    @Inject
    @field: Channel("mygreffe_impression_reponse")
    val retourEmitter: Emitter<MaskMessage>? = null

    /**
     * pour simuler une réponse OK sur une demande d'impression
     */

    @Path("demandeOK")
    @GET
    fun repondreOKsurTopic( @QueryParam("urlfichieraimprimer") urlFichieraImprimer: String,
                            @QueryParam("IDSortieDocument") IDSortieDocument: String,
                            @QueryParam("nbExemplaires") nbExemplaires: String,
                            @QueryParam("nomBacEntree") nomBacEntree: String,
                            @QueryParam("rectoVerso") rectoVerso: String,
                            @QueryParam("nomImprimante") nomImprimante: String) : Response.ResponseBuilder? {

        LOG.info("Construction d'une réponse de type \"impression OK\" ")
        val fakeQuestionMessage = MaskMessage.question( DemandeImpression(   IDSortieDocument,       urlFichieraImprimer,
                                                                            rectoVerso.toBoolean(), nomImprimante,
                                                                            nomBacEntree,           nbExemplaires.toInt()),
                                                    "idLotBidon", "idEmetteurBidon", "idReferenceBidon",
                                                    "idGreffeBidon", "IMPRESSION")
        var messageOut = MaskMessage.reponseOk(RetourImpressionMyGreffe(IDSortieDocument, urlFichieraImprimer,true),
                                                fakeQuestionMessage, urlFichieraImprimer)
        retourEmitter?.send(messageOut)

        return Response.ok("impression OK")
    }


    /**
     * pour simuler une réponse KO sur une demande d'impression
     */
    @Path("demandeKO")
    @GET
    fun repondreKOsurTopic( @QueryParam("urlfichieraimprimer") urlFichieraImprimer: String,
                            @QueryParam("IDSortieDocument") IDSortieDocument: String,
                            @QueryParam("nbExemplaires") nbExemplaires: String,
                            @QueryParam("nomBacEntree") nomBacEntree: String,
                            @QueryParam("rectoVerso") rectoVerso: String,
                            @QueryParam("nomImprimante") nomImprimante: String) {

        LOG.info("Construction d'une réponse de type \"impression KO\" ")
        val fakeQuestionMessage = MaskMessage.question( DemandeImpression(   IDSortieDocument,       urlFichieraImprimer,
                rectoVerso.toBoolean(), nomImprimante,
                nomBacEntree,           nbExemplaires.toInt()),
                "idLotBidon", "idEmetteurBidon", "idReferenceBidon",
                "idGreffeBidon", "IMPRESSION")
        var messageOut = MaskMessage.reponseKo<Exception>(java.lang.Exception("impression KO"),fakeQuestionMessage, "idReferenceBidon")
        retourEmitter?.send(messageOut)

    }
}