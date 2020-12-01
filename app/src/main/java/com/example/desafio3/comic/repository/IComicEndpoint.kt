package com.example.desafio3.comic.repository

import com.example.desafio3.comic.model.ComicModel
import com.example.desafio3.data.api.api.NetworkUtils
import com.example.desafio3.data.api.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IComicEndpoint {
    @GET("/v1/public/comics")
    suspend fun getComics (@Query("offset")offset:Int?=0):ResponseModel<ComicModel>

    companion object{
        val endpoint : IComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IComicEndpoint::class.java)
        }
    }

}
