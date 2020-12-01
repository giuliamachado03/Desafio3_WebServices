package com.example.desafio3.comic.repository

class ComicRepository {

    private val _client = IComicEndpoint.endpoint
    suspend fun getComics (offset :Int? =0) = _client.getComics(offset)
}