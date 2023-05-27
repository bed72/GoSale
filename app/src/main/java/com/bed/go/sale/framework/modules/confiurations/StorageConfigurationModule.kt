package com.bed.go.sale.framework.modules.confiurations

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StorageConfigurationModule {

//    @Provides
//    @Singleton
//    fun provideAppDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
//        preferencesDataStore(name = DATA_STORE).getValue(context, String::javaClass)
}
