package com.pets.di

import com.pets.repository.DogRepository
import com.pets.repository.DogRepositoryInteface
import com.pets.service.DogService
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
    fun injectCreateAccountRepositoty(
        service: DogService
    ) =
        DogRepository(
            service,
        ) as DogRepositoryInteface
}