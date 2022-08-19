package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.model.UserSignUpRequestDTO
import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.repository.UserAuthRepoImpl
import `in`.kay.ehub.domain.repository.UserAuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    /*
     @Provides our UserAuthRepo using hilt
     */
    @Provides
    fun provideUserAuthRepo(apiService: ApiService): UserAuthRepo {
        return UserAuthRepoImpl(apiService)
    }
}