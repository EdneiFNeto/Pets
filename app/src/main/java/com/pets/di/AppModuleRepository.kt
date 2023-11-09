package com.pets.di

import com.pets.repository.CatRepository
import com.pets.repository.CatRepositoryInterface
import com.pets.repository.DogRepository
import com.pets.repository.DogRepositoryInterface
import com.pets.service.CatService
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
    fun injectDogRepository(
        service: DogService
    ) =
        DogRepository(
            service,
        ) as DogRepositoryInterface

    @Singleton
    @Provides
    fun injectCreateAccountRepositoty(
        service: CatService
    ) =
        CatRepository(
            service,
        ) as CatRepositoryInterface
}