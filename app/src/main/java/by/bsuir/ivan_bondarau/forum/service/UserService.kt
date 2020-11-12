package by.bsuir.ivan_bondarau.forum.service

import by.bsuir.ivan_bondarau.forum.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("user")
    fun findAll(): Call<List<User>>
    @POST("user")
    fun insert(@Body user: User): Call<User>
}