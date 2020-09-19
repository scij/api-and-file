package com.senacor.anf.ext

import io.crnk.core.engine.dispatcher.Response
import io.crnk.core.engine.document.Resource
import io.crnk.core.engine.http.HttpRequestContext
import io.crnk.core.engine.http.HttpResponse
import io.crnk.core.engine.http.HttpStatus
import io.crnk.core.engine.internal.http.JsonApiRequestProcessor
import io.crnk.core.module.Module
import mu.KotlinLogging
import org.springframework.http.MediaType

private val log = KotlinLogging.logger {}

class BinaryRequestProcessor(moduleContext: Module.ModuleContext?)
    : JsonApiRequestProcessor(moduleContext) {

    override fun accepts(context: HttpRequestContext?): Boolean {
        log.debug("accepts {}", context)
        return context != null && context.accepts(MediaType.APPLICATION_PDF_VALUE)
    }

    override fun toHttpResponse(response: Response): HttpResponse {
        val httpResponse = HttpResponse()

        log.debug("Generating http response for {}", response)
        httpResponse.body = null
        if (response.document.data.isPresent) {
            val docContent = response.document.data.get() as Resource
            if (docContent.attributes["content"] != null) {
                httpResponse.body = docContent.attributes["content"]?.binaryValue()
                httpResponse.contentType = MediaType.APPLICATION_PDF_VALUE
                httpResponse.statusCode = HttpStatus.OK_200
            } else {
                httpResponse.statusCode = HttpStatus.NOT_FOUND_404
            }
        }
        return httpResponse
    }
}