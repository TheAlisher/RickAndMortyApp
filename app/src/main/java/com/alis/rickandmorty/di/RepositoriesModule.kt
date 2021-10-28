package com.alis.rickandmorty.di

import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.network.apiservices.LocationApiService
import com.alis.rickandmorty.data.repositories.CharacterRepositoryImpl
import com.alis.rickandmorty.data.repositories.EpisodeRepositoryImpl
import com.alis.rickandmorty.data.repositories.LocationRepositoryImpl
import com.alis.rickandmorty.domain.repositories.CharacterRepository
import com.alis.rickandmorty.domain.repositories.EpisodeRepository
import com.alis.rickandmorty.domain.repositories.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideCharacterRepository(service: CharacterApiService): CharacterRepository {
        return CharacterRepositoryImpl(service)
    }

    @Provides
    fun provideLocationRepository(service: LocationApiService): LocationRepository {
        return LocationRepositoryImpl(service)
    }

    @Provides
    fun provideEpisodeRepository(service: EpisodeApiService): EpisodeRepository {
        return EpisodeRepositoryImpl(service)
    }
}