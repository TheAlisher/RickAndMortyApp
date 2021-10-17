package com.alis.rickandmorty.domain.models.character

enum class CharacterGender(val gender: String) {
    FEMALE("female"),
    MALE("male"),
    GENDERLESS("genderless"),
    UNKNOWN("unknown")
}