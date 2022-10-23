package `in`.kay.ehub.domain.di

import `in`.kay.ehub.data.network.ApiService
import `in`.kay.ehub.data.repository.auth.AllBranchesRepoImpl
import `in`.kay.ehub.data.repository.auth.AllCollegesRepoImpl
import `in`.kay.ehub.data.repository.auth.UserAuthRepoImpl
import `in`.kay.ehub.domain.repository.auth.AllBranchesRepo
import `in`.kay.ehub.domain.repository.auth.AllCollegesRepo
import `in`.kay.ehub.domain.repository.auth.UserAuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {
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