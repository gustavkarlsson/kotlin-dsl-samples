package com.meetup.kotlinffm.dsl.http

val location = "FFM"

val demoRequest = request(HttpMethod.POST) {

    header("Accept", "application/json")
    header("Content-Type", "text/plain")

    body {
        val name = if (location == "FFM")
            "Frankfurt am Main"
        else
            "World"

        "Hello $name!"
    }
}
