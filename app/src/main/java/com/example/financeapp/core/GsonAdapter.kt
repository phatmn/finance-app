package com.example.financeapp.core

import com.google.gson.*
import java.lang.reflect.Type

internal class GsonAdapter<T : Any> : JsonSerializer<T>, JsonDeserializer<T> {
    override fun serialize(
        obj: T,
        interfaceType: Type?,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonObject().also {
            it.addProperty("type", obj.javaClass.name)
            it.add("data", context.serialize(obj))
        }
    }

    override fun deserialize(
        elem: JsonElement,
        interfaceType: Type?,
        context: JsonDeserializationContext
    ): T? {
        var data: JsonElement? = null
        var actualType: Type? = null

        if (interfaceType == Currency::class.java && elem !is JsonPrimitive) {
            val wrapper = elem as JsonObject
            val typeName = get(wrapper, "type")
            data = get(wrapper, "data")
            actualType = typeForName(typeName)
        }

        return if (data != null) { // our serialization with type
            context.deserialize(data, actualType)
        } else {                   // instatiating Currency class from string
            Currency::class
                .sealedSubclasses
                .firstOrNull {
                    it.simpleName == elem.asString
                }
                ?.objectInstance as T?
        }
    }

    private fun typeForName(typeElem: JsonElement): Type {
        return try {
            Class.forName(typeElem.asString)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }

    private operator fun get(wrapper: JsonObject, memberName: String): JsonElement {
        return wrapper[memberName]
            ?: throw JsonParseException("no '$memberName' member found in what was expected to be an interface wrapper")
    }
}