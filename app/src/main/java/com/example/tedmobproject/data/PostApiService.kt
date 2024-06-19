package com.example.tedmobproject.data

import com.example.tedmobproject.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}
