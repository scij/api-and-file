package com.senacor.anf.ext

import io.crnk.core.module.InitializingModule
import io.crnk.core.module.Module
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class BinaryResponseModule : InitializingModule {

    override fun getModuleName(): String {
        return "binary-format"
    }

    override fun setupModule(context: Module.ModuleContext) {
        log.debug("setup module context {}", context)
        val requestProcessor = BinaryRequestProcessor(context)
        context.addHttpRequestProcessor(requestProcessor)
    }

    override fun init() {
        log.debug("init module")
    }

}