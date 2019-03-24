package com.meetup.kotlinffm.dsl.json

val demoJson = json {
    "should_bake" to true

    "eggs" to 4

    "liters_of_milk" to 2.5

    "nothing" to null

    "forest" to {
        "birch" to {
            "leafA" to "green"
            "leafB" to "greenish"
        }

        "spruce" to array {
            +"needle"
            +"needle"
            +array {
                +"needle"
            }
        }
    }
}
