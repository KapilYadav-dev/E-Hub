package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.network.NewsApiService
import `in`.kay.ehub.data.repository.home.NewsRepoImpl
import `in`.kay.ehub.domain.repository.home.NewsRepo
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
}