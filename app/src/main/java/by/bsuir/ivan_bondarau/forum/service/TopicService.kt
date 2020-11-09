package by.bsuir.ivan_bondarau.forum.service

import by.bsuir.ivan_bondarau.forum.dto.TopicDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TopicService {

    @GET("topic")
    fun findAll(): Call<List<TopicDto>>

    @POST("topic")
    fun insert(@Body topic: TopicDto): Call<TopicDto>
}