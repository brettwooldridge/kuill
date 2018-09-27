package com.zaxxer.quill.delta

import com.zaxxer.quill.delta.json.DeltaJson
import org.junit.Test
import java.io.InputStreamReader

class Delta2HtmlTest {

   @Test
   fun test1() {
      val resourceAsStream = Delta2HtmlTest::class.java.getResourceAsStream("/test-input1.json")
      val json = InputStreamReader(resourceAsStream).readText()

      fun handler(delta: Delta, opAttrs: OpAttributes, line: Int): Boolean {
         delta.forEach { op: Op ->
            println("$op${if (opAttrs.isNotEmpty()) ", $opAttrs" else ""}")
         }

         return true
      }

      val delta = DeltaJson.fromJson(json)!!
      delta.eachLine(::handler)
   }
}
