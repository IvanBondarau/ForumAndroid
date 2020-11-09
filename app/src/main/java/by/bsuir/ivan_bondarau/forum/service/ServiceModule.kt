package by.bsuir.ivan_bondarau.forum.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class ServiceModule {

    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://20.61.215.54:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun topicService(retrofit: Retrofit): TopicService {
        return retrofit.create(TopicService::class.java)
    }
}