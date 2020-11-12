package by.bsuir.ivan_bondarau.forum.service

import by.bsuir.ivan_bondarau.forum.model.Message
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MessageService {

    @GET("message")
    fun findAll(): Call<List<Message>>

    @POST("message")
    fun insert(@Body message: Message): Call<Message>

    @PUT("message/{id}")
    fun update(@Path("id") messageId: Int, @Body message: Message): Call<Message>

    @DELETE("message/{id}")
    fun delete(@Path("id") id: Int): Call<ResponseBody>
}