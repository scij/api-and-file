package com.senacor.anf.controller

import com.senacor.anf.service.DOC_ID_1
import io.crnk.servlet.CrnkFilter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockFilterConfig
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootTest
@EnableWebMvc
@AutoConfigureMockMvc
class BinaryResponseTest(
        @Autowired val mockMvc: MockMvc,
        @Autowired val crnkFilter: CrnkFilter
) {

    @BeforeEach
    fun init() {
        crnkFilter.init(MockFilterConfig())
    }

    @Test
    fun `Return a PDF document on request 1` () {
        mockMvc.get("/documents/${DOC_ID_1}") {
            accept(MediaType.APPLICATION_PDF)
        }
                .andExpect {
                    status { isOk }
                }
    }

    @Test
    fun `Return a single JSON object for a document` () {
        mockMvc.get("/documents/${DOC_ID_1}") {
            accept(MediaType.parseMediaType("application/vnd.api+json"))
        }
                .andExpect {
                    status { isOk }
                }
    }

    @Test
    fun `Return all documents` () {
        mockMvc.get("/documents")
                .andExpect {
                    status { isOk }
                }
    }

    @Test
    fun `Not finding non-existing document` () {
        mockMvc.get("/documents/99999999-9999-9999-9999-999999999999")
                .andExpect {
                    status { isNotFound }
                }
    }
}
