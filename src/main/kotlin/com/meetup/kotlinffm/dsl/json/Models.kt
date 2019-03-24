package com.meetup.kotlinffm.dsl.json

typealias KotlinString = kotlin.String
typealias KotlinNumber = kotlin.Number
typealias KotlinBoolean = kotlin.Boolean
typealias KotlinArray<T> = kotlin.Array<T>

sealed class Json<T : Any>(val value: T?) {
    object Null : Json<Nothing>(null)
    class Boolean(value: KotlinBoolean) : Json<KotlinBoolean>(value)
    class Number(value: KotlinNumber) : Json<KotlinNumber>(value)
    class String(value: KotlinString) : Json<KotlinString>(value)
    class Array(value: KotlinArray<Json<*>>) : Json<KotlinArray<Json<*>>>(value)
    class Object(value: Map<KotlinString, Json<*>>) : Json<Map<KotlinString, Json<*>>>(value)
}
