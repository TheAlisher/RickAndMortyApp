package com.alis.rickandmorty.data.db.room

import androidx.room.TypeConverter
import com.alis.rickandmorty.data.network.dtos.character.OriginDto
import com.alis.rickandmorty.data.network.dtos.character.SimpleLocationDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private inline fun <reified T> typeToken() = object : TypeToken<T>() {}.type

    private inline fun <reified T> fromJson(value: String?): T {
        return Gson().fromJson(value, typeToken<T>())
    }

    private inline fun <reified T> toJson(generic: T): String? {
        return Gson().toJson(generic, typeToken<T>())
    }

    @TypeConverter
    fun fromOrigin(value: String?) = fromJson<OriginDto>(value)

    @TypeConverter()
    fun originToJson(origin: OriginDto?) = toJson(origin)

    @TypeConverter
    fun fromSimpleLocation(value: String?) = fromJson<SimpleLocationDto>(value)

    @TypeConverter
    fun simpleLocationToJson(simpleLocation: SimpleLocationDto) = toJson(simpleLocation)

    @TypeConverter
    fun fromEpisodes(value: String?) = fromJson<MutableList<String>?>(value)

    @TypeConverter
    fun episodeToJson(episodes: MutableList<String>) = toJson(episodes)
}