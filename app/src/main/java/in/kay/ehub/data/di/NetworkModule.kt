package `in`.kay.ehub.data.di

import `in`.kay.ehub.BuildConfig
import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.network.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    /*
     @Provides our api service using hilt
     */

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsApiService(): NewsApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.NEWS_API_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(NewsApiService::class.java)
    }
}