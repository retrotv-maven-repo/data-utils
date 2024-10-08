package dev.retrotv.data.utils

import org.json.JSONObject
import org.json.XML

object XMLUtils {

    @JvmStatic
    fun xmlToJson(xml: String): JSONObject {
        return XML.toJSONObject(xml)
    }
}