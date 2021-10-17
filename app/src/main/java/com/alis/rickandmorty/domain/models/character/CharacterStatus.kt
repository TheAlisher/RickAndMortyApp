package com.alis.rickandmorty.domain.models.character

enum class CharacterStatus(val status: String) {
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown")
}