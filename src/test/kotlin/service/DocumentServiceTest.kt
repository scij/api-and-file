package service

import com.senacor.anf.service.DOC_ID_1
import com.senacor.anf.service.DOC_ID_2
import com.senacor.anf.service.DocumentService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class DocumentServiceTest {

    private val fix = DocumentService()

    @Test
    fun `Test find one function for existing doc` () {
        val doc = fix.get(UUID.fromString(DOC_ID_1))
        Assertions.assertNotNull(doc)
        Assertions.assertEquals(doc?.id.toString(), DOC_ID_1)
    }

    @Test
    fun `Test find the other document` () {
        val doc = fix.get(UUID.fromString(DOC_ID_2))
        Assertions.assertNotNull(doc)
        Assertions.assertEquals(doc?.id.toString(), DOC_ID_2)
    }

    @Test
    fun `Test find non existing document` () {
        val doc = fix.get(UUID.fromString("99999999-9999-9999-9999-999999999999"))
        Assertions.assertNull(doc)
    }

    @Test
    fun `Test find all documents returns two docs` () {
        val docs = fix.getDocuments()
        Assertions.assertNotNull(docs)
        Assertions.assertEquals(docs.size, 2)
    }

    @Test
    fun `Test load document` () {
        val doc = fix.getDocumentFromClasspath("Doc1.pdf")
        Assertions.assertNotNull(doc)
        Assertions.assertEquals(17388, doc.size)
        Assertions.assertArrayEquals("%PDF".toByteArray(), doc.sliceArray(IntRange(0,3)))
    }
}