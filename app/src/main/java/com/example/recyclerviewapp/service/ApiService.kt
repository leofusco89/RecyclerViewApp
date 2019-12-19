package com.example.recyclerviewapp.service

import com.example.recyclerviewapp.models.Album
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>
}