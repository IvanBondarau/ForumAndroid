package by.bsuir.ivan_bondarau.forum.service

import com.google.gson.GsonBuilder
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
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl("http://20.61.215.54:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun topicService(retrofit: Retrofit): TopicService {
        return retrofit.create(TopicService::class.java)
    }

    @Provides
    fun userService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun messageService(retrofit: Retrofit): MessageService {
        return retrofit.create(MessageService::class.java)
    }
}