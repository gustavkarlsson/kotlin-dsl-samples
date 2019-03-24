package com.meetup.kotlinffm.dsl.familytree

class TwoPersonsBuilder(val personA: Person, val personB: Person)

class ChildBornBuilder(val child: Person, val parentA: Person?, val parentB: Person?)

class ChildAdoptedBuilder(val child: Person, val parentA: Person?, val parentB: Person?)

//FIXME https://github.com/zsmb13/VillageDSL#a-slightly-over-the-top-dsl
class FamilyTreeBuilder {
    private val _events = mutableListOf<Event>()
    val events: Collection<Event> = _events

    infix fun String.wasBornYear(year: Int): Person {
        val person = Person(this)
        _events += Event.ChildBorn(year, person)
        return person
    }

    infix fun Person.diedYear(year: Int) {
        _events += Event.PersonDied(year, this)
    }

    infix fun ChildBornBuilder.year(year: Int): Person {
        _events += Event.ChildBorn(year, child, parentA, parentB)
        return child
    }

    infix fun ChildAdoptedBuilder.year(year: Int) {
        _events += Event.ChildAdopted(year, child, parentA, parentB)
    }

    infix fun Person.had(child: Person): ChildBornBuilder {
        return ChildBornBuilder(child, this, null)
    }

    infix fun Person.adopted(child: Person): ChildAdoptedBuilder {
        return ChildAdoptedBuilder(child, this, null)
    }

    infix fun Person.and(otherPerson: Person): TwoPersonsBuilder {
        return TwoPersonsBuilder(this, otherPerson)
    }

    infix fun TwoPersonsBuilder.had(childName: String): ChildBornBuilder {
        return ChildBornBuilder(Person(childName), personA, personB)
    }

    infix fun TwoPersonsBuilder.adopted(child: Person): ChildAdoptedBuilder {
        return ChildAdoptedBuilder(child, personA, personB)
    }
}

fun familyTree(block: FamilyTreeBuilder.() -> Unit): Collection<Event> {
    val builder = FamilyTreeBuilder()
    builder.block()
    return builder.events
}
