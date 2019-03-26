package com.meetup.kotlinffm.dsl.http

data class HttpRequest(
        val method: HttpMethod,
        val headers: Map<String, String>,
        val body: String?
)

enum class HttpMethod {
    GET, POST, PUT, DELETE, PATCH
}
