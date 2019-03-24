package com.meetup.kotlinffm.dsl.familytree

data class Person(val name: String)

sealed class Event(val year: Int) {

    class ChildBorn(
            year: Int,
            val child: Person,
            val parentA: Person? = null,
            val parentB: Person? = null
    ) : Event(year)

    class ChildAdopted(
            year: Int,
            val child: Person,
            val parentA: Person? = null,
            val parentB: Person? = null
    ) : Event(year)

    class PersonDied(
            year: Int,
            val person: Person
    ) : Event(year)
}
