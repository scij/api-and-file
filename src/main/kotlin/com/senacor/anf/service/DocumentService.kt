package com.senacor.anf.service

import org.springframework.stereotype.Service
import java.util.*

const val DOC_ID_1 = "11111111-1111-1111-1111-111111111111"
const val DOC_ID_2 = "22222222-2222-2222-2222-222222222222"

@Service
class DocumentService {

    private val docs = listOf(
                Document(UUID.fromString(DOC_ID_1),
                        "First Document", getDocumentFromClasspath("Doc1.pdf")),
                Document(UUID.fromString(DOC_ID_2),
                        "Second Document", getDocumentFromClasspath("Doc2.pdf"))
    )

    fun getDocuments(): Collection<Document> {
        return docs
    }

    fun get(id: UUID): Document? {
        return docs.find {
            it.id == id
        }
    }

    final fun getDocumentFromClasspath(filename: String): ByteArray =
        this::class.java.classLoader.getResourceAsStream(filename).readBytes()

}

data class Document(
        val id: UUID,
        val description: String,
        val content: ByteArray
)