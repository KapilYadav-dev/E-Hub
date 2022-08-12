package `in`.kay.ehub.data

import `in`.kay.ehub.BuildConfig
import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    /*
     @Provides our api service using hilt
     */
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }
}