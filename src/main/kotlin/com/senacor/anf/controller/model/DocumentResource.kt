package com.senacor.anf.controller.model

import io.crnk.core.resource.annotations.JsonApiField
import io.crnk.core.resource.annotations.JsonApiId
import io.crnk.core.resource.annotations.JsonApiResource
import java.util.*

@JsonApiResource(
        type = "document",
        resourcePath = "documents",
        postable = false,
        deletable = false,
        patchable = false
)
data class DocumentResource(

    @JsonApiId
    var id: UUID? = null,

    @JsonApiField
    var description: String? = null,

    @JsonApiField
    var content: ByteArray? = null
)