package com.rama.expensetrackerapp.di

import com.rama.expensetrackerapp.data.local.dao.ExpenseDao
import com.rama.expensetrackerapp.data.repository.impl.ExpenseRepositoryImpl
import com.rama.expensetrackerapp.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideExpenseRepository(
        dao: ExpenseDao
    ): ExpenseRepository {
        return ExpenseRepositoryImpl(dao)

    }
}