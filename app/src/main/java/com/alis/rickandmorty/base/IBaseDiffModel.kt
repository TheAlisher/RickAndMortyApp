package com.alis.rickandmorty.base

interface IBaseDiffModel<N : Number> {
    val id: N
    override fun equals(other: Any?): Boolean
}