package com.meetup.kotlinffm.dsl.json

class JsonArrayBuilder {
    private val _values = mutableListOf<Json<*>>()
    val values: List<Json<*>> = _values

    operator fun Nothing?.unaryPlus() {
        _values += Json.Null
    }

    operator fun Boolean.unaryPlus() {
        _values += Json.Boolean(this)
    }

    operator fun Number.unaryPlus() {
        _values += Json.Number(this)
    }

    operator fun String.unaryPlus() {
        _values += Json.String(this)
    }

    operator fun Json.Array.unaryPlus() {
        _values += this
    }

    operator fun Json.Object.unaryPlus() {
        _values += this
    }

    fun array(block: JsonArrayBuilder.() -> Unit): Json.Array = createArray(block)

    fun obj(block: JsonObjectBuilder.() -> Unit): Json.Object = json(block)
}

class JsonObjectBuilder {
    private val _properties = mutableMapOf<String, Json<*>>()
    val properties: Map<String, Json<*>> = _properties

    infix fun String.to(value: Nothing?) {
        _properties[this] = Json.Null
    }

    infix fun String.to(value: Boolean) {
        _properties[this] = Json.Boolean(value)
    }

    infix fun String.to(value: Number) {
        _properties[this] = Json.Number(value)
    }

    infix fun String.to(value: String) {
        _properties[this] = Json.String(value)
    }

    infix fun String.to(value: Json.Array) {
        _properties[this] = value
    }

    infix fun String.to(value: Json.Object) {
        _properties[this] = value
    }

    infix fun String.to(value: JsonObjectBuilder.() -> Unit) {
        to(obj(value))
    }

    @Deprecated("")
    infix fun String.to(value: Any): Nothing {
        throw IllegalArgumentException("Cannot assign key '$this' to value '$value' of type '${value.javaClass}'")
    }

    fun array(block: JsonArrayBuilder.() -> Unit): Json.Array = createArray(block)

    fun obj(block: JsonObjectBuilder.() -> Unit): Json.Object = json(block)
}

private fun createArray(block: JsonArrayBuilder.() -> Unit): Json.Array {
    val builder = JsonArrayBuilder()
    builder.block()
    return Json.Array(builder.values.toTypedArray())
}

fun json(block: JsonObjectBuilder.() -> Unit): Json.Object {
    val builder = JsonObjectBuilder()
    builder.block()
    return Json.Object(builder.properties)
}
