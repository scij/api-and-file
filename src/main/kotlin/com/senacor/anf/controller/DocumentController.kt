package com.senacor.anf.controller

import com.senacor.anf.controller.model.DocumentResource
import com.senacor.anf.service.Document
import com.senacor.anf.service.DocumentService
import io.crnk.core.exception.ResourceNotFoundException
import io.crnk.core.queryspec.QuerySpec
import io.crnk.core.repository.ResourceRepositoryBase
import io.crnk.core.resource.list.ResourceList
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import java.util.*

private val log = KotlinLogging.logger {  }

@Component
class DocumentController(
        private val documentService: DocumentService
) : ResourceRepositoryBase<DocumentResource, UUID>(DocumentResource::class.java) {

    override fun findOne(id: UUID, querySpec: QuerySpec): DocumentResource {
        val doc = documentService.get(id)
        if (doc != null) {
            return doc.toResource()
        } else {
            throw ResourceNotFoundException(id.toString())
        }
    }

    override fun findAll(querySpec: QuerySpec): ResourceList<DocumentResource> {
        log.debug("Query spec = {}", querySpec)
        val docs = documentService.getDocuments()
                .map { it.toResource() }
        return querySpec.apply(docs)
    }
}

fun Document.toResource(): DocumentResource {
    return DocumentResource(
            id = this.id,
            description = this.description,
            content = this.content
    )
}