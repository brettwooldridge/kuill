package com.zaxxer.quill.delta

import com.zaxxer.quill.delta.json.DeltaJson
import org.junit.Test
import java.io.InputStreamReader

class Delta2HtmlTest {

   @Test
   fun test1() {
      val resourceAsStream = Delta2HtmlTest::class.java.getResourceAsStream("/test-input1.json")
      val json = InputStreamReader(resourceAsStream).readText()

      val delta = DeltaJson.fromJson(json)!!
      delta.eachLine(::handler)
   }

   private fun handler(delta: Delta, opAttrs: OpAttributes, line: Int): Boolean {
      if (delta.length() == 0) {
         println("$line: <p />")
         return true
      }

      val output = delta
         .map { op: Op ->
            val attrs: Map<String, String> = (op.attributes?.plus(opAttrs) ?: opAttrs)
                  .map { it.key to it.value.toString() }
                  .toMap()

            val output = when (op.insert) {
               is String -> {
                  val openTags = attrs
                        .map { (k, v) ->
                           when (k) {
                              "header" -> "<h$v>"
                              "bold"   -> "<b>"
                              "italic" -> "<i>"
                              "list"   -> "<li>"
                              else     -> "<unknown>"
                           }
                        }
                        .joinToString(separator = "")

                  val closeTags = attrs
                        .map { (k, v) ->
                           when (k) {
                              "header" -> "</h$v>"
                              "bold"   -> "</b>"
                              "italic" -> "</i>"
                              "list"   -> "</li>"
                              else     -> "</unknown>"
                           }
                        }
                        .joinToString(separator = "")

                  "$openTags${op.insert}$closeTags"
               }

               is Map<*, *> -> (op.insert as Map<*, *>)
                  .map { (k, v) ->
                     when (k) {
                        "image" -> """${'\n'}<img src="$v"/>"""
                        else    -> "<unknown />"
                     }
                  }.joinToString(separator = "")

               else -> "unknown"
            }

            output
         }
         .joinToString(separator = "")

      println("$line:$output")

      return true
   }
}
