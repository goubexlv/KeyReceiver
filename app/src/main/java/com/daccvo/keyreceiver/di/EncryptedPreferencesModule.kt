package com.daccvo.keyreceiver.di

import com.daccvo.keyreceiver.data.EncryptedPreferencesImpl
import com.daccvo.keyreceiver.domain.EncryptedPreferences

import android.content.Context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptedPreferencesModule {
    @Provides
    @Singleton
    fun provideEncryptedPreferences(
        @ApplicationContext context: Context
    ): EncryptedPreferences {
        return EncryptedPreferencesImpl(context = context)
    }
}