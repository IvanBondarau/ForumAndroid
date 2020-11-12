package by.bsuir.ivan_bondarau.forum.service

import by.bsuir.ivan_bondarau.forum.model.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MessageService {

    @GET("message")
    fun findAll(): Call<List<Message>>

    @POST("message")
    fun insert(@Body message: Message): Call<Message>
}