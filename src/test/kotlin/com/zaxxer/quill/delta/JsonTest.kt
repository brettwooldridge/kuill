package com.zaxxer.quill.delta

import com.zaxxer.quill.delta.json.DeltaJson
import com.zaxxer.quill.delta.utils.attrOf
import org.junit.Assert
import org.junit.Test

class JsonTest {

    @Test
    fun objectAttr() {
        val a = Delta().insert("A", attrOf("font" to attrOf("family" to "Helvetica", "size" to "15px")))
        val expected = DeltaJson.fromJson(DeltaJson.toJson(a))

        Assert.assertEquals(expected, a)
    }
}
