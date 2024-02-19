package com.example.todo.di

import android.content.Context
import com.example.todo.data.local.ThemeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    fun provideTheme(@ApplicationContext context: Context): ThemeDataSource {
        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        return ThemeDataSource(sharedPreferences)
    }
}
