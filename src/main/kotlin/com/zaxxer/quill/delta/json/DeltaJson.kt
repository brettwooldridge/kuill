package com.zaxxer.quill.delta.json

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.zaxxer.quill.delta.Delta
import java.io.StringWriter

object DeltaJson {
    @JvmStatic
    val OBJECT_MAPPER = ObjectMapper()

    @JvmStatic
    val JSON_FACTORY = JsonFactory().setCodec(OBJECT_MAPPER)

    init {
        OBJECT_MAPPER.registerModule(KotlinModule())
    }

    fun fromJson(text : String?): Delta? = text?.let { JSON_FACTORY.createParser(text).readValueAs(Delta::class.java) }

    fun toJson(delta: Delta?): String? {
        val writer = StringWriter()
        JSON_FACTORY.createGenerator(writer).writeObject(delta)
        return writer.toString()
    }
}
