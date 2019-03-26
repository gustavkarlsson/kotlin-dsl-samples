package com.meetup.kotlinffm.dsl.http

data class RequestBuilder(var method: HttpMethod = HttpMethod.GET) {
    val headers = mutableMapOf<String, String>()
    var body: String? = null

    fun header(name: String, value: String) {
        headers += (name to value)
    }

    fun body(value: () -> String) {
        body = value()
    }
}

fun request(
        method: HttpMethod = HttpMethod.GET,
        block: RequestBuilder.() -> Unit
): HttpRequest =
        RequestBuilder(method)
                .apply(block)
                .let {
                    HttpRequest(it.method, it.headers, it.body)
                }
