package com.alis.rickandmorty.common.base

interface IBaseDiffModel {
    val id: Int
    override fun equals(other: Any?): Boolean
}