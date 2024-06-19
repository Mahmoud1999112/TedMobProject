package com.example.tedmobproject.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.example.tedmobproject.useCases.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }
    fun provideSavedStateViewModelFactory(
        application: Application,
        owner: SavedStateRegistryOwner
    ): SavedStateViewModelFactory {
        return SavedStateViewModelFactory(application, owner)
    }
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com")
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//            .build()
//    }
//@Provides
//@Singleton
//fun provideOkHttpClient(): OkHttpClient {
//    return OkHttpClient.Builder().build()
//}


    @Provides
    @Singleton
    fun provideLogoutUseCase(@ApplicationContext context: Context): LogoutUseCase {
        return LogoutUseCase(context)
    }
}
