package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.network.NewsApiService
import `in`.kay.ehub.data.repository.home.*
import `in`.kay.ehub.domain.repository.home.*
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

    @Provides
    fun provideEventsRepo(apiService: ApiService) : EventsRepo {
        return EventsRepoImpl(apiService)
    }

    @Provides
    fun provideCampusesRepo(apiService: ApiService) : CampusRepo {
        return CampusRepoImpl(apiService)
    }

    @Provides
    fun provideHandbookRepo(apiService: ApiService) : HandbooksRepo {
        return HandbooksRepoImpl(apiService)
    }
}