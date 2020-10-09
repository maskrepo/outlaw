package fr.convergence.proddoc.controller

import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import java.io.FileNotFoundException
import java.io.FileOutputStream
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@ApplicationScoped
@Path("/convergence-greffe-web/rest/kbis/recupererPdf")
class KbisDummy {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(KbisDummy::class.java)
    }

    @GET
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    fun delivrerKbis(@QueryParam("numeroGestion") numeroGestion: String?): Response {
        val fileOutputStream = this::class.java.getResource("/kbis.pdf").openStream()
        LOG.info("Délivre le kbis : $fileOutputStream")
        return Response.ok(fileOutputStream).build()
    }
}