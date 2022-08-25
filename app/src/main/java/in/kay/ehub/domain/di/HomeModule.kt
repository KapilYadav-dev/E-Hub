package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.network.NewsApiService
import `in`.kay.ehub.data.repository.home.NewsRepoImpl
import `in`.kay.ehub.data.repository.home.YoutubeRepoImpl
import `in`.kay.ehub.domain.repository.home.NewsRepo
import `in`.kay.ehub.domain.repository.home.YoutubeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {
    /*
     @Provides our newsRepo using hilt
     */
    @Provides
    fun provideNewsRepo(newsApiService: NewsApiService): NewsRepo {
        return NewsRepoImpl(newsApiService)
    }

    @Provides
    fun provideYoutubeRepo(apiService: ApiService): YoutubeRepo {
        return YoutubeRepoImpl(apiService)
    }
}