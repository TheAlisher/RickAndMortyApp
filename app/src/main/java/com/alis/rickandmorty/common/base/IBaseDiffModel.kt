package com.alis.rickandmorty.common.base

interface IBaseDiffModel<N : Number> {
    val id: N
    override fun equals(other: Any?): Boolean
}