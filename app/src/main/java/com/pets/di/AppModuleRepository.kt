package com.pets.di

import com.pets.data.repository.DogRepository
import com.pets.data.repository.DogRepositoryInterface
import com.pets.data.api.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleRepository {
    @Singleton
    @Provides
    fun injectDogRepository(service: DogService) = DogRepository(service) as DogRepositoryInterface
}