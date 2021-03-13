package com.alis.rickandmorty.data.db.room

import androidx.room.TypeConverter
import com.alis.rickandmorty.models.character.Origin
import com.alis.rickandmorty.models.character.SimpleLocation
import com.alis.rickandmorty.models.episode.Episode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {

    private inline fun <reified T> typeToken() = object : TypeToken<T>() {}.type

    private inline fun <reified T> fromJson(value: String?): T {
        return Gson().fromJson(value, typeToken<T>())
    }

    private inline fun <reified T> toJson(generic: T): String? {
        return Gson().toJson(generic, typeToken<T>())
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromOrigin(value: String?) = fromJson<Origin>(value)

    @TypeConverter()
    fun originToJson(origin: Origin?) = toJson(origin)

    @TypeConverter
    fun fromSimpleLocation(value: String?) = fromJson<SimpleLocation>(value)

    @TypeConverter
    fun simpleLocationToJson(simpleLocation: SimpleLocation) = toJson(simpleLocation)

    @TypeConverter
    fun fromEpisodes(value: String?) = fromJson<MutableList<String>?>(value)

    @TypeConverter
    fun episodeToJson(episodes: MutableList<Episode>) = toJson(episodes)
}