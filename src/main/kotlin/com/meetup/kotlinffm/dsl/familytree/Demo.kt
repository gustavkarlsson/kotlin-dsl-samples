package com.meetup.kotlinffm.dsl.familytree

val demoFamilyTree = familyTree {
    val adam = "Adam" wasBornYear 1946

    val eve = "Eve" wasBornYear 1948

    val steve = adam and eve had "Steve" year 1971

    val bianca = "Bianca" wasBornYear 2007

    steve adopted bianca year 2008

    adam diedYear 2015
}
