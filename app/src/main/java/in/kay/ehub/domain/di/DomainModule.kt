package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.model.UserSignUpRequestDTO
import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.repository.AllBranchesRepoImpl
import `in`.kay.ehub.data.repository.AllCollegesRepoImpl
import `in`.kay.ehub.data.repository.UserAuthRepoImpl
import `in`.kay.ehub.domain.repository.AllBranchesRepo
import `in`.kay.ehub.domain.repository.AllCollegesRepo
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

    /*
     @Provides our AllBranchesRepo using hilt
     */
    @Provides
    fun provideAllBranchesRepo(apiService: ApiService): AllBranchesRepo {
        return AllBranchesRepoImpl(apiService)
    }

    /*
    @Provides our AllCollegesRepo using hilt
    */
    @Provides
    fun provideAllCollegesRepo(apiService: ApiService): AllCollegesRepo {
        return AllCollegesRepoImpl(apiService)
    }
}