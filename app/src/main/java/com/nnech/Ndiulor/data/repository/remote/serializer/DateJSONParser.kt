package com.nnech.Ndiulor.data.repository.remote.serializer

import android.util.Log
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateJSONParser : JsonDeserializer<Date?>, JsonSerializer<Date?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        try {

            //2018-02-01T00:00:00+01:00
            val jsonString = json.toString().replace("T", " ").replace("Z", "").replace("\"", "")

            // 2018-02-01 00:00:00+01:00
            Log.i(DateJSONParser::class.java.simpleName, "json: $jsonString")
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
            val date: Date = format.parse(jsonString)
            Log.i(DateJSONParser::class.java.simpleName, "object: $date")

            // 2018-02-01 00:00:00
            return date
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override fun serialize(
        date: Date?,
        type: Type?,
        jsonSerializationContext: JsonSerializationContext?
    ): JsonElement {
        var dateStr: String? = null
        try {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
            dateStr = format.format(date)
        } catch (e: Exception) {
            Log.i(DateJSONParser::class.java.simpleName, e.toString())
        }
        return JsonPrimitive(dateStr)
    }
}