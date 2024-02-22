package com.pets.di

import com.pets.utils.PreferencesService
import com.pets.utils.PreferencesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class, ViewModelComponent::class, SingletonComponent::class)
abstract class PreferencesModule {
    @Binds
    abstract fun bindPreferencesService(impl: PreferencesServiceImpl): PreferencesService
}